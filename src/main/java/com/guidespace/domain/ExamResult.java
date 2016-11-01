package com.guidespace.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Vitali on 01.11.2016.
 */

@Entity
public class ExamResult extends BaseEntity {

    @NotNull
    @Column(name = "EXAM_RESULT_ID")
    private Integer exam_result_id;


    @NotNull
    @Column(name = "PERSON_ID")
    private Integer person_id;

    @NotNull
    @Column(name = "EXAM_ID")
    private Integer exam_id;

    @NotNull
    @Column(name = "RESULT")
    private Boolean result;

    @NotNull
    @Column(name = "SCORE")
    private Integer score;

    public ExamResult(Boolean result, Integer score){
        this.setCreatedAt(new Date(Calendar.getInstance().getTime().getTime()));
        this.result = result;
        this.score = score;
    }

    public ExamResult(){}

    public Integer getExam_result_id() {
        return exam_result_id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public Integer getExam_id() {
        return exam_id;
    }

    public Boolean getResult() {
        return result;
    }

    public Integer getScore() {
        return score;
    }
}
