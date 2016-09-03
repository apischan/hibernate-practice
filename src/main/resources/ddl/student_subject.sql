CREATE TABLE student_subject
(
  id SERIAL PRIMARY KEY,
  student_id INTEGER,
  subject_id INTEGER,
  year DATE,
  teacher_id INTEGER
);
