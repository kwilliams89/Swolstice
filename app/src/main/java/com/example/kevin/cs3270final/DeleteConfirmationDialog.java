package com.example.kevin.cs3270final;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;


public class DeleteConfirmationDialog extends DialogFragment {

    long courseID;

    public DeleteConfirmationDialog() {
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Dialog dialog = builder.setMessage("This will permanently delete the workout.")
                .setCancelable(false)
                .setTitle("Are You Sure?")
                .setPositiveButton("Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                DatabaseHelper dbHelper = new DatabaseHelper(getActivity(), "WorkoutTracker", null, 1);
                                MainActivity ma = (MainActivity) getActivity();
                                String workout_id = ma.getWorkoutID();
                                if (workout_id != null) {
                                    dbHelper.deleteWorkout(workout_id);
                                    Toast toast = Toast.makeText(getActivity(), "Workout has been deleted.", Toast.LENGTH_SHORT);
                                    toast.show();
                                    ma.loadStartScreenFragment();
                                }
                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        }).create();

        return dialog;
    }

}
