package com.apischan.hibertest;

import com.apischan.hibertest.dto.SubjectCount;
import com.apischan.hibertest.entity.Subject;
import com.apischan.hibertest.service.SqlHibernateService;
import com.apischan.hibertest.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import java.util.List;

public class EntryPoint {

    public static void main(String[] args) {

        HibernateUtils hibernateUtils = new HibernateUtils();
        Session session = hibernateUtils.createSession();

        try {
            List list = session.createCriteria(Subject.class)
                    .createCriteria("studentSubjects", "studSubj")
                    .createCriteria("student", "stud")
                    .add(Restrictions.eq("stud.name", "John"))
                    .list();

            List lst = session.createCriteria(Subject.class, "subj")
                    .createCriteria("studentSubjects", "studSubj")
                    .setProjection(Projections.projectionList()
                            .add(Projections.property("subj.subjectName"), "subject")
                            .add(Projections.count("studSubj.subject"), "count")
                            .add(Projections.groupProperty("subj.id"))
                            .add(Projections.groupProperty("subj.subjectName"))
                    )
                    .setResultTransformer(Transformers.aliasToBean(SubjectCount.class))
                    .list();

            SqlHibernateService sqlHibernateService = new SqlHibernateService(session);

            System.out.println(sqlHibernateService.getStatistics("Bill"));
        } finally {
            hibernateUtils.closeSession();
            hibernateUtils.closeSessionFactory();
        }

    }

}
