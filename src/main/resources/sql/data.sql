-- data.sql

-- Insert users
INSERT INTO users (name, last_name, email, password, role, created)
VALUES ('John', 'Doe', 'john.doe@example.com', 'password123', 'ROLE_ADMIN', '1997-05-28 17:00:00');

INSERT INTO users (name, last_name, email, password, role, created)
VALUES ('Jane', 'Smith', 'jane.smith@example.com', 'password123', 'ROLE_USER', '1997-05-28 17:00:00');

-- Insert events
INSERT INTO Event (name, date_start, date_end, place, id_user, status, ticket_limit, tickets_sold, created)
VALUES ('Concert', '2023-12-25 20:00:00', '2023-12-25 23:00:00', 'New York', 1, '1', 1000, 500, '1997-05-28 17:00:00');

INSERT INTO Event (name, date_start, date_end, place, id_user, status, ticket_limit, tickets_sold, created)
VALUES ('Conference', '2023-11-15 09:00:00', '2023-11-15 17:00:00', 'San Francisco', 2, '1', 500, 250, '1997-05-28 17:00:00');

-- Insert tickets
INSERT INTO Ticket (id_user, id_Event, value, quantity)
VALUES (1, 1, 50, 2);

INSERT INTO Ticket (id_user, id_Event, value, quantity)
VALUES (2, 2, 100, 1);

-- Insert attendances
INSERT INTO Attendance (id_Event, id_user, status)
VALUES (1, 1, 'Confirmed');

INSERT INTO Attendance (id_Event, id_user, status)
VALUES (2, 2, 'Pending');