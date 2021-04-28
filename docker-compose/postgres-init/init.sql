create table Student
(
    id      int primary key,
    name    text,
    surname text
);

INSERT INTO Student(id, name, surname)
VALUES (1, 'Vasya', 'Pupkin');

INSERT INTO Student(id, name, surname)
VALUES (2, 'Petya', 'Ivanov');