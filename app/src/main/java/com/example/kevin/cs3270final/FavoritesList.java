package com.example.kevin.cs3270final;


    import android.database.Cursor;
    import android.os.AsyncTask;
    import android.os.Bundle;
    import android.support.v4.app.Fragment;
    import android.support.v4.app.ListFragment;
    import android.support.v4.widget.CursorAdapter;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ListView;
    import android.widget.SimpleCursorAdapter;


public class FavoritesList extends ListFragment {

    View rootView;
    Cursor cursor;
    SimpleCursorAdapter adapter;

    public FavoritesList() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = super.onCreateView(inflater, container, savedInstanceState);
        MainActivity ma = (MainActivity) getActivity();
        ma.setTitle("Favorites");

        new getAllFavorites().execute(1);

        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        MainActivity ma = (MainActivity) getActivity();
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity(), "WorkoutTracker", null, 1);
        Cursor cursor = dbHelper.getSingleWorkout(String.valueOf(id));
        cursor.moveToFirst();

        String workout_name = cursor.getString(cursor.getColumnIndex("name"));
        ma.setWorkoutID(String.valueOf(id));
        ma.setWorkoutName(workout_name);
        ma.setWorkoutMenuStatus(true);
        ma.loadWorkoutEditFragment();
    }

    public class getAllFavorites extends AsyncTask<Integer, Integer, Cursor> {

        DatabaseHelper dbHelper = new DatabaseHelper(getActivity(), "WorkoutTracker", null, 1);

        @Override
        protected Cursor doInBackground(Integer... params) {
            cursor = dbHelper.getFavoriteWorkouts();
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            String[] columns = new String[]{"name"};
            int[] views = new int[]{android.R.id.text1};
            adapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1,
                    cursor, columns, views,
                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            setListAdapter(adapter);

        }
    } // end of getAllWorkouts asyncTask
}
