package com.guidespace.repository;

import com.guidespace.domain.ExamQuestionAnswer;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Vlad on 18.10.2016.
 */
public interface ExamQuestionAnswerRepository extends Repository<ExamQuestionAnswer, Long> {

    ExamQuestionAnswer save(ExamQuestionAnswer question);

    List<ExamQuestionAnswer> findAll();
}
