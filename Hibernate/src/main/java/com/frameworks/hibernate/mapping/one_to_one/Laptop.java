package com.frameworks.hibernate.mapping.one_to_one;

import javax.persistence.*;

@Entity
@Table(name = "A_LAPTOP")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int serialNumber;

    @Column
    private String model;

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
}
