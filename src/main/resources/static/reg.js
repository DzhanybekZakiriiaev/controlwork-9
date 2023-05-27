$(document).ready(function() {
    $('#createUserForm').submit(function(event) {
    event.preventDefault();

    var formData = {
        email: $('#email').val(),
        type: $('#type').val(),
        password: $('#password').val()
    };

    $.ajax({
        type: 'POST',
        url: 'users/create',
        contentType: 'application/json',
        data: JSON.stringify(formData),
        success: function(response) {
            console.log('User created:', response);
            $('#createUserForm')[0].reset();
        },
        error: function(error) {
            console.error('Error creating user:', error);
        }
    });
});
});