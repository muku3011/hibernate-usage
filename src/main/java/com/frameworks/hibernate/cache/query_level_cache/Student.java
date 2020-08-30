package com.frameworks.hibernate.cache.query_level_cache;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "A_STUDENT")
public class Student implements Serializable, Cloneable {

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

    public Student() {

    }

    public Student clone() {
        return new Student(this);
    }

    private Student(Student student) {
        this.userId = student.userId;
        this.username = student.username;
        this.createdBy = student.createdBy;
        this.createdDate = student.createdDate;
    }

    public String toString() {
        return "====================================\n"
                + "Student :" + "\n"
                + "Name: '" + getUsername() + "'\n"
                + "Created on: '" + getCreatedDate() + "'\n";
    }
}
