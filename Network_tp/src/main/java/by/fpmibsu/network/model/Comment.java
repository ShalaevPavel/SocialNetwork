package by.fpmibsu.network.model;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private int postId; // Post ID to which the comment belongs
    private int userId; // User ID of the commenter
    private String content;
    private LocalDateTime creationTime;

    // Constructors
    public Comment() {
    }

    public Comment(int id, int postId, int userId, String content, LocalDateTime creationTime) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.creationTime = creationTime;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
