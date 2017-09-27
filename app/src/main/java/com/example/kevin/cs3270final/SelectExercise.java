package com.example.kevin.cs3270final;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class SelectExercise extends Fragment {

    View rootView;

    public SelectExercise() {
    }

    ListView lsvExerciseList;
    Button btnAddExercise;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_select_exercise, container, false);

        MainActivity ma = (MainActivity) getActivity();
        ma.setTitle("Select Exercise");

        lsvExerciseList = (ListView) rootView.findViewById(R.id.lsvExerciseList);
        btnAddExercise = (Button) rootView.findViewById(R.id.btnAddExercise);

        DatabaseHelper dbHelper = new DatabaseHelper(getActivity(), "WorkoutTracker", null, 1);
        Cursor cursor = dbHelper.getAllExercises();
        String[] columns = new String[] {"name"};
        int[] views = new int[] {android.R.id.text1};
        SimpleCursorAdapter adapter =
                new SimpleCursorAdapter(getActivity(),android.R.layout.simple_list_item_1,
                        cursor,columns,views,
                        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lsvExerciseList.setAdapter(adapter);

        btnAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity ma = (MainActivity) getActivity();
                ma.loadCreateExerciseFragment();
            }
        });


        lsvExerciseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MainActivity ma = (MainActivity) getActivity();
                ma.setExerciseID(String.valueOf(id));
                ma.loadCreateWorkoutExerciseFragment();
            }
        });
        return rootView;
    }
}
