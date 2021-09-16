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

public class MainActivity extends AppCompatActivity {
    //hello world
    private EditText userName;
    private EditText password;
    private Button loginBtn;
    private TextView helpMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText) findViewById(R.id.loginUserName);
        password = (EditText) findViewById(R.id.loginPassword);
        loginBtn = (Button) findViewById(R.id.loginButton);
        helpMessage = (TextView) findViewById(R.id.helpingMessage);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if username and password are valid
                validate(userName.getText().toString(), password.getText().toString());
            }
        });
   }

   private void validate(String user, String userPassword){
       //Access Database
       UserDatabase userDatabase = UserDatabase.getInstance(this.getApplicationContext());
       //Populate database
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

}