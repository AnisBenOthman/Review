package tn.esprit.review.models;

public class ReviewItem {
    private float rating;
    private String comment;
    private long createdAt; // timestamp

    private int userId;

    public ReviewItem(float rating, String comment, long createdAt, int userId) {
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public int getUserId() { return userId; }


    public float getRating() { return rating; }
    public String getComment() { return comment; }
    public long getCreatedAt() { return createdAt; }
}
