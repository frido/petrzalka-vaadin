package com.example.springboot.model;

import javax.persistence.*;

@Entity(name = "validable_entity")
public class ValidableEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 3)
    private String length;

    @Column(insertable = false)
    private String notInsertable;

    @Column(nullable = false)
    private String notNulable;

    @Column(unique = true)
    private String isUnique;

    @Column(updatable = false)
    private String notUpdatable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getNotInsertable() {
        return notInsertable;
    }

    public void setNotInsertable(String notInsertable) {
        this.notInsertable = notInsertable;
    }

    public String getNotNulable() {
        return notNulable;
    }

    public void setNotNulable(String notNulable) {
        this.notNulable = notNulable;
    }

    public String getIsUnique() {
        return isUnique;
    }

    public void setIsUnique(String isUnique) {
        this.isUnique = isUnique;
    }

    public String getNotUpdatable() {
        return notUpdatable;
    }

    public void setNotUpdatable(String notUpdatable) {
        this.notUpdatable = notUpdatable;
    }
}
