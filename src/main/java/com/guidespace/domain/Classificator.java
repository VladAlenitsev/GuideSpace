package com.guidespace.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by Vitali on 10/18/2016.
 */
@Entity
public class Classificator extends BaseEntity {

    @Column(name = "CLASSIF_TYPE")
    private String classif_type;

    @Column(name = "CLASSIF_CODE")
    private String classif_code;

    @Column(name = "CLASSIF_NAME", unique = true)
    private String classif_name;

    public Classificator(){}

    public Classificator(String type, String code, String name){
        this.classif_type = type;
        this.classif_code = code;
        this.classif_name = name;
    }

    public String getClassif_type() {
        return classif_type;
    }

    public void setClassif_type(String classif_type) {
        this.classif_type = classif_type;
    }

    public String getClassif_code() {
        return classif_code;
    }

    public void setClassif_code(String classif_code) {
        this.classif_code = classif_code;
    }

    public String getClassif_name() {
        return classif_name;
    }

    public void setClassif_name(String classif_name) {
        this.classif_name = classif_name;
    }
}
