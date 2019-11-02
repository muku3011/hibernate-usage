package com.frameworks.hibernate.mapping.many_to_many;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "A_LAPTOP")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int serialNumber;

    @Column
    private String model;

    @ManyToMany
    private List<Student> students = new ArrayList<>();

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "serialNumber=" + serialNumber +
                ", model='" + model + '\'' +
                ", students=" + students +
                '}';
    }
}
