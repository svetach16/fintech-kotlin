create table StudentInfo
(
    student_id int primary key auto_increment,
    faculty    text,
    height     int,
    weight     int
);

INSERT INTO StudentInfo(student_id, faculty, height, weight)
VALUES (1, 'Medical biophysics', 170, 60);