
DROP TABLE IF EXISTS Attendance;
DROP TABLE IF EXISTS Ticket;
DROP TABLE IF EXISTS Event;
DROP TABLE IF EXISTS users;
--DROP TABLE IF EXISTS Rol;


CREATE TABLE users (
 id_user BIGSERIAL,
 name VARCHAR(100) NOT NULL,
 last_name VARCHAR(100) NOT NULL,
 email VARCHAR(255) UNIQUE NOT NULL,
 password VARCHAR(255) NOT NULL,
 role VARCHAR(100) NOT NULL,
 created TIMESTAMP NOT NULL,
 deleted TIMESTAMP
);


ALTER TABLE users ADD CONSTRAINT User_pkey PRIMARY KEY (id_user);

CREATE TABLE Event (
 id_Event BIGSERIAL,
 name VARCHAR(100),
 date_start TIMESTAMP,
 date_end TIMESTAMP,
 place VARCHAR(255),
 id_user INTEGER,
 status VARCHAR(255),
 ticket_limit INTEGER,
 tickets_sold INTEGER,
 created TIMESTAMP NOT NULL,
 deleted TIMESTAMP
);


ALTER TABLE Event ADD CONSTRAINT Event_pkey PRIMARY KEY (id_Event);

CREATE TABLE Ticket (
 id_ticket BIGSERIAL,
 id_user INTEGER,
 id_Event INTEGER,
 value INTEGER,
 quantity INTEGER
);


ALTER TABLE Ticket ADD CONSTRAINT Ticket_pkey PRIMARY KEY (id_ticket);

CREATE TABLE Attendance (
 id_Event INTEGER,
 id_user INTEGER,
 status VARCHAR(100)
);


ALTER TABLE Attendance ADD CONSTRAINT Attendance_pkey PRIMARY KEY (id_Event);

ALTER TABLE Event ADD CONSTRAINT Event_id_user_fkey FOREIGN KEY (id_user) REFERENCES users(id_user);
ALTER TABLE Ticket ADD CONSTRAINT Ticket_id_user_fkey FOREIGN KEY (id_user) REFERENCES users(id_user);
ALTER TABLE Ticket ADD CONSTRAINT Ticket_id_Event_fkey FOREIGN KEY (id_Event) REFERENCES Event(id_Event);
ALTER TABLE Attendance ADD CONSTRAINT Attendance_id_Event_fkey FOREIGN KEY (id_Event) REFERENCES Event(id_Event);
ALTER TABLE Attendance ADD CONSTRAINT Attendance_id_user_fkey FOREIGN KEY (id_user) REFERENCES users(id_user);