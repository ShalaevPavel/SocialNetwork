package by.fpmibsu.network.model;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private int userId;
    private String content;
    private String title = "initial";
    private LocalDateTime creationTime;

    // Constructors


    public Post(int id, int userId, String content, LocalDateTime creationTime) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.creationTime = creationTime;
    }

    public Post() {

    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}
