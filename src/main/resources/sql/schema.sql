
DROP TABLE IF EXISTS Attendance;
DROP TABLE IF EXISTS Ticket;
DROP TABLE IF EXISTS Event;
DROP TABLE IF EXISTS "User";
DROP TABLE IF EXISTS Rol;



CREATE TABLE "User" (
 id_user BIGSERIAL,
 name VARCHAR(100),
 last_name VARCHAR(100),
 email VARCHAR(255) UNIQUE NOT NULL,
 password VARCHAR(255) NOT NULL,
 id_rol INTEGER
);


ALTER TABLE "User" ADD CONSTRAINT User_pkey PRIMARY KEY (id_user);

CREATE TABLE Event (
 id_Event BIGSERIAL,
 name VARCHAR(100),
 date_start TIMESTAMP,
 date_end TIMESTAMP,
 localidad VARCHAR(255),
 id_user INTEGER,
 status INTEGER,
 ticket_limit INTEGER,
 tickets_sold INTEGER
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

CREATE TABLE Rol (
 id_rol BIGSERIAL,
 Type INTEGER
);


ALTER TABLE Rol ADD CONSTRAINT Rol_pkey PRIMARY KEY (id_rol);

ALTER TABLE "User" ADD CONSTRAINT User_id_rol_fkey FOREIGN KEY (id_rol) REFERENCES Rol(id_rol);
ALTER TABLE Event ADD CONSTRAINT Event_id_user_fkey FOREIGN KEY (id_user) REFERENCES "User"(id_user);
ALTER TABLE Ticket ADD CONSTRAINT Ticket_id_user_fkey FOREIGN KEY (id_user) REFERENCES "User"(id_user);
ALTER TABLE Ticket ADD CONSTRAINT Ticket_id_Event_fkey FOREIGN KEY (id_Event) REFERENCES Event(id_Event);
ALTER TABLE Attendance ADD CONSTRAINT Attendance_id_Event_fkey FOREIGN KEY (id_Event) REFERENCES Event(id_Event);
ALTER TABLE Attendance ADD CONSTRAINT Attendance_id_user_fkey FOREIGN KEY (id_user) REFERENCES "User"(id_user);