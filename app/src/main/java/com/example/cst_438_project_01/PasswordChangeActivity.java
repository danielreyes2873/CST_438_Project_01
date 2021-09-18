package com.example.cst_438_project_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cst_438_project_01.data_model.User;
import com.example.cst_438_project_01.database.UserDatabase;

/**
 * Activity that allows the user to change the password. User ID is passed through an intent so we know which user to change the password for.
 */
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

            //get the password text from the password text view and pass it into the change function. user id is from the intent.
            passwordButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    change(userid, passwordText.getText().toString());
                }
            });
        }

    /**
     * Method that uses the database in order to change the password
     * @param userID The primary key of the user in our database.
     * @param password the new password to overwrite the old one
     */
    private void change(int userID, String password) {
        UserDatabase userDatabase = UserDatabase.getInstance(this.getApplicationContext());
        User current = userDatabase.userDao().findByUserID(userID);
        User newUser = new User(current.getUsername(), password, current.getUserID());
        userDatabase.userDao().update(newUser);
    }
}