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
import android.widget.Toast;

import static java.util.Calendar.DATE;


public class CreateWorkoutExercise extends Fragment {

    View rootView;
    Button btnAddToWorkout;
    EditText edtReps;
    EditText edtSets;
    EditText edtDuration;
    EditText edtRest;
    EditText edtNotes;
    String workout_id;
    String exercise_id;

    public CreateWorkoutExercise() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_create_workout_exercise, container, false);

        MainActivity ma = (MainActivity) getActivity();
        workout_id = ma.getWorkoutID();
        exercise_id = ma.getExerciseID();

        edtReps = (EditText) rootView.findViewById(R.id.edtReps);
        edtSets = (EditText) rootView.findViewById(R.id.edtSets);
        edtDuration = (EditText) rootView.findViewById(R.id.edtDuration);
        edtRest = (EditText) rootView.findViewById(R.id.edtRest);
        edtNotes = (EditText) rootView.findViewById(R.id.edtNotes);
        btnAddToWorkout = (Button) rootView.findViewById(R.id.btnAddToWorkout);


        btnAddToWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(getActivity(), "WorkoutTracker", null, 1);

                long rowID = dbHelper.addWorkoutExercise(workout_id, exercise_id, edtNotes.getText().toString(), edtReps.getText().toString(),
                        edtSets.getText().toString(), edtDuration.getText().toString(), edtRest.getText().toString());
                Toast toast = Toast.makeText(getActivity(), "Exercise added to your workout!", Toast.LENGTH_SHORT);
                toast.show();
                MainActivity ma = (MainActivity) getActivity();
                ma.loadWorkoutEditFragment();
            }
        });

        return rootView;
    }

}
