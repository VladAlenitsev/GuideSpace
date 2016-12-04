package com.guidespace.service;

import com.guidespace.domain.ExamQuestion;
import com.guidespace.domain.ExamQuestionAnswer;
import com.guidespace.repository.ExamQuestionAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vlad on 02.11.2016.
 */
@Service
public class ExamQuestionAnswerService {

    @Autowired
    private ExamQuestionAnswerRepository examQuestionAnswerRepository;

    public void addQuestionAnswer(ExamQuestionAnswer answer){

        examQuestionAnswerRepository.save(answer);
    }

    public List<ExamQuestionAnswer>  getAllAnswers(){return examQuestionAnswerRepository.findAll();}

    public ExamQuestionAnswer getAnswerById(Long id){return examQuestionAnswerRepository.findById(id);}

    public void deleteQuestionAnswer(ExamQuestionAnswer answer){ examQuestionAnswerRepository.delete(answer);}
}
