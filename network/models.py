from django.contrib.auth.models import AbstractUser
from django.db import models

class User(AbstractUser):
    def followers_count(self):
        return self.followers.count()

    def following_count(self):
        return self.following.count()

    def is_following(self, user):
        return self.following.filter(following=user).exists()

    def __str__(self):
        return f"{self.username}"

class Post(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE, related_name="user_posts")
    content = models.TextField()
    timestamp = models.DateTimeField(auto_now_add=True)
    likes = models.ManyToManyField(User, blank=True, related_name="likes")

    def likes_count(self):
        return self.likes.count()

    def serialize(self):
        return {
            "id": self.id,
            "user": self.user.username,
            "content": self.content,
            "timestamp": self.timestamp.strftime("%b %-d %Y, %-I:%M %p"),
            "likes": self.likes_count()
        }

    def __str__(self):
        return f"{self.user} posted {self.content} at {self.timestamp}"

class UserFollowing(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE, related_name="following")
    following = models.ForeignKey(User, on_delete=models.CASCADE, related_name="followers")

    def __str__(self):
        return f"{self.user} is following {self.following}"