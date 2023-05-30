document.addEventListener('DOMContentLoaded', function () {
  const button = document.querySelector('#follow-button');

  button.addEventListener('click', function (event) {
    event.preventDefault();

    const username = button.dataset.username;

    fetch(`/following`, {
      method: 'PUT',
      body: JSON.stringify({
        username: username
      }),
      headers: {
        'X-CSRFToken': CSRF_TOKEN
      }
    })
    .then(response => response.json())
    .then(result => {
      if (result.message === 'Following') {
        button.innerHTML = 'Unfollow';
      } else {
        button.innerHTML = 'Follow';
      }

      const followers = document.querySelector('#followers');
      followers.innerHTML = result.followers;

      const following = document.querySelector('#following');
      following.innerHTML = result.following;
    });
  });
});