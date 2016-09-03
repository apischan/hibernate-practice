package com.apischan.hibertest.dto;

public class SubjectCount {

    private String subject;

    private Long count;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "SubjectCount{\n" +
                "subject='" + subject + "\'\n" +
                ", count=" + count + "\n" +
                "}\n";
    }
}
