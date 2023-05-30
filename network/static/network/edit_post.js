document.addEventListener('DOMContentLoaded', () => {
  const edit_buttons = document.querySelectorAll('.edit-post');

  edit_buttons.forEach((button) => {
    button.addEventListener('click', () => {
      const post_id = button.dataset.id;
      console.log(button);
      console.log(button.dataset.id);
      const post_body = document.getElementById(post_id);

      post_body.innerHTML = `
        <form id="edit-form-${post_id}" class="edit-form">
          <textarea id="edit-text-${post_id}" class="edit-text" rows="3">${post_body.innerText}</textarea>
          <button id="edit-submit-${post_id}" class="edit-submit">Save</button>
        </form>
      `;

      const edit_form = document.getElementById(`edit-form-${post_id}`);

      edit_form.addEventListener('submit', (e) => {
        e.preventDefault();
        const edit_text = document.getElementById(`edit-text-${post_id}`);
        const post_body = document.getElementById(post_id);

        fetch(`/edit/${post_id}`, {
          method: 'PUT',
          body: JSON.stringify({
            content: edit_text.value,
          }),
          headers: {
            'X-CSRFToken': CSRF_TOKEN
          }
        })
          .then((response) => response.json())
          .then((result) => {
            console.log(result);
            post_body.innerText = result.body;
          });
      });
    });
  });
});
