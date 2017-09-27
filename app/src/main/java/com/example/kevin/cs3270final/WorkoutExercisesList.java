package com.example.kevin.cs3270final;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class WorkoutExercisesList extends ListFragment {

    View rootView;

    public WorkoutExercisesList() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = super.onCreateView(inflater, container, savedInstanceState);

        DatabaseHelper dbHelper = new DatabaseHelper(getActivity(), "WorkoutTracker", null, 1);

        MainActivity ma = (MainActivity) getActivity();
        ma.setTitle("Workout Exercises");
        String exercise_id = ma.getExerciseID();
        Cursor cursor = dbHelper.getAllWorkoutExercises(exercise_id);
        String[] columns = new String[] {"name"};
        int[] views = new int[] {android.R.id.text1};
        SimpleCursorAdapter adapter =
                new SimpleCursorAdapter(getActivity(),android.R.layout.simple_list_item_1,
                        cursor,columns,views,
                        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        setListAdapter(adapter);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        MainActivity ma = (MainActivity) getActivity();
        ma.setWorkoutExerciseID(String.valueOf(id));
        ma.loadViewWorkoutExerciseFragment();
    }
}
