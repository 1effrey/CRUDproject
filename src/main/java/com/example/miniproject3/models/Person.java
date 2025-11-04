package com.example.miniproject3.models;

import javafx.beans.property.*;

public class Person {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty age;
    private final SimpleStringProperty address;
    private final SimpleDoubleProperty satisfactionScore;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty company;
    private final SimpleStringProperty phoneNumber;

    public Person(String name, Integer age, String address, Double satisfactionScore, String gender, String company, String phoneNumber) {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.address = new SimpleStringProperty(address);
        this.satisfactionScore = new SimpleDoubleProperty(satisfactionScore);
        this.gender = new SimpleStringProperty(gender);
        this.company = new SimpleStringProperty(company);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    // Name property methods
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return this.name;
    }

    // Age property methods
    public int getAge() {
        return age.get();
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public IntegerProperty ageProperty() {
        return this.age;
    }

    // Address property methods
    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty addressProperty() {
        return this.address;
    }

    // GPA property methods
    public double getSatisfactionScore() {
        return satisfactionScore.get();
    }

    public void setGpa(double gpa) {
        this.satisfactionScore.set(gpa);
    }

    public DoubleProperty satisfactionScoreProperty() {
        return this.satisfactionScore;
    }

    // Gender property methods
    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public StringProperty genderProperty() {
        return this.gender;
    }

    // Major property methods
    public String getMajor() {
        return company.get();
    }

    public void setMajor(String major) {
        this.company.set(major);
    }

    public StringProperty companyProperty() {
        return this.company;
    }

    // Phone Number property methods
    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public StringProperty phoneNumberProperty() {
        return this.phoneNumber;
    }
}