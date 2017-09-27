package com.example.kevin.cs3270final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase database;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    public SQLiteDatabase open()
    {
        database = getWritableDatabase();
        return database;
    }

    public void close()
    {
        if (database != null)
            database.close();
    }

    public long addWorkout(String name, String notes, String favorite, String date, String length){

        long rowID = -1;

        ContentValues newWorkout = new ContentValues();
        newWorkout.put("name", name);
        newWorkout.put("notes", notes);
        newWorkout.put("favorite", favorite);
        newWorkout.put("date", date);
        newWorkout.put("length", length);

        if (open() != null){
            rowID = database.insert("workouts", null, newWorkout);
            close();
        }
        return rowID;
    }

    public long updateWorkout(String _id, String name, String notes, String favorite, String date, String length){

        long rowID = -1;

        ContentValues updateWorkout = new ContentValues();
        updateWorkout.put("name", name);
        updateWorkout.put("notes", notes);
        updateWorkout.put("favorite", favorite);
        updateWorkout.put("date", date);
        updateWorkout.put("length", length);

        if (open() != null){
            rowID = database.update("workouts", updateWorkout, "_id=" + _id, null);
            close();
        }
        return rowID;
    }


    public long addWorkoutExercise(String workout_id, String exercise_id, String notes, String reps, String sets, String duration, String rest){

        long rowID = -1;

        ContentValues addWorkoutExercise = new ContentValues();
        addWorkoutExercise.put("workout_id", workout_id);
        addWorkoutExercise.put("exercise_id", exercise_id);
        addWorkoutExercise.put("notes", notes);
        addWorkoutExercise.put("reps", reps);
        addWorkoutExercise.put("sets", sets);
        addWorkoutExercise.put("duration", duration);
        addWorkoutExercise.put("rest", rest);

        if (open() != null){
            rowID = database.insert("workoutexercises", null, addWorkoutExercise);
            close();
        }
        return rowID;
    }

    public long updateWorkoutExercise(String _id, String workout_id, String exercise_id, String notes, String reps, String sets, String duration, String rest){

        long rowID = -1;

        ContentValues updateWorkoutExercise = new ContentValues();
        updateWorkoutExercise.put("workout_id", workout_id);
        updateWorkoutExercise.put("exercise_id", exercise_id);
        updateWorkoutExercise.put("notes", notes);
        updateWorkoutExercise.put("reps", reps);
        updateWorkoutExercise.put("sets", sets);
        updateWorkoutExercise.put("duration", duration);
        updateWorkoutExercise.put("rest", rest);

        if (open() != null){
            rowID = database.update("workoutexercises", updateWorkoutExercise, "_id=" + _id, null);
            close();
        }
        return rowID;
    }

    public long addExercise(String name, String type, String instructions){

        long rowID = -1;

        ContentValues addExercise = new ContentValues();
        addExercise.put("name", name);
        addExercise.put("type", type);
        addExercise.put("instructions", instructions);

        if (open() != null){
            rowID = database.insert("exercises", null, addExercise);
            close();
        }
        return rowID;
    }

    public long updateExercise(long _id, String name, String type, String instructions){

        long rowID = -1;

        ContentValues updateExercise = new ContentValues();
        updateExercise.put("name", name);
        updateExercise.put("type", type);
        updateExercise.put("instructions", instructions);
        if (open() != null){
            rowID = database.update("exercises", updateExercise, "_id=" + _id, null);
            close();
        }
        return rowID;
    }

    public Cursor getSingleWorkout(String id){

        String[] params = new String[1];
        params[0] = "" + id;
        Cursor cursor = null;
        if (open() != null){
            cursor = database.rawQuery("SELECT * FROM workouts WHERE _id = ?", params);
    }
        return cursor;
    }

    public Cursor getWorkoutRowID(long id){

        String[] params = new String[1];
        params[0] = "" + id;
        Cursor cursor = null;
        if (open() != null){
            cursor = database.rawQuery("SELECT * FROM workouts WHERE ROWID = ?", params);
        }
        return cursor;
    }

    public Cursor getSingleExercise(long id){

        String[] params = new String[1];
        params[0] = "" + id;
        Cursor cursor = null;
        if (open() != null){
            cursor = database.rawQuery("SELECT * FROM exercises WHERE _id = ?", params);
        }
        return cursor;
    }

    public void deleteAllWorkouts(){
        if (open() != null) {
            database.delete("workouts", null, null);
        }
    }

    public void deleteWorkout(String id){

        String[] params = new String[1];
        params[0] = "" + id;
        if (open() != null) {
            database.delete("workouts", "_id" + "=?", new String[]{String.valueOf(id)});
            close();
        }

    }

    public void deleteExercise(long id){

        if (open() != null) {
            database.delete("exercises", "_id" + "=?", new String[]{String.valueOf(id)});
            close();
        }

    }

    public void deleteWorkoutExercise(String id) {

        if (open() != null) {
            database.delete("workoutexercises", "_id" + "=?", new String[]{id});
            close();
        }
    }

    public Cursor getSingleWorkoutExercise(String id){

        String[] params = new String[1];
        params[0] = "" + id;
        Cursor cursor = null;
        if (open() != null){
            cursor = database.rawQuery("SELECT * FROM workoutexercises WHERE _id = ?", params);
        }
        return cursor;
    }

    public Cursor getAllWorkouts(){

        Cursor cursor = null;
        if (open() != null){
            cursor = database.rawQuery("SELECT * FROM workouts ORDER BY name", null);
        }
        return cursor;
    }

    public Cursor getAllExercises(){

        Cursor cursor = null;
        if (open() != null){
            cursor = database.rawQuery("SELECT * FROM exercises ORDER BY name", null);
        }
        return cursor;
    }

    public Cursor getAllWorkoutExercises(String exercise_id){

        String[] params = new String[1];
        params[0] = "" + exercise_id;
        Cursor cursor = null;
        if (open() != null){
            cursor = database.rawQuery("SELECT e.name, we._id, we.reps, we.sets, we.duration, we.rest FROM workoutexercises AS we, exercises AS e WHERE we.exercise_id = e._id  AND we.exercise_id = ? ORDER BY we.workout_id", params);
        }
        return cursor;
    }

    public Cursor getAllUserWorkoutExercises(String workout_id){

        String[] params = new String[1];
        params[0] = "" + workout_id;
        Cursor cursor = null;
        if (open() != null){
            cursor = database.rawQuery("SELECT e.name, we._id, we.reps, we.sets, we.duration, we.rest FROM workoutexercises AS we, exercises AS e WHERE we.exercise_id = e._id  AND we.workout_id = ? ORDER BY we.workout_id", params);
        }
        return cursor;
    }

    public Cursor getAllWorkoutExercisesEver(){

        Cursor cursor = null;
        if (open() != null){
            cursor = database.rawQuery("SELECT * FROM workoutexercises", null);
        }
        return cursor;
    }

    public Cursor getFavoriteWorkouts(){

        String favorite = "yes";
        String[] params = new String[1];
        params[0] = "" + favorite;
        Cursor cursor = null;
        if (open() != null){
            cursor = database.rawQuery("SELECT * FROM workouts WHERE favorite = ? ORDER BY name", params);
        }
        return cursor;
    }

    public void deleteWorkoutExercisesTable(){
        if (open() != null) {
            database.execSQL("DROP TABLE IF EXISTS workoutexercises");

            String createWorkoutExercisesQuery = "CREATE TABLE workoutexercises " +
                    "(_id integer primary key autoincrement, workout_id TEXT," +
                    "exercise_id TEXT, notes TEXT, reps TEXT," +
                    "sets TEXT, duration TEXT, rest TEXT);";

            database.execSQL(createWorkoutExercisesQuery);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createWorkoutsQuery = "CREATE TABLE workouts " +
                "(_id integer primary key autoincrement," +
                "name TEXT, notes TEXT, favorite TEXT," +
                "date TEXT, length TEXT);";

        String createExercisesQuery = "CREATE TABLE exercises " +
                "(_id integer primary key autoincrement," +
                "name TEXT, type TEXT, instructions TEXT);";

        String createWorkoutExercisesQuery = "CREATE TABLE workoutexercises " +
                "(workout_id TEXT," +
                "exercise_id TEXT, notes TEXT, reps TEXT," +
                "sets TEXT, duration TEXT, rest TEXT);";

        db.execSQL(createWorkoutsQuery);
        db.execSQL(createExercisesQuery);
        db.execSQL(createWorkoutExercisesQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }


}
