package com.example.kevin.cs3270final;


import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class WorkoutEdit extends Fragment {

View rootView;

    public WorkoutEdit() {
    }

    EditText edtNotes;
    ListView lsvExerciseList;
    TextView txvWorkoutNameDisplay;
    Button btnAddExercise;
    Button btnSaveWorkout;
    String workout_id;
    DatabaseHelper dbHelper;
    Chronometer chrnWorkoutTimer;
    SimpleCursorAdapter adapter;
    Boolean workout_menu_status;
    String workout_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_workout_edit, container, false);

        MainActivity ma = (MainActivity) getActivity();
        ma.setTitle("Current Workout");
        workout_id = ma.getWorkoutID();
        workout_menu_status = ma.getWorkoutMenuStatus();
        workout_name = ma.getWorkoutName();

        lsvExerciseList = (ListView) rootView.findViewById(R.id.lsvExerciseList);
        edtNotes = (EditText) rootView.findViewById(R.id.edtNotes);
        btnAddExercise = (Button) rootView.findViewById(R.id.btnAddExercise);
        btnSaveWorkout = (Button) rootView.findViewById(R.id.btnSaveWorkout);
        chrnWorkoutTimer = (Chronometer) rootView.findViewById(R.id.chrnWorkoutTimer);
        txvWorkoutNameDisplay = (TextView) rootView.findViewById(R.id.txvWorkoutNameDisplay);
        txvWorkoutNameDisplay.setText(workout_name);

        if (workout_menu_status){
            setHasOptionsMenu(true);
            btnSaveWorkout.setEnabled(false);
            btnAddExercise.setEnabled(false);
        }
        else{
            chrnWorkoutTimer.start();
        }

        dbHelper = new DatabaseHelper(getActivity(), "WorkoutTracker", null, 1);
        Cursor cursor = dbHelper.getAllUserWorkoutExercises(workout_id);

        String[] columns = new String[] {"name"};
        int[] views = new int[] {android.R.id.text1};
        adapter =
                new SimpleCursorAdapter(getActivity(),android.R.layout.simple_list_item_1,
                        cursor,columns,views,
                        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lsvExerciseList.setAdapter(adapter);

        btnAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity ma = (MainActivity) getActivity();
                ma.loadSelectExerciseFragment();
            }
        });

        btnSaveWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chrnWorkoutTimer.stop();

                FavoritesConfirmationDialog dialog = new FavoritesConfirmationDialog();
                dialog.setCancelable(false);
                dialog.setWorkoutFields(edtNotes.getText().toString(), chrnWorkoutTimer.getText().toString());
                dialog.show(getFragmentManager(), "Favorite Exercise");

            }
        });


        lsvExerciseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MainActivity ma = (MainActivity) getActivity();
                ma.setWorkoutExerciseID(String.valueOf(id));
                ma.loadViewWorkoutExerciseFragment();
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.viewmenu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnuEdit:
                Toast toast = Toast.makeText(getActivity(), "You can now edit your workout.", Toast.LENGTH_SHORT);
                toast.show();
                btnSaveWorkout.setEnabled(true);
                btnAddExercise.setEnabled(true);
                return true;
            case R.id.mnuDelete:
                DeleteConfirmationDialog dialog = new DeleteConfirmationDialog();
                dialog.setCancelable(false);
                dialog.show(getFragmentManager(), "Delete Workout");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
