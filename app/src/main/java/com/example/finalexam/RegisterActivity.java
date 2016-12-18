package com.example.finalexam;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalexam.db.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();

        final EditText nameEt = (EditText) findViewById(R.id.editNAME);
        final EditText usernameEt = (EditText) findViewById(R.id.editUSERNAME);
        final EditText passwordEt = (EditText) findViewById(R.id.editPASSWORD);

        Button createBtn = (Button) findViewById(R.id.button2);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEt.getText().toString();
                String username = usernameEt.getText().toString();
                String password = passwordEt.getText().toString();

                DatabaseHelper helper = new DatabaseHelper(v.getContext());
                SQLiteDatabase db = helper.getWritableDatabase();

                String query = DatabaseHelper.COL_USERNAME + " ? AND " + DatabaseHelper.COL_PASSWORD + " = ?";
                Cursor cursor = db.query(DatabaseHelper.TABLE_NAME,
                        null,
                        query,
                        new String[]{username, password},
                        null,null,null
                );

                if (cursor.getCount() == 0 ){
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COL_NAME, name);
                    cv.put(DatabaseHelper.COL_USERNAME, username);
                    cv.put(DatabaseHelper.COL_PASSWORD, password);
                    db.insert(DatabaseHelper.TABLE_NAME, null, cv);
                    CharSequence text = "successful";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(v.getContext(), text, duration);
                    toast.show();
                    finish();
                }else{
                    AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                    dialog.setTitle("Registration failed");
                    dialog.setMessage("username already exists");
                    dialog.show();
                }
            }
        });
    }
}
