package tn.esprit.review.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import tn.esprit.review.R;
import tn.esprit.review.models.ReviewItem;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<ReviewItem> reviewList;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public ReviewAdapter(List<ReviewItem> reviewList) {
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {

        ReviewItem r = reviewList.get(position);

        // Note
        holder.tvRating.setText(String.valueOf(r.getRating()));

        // Commentaire
        holder.tvComment.setText(
                (r.getComment() == null || r.getComment().isEmpty())
                        ? "(Pas de commentaire)"
                        : r.getComment()
        );

        // User ID
        holder.tvUser.setText("User #" + r.getUserId());

        // Date
        Date date = new Date(r.getCreatedAt());
        holder.tvDate.setText(dateFormat.format(date));
    }


    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {

        TextView tvRating, tvComment, tvDate, tvUser;
        ImageView imgStar;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvComment = itemView.findViewById(R.id.tvComment);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvDate = itemView.findViewById(R.id.tvDate);
            imgStar = itemView.findViewById(R.id.imgStar);
        }
    }

    }
