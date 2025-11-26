package tn.esprit.review.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import tn.esprit.review.ui.AddReviewActivity;
import tn.esprit.review.R;
import tn.esprit.review.ui.RestaurantReviewActivity;


public class MainActivity extends AppCompatActivity {
    private Button btnAddReview;
    private Button btnListReviews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnAddReview = findViewById(R.id.btnAddReview);
        btnListReviews = findViewById(R.id.btnListReviews);


        btnAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddReviewActivity.class);
                startActivity(intent);
            }
        });

        btnListReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RestaurantReviewActivity.class);
                startActivity(intent);
            }
      });
    }
}