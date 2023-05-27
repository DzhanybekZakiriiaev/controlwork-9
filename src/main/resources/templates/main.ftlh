<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task Management</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Task Management</h1>

    <!-- Create Task Form -->


    <hr>

    <!-- Task List -->
    <h2>Task List</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Task ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody id="taskList"></tbody>
    </table>
</div>

<h2>Create Task</h2>
<form id="createTaskForm" enctype="multipart/form-data">
    <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" class="form-control" id="name" name="name" required>
    </div>
    <div class="form-group">
        <label for="createdDate">Created Date:</label>
        <input type="date" class="form-control" id="createdDate" name="createdDate" required>
    </div>
    <div class="form-group">
        <label for="developerId">Developer ID:</label>
        <input type="number" class="form-control" id="developerId" name="developerId" required>
    </div>
    <div class="form-group">
        <label for="status">Status:</label>
        <select class="form-control" id="status" name="status" required>
            <option value="Created">Created</option>
            <option value="In progress">In progress</option>
            <option value="Completed">Completed</option>
        </select>
    </div>
    <div class="form-group">
        <label for="attachment">Attachment:</label>
        <input type="file" class="form-control-file" id="attachment" name="attachment" required>
    </div>
    <button type="submit" class="btn btn-primary">Create Task</button>
</form>


<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="../main.js"></script>
</body>
</html>
