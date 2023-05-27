$(document).ready(function() {
    var existingUser = {
        email: 'example@example.com',
        type: 'Manager',
        password: '******'
    };

    $('#email').val(existingUser.email);
    $('#type').val(existingUser.type);
    $('#password').val(existingUser.password);
    $('#updateUserForm').submit(function(event) {
        event.preventDefault();
        var formData = {
            email: $('#email').val(),
            type: $('#type').val(),
            password: $('#password').val()
        };
        $.ajax({
                type: 'PUT',
                url: '/updateUser/' + userId,
                contentType: 'application/json',
                data: JSON.stringify(formData),
                success: function(response){
                console.log('User updated:', response);
            },
            error: function(error) {
            console.error('Error updating user:', error);
        }
    });
});
});