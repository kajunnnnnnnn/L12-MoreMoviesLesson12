package sg.edu.rp.c346.id20026955.moremovieslesson12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movie.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SONG = "movie";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATING = "rating";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSql = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_GENRE + " TEXT, "
                + COLUMN_YEAR + " INTEGER, "
                + COLUMN_RATING + " TEXT )";
        db.execSQL(createSongTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        onCreate(db);

    }

    public ArrayList<Movies> getAllMovies() {
        ArrayList<Movies> movies = new ArrayList<Movies>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
        Cursor cursor = db.query(TABLE_SONG, columns, null, null,
                null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movies movie = new Movies(id, title, genre, year, rating);
                movies.add(movie);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movies;
    }

    public long insertMovie(String title, String genre, int year, String rating){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_GENRE, genre);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_RATING, rating);
        long result = db.insert(TABLE_SONG, null, values);
        db.close();
        return result;
    }

    public int updateMovie(Movies data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_GENRE, data.getGenre());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_RATING, data.getRating());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_SONG, values, condition, args);
        db.close();
        return result;
    }

    public int deleteMovie(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }
}
