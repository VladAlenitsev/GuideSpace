package com.guidespace.repository;

import com.guidespace.domain.ExamResult;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Vlad on 02.11.2016.
 */
public interface ExamResultRepository extends Repository<ExamResult, Long>{

    ExamResult save(ExamResult result);

    List<ExamResult> findAll();

    ExamResult findById(Long id);
}
