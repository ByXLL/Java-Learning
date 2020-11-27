package com.transform_student;

public class Student {
    private String name;
    private int id;
    private String clas;

    public Student() {
    }

    public Student(String name, int id, String clas) {
        this.name = name;
        this.id = id;
        this.clas = clas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }
}
