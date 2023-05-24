package by.fpmibsu.network.dao;

import by.fpmibsu.network.model.Post;

import java.util.List;

public interface PostDAO {
    Post getPostById(int postId);
    void createPost(Post post);
    void updatePost(Post post);
    void deletePost(int postId);
    List<Post> getPostsByUser(int userId);
    List<Post> getAllPosts();
}
