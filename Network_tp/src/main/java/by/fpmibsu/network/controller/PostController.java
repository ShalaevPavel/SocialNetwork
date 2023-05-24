package by.fpmibsu.network.controller;

import by.fpmibsu.network.dao.PostDAO;
import by.fpmibsu.network.model.Post;

import java.util.List;

public class PostController {
    private PostDAO postDAO;

    public PostController(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public List<Post> getAllPosts() {
        return postDAO.getAllPosts();
    }

    public Post getPostById(int postId) {
        return postDAO.getPostById(postId);
    }

    public void createPost(Post post) {
        postDAO.createPost(post);
    }

    public void updatePost(Post post) {
        postDAO.updatePost(post);
    }

    public void deletePost(int postId) {
        postDAO.deletePost(postId);
    }
}

