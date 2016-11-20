package com.guidespace.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vlad on 02.11.2016.
 */

@Entity
public class Examination extends BaseEntity{

    @Column(name = "START_DATE")
    private Date start_date;

    @Column(name = "END_DATE")
    private Date end_date;

    @Column(name = "PARTICIPANTS_AMOUNT")
    private Integer participants_amount = 0; //when exam is just created

    @OneToMany
    @JoinColumn(name = "EXAM_RESULT_ID")
    private List<ExamResult> results = new ArrayList<>();

    @Column(name = "CLASSIF_ID")
    private Long classif_id;

    public Examination(){}

    //date format "dd-MM-yyyy HH:mm"
    private static final DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    public Examination(String start, String end) throws ParseException{
        this.start_date = df.parse(start);
        this.end_date = df.parse(end);
    }

    public Examination(String start) throws ParseException{
        this.start_date = df.parse(start);
    }

    public Examination(Date start, Date end){
        this.start_date = start;
        this.end_date = end;
    }

    public Examination(Date start){
        this.start_date = start;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setStart_date(String start_date) throws ParseException {this.start_date = df.parse(start_date);}

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setEnd_date(String end_date) throws ParseException {this.end_date = df.parse(end_date);}

    public Integer getParticipants_amount() {
        return participants_amount;
    }

    public void setParticipants_amount(Integer participants_amount) {
        this.participants_amount = participants_amount;
    }

    public List<ExamResult> getResults() {
        return results;
    }

    public void setResults(List<ExamResult> results) {
        this.results = results;
    }

    public Long getClassif_id() {
        return classif_id;
    }

    public void setClassif_id(Long classif_id) {
        this.classif_id = classif_id;
    }
}
