package com.guidespace.service;

import com.guidespace.domain.ExamQuestion;
import com.guidespace.repository.ExamQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 18.10.2016.
 */
@Service
public class ExamQuestionService {

    @Autowired
    private ExamQuestionRepository examQuestionRepository;

    public void addQuestion(ExamQuestion question){examQuestionRepository.save(question);}

    public List<ExamQuestion> getQuestions() {
        return examQuestionRepository.findAll();
    }

    public ExamQuestion getQuestion(String question) {
        return examQuestionRepository.findByQuestion(question).get(0);
    }

    public void deleteQuestion(ExamQuestion e){examQuestionRepository.delete(e);}

    public ExamQuestion getQuestionById(Long id) {return examQuestionRepository.findById(id);}

    public List<ExamQuestion> getQuestionsByClassificatorId(Long id){
        ArrayList<ExamQuestion> result = new ArrayList<>();
        for(ExamQuestion eq: examQuestionRepository.findAll()){
            if (eq.getClassificator().getId() == id){
                result.add(eq);
            }
        }
        return result;
    }
}

