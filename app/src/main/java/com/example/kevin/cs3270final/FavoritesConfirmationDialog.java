package com.example.kevin.cs3270final;


import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;


public class FavoritesConfirmationDialog extends DialogFragment {


    String workout_id;
    String name;
    String notes;
    String date;
    String favorite;
    String length;

    public FavoritesConfirmationDialog() {
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Dialog dialog = builder.setMessage("Would you like to save this workout as a favorite?")
                .setCancelable(false)
                .setTitle("Favorite Workout?")
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity ma = (MainActivity) getActivity();
                                ma.setFavoriteStatus("yes");
                                favorite = ma.getFavoriteStatus();
                                workout_id = ma.getWorkoutID();

                                DatabaseHelper dbHelper = new DatabaseHelper(getActivity(), "WorkoutTracker", null, 1);
                                Cursor cursor = dbHelper.getSingleWorkout(workout_id);
                                cursor.moveToFirst();

                                name = cursor.getString(cursor.getColumnIndex("name"));
                                date = cursor.getString(cursor.getColumnIndex("date"));
                                dbHelper.updateWorkout(workout_id, name, notes, favorite, date, length);

                                Toast toast = Toast.makeText(getActivity(), "Great job! Workout completed!!!", Toast.LENGTH_SHORT);
                                toast.show();

                                ma.loadStartScreenFragment();
                            }
                        }
                )
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity ma = (MainActivity) getActivity();
                                ma.setFavoriteStatus("no");

                                favorite = ma.getFavoriteStatus();
                                workout_id = ma.getWorkoutID();

                                DatabaseHelper dbHelper = new DatabaseHelper(getActivity(), "WorkoutTracker", null, 1);
                                Cursor cursor = dbHelper.getSingleWorkout(workout_id);
                                cursor.moveToFirst();

                                name = cursor.getString(cursor.getColumnIndex("name"));
                                date = cursor.getString(cursor.getColumnIndex("date"));
                                dbHelper.updateWorkout(workout_id, name, notes, favorite, date, length);

                                Toast toast = Toast.makeText(getActivity(), "Great job! Workout completed!!!", Toast.LENGTH_SHORT);
                                toast.show();

                                ma.loadWorkoutEditFragment();
                            }
                        }).create();

        return dialog;
    }

    public void setWorkoutFields(String updatednotes, String updatedlength){
        notes = updatednotes;
        length = updatedlength;
    }

}
