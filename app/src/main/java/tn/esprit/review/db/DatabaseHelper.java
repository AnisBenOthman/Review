package tn.esprit.review.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "reviews.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE review_restaurant (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "restaurant_id INTEGER," +
                "user_id INTEGER," +
                "rating REAL," +
                "comment TEXT," +
                "created_at INTEGER)");


        db.execSQL("CREATE TABLE review_livreur (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "livreur_id INTEGER," +
                "user_id INTEGER," +
                "rating REAL," +
                "comment TEXT," +
                "created_at INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS review_restaurant");
        db.execSQL("DROP TABLE IF EXISTS review_livreur");
        onCreate(db);

    }
}
