package com.guidespace.repository;

import com.guidespace.domain.ExamQuestionAnswer;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Vitali on 11/1/2016.
 */
public interface ExamQuestionAnswerRepository extends Repository<ExamQuestionAnswer, Long> {

    ExamQuestionAnswer save(ExamQuestionAnswer answer);

    List<ExamQuestionAnswer> findAll();

    ExamQuestionAnswer findById(Long id);

    void delete(ExamQuestionAnswer examination);
}
