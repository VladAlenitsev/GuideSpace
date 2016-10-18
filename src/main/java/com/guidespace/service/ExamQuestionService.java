package com.guidespace.service;

import com.guidespace.domain.ExamQuestion;
import com.guidespace.repository.ExamQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Vlad on 18.10.2016.
 */
@Service
public class ExamQuestionService {

    @Autowired
    private ExamQuestionRepository ExamQuestionRepository;

    public void addQuestion(ExamQuestion question){
        ExamQuestionRepository.save(question);
    }
}
