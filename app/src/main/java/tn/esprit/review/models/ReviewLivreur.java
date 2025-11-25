package tn.esprit.review.models;

public class ReviewLivreur {


        private int id;
        private int livreurId;
        private int userId;
        private float rating;
        private String comment;
        private long createdAt;

        public ReviewLivreur(int livreurId, int userId, float rating, String comment) {
            this.livreurId = livreurId;
            this.userId = userId;
            this.rating = rating;
            this.comment = comment;
            this.createdAt = System.currentTimeMillis();
        }


        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public int getLivreurId() { return livreurId; }

        public int getUserId() { return userId; }

        public float getRating() { return rating; }

        public String getComment() { return comment; }

        public long getCreatedAt() { return createdAt; }


}
