$(document).ready(function() {
    $('#createTaskForm').submit(function(event) {
        event.preventDefault();

        var formData = {
            title: $('#title').val(),
            description: $('#description').val(),
            status: $('#status').val()
        };

        $.ajax({
            type: 'POST',
            url: '/tasks',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(response) {
                console.log('Task created:', response);
                $('#createTaskForm')[0].reset();
                getTasks();
            },
            error: function(error) {
                console.error('Error creating task:', error);
            }
        });
    });

    function updateTask(taskId, newStatus) {
        var formData = {
            status: newStatus
        };

        $.ajax({
            type: 'PUT',
            url: '/tasks/' + taskId,
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(response) {
                console.log('Task updated:', response);
                getTasks();
            },
            error: function(error) {
                console.error('Error updating task:', error);
            }
        });
    }

    function deleteTask(taskId) {
        $.ajax({
            type: 'DELETE',
            url: '/tasks/' + taskId,
            success: function(response) {
                console.log('Task deleted:', response);
                getTasks();
            },
            error: function(error) {
                console.error('Error deleting task:', error);
            }
        });
    }

    function getTasks() {
        $.ajax({
            type: 'GET',
            url: '/tasks',
            success: function(response) {
                console.log('Tasks:', response);
                var taskList = $('#taskList');
                taskList.empty();
                response.forEach(function(task) {
                    var row = $('<tr></tr>');
                    row.append('<td>' + task.taskId + '</td>');
                    row.append('<td>' + task.title + '</td>');
                    row.append('<td>' + task.description + '</td>');
                    row.append('<td>' + task.status + '</td>');
                    var actions = $('<td></td>');
                    var updateButton = $('<button type="button" class="btn btn-primary">Update</button>');
                    updateButton.data('task-id', task.taskId);
                    updateButton.data('current-status', task.status);
                    updateButton.click(function() {
                        var taskId = $(this).data('task-id');
                        var currentStatus = $(this).data('current-status');
                        var newStatus = '';
                        if (currentStatus === 'Created') {
                            newStatus = 'In progress';
                        } else if (currentStatus === 'In progress') {
                            newStatus = 'Completed';
                        }

                        updateTask(taskId, newStatus);
                    });
                    actions.append(updateButton);
                    actions.append(' ');
                    var deleteButton = $('<button type="button" class="btn btn-danger">Delete</button>');
                    deleteButton.data('task-id', task.taskId);
                    deleteButton.click(function() {
                        var taskId = $(this).data('task-id');
                        deleteTask(taskId);
                    });
                    actions.append(deleteButton);
                    row.append(actions);
                    taskList.append(row);
                });
            },
            error: function(error) {
                console.error('Error getting tasks:', error);
            }
        });
    }

    getTasks();
});