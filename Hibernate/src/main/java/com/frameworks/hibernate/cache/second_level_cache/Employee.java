package com.frameworks.hibernate.cache.second_level_cache;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "AN_EMPLOYEE")
public class Employee implements Serializable, Cloneable {

    @Id
    @Column(name = "USER_ID", nullable = false, precision = 5, scale = 0)
    private Integer userId;

    @Column(name = "USERNAME", length = 20, nullable = false)
    private String username;

    @Column(name = "CREATED_BY", length = 20, nullable = false)
    private String createdBy;

    @Column(name = "CREATED_DATE", length = 7, nullable = false)
    private Date createdDate;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Employee() {

    }

    public Employee clone() {
        return new Employee(this);
    }

    private Employee(Employee employee) {
        this.userId = employee.userId;
        this.username = employee.username;
        this.createdBy = employee.createdBy;
        this.createdDate = employee.createdDate;
    }

    public String toString() {
        return "====================================\n"
                + "Employee :" + "\n"
                + "Name: '" + getUsername() + "'\n"
                + "Created on: '" + getCreatedDate() + "'\n";
    }
}
