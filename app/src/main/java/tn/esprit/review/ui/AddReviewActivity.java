package tn.esprit.review.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import tn.esprit.review.R;
import tn.esprit.review.ui.fragments.AddReviewFragment;

public class AddReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        }

        // Récupérer les extras passés par l'Intent
        //String mode = getIntent().getStringExtra("mode"); // "restaurant" ou "livreur"
        //int id = getIntent().getIntExtra("id", -1);
        String mode = "livreur";   // "restaurant" ou "livreur"
        int id = 5;                   // ID du restaurant ou du livreur


        // Optionnel : vérifier que id est valide
//        if (id == -1 || mode == null) {
//            // si paramètres manquants, fermer et prévenir
//            finish();
//            return;
//        }

        // Charger le fragment et lui passer les arguments
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, AddReviewFragment.newInstance(mode, id))
                .commit();
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // ferme l’activité quand on clique sur ←
        return true;
    }
}
