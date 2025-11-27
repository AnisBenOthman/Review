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
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tn.esprit.review.R;
import tn.esprit.review.dao.ReviewDao;
import tn.esprit.review.models.ReviewItem;
import tn.esprit.review.models.ReviewLivreur;
import tn.esprit.review.models.ReviewRestaurant;
import tn.esprit.review.ui.ReviewAdapter;

public class ReviewListFragment extends Fragment {

    // Arguments
    private static final String ARG_MODE = "mode";
    private static final String ARG_ID = "targetId";

    private String mode;
    private int targetId;

    private RecyclerView recyclerReviews;
    private ReviewDao dao;

    // Résumé Google Play
    ProgressBar pb1, pb2, pb3, pb4, pb5;
    TextView tvAvg, tvCount;
    RatingBar rbAvg;

    public static ReviewListFragment newInstance(String mode, int id) {
        ReviewListFragment f = new ReviewListFragment();
        Bundle b = new Bundle();
        b.putString(ARG_MODE, mode);
        b.putInt(ARG_ID, id);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mode = getArguments().getString(ARG_MODE);
            targetId = getArguments().getInt(ARG_ID);
        }
        dao = new ReviewDao(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_review_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Résumé
        pb5 = view.findViewById(R.id.pb5);
        pb4 = view.findViewById(R.id.pb4);
        pb3 = view.findViewById(R.id.pb3);
        pb2 = view.findViewById(R.id.pb2);
        pb1 = view.findViewById(R.id.pb1);

        tvAvg = view.findViewById(R.id.tvAvg);
        tvCount = view.findViewById(R.id.tvCount);
        rbAvg = view.findViewById(R.id.rbAvg);

        // RecyclerView
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
        }

        updateSummary(items);

        recyclerReviews.setAdapter(new ReviewAdapter(items));
    }


    // Résumé Google Play
    private void updateSummary(List<ReviewItem> items) {

        int total = items.size();
        if (total == 0) return;

        float sum = 0;
        int c1 = 0, c2 = 0, c3 = 0, c4 = 0, c5 = 0;

        for (ReviewItem r : items) {
            float rating = r.getRating();
            sum += rating;

            if (rating >= 4.5) c5++;
            else if (rating >= 3.5) c4++;
            else if (rating >= 2.5) c3++;
            else if (rating >= 1.5) c2++;
            else c1++;
        }

        float avg = sum / total;

        pb5.setProgress(c5 * 100 / total);
        pb4.setProgress(c4 * 100 / total);
        pb3.setProgress(c3 * 100 / total);
        pb2.setProgress(c2 * 100 / total);
        pb1.setProgress(c1 * 100 / total);

        tvAvg.setText(String.format(Locale.US, "%.1f", avg));
        tvCount.setText(total + " avis");

        rbAvg.setRating(avg);
    }
}
