package com.example.springboot.model;

import javax.persistence.*;

//CREATE TABLE simple (
//        id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
//        name varchar(255)
//        );


@Entity
public class Simple {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="NAME", length=50, nullable=false, unique=false)
    private String name;

    public Simple() {
    }

    public Simple(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Simple{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
