package com.frameworks.hibernate.mapping.one_to_many;

import javax.persistence.*;

@Entity
@Table(name = "A_LAPTOP")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int serialNumber;

    @Column
    private String model;

    @ManyToOne
    private Student student;

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "serialNumber=" + serialNumber +
                ", model='" + model + '\'' +
                ", student=" + student +
                '}';
    }
}
