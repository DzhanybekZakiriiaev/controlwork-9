
CREATE TABLE IF NOT EXISTS users (
                        id SERIAL PRIMARY KEY,
                        email VARCHAR(25) NOT NULL,
                        password VARCHAR(80) NOT NULL,
                        type VARCHAR(20) NOT NULL,
                        enabled BOOLEAN NOT NULL DEFAULT true
);



CREATE TABLE IF NOT EXISTS tasks (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(20) NOT NULL,
                      created_date DATE,
                      developer_id INTEGER REFERENCES users (id),
                      status VARCHAR(20) NOT NULL
);


CREATE TABLE IF NOT EXISTS attachments (
                            id SERIAL PRIMARY KEY,
                            task_id INTEGER REFERENCES tasks (id),
                            filename VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS worklog (
                         id SERIAL PRIMARY KEY,
                         task_id INTEGER REFERENCES tasks (id),
                         time_spent VARCHAR(6) NOT NULL,
                         description VARCHAR(50) NOT NULL
);
