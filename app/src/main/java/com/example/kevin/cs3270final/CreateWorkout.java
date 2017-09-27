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


public class CreateWorkout extends Fragment {

    View rootView;
    Button btnStartWorkout;
    EditText edtWorkoutName;
    long courseID;
    Boolean pressedEdit;
    Calendar workoutStartTime;
    Calendar workoutEndTime;

    public CreateWorkout() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_create_workout, container, false);

        MainActivity ma = (MainActivity) getActivity();
        ma.setTitle("Create Workout");

        edtWorkoutName = (EditText) rootView.findViewById(R.id.edtWorkoutName);
        btnStartWorkout = (Button) rootView.findViewById(R.id.btnStartWorkout);

        workoutStartTime = Calendar.getInstance();

        btnStartWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(getActivity(), "WorkoutTracker", null, 1);

                long rowID = dbHelper.addWorkout(edtWorkoutName.getText().toString(), "", "no",
                        (workoutStartTime.get(Calendar.MONTH) + "-" + workoutStartTime.get(Calendar.DAY_OF_MONTH) + "-" + workoutStartTime.get(Calendar.YEAR)).toString() ,"");

                Cursor cursor = dbHelper.getWorkoutRowID(rowID);
                cursor.moveToFirst();
                String workout_id = cursor.getString(cursor.getColumnIndex("_id"));
                String workout_name = cursor.getString(cursor.getColumnIndex("name"));

                MainActivity ma = (MainActivity) getActivity();
                ma.setWorkoutID(workout_id);
                ma.setWorkoutName(workout_name);
                ma.loadWorkoutEditFragment();
            }
        });

        return rootView;
    }

}
