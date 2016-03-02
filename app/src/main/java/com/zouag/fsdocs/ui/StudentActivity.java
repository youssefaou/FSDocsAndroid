package com.zouag.fsdocs.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.zouag.fsdocs.R;
import com.zouag.fsdocs.networking.NodeAPI;
import com.zouag.fsdocs.ui.fragments.student.StudentFrag1;
import com.zouag.fsdocs.ui.fragments.student.StudentFrag2;
import com.zouag.fsdocs.ui.fragments.student.StudentFrag3;
import com.zouag.fsdocs.networking.Method;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StudentActivity extends AppCompatActivity implements
        StudentFrag1.FirstFragListener,
        StudentFrag2.SecondFragListener,
        StudentFrag3.ThirdFragListener {

    private JSONObject mUser; // the details of the user currently registering
    private boolean isSignin = true;
    OkHttpClient client = new OkHttpClient();

    @Bind(R.id.viewSwitcherLayout)
    ViewSwitcher mViewSwitcher;
    @Bind(R.id.signinLabel)
    TextView mSigninTextview;
    @Bind(R.id.signupLabel)
    TextView mSignupTextview;
    @Bind(R.id.switchLabel)
    TextView switchLabel;
    @Bind(R.id.emailText)
    EditText emailText;
    @Bind(R.id.passwordText)
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        ButterKnife.bind(this); // Butterknife's setup

        mSigninTextview.setOnClickListener(v -> {
            if (!isSignin) {
                mViewSwitcher.showPrevious();
                isSignin = true;
                mSignupTextview.setBackgroundColor(getResources().getColor(R.color.inactiveTab));
                mSigninTextview.setBackgroundColor(getResources().getColor(R.color.activeTab));
            }
        });
        mSignupTextview.setOnClickListener(v -> {
            if (isSignin) {
                mViewSwitcher.showNext();
                isSignin = false;
                mSignupTextview.setBackgroundColor(getResources().getColor(R.color.activeTab));
                mSigninTextview.setBackgroundColor(getResources().getColor(R.color.inactiveTab));
            }
        });

        switchLabel.setOnClickListener(v ->
                startActivity(new Intent(StudentActivity.this, TeacherActivity.class)));

        displayFragment(0);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment instanceof StudentFrag2) {
            displayFragment(0);
        } else if (fragment instanceof StudentFrag3) {
            displayFragment(1);
        } else
            super.onBackPressed();
    }

    private void displayFragment(int position) {
        Fragment fragment = null;
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        switch (position) {
            case 0:
                fragment = new StudentFrag1();
                ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
                break;
            case 1:
                fragment = new StudentFrag2();
                ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
                break;
            case 2:
                fragment = new StudentFrag3();
                ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left);
                break;
        }

        ft.replace(R.id.fragment_container, fragment, "fragment" + position);
        ft.commit();
    }

    @Override
    public void navigateToSecondFrag(String firstname, String lastname) {
        mUser = new JSONObject();
        try {
            mUser.put("fname", firstname);
            mUser.put("lname", lastname);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        displayFragment(1);
    }

    @Override
    public void navigateToThirdFrag(String cne, String date, int level) {
        assert mUser != null;

        try {
            mUser.put("cne", cne);
            mUser.put("birthday", date);
            mUser.put("level", level);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        displayFragment(2);
    }

    @Override
    public void confirmSignup(String email, String password) {
        try {
            mUser.put("email", email);
            mUser.put("password", password);

            postSignup(NodeAPI.getStudentSignupURL(), mUser);

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param url
     * @param userInfo
     * @throws IOException Sends the sign up, HTTP request
     */
    void postSignup(String url, JSONObject userInfo) throws IOException {
        Request request = NodeAPI.createHTTPRequest(userInfo, url, Method.POST);

        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, IOException e) {
                        runOnUiThread(() ->
                                showSignupResultDialog("Unknown error",
                                        "An unknown error occured while trying to " +
                                                "reach out to the server. Please try again.",
                                        "OK",
                                        null));
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        final String res = response.body().string();
                        final JSONObject obj;

                        try {
                            Log.i("RESPONSE", res);
                            obj = new JSONObject(res);

                            String status = obj.getString("status");
                            String message = obj.getString("msg");
                            String actionBtnText;
                            DialogInterface.OnClickListener listener;

                            if (status.equals("Success")) {
                                // Sign up successful
                                actionBtnText = "LOG IN NOW";
                                listener = (dialog, which) -> {
                                    try {
                                        navigateToLoginTab();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                };
                            } else {
                                // Sign up failed
                                actionBtnText = "GOT IT";
                                listener = null;
                            }

                            // Show dialog
                            runOnUiThread(() ->
                                    showSignupResultDialog(status, message,
                                            actionBtnText, listener));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * @param title      of the dialog
     * @param message    of the dialog
     * @param actionText text of the dialog's action button
     * @param listener   on the action button
     *                   <p>
     *                   Shows a custom dialog
     */
    public void showSignupResultDialog(final String title, final String message, final String actionText,
                                       final DialogInterface.OnClickListener listener) {
        runOnUiThread(() -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(StudentActivity.this, R.style.AppCompatAlertDialogStyle);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setPositiveButton(actionText, listener);

            AlertDialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        });
    }

    /**
     * Switches to the Sign in tab of the ViewSwitcher.
     */
    private void navigateToLoginTab() throws JSONException {
        mViewSwitcher.showPrevious();
        isSignin = true;
        mSignupTextview.setBackgroundColor(getResources().getColor(R.color.inactiveTab));
        mSigninTextview.setBackgroundColor(getResources().getColor(R.color.activeTab));
        emailText.setText(mUser.getString("email"));
        passwordText.requestFocus();
    }
}
