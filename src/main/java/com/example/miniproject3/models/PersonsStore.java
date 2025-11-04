package com.example.miniproject3.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonsStore {
    private final ObservableList<Person> persons =
            FXCollections.observableArrayList();

    public PersonsStore() {
        this.persons.addAll(
                new Person("John", 23, "NY", 3.0, "Male", "usek", "123-456-7890"),
                new Person("Sara", 32, "LA", 5.0, "Female", "usek", "234-567-8901"),
                new Person("Tom", 22, "CA", 5.0, "Male", "usek", "345-678-9012")
        );
    }

    public ObservableList<Person> getPersonsList() {
        return persons;
    }

    public void addPerson(Person person) {
        if (person != null)
            this.persons.add(person);
    }

    public void deletePerson(Person person){
        if(person != null)
            this.persons.remove(person);
    }

    public void updatePerson(Person person, String name, Integer age, String address,
                             Double staisfactionScore, String gender, String major, String phoneNumber)
    {
        if(person != null){
            person.setName(name);
            person.setAge(age);
            person.setAddress(address);
            person.setGpa(staisfactionScore);
            person.setGender(gender);
            person.setMajor(major);
            person.setPhoneNumber(phoneNumber);
        }
    }
}