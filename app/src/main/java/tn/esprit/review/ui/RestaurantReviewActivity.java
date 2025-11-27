package tn.esprit.review.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import tn.esprit.review.R;
import tn.esprit.review.ui.fragments.ReviewListFragment;

public class RestaurantReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        String mode = "livreur"; // ou "livreur"
        int id = 5;

        //  via Intent (plus tard)
        // String mode = getIntent().getStringExtra("mode");
        // int id = getIntent().getIntExtra("id", -1);

        if (mode == null || id == -1) {
            Toast.makeText(this, "Paramètres manquants", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Charger le fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_reviews, ReviewListFragment.newInstance(mode, id))
                .commit();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();   // retourne en arrière
        return true;
    }

}
