package com.zouag.fsdocs.ui.fragments.student;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.zouag.fsdocs.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentFrag1 extends Fragment {

    private FirstFragListener mListener;

    @Bind(R.id.firstnameText)
    EditText firstnameText;
    @Bind(R.id.lastnameText)
    EditText lastnameText;
    @Bind(R.id.nextButton)
    Button nextButton;

    public StudentFrag1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_frag1, container, false);
        ButterKnife.bind(this, view);

        nextButton.setOnClickListener(v -> {
            String firstname = firstnameText.getText().toString();
            String lastname = lastnameText.getText().toString();

            validateInput(firstname, lastname);
        });

        return view;
    }

    /**
     * @param firstname
     * @param lastname
     *
     * Validates input.
     * Checks whether the firstname & lastname have reached (at least) their minimum length.
     */
    private void validateInput(String firstname, String lastname) {
        String dialogTitle = "Error";
        String dialogMessage;

        boolean firstnameStatus = firstname.length() > 1;
        boolean lastnameStatus = lastname.length() > 1;

        if (firstnameStatus && lastnameStatus) {
            mListener.navigateToSecondFrag(firstname, lastname);
            return;
        } else if (!firstnameStatus) {
            dialogMessage = "Please enter your real firstname.";
        } else {
            dialogMessage = "Please enter your real lastname.";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
        builder.setTitle(dialogTitle);
        builder.setMessage(dialogMessage);
        builder.setPositiveButton("GOT IT", null);
        builder.show();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (FirstFragListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FirstFragListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface FirstFragListener {
        void navigateToSecondFrag(String firstname, String lastname);
    }
}
