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

        /*writeNewUser("Mandel","Brabban","2EA3",258927069,376350);
        writeNewUser("Caldwell","Renshell","2EA1",330927838,121229);
        writeNewUser("Ruperto","Wenderott","2EA2",788927617,601354);
        writeNewUser("Talya","Peres","2EA3",613303834,900132);
        writeNewUser("Cristin","Ollin","2EA4",656700197,379644);
        writeNewUser("Cynthia","Aldie","2EA1",139978296,161841);*/
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


