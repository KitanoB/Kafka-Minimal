package com.kitano.kafka.dto;

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person() {

    }

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String msg) {
        this.firstName = msg;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return     "{firstName:\""+firstName+"\", lastName:\""+lastName+"\", age:"+String.valueOf(age)+"}";
    }
}
