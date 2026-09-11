package com.clinic.models;

public class Patient {

    private String id;
    private String name;
    private int age;
    private String contact;

    public Patient(String id, String name, int age, String contact){
        this.id = id;
        this.name = name;
        this.age = age;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

  @Override
    public String toString(){
        return "Id: " + id +
                "\nName: " + name +
                "\nAge: " + age +
                "\nContact: " + contact;
  }
}
