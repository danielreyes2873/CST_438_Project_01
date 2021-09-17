package com.example.cst_438_project_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cst_438_project_01.data_model.User;
import com.example.cst_438_project_01.database.UserDatabase;

public class PasswordChangeActivity extends AppCompatActivity {
    private EditText passwordText;
    private Button passwordButton;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_password_change);

            passwordText = (EditText) findViewById(R.id.passwordChanger);
            passwordButton = (Button) findViewById(R.id.passwordChangeButton);
            int userid = getIntent().getIntExtra("USERID", 0);

            passwordButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    change(userid, passwordText.getText().toString());
                }
            });
        }

    private void change(int userID, String password) {
        UserDatabase userDatabase = UserDatabase.getInstance(this.getApplicationContext());
        User current = userDatabase.userDao().findByUserID(userID);
        User newUser = new User(current.getUsername(), password, current.getUserID());
        userDatabase.userDao().update(newUser);
        System.out.println("ouirhopahoriah");
    }
}