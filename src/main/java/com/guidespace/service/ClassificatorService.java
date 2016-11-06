package com.guidespace.service;

import com.guidespace.domain.Classificator;
import com.guidespace.repository.ClassificatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vlad on 02.11.2016.
 */
@Service
public class ClassificatorService {

    @Autowired
    private ClassificatorRepository classificatorRepository;

    public void addClassificator(Classificator c){classificatorRepository.save(c);}

    public List<Classificator> getClassificators(){return classificatorRepository.findAll();}

    public Classificator getClassifById(Long id){return classificatorRepository.findOne(id);}
}
