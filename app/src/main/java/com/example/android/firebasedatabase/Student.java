package com.example.android.firebasedatabase;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Student {

    public String FirstName;
    public String LastName;
    public String Klas;
    public int StudentNr;
    public int SerialNr;

    public Student() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Student(String firstname, String lastname, String klas, int studentnr, int serialnr) {
        this.FirstName = firstname;
        this.LastName = lastname;
        this.Klas = klas;
        this.StudentNr = studentnr;
        this.SerialNr = serialnr;
    }

    @Override
    public String toString() {
        return this.FirstName + " " + this.LastName + " " + this.Klas + " " + this.StudentNr + " " + this.SerialNr;
    }
}
