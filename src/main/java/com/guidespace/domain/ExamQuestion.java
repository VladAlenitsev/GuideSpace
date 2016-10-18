package com.guidespace.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by Vitali on 10/10/2016.
 */

@Entity
public class ExamQuestion extends BaseEntity {

    @NotNull
    @Column(name = "QUESTION")
    private String question;

    @NotNull
    @Column(name = "QUESTION_CLASSIF_ID")
    private Integer question_classif_id;


    public String getQuestion() {
        return question;
    }

    public Integer getQuestion_classif_id() {
        return question_classif_id;
    }
}
