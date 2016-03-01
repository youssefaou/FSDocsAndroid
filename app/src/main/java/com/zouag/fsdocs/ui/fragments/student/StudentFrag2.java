package com.zouag.fsdocs.ui.fragments.student;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentFrag2 extends Fragment {

    private SecondFragListener mListener;

    @Bind(R.id.cneText)
    EditText cneText;
    @Bind(R.id.birthdateText)
    EditText birthdateText;
    @Bind(R.id.nextButton)
    Button nextButton;

    public StudentFrag2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_frag2, container, false);
        ButterKnife.bind(this, view);

        nextButton.setOnClickListener(v -> {
            String cne = cneText.getText().toString();
            String birthdate = birthdateText.getText().toString();

            validateInput(cne, birthdate);
        });

        return view;
    }

    /**
     * @param cne
     * @param birthdate Validates input.
     */
    private void validateInput(String cne, String birthdate) {
        String dialogTitle = "Error";
        String dialogMessage;

        boolean cneStatus = cne.length() == 10;
        boolean birthdateStatus = birthdate.length() > 0;

        if (cneStatus && birthdateStatus) {
            mListener.navigateToThirdFrag(cne, birthdate);
            return;
        } else if (!cneStatus) {
            dialogMessage = "Please enter your 10-digits' CNE.";
        } else {
            dialogMessage = "Please enter your correct birth date.";
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
        void navigateToThirdFrag(String cne, String date);
    }
}
