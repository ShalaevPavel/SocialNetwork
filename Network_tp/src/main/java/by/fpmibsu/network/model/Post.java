package by.fpmibsu.network.model;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private int userId; // Foreign key referencing the user who created the post
    private String content;
    private LocalDateTime creationTime;

    public String getTitle() {
    }

    public String getContent() {
    }

    public int getId() {
    }

    public void setId(int id) {
    }

    public void setTitle(String title) {
    }
    // Additional fields such as likes, comments, etc.

    // Constructors, getters, setters, and other methods
}

