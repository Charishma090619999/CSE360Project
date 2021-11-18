package com.company;

public class Doctor {
    private String firstName;
    private String lastName;
    private int userID;
    private int nurseID;

    public int getUserID() {
        return userID;
    }

    public int getNurseID() {
        return nurseID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Dr. " + firstName + " " + lastName;
    }
}
