package com.example.healthiu.entity;

import com.example.healthiu.security.FieldMatch;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
@FieldMatch(first = "password", second = "confirm_password", message = "The password fields must match")
public class UserData implements Serializable {

    @NotBlank(message = "Login can't be blank")
    @Size(min = 3, max = 10, message = "Login can be of length from 3 to 10")
    private String login;

    @NotBlank(message = "Name can't be blank")
    @Size(min = 2, max = 20, message = "Name can be of length from 2 to 20")
    private String name;

    @NotBlank(message = "Email cant be blank")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Password can't be blank")
    @Size(min = 6, max = 10, message = "Password can be of length from 6 to 10")
    private String password;

    private String confirmPassword;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Date can't be null")
    @Past(message = "Invalid date")
    private LocalDate dateOfBirth;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public UserData() {
    }

    public UserData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UserData(String login, String name, String email, String password, String confirmPassword, LocalDate dateOfBirth) {
        this.login = login;
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.dateOfBirth = dateOfBirth;
    }
}
