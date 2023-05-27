
INSERT INTO users (email, password, type) VALUES
                                               ('manager@example.com', 'manager123', 'Manager'),
                                               ('developer1@example.com', 'developer1', 'Developer'),
                                               ('developer2@example.com', 'developer2', 'Developer');

INSERT INTO tasks (name, created_date, developer_id, status) VALUES
                                                                ('Task 1', CURRENT_DATE, 2, 'Created'),
                                                                ('Task 2', CURRENT_DATE, 3, 'Created');

INSERT INTO attachments (task_id, filename) VALUES
                                               (1, 'attachment1.jpeg'),
                                               (2, 'attachment2.jpeg');
