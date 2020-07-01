package com.mimu.springboot.demo.model;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private Integer id;

    private Integer personId;

    private String personName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", personId=" + personId +
                ", personName='" + personName + '\'' +
                '}';
    }
}