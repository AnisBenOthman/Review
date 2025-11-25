package tn.esprit.review.ui.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import tn.esprit.review.R;
import tn.esprit.review.dao.ReviewDao;
import tn.esprit.review.models.ReviewLivreur;
import tn.esprit.review.models.ReviewRestaurant;

public class AddReviewFragment extends Fragment {

    private static final String ARG_MODE = "mode";
    private static final String ARG_ID = "targetId";

    private String mode;        // "restaurant" or "livreur"
    private int targetId;

    private RatingBar ratingBar;
    private EditText edtComment;
    private Button btnSubmit;
    private Button btnBack;

    private ReviewDao dao;

    public static AddReviewFragment newInstance(String mode, int id) {
        AddReviewFragment fragment = new AddReviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MODE, mode);
        args.putInt(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // récupérer arguments
        if (getArguments() != null) {
            mode = getArguments().getString(ARG_MODE);
            targetId = getArguments().getInt(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // inflate le layout fragment
        return inflater.inflate(R.layout.fragment_add_review, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        ratingBar = view.findViewById(R.id.ratingBar);
        edtComment = view.findViewById(R.id.edtComment);
        btnSubmit = view.findViewById(R.id.btnSubmit);


        dao = new ReviewDao(requireContext());



        btnSubmit.setOnClickListener(v -> saveReview());
    }

    private void saveReview() {
        float rating = ratingBar.getRating();
        String comment = edtComment.getText().toString().trim();

        if (rating == 0) {
            Toast.makeText(getContext(), "Donnez une note !", Toast.LENGTH_SHORT).show();
            return;
        }

        if ("restaurant".equals(mode)) {
            ReviewRestaurant r = new ReviewRestaurant(
                    targetId,
                    1, // user_id fixe pour l'instant
                    rating,
                    comment
            );
            long insertedId = dao.addReviewRestaurant(r);
            if (insertedId > 0) {
                Toast.makeText(getContext(), "Avis restaurant ajouté", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Erreur lors de l'ajout", Toast.LENGTH_SHORT).show();
            }

        } else if ("livreur".equals(mode)) {
            ReviewLivreur r = new ReviewLivreur(
                    targetId,
                    1, // user_id fixe
                    rating,
                    comment
            );
            long insertedId = dao.addReviewLivreur(r);
            if (insertedId > 0) {
                Toast.makeText(getContext(), "Avis livreur ajouté", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Erreur lors de l'ajout", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getContext(), "Mode invalide", Toast.LENGTH_SHORT).show();
            return;
        }

        // fermer l'activité parent (retour à l'écran précédent)
        requireActivity().finish();
    }
}
