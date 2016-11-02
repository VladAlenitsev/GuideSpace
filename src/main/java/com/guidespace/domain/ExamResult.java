package com.guidespace.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by Vlad on 02.11.2016.
 */

@Entity
public class ExamResult extends BaseEntity{

    @NotNull
    @Column(name = "PASSED")
    private Boolean passed;

    @NotNull
    @Column(name = "SCORE")
    private Integer score; //0 to 100 (%)

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "EXAMINATION_ID")
    private Examination examination;

    public ExamResult(){}

    public ExamResult(Boolean b, Integer s){
        this.passed = b;
        this.score = s;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Examination getExamination() {
        return examination;
    }

    public void setExamination(Examination examination) {
        this.examination = examination;
    }
}
