package com.zouag.fsdocs.ui.fragments.teacher;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.zouag.fsdocs.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TeacherFrag1 extends Fragment {

    private FirstFragListener mListener;

    @Bind(R.id.firstnameText)
    EditText firstnameText;
    @Bind(R.id.lastnameText)
    EditText lastnameText;
    @Bind(R.id.specialtyText)
    EditText specialtyText;
    @Bind(R.id.nextButton)
    Button nextButton;

    public TeacherFrag1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teacher_frag1, container, false);
        ButterKnife.bind(this, view);

        nextButton.setOnClickListener(v -> {
            String firstname = firstnameText.getText().toString();
            String lastname = lastnameText.getText().toString();
            String specialty = specialtyText.getText().toString();

            validateInput(firstname, lastname, specialty);
        });

        return view;
    }

    /**
     * @param firstname
     * @param lastname
     * @param specialty Validates input.
     *                  Checks whether the firstname & lastname have reached (at least) their minimum length.
     */
    private void validateInput(String firstname, String lastname, String specialty) {
        String dialogTitle = "Error";
        String dialogMessage;

        boolean firstnameStatus = firstname.length() > 1;
        boolean lastnameStatus = lastname.length() > 1;
        boolean specialtyStatus = lastname.length() > 1;

        if (firstnameStatus && lastnameStatus && specialtyStatus) {
            mListener.navigateToSecondFrag(firstname, lastname, specialty);
            return;
        } else if (!firstnameStatus) {
            dialogMessage = "Please enter your real first name.";
        } else if (!lastnameStatus) {
            dialogMessage = "Please enter your real last name.";
        } else {
            dialogMessage = "Please provide a description for your specialty.";
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
        void navigateToSecondFrag(String firstname, String lastname, String specialty);
    }
}
