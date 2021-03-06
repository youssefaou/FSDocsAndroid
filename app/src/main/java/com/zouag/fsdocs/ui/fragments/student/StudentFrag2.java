package com.zouag.fsdocs.ui.fragments.student;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.zouag.fsdocs.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentFrag2 extends Fragment {

    private SecondFragListener mListener;

    @Bind(R.id.levelSpinner)
    Spinner levelSpinner;
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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_list_item_1,
                new String[]{"Level1", "Level2", "Level3",
                        "Level4", "Level5", "Level6"}
        );
        levelSpinner.setAdapter(adapter);

        nextButton.setOnClickListener(v -> {
            String cne = cneText.getText().toString();
            String birthdate = birthdateText.getText().toString();
            int level = levelSpinner.getSelectedItemPosition() + 1;

            validateInput(cne, birthdate, level);
        });

        return view;
    }

    /**
     * @param cne
     * @param birthdate
     * @param level
     */
    private void validateInput(String cne, String birthdate, int level) {
        String dialogTitle = "Error";
        String dialogMessage;

        boolean cneStatus = cne.length() == 10;
        boolean birthdateStatus = birthdate.length() > 0;
        boolean levelStatus = level != 0;

        if (cneStatus && birthdateStatus && levelStatus) {
            mListener.navigateToThirdFrag(cne, birthdate, level);
            return;
        } else if (!cneStatus) {
            dialogMessage = "Please enter your 10-digits' CNE.";
        } else if (!birthdateStatus) {
            dialogMessage = "Please enter your correct birth date.";
        } else {
            dialogMessage = "Please choose your level of studies.";
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
        void navigateToThirdFrag(String cne, String date, int level);
    }
}
