SELECT ts.subject_name, ts.subj_count
FROM (SELECT
        subj.id,
        subj.subject_name,
        count(ss.subject_id) as subj_count
      FROM subject subj
        INNER JOIN student_subject ss
          ON subj.id = ss.subject_id
      GROUP BY subj.id, subj.subject_name
      HAVING count(ss.subject_id) > 2) ts
INNER JOIN student_subject ss2
  ON ts.id = ss2.subject_id
INNER JOIN student stud
  ON stud.id = ss2.student_id
WHERE stud.name = 'Bill'
GROUP BY ts.subject_name, ts.subj_count;


SELECT
  subj.subject_name,
  count(ss.subject_id) AS subj_count
FROM subject subj
  INNER JOIN student_subject ss
    ON subj.id = ss.subject_id
  INNER JOIN (SELECT stud_subj.subject_id
              FROM student_subject stud_subj
                INNER JOIN student st
                  ON stud_subj.student_id = st.id
              WHERE st.name = 'Bill'
              GROUP BY stud_subj.subject_id) ss2
    ON ss2.subject_id = ss.subject_id
GROUP BY subj.id, subj.subject_name
HAVING count(ss.subject_id) > 2;



SELECT stud_subj.subject_id
FROM student_subject stud_subj
INNER JOIN student st
  ON stud_subj.student_id = st.id
WHERE st.name = 'Bill'
GROUP BY stud_subj.subject_id;


SELECT * FROM student;

SELECT * FROM student_subject;