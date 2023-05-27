$(document).ready(function() {
    $('#createTaskForm').submit(function(event) {
        event.preventDefault();

        var formData = new FormData();
        formData.append('name', $('#title').val());
        formData.append('createdDate', $('#createdDate').val());
        formData.append('developerId', $('#developerId').val());
        formData.append('status', $('#status').val());
        formData.append('attachment', $('#attachment')[0].files[0]);

        $.ajax({
            type: 'POST',
            url: '/tasks',
            contentType: false,
            processData: false,
            data: formData,
            success: function(response) {
                console.log('Task created:', response);
                $('#createTaskForm')[0].reset();
                uploadAttachment(response.id);
                getTasks();
            },
            error: function(error) {
                console.error('Error creating task:', error);
            }
        });
    });

    function uploadAttachment(taskId) {
        var attachmentData = new FormData();
        attachmentData.append('file', $('#attachment')[0].files[0]);

        $.ajax({
            type: 'POST',
            url: '/tasks/' + taskId + '/attachments',
            contentType: false,
            processData: false,
            data: attachmentData,
            success: function(response) {
                console.log('Attachment uploaded:', response);
            },
            error: function(error) {
                console.error('Error uploading attachment:', error);
            }
        });
    }

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
                if (newStatus === 'Completed') {
                    createWorklog(taskId);
                }
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

    function createWorklog(taskId) {
        var worklogData = {
            taskId: taskId,
            timeSpent: calculateTimeSpent(taskId),
            description: 'Worklog of task with id: ' + taskId
        };

        $.ajax({
            type: 'POST',
            url: '/tasks/' + taskId + '/worklogs',
            contentType: 'application/json',
            data: JSON.stringify(worklogData),
            success: function(response) {
                console.log('Worklog created:', response);
            },
            error: function(error) {
                console.error('Error creating worklog:', error);
            }
        });
    }

    function calculateTimeSpent(taskId) {
        $.ajax({
            type: 'GET',
            url: '/tasks/' + taskId,
            success: function(response) {
                var createdAt = new Date(response.created_at);
                var now = new Date();
                var timeDifference = now.getTime() - createdAt.getTime();
                var minutesDifference = Math.floor(timeDifference / (1000 * 60));
                console.log('Time spent:', minutesDifference + ' minutes');
                return minutesDifference + ' minutes';
            },
            error: function(error) {
                console.error('Error getting task:', error);
                return '';
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