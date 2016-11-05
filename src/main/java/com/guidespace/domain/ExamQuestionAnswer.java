package com.guidespace.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by ALLI on 02.11.2016.
 */
@Entity
public class ExamQuestionAnswer extends BaseEntity{

    @NotNull
    @Column(name = "IS_FALSE")
    private Boolean is_false;

    @NotNull
    @Column(name = "ANSWER")
    private String answer;

    @Override
    public String toString() {
        return " "+answer+" ";
    }

    @ManyToOne
    @JoinColumn(name = "EXAM_QUESTION_ID")
    private ExamQuestion examQuestion;

    public ExamQuestionAnswer(){}

    public ExamQuestionAnswer(Boolean b, String answer){
        this.is_false = b;
        this.answer = answer;
    }

    public ExamQuestionAnswer(Boolean b, String answer, ExamQuestion examQuestion){
        this.is_false = b;
        this.answer = answer;
        this.examQuestion = examQuestion;
    }

    public Boolean getIs_false() {
        return is_false;
    }

    public void setIs_false(Boolean is_false) {
        this.is_false = is_false;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ExamQuestion getExamQuestion() {
        return examQuestion;
    }

    public void setExamQuestion(ExamQuestion examQuestion) {
        this.examQuestion = examQuestion;
    }
}
