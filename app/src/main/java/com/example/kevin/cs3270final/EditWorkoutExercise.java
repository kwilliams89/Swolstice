package com.example.kevin.cs3270final;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class EditWorkoutExercise extends Fragment {


    View rootView;
    TextView edtReps;
    TextView edtSets;
    TextView edtDuration;
    TextView edtRest;
    TextView edtNotes;
    Button btnEditWorkoutExercise;
    String workout_id;
    String exercise_id;
    String workout_exercise_id;

    public EditWorkoutExercise() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_edit_workout_exercise, container, false);

        MainActivity ma = (MainActivity) getActivity();
        ma.setTitle("Edit Exercise Details");
        workout_exercise_id = ma.getWorkoutExerciseID();
        workout_id = ma.getWorkoutID();
        exercise_id = ma.getExerciseID();

        edtReps = (EditText) rootView.findViewById(R.id.edtReps);
        edtSets = (EditText) rootView.findViewById(R.id.edtSets);
        edtDuration = (EditText) rootView.findViewById(R.id.edtDuration);
        edtRest = (EditText) rootView.findViewById(R.id.edtRest);
        edtNotes = (EditText) rootView.findViewById(R.id.edtNotes);
        btnEditWorkoutExercise = (Button) rootView.findViewById(R.id.btnEditWorkoutExercise);

        populateExerciseDetails(workout_exercise_id);

        btnEditWorkoutExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(getActivity(), "WorkoutTracker", null, 1);

                long rowID = dbHelper.updateWorkoutExercise(workout_exercise_id, workout_id, exercise_id, edtNotes.getText().toString(), edtReps.getText().toString(),
                        edtSets.getText().toString(), edtDuration.getText().toString(), edtRest.getText().toString());
                Toast toast = Toast.makeText(getActivity(), "Exercise has been updated!", Toast.LENGTH_SHORT);
                toast.show();
                MainActivity ma = (MainActivity) getActivity();
                ma.loadWorkoutEditFragment();
            }
        });


        return rootView;
    }

    public void populateExerciseDetails(String id){

        DatabaseHelper dbHelper = new DatabaseHelper(getActivity(), "WorkoutTracker",null,1);
        Cursor cursor = dbHelper.getSingleWorkoutExercise(id);
        cursor.moveToFirst();

        String reps = cursor.getString(cursor.getColumnIndex("reps"));
        String sets = cursor.getString(cursor.getColumnIndex("sets"));
        String duration = cursor.getString(cursor.getColumnIndex("duration"));
        String rest = cursor.getString(cursor.getColumnIndex("rest"));
        String notes = cursor.getString(cursor.getColumnIndex("notes"));

        edtReps.setText(reps);
        edtSets.setText(sets);
        edtDuration.setText(duration);
        edtRest.setText(rest);
        edtNotes.setText(notes);
    }

}
