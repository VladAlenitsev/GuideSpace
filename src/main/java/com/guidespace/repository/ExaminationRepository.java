package com.guidespace.repository;

import com.guidespace.domain.Examination;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Vlad on 02.11.2016.
 */
public interface ExaminationRepository extends Repository<Examination, Long>{

    Examination save(Examination examination);

    List<Examination> findAll();

    Examination findById(Long id);

    void delete(Examination examination);
}
