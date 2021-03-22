CREATE TABLE Student
(
    id       INTEGER PRIMARY KEY,
    name     VARCHAR(40),
    age      INTEGER,
    group_id INTEGER
);

CREATE TABLE Teacher
(
    id                  INTEGER PRIMARY KEY,
    name                VARCHAR(40),
    subject             VARCHAR(40),
    years_of_experience INTEGER
);

CREATE TABLE StudentGroup
(
    id            INTEGER PRIMARY KEY,
    course_number INTEGER,
    department    VARCHAR(30)
);

CREATE TABLE TeacherGroups
(
    id         INTEGER,
    group_id   INTEGER
);
