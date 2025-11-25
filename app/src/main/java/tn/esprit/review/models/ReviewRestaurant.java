package tn.esprit.review.models;

public class ReviewRestaurant {


        private int id;
        private int restaurantId;
        private int userId;
        private float rating;
        private String comment;
        private long createdAt;

        public ReviewRestaurant(int restaurantId, int userId, float rating, String comment) {
            this.restaurantId = restaurantId;
            this.userId = userId;
            this.rating = rating;
            this.comment = comment;
            this.createdAt = System.currentTimeMillis();
        }


        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public int getRestaurantId() { return restaurantId; }

        public int getUserId() { return userId; }

        public float getRating() { return rating; }

        public String getComment() { return comment; }

        public long getCreatedAt() { return createdAt; }


}
