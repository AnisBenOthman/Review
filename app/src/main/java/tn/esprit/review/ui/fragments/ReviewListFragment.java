package tn.esprit.review.ui.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.review.R;
import tn.esprit.review.dao.ReviewDao;
import tn.esprit.review.models.ReviewItem;
import tn.esprit.review.models.ReviewLivreur;
import tn.esprit.review.models.ReviewRestaurant;
import tn.esprit.review.ui.ReviewAdapter;

public class ReviewListFragment extends Fragment {

    private static final String ARG_MODE = "mode";
    private static final String ARG_ID = "targetId";

    private String mode; // "restaurant" ou "livreur"
    private int targetId;

    private TextView txtAverage;
    private RecyclerView recyclerReviews;
    private ReviewDao dao;

    public static ReviewListFragment newInstance(String mode, int id) {
        ReviewListFragment f = new ReviewListFragment();
        Bundle b = new Bundle();
        b.putString(ARG_MODE, mode);
        b.putInt(ARG_ID, id);
        f.setArguments(b);
        return f;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mode = getArguments().getString(ARG_MODE);
            targetId = getArguments().getInt(ARG_ID);
        }
        dao = new ReviewDao(requireContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_review_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        txtAverage = view.findViewById(R.id.txtAverage);
        recyclerReviews = view.findViewById(R.id.recyclerReviews);
        recyclerReviews.setLayoutManager(new LinearLayoutManager(requireContext()));

        loadData();
    }

    private void loadData() {
        if (mode == null) {
            Toast.makeText(requireContext(), "Mode manquant", Toast.LENGTH_SHORT).show();
            return;
        }

        List<ReviewItem> items = new ArrayList<>();

        if ("restaurant".equals(mode)) {
            ArrayList<ReviewRestaurant> reviews = dao.getReviewsByRestaurant(targetId);
            for (ReviewRestaurant r : reviews) {
                items.add(new ReviewItem(
                        r.getRating(),
                        r.getComment(),
                        r.getCreatedAt(),
                        r.getUserId()
                ));

            }
            float avg = dao.getAverageRestaurant(targetId);
            txtAverage.setText("Moyenne : " + avg + " ★");
        } else if ("livreur".equals(mode)) {
            ArrayList<ReviewLivreur> reviews = dao.getReviewsByLivreur(targetId);
            for (ReviewLivreur r : reviews) {
                items.add(new ReviewItem(
                        r.getRating(),
                        r.getComment(),
                        r.getCreatedAt(),
                        r.getUserId()
                ));

            }
            float avg = dao.getAverageLivreur(targetId);
            txtAverage.setText("Moyenne : " + avg + " ★ (" + items.size() + " avis)");

        } else {
            Toast.makeText(requireContext(), "Mode inconnu", Toast.LENGTH_SHORT).show();
        }

        ReviewAdapter adapter = new ReviewAdapter(items);
        recyclerReviews.setAdapter(adapter);
    }
}
