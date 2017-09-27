package com.example.kevin.cs3270final;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ViewWorkoutExercise extends Fragment {


    View rootView;
    TextView txvReps;
    TextView txvSets;
    TextView txvDuration;
    TextView txvRest;
    TextView txvNotes;
    String workout_name;

    public ViewWorkoutExercise() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_view_workout_exercise, container, false);

        MainActivity ma = (MainActivity) getActivity();
        ma.setTitle("My Exercise Details");
        String id = ma.getWorkoutExerciseID();
        workout_name = ma.getWorkoutName();

        if (workout_name != null) {
            setHasOptionsMenu(true);
        }

        txvReps = (TextView) rootView.findViewById(R.id.txvReps);
        txvSets = (TextView) rootView.findViewById(R.id.txvSets);
        txvDuration = (TextView) rootView.findViewById(R.id.txvDuration);
        txvRest = (TextView) rootView.findViewById(R.id.txvRest);
        txvNotes = (TextView) rootView.findViewById(R.id.txvNotes);

        populateExerciseDetails(id);

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

        txvReps.setText(reps);
        txvSets.setText(sets);
        txvDuration.setText(duration);
        txvRest.setText(rest);
        txvNotes.setText(notes);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.viewmenu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuEdit:
                MainActivity ma = (MainActivity) getActivity();
                ma.loadEditWorkoutExerciseFragment();
                return true;
            case R.id.mnuDelete:
                DeleteWorkoutExerciseConfirmationDialog dialog = new DeleteWorkoutExerciseConfirmationDialog();
                dialog.setCancelable(false);
                dialog.show(getFragmentManager(), "Delete Workout Exercise");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
