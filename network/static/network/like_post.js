document.addEventListener('DOMContentLoaded', () => {
  const like_buttons = document.querySelectorAll('.like-post');

  like_buttons.forEach((button) => {
    button.addEventListener('click', () => {
      const post_id = button.dataset.id;
      const like_count = document.getElementById(`like-count-${post_id}`);
      const like_icon = document.getElementById(`like-icon-${post_id}`);

      fetch(`/like/${post_id}`, {
        method: 'PUT',
        body: JSON.stringify({
          post_id: post_id,
        }),
        headers: {
          'X-CSRFToken': CSRF_TOKEN
        }
      })
        .then((response) => response.json())
        .then((result) => {
          console.log(result);
          like_count.innerText = result.likes + ' likes';
          if (result.liked) {
            like_icon.src = '/static/network/images/heart-2.svg';
          } else {
            like_icon.src = '/static/network/images/heart-1.svg';
          }
        });
    });
  });
});