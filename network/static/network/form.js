document.addEventListener('DOMContentLoaded', function() {
  const form = document.querySelector('#new-post-form');

  form.addEventListener('submit', function(event) {
    event.preventDefault();

    // Get the form data
    const data = new FormData(form);

    // Send the form data to the server
    fetch('/new_post', {
      method: 'POST',
      body: data,
      headers: {
        'X-CSRFToken': CSRF_TOKEN
      }
    })
    .then(result => {
      // Clear the form
      form.reset();

      // Reload the page
      window.location.reload();
    })
    .catch(error => {
      console.log('Error:', error.message);
    })
  });
});