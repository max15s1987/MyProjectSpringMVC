package org.example.model;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "Enter your name")
    @Size(min = 2, max = 50, message = "Please enter a valid name")
    private String name;

    @Column
    @Min(value = 0, message = "Please enter a valid age")
    private Integer age;

    @Column
    @NotEmpty(message = "Enter your city")
    private String city;

    @Column
    @NotEmpty(message = "Enter your email")
    @Email
    private String email;

    public User(Integer id, String name, Integer age, String city, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
        this.email = email;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
