package com.guidespace.repository;

import com.guidespace.domain.ExamQuestion;
import org.springframework.data.repository.Repository;

/**
 * Created by ALLI on 18.10.2016.
 */
public interface ExamQuestionRepository extends Repository<ExamQuestion, Long> {

    ExamQuestion save(ExamQuestion question);
}
