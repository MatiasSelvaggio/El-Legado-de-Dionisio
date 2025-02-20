
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
 id_event BIGSERIAL,
 name VARCHAR(100),
 date_start TIMESTAMP,
 date_end TIMESTAMP,
 place VARCHAR(255),
 id_user INTEGER,
 status VARCHAR(255),
 ticket_limit INTEGER,
 tickets_sold INTEGER,
 ticket_price FLOAT,
 created TIMESTAMP NOT NULL,
 deleted TIMESTAMP
);


ALTER TABLE Event ADD CONSTRAINT Event_pkey PRIMARY KEY (id_event);

CREATE TABLE Ticket (
 id_ticket BIGSERIAL NOT NULL,
 id_user INTEGER NOT NULL,
 id_event INTEGER NOT NULL,
 value FLOAT NOT NULL,
 code VARCHAR(255) NOT NULL,
 quantity INTEGER NOT NULL
);


ALTER TABLE Ticket ADD CONSTRAINT Ticket_pkey PRIMARY KEY (id_ticket);

CREATE TABLE Attendance (
 id_event INTEGER,
 id_user INTEGER,
 status VARCHAR(100),
 PRIMARY KEY (id_event, id_user)
);


ALTER TABLE Event ADD CONSTRAINT Event_id_user_fkey FOREIGN KEY (id_user) REFERENCES users(id_user);
ALTER TABLE Ticket ADD CONSTRAINT Ticket_id_user_fkey FOREIGN KEY (id_user) REFERENCES users(id_user);
ALTER TABLE Ticket ADD CONSTRAINT Ticket_id_Event_fkey FOREIGN KEY (id_event) REFERENCES Event(id_event);
ALTER TABLE Attendance ADD CONSTRAINT Attendance_id_Event_fkey FOREIGN KEY (id_event) REFERENCES Event(id_event);
ALTER TABLE Attendance ADD CONSTRAINT Attendance_id_user_fkey FOREIGN KEY (id_user) REFERENCES users(id_user);