package com.apischan.hibertest.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "subject_name")
    private String subjectName;

    @OneToMany(mappedBy = "subject")
    private Set<StudentSubject> studentSubjects = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Set<StudentSubject> getStudentSubjects() {
        return studentSubjects;
    }

    public void setStudentSubjects(Set<StudentSubject> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }

    //---------------- utils -----------------
    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                ", studentSubjects=" + studentSubjects +
                '}';
    }
}
