package tn.esprit.review.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import tn.esprit.review.R;
import tn.esprit.review.dao.ReviewDao;
import tn.esprit.review.models.ReviewRestaurant;

public class RestaurantReviewActivity extends AppCompatActivity {

    private RecyclerView recyclerReviews;
    private TextView txtAverage;
    private ReviewDao dao;
    private int restaurantId = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        dao = new ReviewDao(this);

        recyclerReviews = findViewById(R.id.recyclerReviews);
        txtAverage = findViewById(R.id.txtAverage);
        loadData();


    }
    private void loadData() {
        ArrayList<ReviewRestaurant> reviews = dao.getReviewsByRestaurant(restaurantId);

        ReviewAdapter adapter = new ReviewAdapter(reviews);
        recyclerReviews.setLayoutManager(new LinearLayoutManager(this));
        recyclerReviews.setAdapter(adapter);

        float avg = dao.getAverageRestaurant(restaurantId);
        txtAverage.setText("Moyenne : " + avg + " ★");
    }
}
