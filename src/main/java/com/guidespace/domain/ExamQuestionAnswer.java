package com.guidespace.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by Vitali on 01.11.2016.
 */
@Entity
public class ExamQuestionAnswer extends BaseEntity {

    @NotNull
    @Column(name = "ANSWER_ID")
    private Integer answer_id;

    @NotNull
    @Column(name = "QUESTION_ID")
    private Integer question_id;

    @NotNull
    @Column(name = "IS_FALSE")
    private Boolean is_false;

    @NotNull
    @Column(name = "ANSWER")
    private String answer;

    public ExamQuestionAnswer(){}

    public Integer getAnswer_id() {
        return answer_id;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public Boolean getIs_false() {
        return is_false;
    }

    public String getAnswer() {
        return answer;
    }
}
