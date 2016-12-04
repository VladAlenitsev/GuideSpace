package com.guidespace.service;

import com.guidespace.domain.ExamResult;
import com.guidespace.domain.Person;
import com.guidespace.repository.ExamResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vlad on 02.11.2016.
 */
@Service
public class ExamResultService {

    @Autowired
    private ExamResultRepository examResultRepository;

    public void addExamResult(ExamResult result){examResultRepository.save(result);}

    public List<ExamResult> getExamResults(){return examResultRepository.findAll();}

    public ExamResult getById(Long id){return examResultRepository.findById(id);}

    public void save(ExamResult result){examResultRepository.save(result);}

    public boolean isNotDone(Person p){
        for(ExamResult ex : getExamResults()){
            if(ex.getPerson().equals(p)){
                return false;
            }
        }
        return true;
    }
}
