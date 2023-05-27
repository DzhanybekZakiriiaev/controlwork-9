CREATE TABLE "User" (
                        id SERIAL PRIMARY KEY,
                        email VARCHAR(25) NOT NULL,
                        password VARCHAR(16) NOT NULL,
                        type VARCHAR(20) NOT NULL
);

CREATE TABLE Task (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(20) NOT NULL,
                      created_date DATE DEFAULT CURRENT_DATE,
                      developer_id INTEGER REFERENCES "User" (id),
                      status VARCHAR(20) NOT NULL
);

CREATE TABLE Worklog (
                         id SERIAL PRIMARY KEY,
                         task_id INTEGER REFERENCES Task (id),
                         time_spent VARCHAR(6) NOT NULL,
                         description VARCHAR(50) NOT NULL
);

CREATE TABLE Attachment (
                            id SERIAL PRIMARY KEY,
                            task_id INTEGER REFERENCES Task (id),
                            filename VARCHAR(255) NOT NULL
);
