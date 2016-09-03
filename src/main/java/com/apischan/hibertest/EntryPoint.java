package com.apischan.hibertest;

import com.apischan.hibertest.dto.SubjectCount;
import com.apischan.hibertest.entity.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.transform.Transformers;

import java.util.List;

public class EntryPoint {

    public static void main(String[] args) {

        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder();
        registry.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = registry.build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

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

        System.out.println(lst);

    }

}
