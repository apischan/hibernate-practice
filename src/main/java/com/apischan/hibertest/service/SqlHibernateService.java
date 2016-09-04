package com.apischan.hibertest.service;

import com.apischan.hibertest.dto.SubjectCount;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import java.util.List;

public class SqlHibernateService {
    private static final String SQL_QUERY = "SELECT\n" +
            "  subj.subject_name as subject,\n" +
            "  count(ss.subject_id) AS count\n" +
            "FROM subject subj\n" +
            "  INNER JOIN student_subject ss\n" +
            "    ON subj.id = ss.subject_id\n" +
            "  INNER JOIN (SELECT stud_subj.subject_id\n" +
            "              FROM student_subject stud_subj\n" +
            "                INNER JOIN student st\n" +
            "                  ON stud_subj.student_id = st.id and st.name = :name\n" +
            "              GROUP BY stud_subj.subject_id) ss2\n" +
            "    ON ss2.subject_id = ss.subject_id\n" +
            "GROUP BY subj.id, subj.subject_name\n" +
            "HAVING count(ss.subject_id) > 2\n";

    private Session session;

    public SqlHibernateService(Session session) {
        this.session = session;
    }

    public List getStatistics(String studentName) {
        Query query = session.createSQLQuery(SQL_QUERY)
                .addScalar("subject", StandardBasicTypes.STRING)
                .addScalar("count", StandardBasicTypes.LONG)
                .setResultTransformer(Transformers.aliasToBean(SubjectCount.class))
                .setString("name", studentName);

        return query.list();
    }

}
