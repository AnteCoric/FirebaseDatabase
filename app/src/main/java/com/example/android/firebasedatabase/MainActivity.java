package com.example.android.firebasedatabase;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "test";
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        //writeNewUser("Ante","Coric","2EA1",123456789,141592);
        //writeNewUser("Pepijn","Janssen","2EA1",987654321,784512);
        // Read from the database
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot child : dataSnapshot.child("Students").getChildren()) {
                    Student value = child.getValue(Student.class);
                    Log.d(TAG,"Value is: " + value);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    public void writeNewUser(String firstname, String lastname, String klas, int studentnr, int serialnr){
        Student user = new Student(firstname, lastname,klas,studentnr,serialnr);

        mDatabase.child("Students").child(Integer.toString(studentnr)).setValue(user);
    }


}
@IgnoreExtraProperties
class Student {

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

