package tn.esprit.review.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tn.esprit.review.db.DatabaseHelper;
import tn.esprit.review.models.ReviewLivreur;
import tn.esprit.review.models.ReviewRestaurant;

public class ReviewDao {
    private DatabaseHelper dbHelper;
    public ReviewDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }
    public long addReviewRestaurant(ReviewRestaurant review) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("restaurant_id", review.getRestaurantId());
        values.put("user_id", review.getUserId());
        values.put("rating", review.getRating());
        values.put("comment", review.getComment());
        values.put("created_at", review.getCreatedAt());
        long id = db.insert("review_restaurant", null, values);
        db.close();
        return id;
    }
    public long addReviewLivreur(ReviewLivreur review) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("livreur_id", review.getLivreurId());
        values.put("user_id", review.getUserId());
        values.put("rating", review.getRating());
        values.put("comment", review.getComment());
        values.put("created_at", review.getCreatedAt());
        long id = db.insert("review_livreur", null, values);
        db.close();
        return id;
    }
    public ArrayList<ReviewRestaurant> getReviewsByRestaurant(int restaurantId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<ReviewRestaurant> list = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM review_restaurant WHERE restaurant_id=? ORDER BY created_at DESC",
                new String[]{String.valueOf(restaurantId)}
        );

        if (cursor.moveToFirst()) {
            do {
                ReviewRestaurant r = new ReviewRestaurant(
                        cursor.getInt(cursor.getColumnIndexOrThrow("restaurant_id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("user_id")),
                        cursor.getFloat(cursor.getColumnIndexOrThrow("rating")),
                        cursor.getString(cursor.getColumnIndexOrThrow("comment"))
                );
                r.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                list.add(r);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }
    public ArrayList<ReviewLivreur> getReviewsByLivreur(int livreurId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<ReviewLivreur> list = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM review_livreur WHERE livreur_id=? ORDER BY created_at DESC",
                new String[]{String.valueOf(livreurId)}
        );

        if (cursor.moveToFirst()) {
            do {
                ReviewLivreur r = new ReviewLivreur(
                        cursor.getInt(cursor.getColumnIndexOrThrow("livreur_id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("user_id")),
                        cursor.getFloat(cursor.getColumnIndexOrThrow("rating")),
                        cursor.getString(cursor.getColumnIndexOrThrow("comment"))
                );
                r.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                list.add(r);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }
    public float getAverageRestaurant(int restaurantId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT AVG(rating) FROM review_restaurant WHERE restaurant_id=?",
                new String[]{String.valueOf(restaurantId)}
        );

        if (cursor.moveToFirst()) {
            return cursor.getFloat(0);
        }

        cursor.close();
        return 0;
    }
    public float getAverageLivreur(int livreurId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT AVG(rating) FROM review_livreur WHERE livreur_id=?",
                new String[]{String.valueOf(livreurId)}
        );

        if (cursor.moveToFirst()) {
            return cursor.getFloat(0);
        }

        cursor.close();
        return 0;
    }




}
