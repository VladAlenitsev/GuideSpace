package com.guidespace.repository;

import com.guidespace.domain.ExamResult;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Vitali on 11/1/2016.
 */
public interface ExamResultRepository extends Repository<ExamResult, Long> {

    ExamResult save(ExamResult result);

    List<ExamResult> findAll();
}
