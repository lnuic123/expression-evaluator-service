package com.exercise.expressionevaluator.dao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "EXPRESSIONS")
@SequenceGenerator(name = "expressionsSeq", sequenceName = "EXPRESSIONS_SEQ", allocationSize = 1)
public class Expression {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expressionsSeq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EXP_VALUE")
    private String expValue;

    @Column(name = "UUID")
    private String uuid;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpValue() {
        return expValue;
    }

    public void setExpValue(String expValue) {
        this.expValue = expValue;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
