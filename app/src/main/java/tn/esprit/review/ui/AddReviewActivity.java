package tn.esprit.review.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import tn.esprit.review.R;
import tn.esprit.review.dao.ReviewDao;
import tn.esprit.review.models.ReviewRestaurant;

public class AddReviewActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText edtComment;
    private Button btnSubmit;
    private ReviewDao dao;
    private int restaurantId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        ratingBar = findViewById(R.id.ratingBar);
        edtComment = findViewById(R.id.edtComment);
        btnSubmit = findViewById(R.id.btnSubmit);
dao = new ReviewDao(this);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float rating = ratingBar.getRating();
                String comment = edtComment.getText().toString().trim();

                if (rating == 0) {
                    Toast.makeText(AddReviewActivity.this, "Donnez une note !", Toast.LENGTH_SHORT).show();
                    return;
                }

                ReviewRestaurant review = new ReviewRestaurant(
                        restaurantId,
                        1,
                        rating,
                        comment);
                long id = dao.addReviewRestaurant(review);

                if (id > 0) {
                    Toast.makeText(AddReviewActivity.this, "Avis ajouté !", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddReviewActivity.this, "Erreur lors de l'ajout", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
