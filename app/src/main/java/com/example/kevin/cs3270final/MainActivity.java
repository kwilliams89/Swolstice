package com.example.kevin.cs3270final;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    String workout_id;
    String exercise_id;
    String favorite;
    String workout_exercise_id;
    Boolean workout_menu_status;
    String workout_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, new StartScreen(), "SS").addToBackStack(null).commit();

    }


    public void loadWorkoutEditFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, new WorkoutEdit(), "WE").addToBackStack(null).commit();
    }

    public void loadEditWorkoutExerciseFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, new EditWorkoutExercise(), "EWE").commit();
    }

    public void loadWorkoutExercisesListFragment(){

        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, new WorkoutExercisesList(), "WEL").addToBackStack(null).commit();
    }

    public void loadSelectExerciseFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, new SelectExercise(), "SE").addToBackStack(null).commit();
    }

    public void loadCreateExerciseFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, new CreateExercise(), "CE").addToBackStack(null).commit();
    }

    public void loadCreateWorkoutFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, new CreateWorkout(), "CW").addToBackStack(null).commit();
    }

    public void loadCreateWorkoutExerciseFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, new CreateWorkoutExercise(), "CWE").addToBackStack(null).commit();
    }

    public void loadFavoritesListFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, new FavoritesList(), "FL").addToBackStack(null).commit();
    }

    public void loadExerciseListFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, new ExercisesList(), "EL").addToBackStack(null).commit();
    }

    public void loadWorkoutListFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, new WorkoutsList(), "WL").addToBackStack(null).commit();
    }

    public void loadStartScreenFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, new StartScreen(), "SS").addToBackStack(null).commit();
    }

    public void loadViewWorkoutExerciseFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.containerFragments, new ViewWorkoutExercise(), "VWE").addToBackStack(null).commit();
    }


    public String getExerciseID() {
        return exercise_id;
    }

    public void setExerciseID(String exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getFavoriteStatus() {
        return favorite;
    }

    public void setFavoriteStatus(String favorite) {
        this.favorite = favorite;
    }

    public String getWorkoutID() {
        return workout_id;
    }

    public void setWorkoutID(String workout_id) {
        this.workout_id = workout_id;
    }

    public String getWorkoutName() {
        return workout_name;
    }

    public void setWorkoutName(String workout_name) {
        this.workout_name = workout_name;
    }

    public String getWorkoutExerciseID() {
        return workout_exercise_id;
    }

    public void setWorkoutExerciseID(String workout_exercise_id) { this.workout_exercise_id = workout_exercise_id;}

    public Boolean getWorkoutMenuStatus() {
        return workout_menu_status;
    }

    public void setWorkoutMenuStatus(Boolean workout_menu_status) { this.workout_menu_status = workout_menu_status;}
}

