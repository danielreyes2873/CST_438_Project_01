package com.example.cst_438_project_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cst_438_project_01.data_model.User;
import com.example.cst_438_project_01.database.UserDatabase;

import org.w3c.dom.Text;

/**
 * Added a createUser Method
 */
public class MainActivity extends AppCompatActivity {
    //hello world
    private EditText userName;
    private EditText password;
    private Button loginBtn;
    private Button signUp;
    private TextView helpMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText) findViewById(R.id.loginUserName);
        password = (EditText) findViewById(R.id.loginPassword);
        loginBtn = (Button) findViewById(R.id.loginButton);
        helpMessage = (TextView) findViewById(R.id.helpingMessage);
        signUp = (Button) findViewById(R.id.signUpButton);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if username and password are valid
                validate(userName.getText().toString(), password.getText().toString());
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser(userName.getText().toString(), password.getText().toString());
            }
        });
   }

   private void validate(String user, String userPassword){
       //Access Database
       UserDatabase userDatabase = UserDatabase.getInstance(this.getApplicationContext());
       //Populate database (uncomment below line if you don't want to have to register)
       userDatabase.populateInitialData();

       //Check if the username exists in the database
       if(userDatabase.userDao().findByUsername(user) != null){
           //Save the info for that username
           User current = userDatabase.userDao().findByUsername(user);

           //check if the password corresponds to the username
           if(current.getPassword().equals(userPassword)){
               //If everything is good then the application moves on to the next activity
               Intent intent = new Intent(MainActivity.this, MainPage.class);
               intent.putExtra("USERNAME", user);
               intent.putExtra("USERID", current.getUserID());
               startActivity(intent);
           }
           else{
               helpMessage.setText("Password is wrong. Try Again");
           }
       }
       else{
           helpMessage.setText("Username does not exist");
       }
   }

   private void createUser(String user, String userPassword) {
        // Get the DB
       UserDatabase userDatabase = UserDatabase.getInstance(this.getApplicationContext());
       //Populate just in case
       userDatabase.populateInitialData();
       if (user.length()<1 || userPassword.length()<1) {
           helpMessage.setText("Please enter a username and password");
       } else if (userDatabase.userDao().findByUsername(user)==null) {
           userDatabase.userDao().addUser(new User(user, userPassword));
           helpMessage.setText("User created: welcome " + user);
       } else {
           helpMessage.setText("Username already in use, try another");
       }
   }

}