package com.guidespace.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by Vitali on 10/18/2016.
 */
@Entity
public class Classificator extends BaseEntity {
    @NotNull
    @Column(name = "CLASSIF_TYPE", unique = true)
    private String classif_type;

    @NotNull
    @Column(name = "CLASSIF_CODE", unique = true)
    private String classif_code;

    @NotNull
    @Column(name = "CLASSIF_NAME", unique = true)
    private String classif_name;

    public String getClassif_type() {
        return classif_type;
    }

    public String getClassif_code() {
        return classif_code;
    }

    public String getClassif_name() {
        return classif_name;
    }

    public String getDate_created() {
        return date_created;
    }

    @NotNull
    @Column(name = "DATE_CREATED", unique = true)
    private String date_created;

}
