package com.example.kevin.cs3270final;

import java.util.Calendar;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import static java.util.Calendar.DATE;


public class CreateExercise extends Fragment {

    View rootView;
    Button btnSaveExercise;
    EditText edtExerciseName;
    EditText edtExerciseType;
    EditText edtExerciseInstructions;

    long courseID;

    public CreateExercise() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_create_exercise, container, false);

        MainActivity ma = (MainActivity) getActivity();
        ma.setTitle("Create Workout");

        edtExerciseName = (EditText) rootView.findViewById(R.id.edtExerciseName);
        edtExerciseType = (EditText) rootView.findViewById(R.id.edtExerciseType);
        edtExerciseInstructions = (EditText) rootView.findViewById(R.id.edtExerciseInstructions);
        btnSaveExercise = (Button) rootView.findViewById(R.id.btnSaveExercise);


        btnSaveExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(getActivity(), "WorkoutTracker", null, 1);

                long rowID = dbHelper.addExercise(edtExerciseName.getText().toString(), edtExerciseType.getText().toString(), edtExerciseInstructions.getText().toString());

                MainActivity ma = (MainActivity) getActivity();
                ma.loadSelectExerciseFragment();
            }
        });

        return rootView;
    }

}
