from django import forms

class NewPostForm(forms.Form):
    content = forms.CharField(widget=forms.Textarea(attrs={'rows': 3, 'cols': 40, 'placeholder': 'What\'s on your mind?', 'class': 'form-control', 'id': 'post-content'}), label='')
