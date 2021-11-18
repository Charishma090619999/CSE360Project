package com.company;

public class Patient {

    private String firstName;
    private String lastName;
    private final int userID;
    private int doctorID;
    private int nurseID;

    private String username;
    private String password;

    public Patient(String firstName, String lastName, int userID, int doctorID, int nurseID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.doctorID = doctorID;
        this.nurseID = nurseID;
    }

    public Patient(String firstName, String lastName, int userID, int doctorID, int nurseID, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.doctorID = doctorID;
        this.nurseID = nurseID;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNurseID(int nurseID) {
        this.nurseID = nurseID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}


