package com.guidespace.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by Vitali on 10/10/2016.
 */

@Entity
public class ExamQuestion extends BaseEntity {

    @NotNull
    @Column(name = "QUESTION")
    private String question;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "EXAM_QUESTION_ANSWER_ID")
    private List<ExamQuestionAnswer> answers = new ArrayList<ExamQuestionAnswer>();

    @ManyToOne
    @JoinColumn(name = "CLASSIFICATOR_ID")
    private Classificator classificator;

    public ExamQuestion(String question){
        this.question = question;
    }

    public ExamQuestion(String question, List<ExamQuestionAnswer> answers){
        this.question = question;
        this.answers = answers;
    }

    public ExamQuestion(String question, List<ExamQuestionAnswer> answers, Classificator classificator){
        this.question = question;
        this.answers = answers;
        this.classificator = classificator;
    }

    public ExamQuestion(){}

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public List<ExamQuestionAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ExamQuestionAnswer> answers) {
        this.answers = answers;
    }

    public Classificator getClassificator() {
        return classificator;
    }

    public void setClassificator(Classificator classificator) {
        this.classificator = classificator;
    }
}
