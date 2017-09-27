package com.example.kevin.cs3270final;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class StartScreen extends Fragment {

    View rootView;
    Button btnNewWorkout;
    Button btnFavoriteWorkouts;
    Button btnWorkoutHistory;
    Button btnExerciseHistory;

    public StartScreen() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_start_screen, container, false);

        MainActivity ma = (MainActivity) getActivity();
        ma.setWorkoutMenuStatus(false);
        ma.setTitle("Select Activity");

        btnNewWorkout = (Button) rootView.findViewById(R.id.btnNewWorkout);
        btnFavoriteWorkouts = (Button) rootView.findViewById(R.id.btnFavoriteWorkouts);
        btnWorkoutHistory = (Button) rootView.findViewById(R.id.btnWorkoutHistory);
        btnExerciseHistory = (Button) rootView.findViewById(R.id.btnExerciseHistory);

        btnNewWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity ma = (MainActivity) getActivity();
                ma.loadCreateWorkoutFragment();
            }
        });

        btnFavoriteWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity ma = (MainActivity) getActivity();
                ma.loadFavoritesListFragment();
            }
        });

        btnWorkoutHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity ma = (MainActivity) getActivity();
                ma.loadWorkoutListFragment();
            }
        });

        btnExerciseHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity ma = (MainActivity) getActivity();
                ma.loadExerciseListFragment();
            }
        });
        return rootView;
    }

}
