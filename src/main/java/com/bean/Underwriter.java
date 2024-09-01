package com.bean;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Underwriter {
    private int underwriterId;
    private String name;
    private LocalDate dob;
    private LocalDate joiningDate;
    private String password;

    // Default constructor
    public Underwriter() {}

    // Parameterized constructor
    public Underwriter(int underwriterId, String name, LocalDate dob, LocalDate joiningDate, String password) {
        this.underwriterId = underwriterId;
        this.name = name;
        this.dob = dob;
        this.joiningDate = joiningDate;
        this.password = password;
    }
    
    public Underwriter(String name, LocalDate dob, LocalDate joiningDate, String password) {
        this.name = name;
        this.dob = dob;
        this.joiningDate = joiningDate;
        this.password = password;
    }

    // Getters and setters
    public int getUnderwriterId() {
        return underwriterId;
    }

    public void setUnderwriterId(int underwriterId) {
        this.underwriterId = underwriterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString() method
    @Override
    public String toString() {
        return "Underwriter{" +
                "underwriterId=" + underwriterId +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", joiningDate=" + joiningDate +
                ", password='[PROTECTED]'" +
                '}';
    }

    // equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Underwriter that = (Underwriter) o;
        return underwriterId == that.underwriterId &&
                Objects.equals(name, that.name) &&
                Objects.equals(dob, that.dob) &&
                Objects.equals(joiningDate, that.joiningDate);
    }

    // hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(underwriterId, name, dob, joiningDate);
    }
}