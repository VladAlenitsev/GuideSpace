package com.guidespace.service;

import com.guidespace.domain.Examination;
import com.guidespace.repository.ExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vlad on 02.11.2016.
 */
@Service
public class ExaminationService {

    @Autowired
    private ExaminationRepository examinationRepository;

    public void addExamination(Examination e){examinationRepository.save(e);}

    public List<Examination> getExaminations(){return examinationRepository.findAll();}
}
