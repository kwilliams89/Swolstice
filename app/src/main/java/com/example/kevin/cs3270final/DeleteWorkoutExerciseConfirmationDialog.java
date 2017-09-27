package com.example.kevin.cs3270final;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;


public class DeleteWorkoutExerciseConfirmationDialog extends DialogFragment {


    public DeleteWorkoutExerciseConfirmationDialog() {
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Dialog dialog = builder.setMessage("This will delete the exercise and remove it from the current workout. Do you wish to proceed?")
                .setCancelable(false)
                .setTitle("Delete Workout Exercise")
                .setPositiveButton("Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                DatabaseHelper dbHelper = new DatabaseHelper(getActivity(), "WorkoutTracker", null, 1);
                                MainActivity ma = (MainActivity) getActivity();
                                String workout_exercise_id = ma.getWorkoutExerciseID();
                                if (workout_exercise_id != null) {
                                    dbHelper.deleteWorkoutExercise(String.valueOf(workout_exercise_id));
                                    Toast toast = Toast.makeText(getActivity(), "Exercise has been deleted from workout.", Toast.LENGTH_SHORT);
                                    toast.show();
                                    ma.loadWorkoutEditFragment();
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
