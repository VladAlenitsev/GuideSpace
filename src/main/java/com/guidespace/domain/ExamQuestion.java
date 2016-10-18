package com.guidespace.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Vitali on 10/10/2016.
 */

@Entity
public class ExamQuestion extends BaseEntity {

    @NotNull
    @Column(name = "QUESTION")
    private String question;

    @Column(name = "QUESTION_CLASSIF_ID")
    private Integer question_classif_id;

    public ExamQuestion(String question){
        this.setCreatedAt(new Date(Calendar.getInstance().getTime().getTime()));
        this.question = question;

    }

    public String getQuestion() {
        return question;
    }

    public Integer getQuestion_classif_id() {
        return question_classif_id;
    }
}
