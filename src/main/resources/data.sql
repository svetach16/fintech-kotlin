INSERT INTO Student (id, name, age, group_id)
VALUES (1, 'Kiselev Vladimir', 20, 240),
       (2, 'Klimenko Ekaterina', 21, 240),
       (3, 'Prokofeva Alina', 20, 240),
       (4, 'Lusanova Olga', 25, 551),
       (5, 'Clesareva Margarita', 23, 551),
       (6, 'Ponomarev Sergey', 22, 551),
       (7, 'Lovchikova Natalia', 23, 551),
       (8, 'Astshov Mikhail', 19, 322),
       (9, 'Pavlova Maria', 20, 322),
       (10, 'Churilova Daria', 22, 520),
       (11, 'Filippova Arina', 23, 520),
       (12, 'Kovalev Vladislav', 22, 520),
       (13, 'Mochenko Sofia', 25, 602),
       (14, 'Nazarov Oleg', 24, 602),
       (15, 'Martianov Alexandr', 24, 602);

INSERT INTO StudentGroup (id, course_number, department)
VALUES (240, 2, 'Pediatrics'),
       (551, 5, 'Medical biophysics'),
       (520, 5, 'Clinical Psychology'),
       (322, 3, 'Clinical Psychology'),
       (602, 6, 'Pediatrics');

INSERT INTO Teacher
VALUES (1, 'Chasnik Viacheslav Grigorievich', 'Pediatrics', 14),
       (2, 'Kulikova Anna Vladimirovna', 'Physics', 4),
       (3, 'Komissarova Elena Nikolaevna', 'Human anatomy', 23),
       (4, 'Sharf Olga Yakovlevna', 'Histology', 20),
       (5, 'Vasiliev Andrey Glebovich', 'Pathological physiology', 15),
       (6, 'Brzheskiy Vladimir Vsevolodovich', 'Ophthalmology', 7),
       (7, 'Yevgeny Naumovich Imyanitov', 'General and molecular medical genetics', 18),
       (8, 'Gorkovaya Irina Alekseevna', 'Psychosomatics and psychotherapy', 30);

INSERT INTO TeacherGroups (id, group_id)
VALUES (8, 551),
       (5, 551),
       (7, 551),
       (2, 240),
       (3, 240),
       (4, 240),
       (6, 520),
       (7, 520),
       (8, 520),
       (1, 322),
       (6, 322),
       (5, 322),
       (8, 602),
       (7, 602);
