package com.guidespace.repository;

import com.guidespace.domain.Classificator;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Vlad on 02.11.2016.
 */
public interface ClassificatorRepository extends Repository<Classificator, Long> {

    Classificator save(Classificator classificator);

    List<Classificator> findAll();

    Classificator findOne(Long id);
}
