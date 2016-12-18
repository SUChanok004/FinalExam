package com.example.finalexam;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalexam.db.DatabaseHelper;
import com.example.finalexam.model.MemberList;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText editUsername = (EditText) findViewById(R.id.editText);
        final EditText editPassword = (EditText) findViewById(R.id.editText2);
        Button loginBtn = (Button) findViewById(R.id.button_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();
                Cursor cursor = isMember(username, password);
                if( cursor.getCount() == 0 ){
                    Context context = v.getContext();
                    CharSequence text = "invalid username or password";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{
                    Intent intent = new Intent(v.getContext(), UserListActivity.class);
                    intent.putExtra("name", cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME)));
                    startActivity(intent);
                }
            }
        });

        Button regBtn = (Button) findViewById(R.id.button2);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_show_list) {
            Intent intent = new Intent(this, UserListActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Cursor isMember(String username, String password){
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String query = DatabaseHelper.COL_USERNAME + " ? AND " + DatabaseHelper.COL_PASSWORD + " = ?";
        return db.query(DatabaseHelper.TABLE_NAME,
                null,
                query,
                new String[]{username, password},
                null,null,null
        );
    }
}
