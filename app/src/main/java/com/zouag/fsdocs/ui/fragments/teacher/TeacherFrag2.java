package com.zouag.fsdocs.ui.fragments.teacher;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.zouag.fsdocs.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TeacherFrag2 extends Fragment {

    private static final int PASSWORD_MIN_CHARACTERS = 3;

    @Bind(R.id.emailText)
    EditText emailText;
    @Bind(R.id.passwordText)
    EditText passwordText;
    @Bind(R.id.confirmText)
    EditText confirmText;
    @Bind(R.id.nextButton)
    Button nextButton;

    private SecondFragListener mListener;

    public TeacherFrag2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teacher_frag2, container, false);
        ButterKnife.bind(this, view);

        nextButton.setOnClickListener(v -> {
            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();
            String confirm = confirmText.getText().toString();

            validateInput(email, password, confirm);
        });

        return view;
    }

    /**
     * @param email
     * @param password
     * @param confirm  the password's confirmation
     *                 <p>
     *                 Validates input.
     */
    private void validateInput(String email, String password, String confirm) {
        String dialogTitle = "Error";
        String dialogMessage;

        boolean emailStatus = isValidEmail(email);
        boolean passwordMatchStatus = password.equals(confirm);
        boolean passwordLengthStatus = password.length() >= PASSWORD_MIN_CHARACTERS;

        if (emailStatus && passwordMatchStatus && passwordLengthStatus) {
            Log.i("SUCCESS", "SIGNUP SUCCESS");
            return;
        } else if (!emailStatus) {
            dialogMessage = "Please enter a valid email address.";
        } else if (!passwordMatchStatus) {
            dialogMessage = "Your passwords do not match.";
            passwordText.setError("Make sure your caps lock is OFF.");
        } else {
            dialogMessage = "Your password is too short.";
            passwordText.setError(String.format("Your password should be at least " +
                    "%d characters long.", PASSWORD_MIN_CHARACTERS));
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
        builder.setTitle(dialogTitle);
        builder.setMessage(dialogMessage);
        builder.setPositiveButton("GOT IT", null);
        builder.show();
    }

    /**
     * @param target the email to be verified.
     * @return true if valid, false otherwise.
     */
    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (SecondFragListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SecondFragListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface SecondFragListener {
        void confirmSignup(String email, String password);
    }
}
