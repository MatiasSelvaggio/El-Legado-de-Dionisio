-- data.sql

-- Insert users
INSERT INTO "User" (name, last_name, email, password, role)
VALUES ('John', 'Doe', 'john.doe@example.com', 'password123', 'ROLE_ADMIN');

INSERT INTO "User" (name, last_name, email, password, role)
VALUES ('Jane', 'Smith', 'jane.smith@example.com', 'password123', 'ROLE_USER');

-- Insert events
INSERT INTO Event (name, date_start, date_end, localidad, id_user, status, ticket_limit, tickets_sold)
VALUES ('Concert', '2023-12-25 20:00:00', '2023-12-25 23:00:00', 'New York', 1, 1, 1000, 500);

INSERT INTO Event (name, date_start, date_end, localidad, id_user, status, ticket_limit, tickets_sold)
VALUES ('Conference', '2023-11-15 09:00:00', '2023-11-15 17:00:00', 'San Francisco', 2, 1, 500, 250);

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