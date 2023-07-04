package com.example.health_care;

public class Users {
    String name,phone,age,password,email;

    public Users() {
    }

    public Users(String name, String phone, String age, String email, String password) {
        this.name = name;
        this.phone = phone;
        this.age=age;
        this.email=email;
        this.password=password;



    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
