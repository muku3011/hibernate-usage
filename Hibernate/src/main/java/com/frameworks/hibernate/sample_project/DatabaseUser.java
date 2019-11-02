package com.frameworks.hibernate.sample_project;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "A_DATABASEUSER")
public class DatabaseUser implements Serializable, Cloneable {

    @Id
    @Basic //Denotes that this column is to used for persistence
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
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

    public DatabaseUser() {

    }

    public DatabaseUser clone() {
        return new DatabaseUser(this);
    }

    private DatabaseUser(DatabaseUser databaseUser) {
        this.userId = databaseUser.userId;
        this.username = databaseUser.username;
        this.createdBy = databaseUser.createdBy;
        this.createdDate = databaseUser.createdDate;
    }

    public String toString() {
        return "====================================\n"
                + "Database user:" + "\n"
                + "User: '" + getUsername() + "'\n"
                + "Created on: '" + getCreatedDate() + "'\n";
    }
}
