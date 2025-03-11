package org.example.lap6.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Employee {

    @NotEmpty(message = "Id cannot be Empty")
    @Size(min = 3, max = 7, message = "id must be between 3-7")
    private String id;

    @NotEmpty(message = "Name cannot be Empty!")
    @Size(min = 4, max = 10, message = "size name between 4-10 ")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name Must contain only characters (no numbers)!! ")
    public String name;

    @Email(message = "Invalid email!")
    private String email;

    @Pattern(regexp = "^05\\d{8}$", message = "phoneNumber start with \"05\".")
    private String phoneNumber;


    @NotNull(message = "Age cannot be Empty!")
    @Min(value=26,message = "Must be over 26 years old")
    private int age;

    //@NotEmpty(message ="Position cannot be Empty!")
    @Pattern(regexp ="supervisor|coordinator",message = "The position Must be either \"supervisor\" or \"coordinator\" only")
    private String position;

    private boolean onLeave = false;

    @NotNull(message ="HireDate be not null")
    @FutureOrPresent(message = "Must be future or present")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;

    @NotNull(message = "AnnualLeave con not be null")
    //@Positive (message = "AnnualLeave cannot be negative")
    private int annualLeave;

    public String getId() {
        return id;
    }

    public void setId(@NotEmpty(message = "Id cannot be Empty") @Size(min = 3, max = 7, message = "id must be between 3-7") String id) {
        this.id = id;
    }

    public @NotEmpty(message = "Name cannot be Empty!") @Size(min = 4, max = 10, message = "size name between 4-10 ") @Pattern(regexp = "^[A-Za-z]+$", message = "Name Must contain only characters (no numbers)!! ") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name cannot be Empty!") @Size(min = 4, max = 10, message = "size name between 4-10 ") @Pattern(regexp = "^[A-Za-z]+$", message = "Name Must contain only characters (no numbers)!! ") String name) {
        this.name = name;
    }

    public @Email(message = "Invalid email!") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Invalid email!") String email) {
        this.email = email;
    }

    public @Pattern(regexp = "^05\\d{8}$", message = "phoneNumber start with \"05\".") String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@Pattern(regexp = "^05\\d{8}$", message = "phoneNumber start with \"05\".") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NotNull(message = "Age cannot be Empty!")
    public int getAge() {
        return age;
    }

    public void setAge(@NotNull(message = "Age cannot be Empty!") int age) {
        this.age = age;
    }

    public @Pattern(regexp = "supervisor|coordinator", message = "The position Must be either \"supervisor\" or \"coordinator\" only") String getPosition() {
        return position;
    }

    public void setPosition(@Pattern(regexp = "supervisor|coordinator", message = "The position Must be either \"supervisor\" or \"coordinator\" only") String position) {
        this.position = position;
    }

    public boolean isOnLeave() {
        return onLeave;
    }

    public void setOnLeave(boolean onLeave) {
        this.onLeave = onLeave;
    }

    public @NotNull(message = "HireDate be not null") @FutureOrPresent(message = "Must be future or present") LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(@NotNull(message = "HireDate be not null") @FutureOrPresent(message = "Must be future or present") LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @NotNull(message = "AnnualLeave con not be null")
    public int getAnnualLeave() {
        return annualLeave;
    }

    public void setAnnualLeave(@NotNull(message = "AnnualLeave con not be null") int annualLeave) {
        this.annualLeave = annualLeave;
    }
}
