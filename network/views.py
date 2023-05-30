import json
from django.contrib.auth import authenticate, login, logout
from django.db import IntegrityError
from django.http import HttpResponse, HttpResponseRedirect, JsonResponse
from django.shortcuts import render
from django.urls import reverse
from django.contrib.auth.decorators import login_required
from django.core.paginator import Paginator

from .forms import NewPostForm
from .models import User, Post, UserFollowing


def index(request):
    page = request.GET.get("page", 1)
    p = Paginator(Post.objects.all().order_by("-timestamp"), 10)

    posts = p.page(page)

    return render(request, "network/index.html", {
        "form": NewPostForm(),
        "posts": posts
    })


def login_view(request):
    if request.method == "POST":

        # Attempt to sign user in
        username = request.POST["username"]
        password = request.POST["password"]
        user = authenticate(request, username=username, password=password)

        # Check if authentication successful
        if user is not None:
            login(request, user)
            return HttpResponseRedirect(reverse("index"))
        else:
            return render(request, "network/login.html", {
                "message": "Invalid username and/or password."
            })
    else:
        return render(request, "network/login.html")


def logout_view(request):
    logout(request)
    return HttpResponseRedirect(reverse("index"))


def register(request):
    if request.method == "POST":
        username = request.POST["username"]
        email = request.POST["email"]

        # Ensure password matches confirmation
        password = request.POST["password"]
        confirmation = request.POST["confirmation"]
        if password != confirmation:
            return render(request, "network/register.html", {
                "message": "Passwords must match."
            })

        # Attempt to create new user
        try:
            user = User.objects.create_user(username, email, password)
            user.save()
        except IntegrityError:
            return render(request, "network/register.html", {
                "message": "Username already taken."
            })
        login(request, user)
        return HttpResponseRedirect(reverse("index"))
    else:
        return render(request, "network/register.html")

@login_required
def new_post(request):
    if request.method == "POST":
        form = NewPostForm(request.POST)
        if form.is_valid():
            content = form.cleaned_data["content"]
            post = Post(user=request.user, content=content)
            post.save()

            return JsonResponse({"post": post.serialize()}, status=201)
        else:
            errors = form.errors.as_json()
            return JsonResponse({"error": errors}, status=400)

def profile(request, username):
    user = User.objects.get(username=username)
    if request.user.is_authenticated:
      button_text = "Follow" if not request.user.is_following(user) else "Unfollow"
    else:
      button_text = ""

    page = request.GET.get("page", 1)
    p = Paginator(user.user_posts.all().order_by("-timestamp"), 10)

    posts = p.page(page)

    return render(request, "network/profile.html", {
        "profile_user": user,
        "posts": posts,
        "button_text": button_text
    })

@login_required
def following(request):
    # Follow or unfollow a user if already following
    if request.method == "PUT":
        data = json.loads(request.body)
        username = data.get("username", "")
        user = User.objects.get(username=username)

        if request.user.is_following(user):
            UserFollowing.objects.get(user=request.user, following=user).delete()
            return JsonResponse({"message": "Unfollowed",
                                 "followers": user.followers_count(),
                                  "following": user.following_count()}, status=201)
        else:
            UserFollowing.objects.create(user=request.user, following=user)
            return JsonResponse({"message": "Following",
                                 "followers": user.followers_count(),
                                 "following": user.following_count()}, status=201)

@login_required
def following_posts(request):
    following = request.user.following.all()
    users = [user.following for user in following]

    page = request.GET.get("page", 1)
    p = Paginator(Post.objects.filter(
        user__in=users).order_by("-timestamp"), 10)

    posts = p.page(page)

    return render(request, "network/following.html", {
        "posts": posts
    })

@login_required
def edit_post(request, post_id):
    post = Post.objects.get(pk=post_id)

    if request.method == "PUT":
        data = json.loads(request.body)
        content = data.get("content", "")

        post.content = content
        post.save()

        return JsonResponse({"message": "Post updated", "body": post.content}, status=201)

@login_required
def like_post(request, post_id):
    post = Post.objects.get(pk=post_id)

    if request.method == "PUT":
        if not post.likes.filter(pk=request.user.id).exists():
            post.likes.add(request.user)
            return JsonResponse({"liked": True, "likes": post.likes_count()}, status=201)
        else:
            post.likes.remove(request.user)
            return JsonResponse({"liked": False, "likes": post.likes_count()}, status=201)