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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
                validate(userName.getText().toString(), password.getText().toString());
            }
        });
   }

   private void validate(String user, String userPassword){

        if(user.equals("Admin")){
            if(userPassword.equals("!!!")){
                Intent intent = new Intent(MainActivity.this, HomePage.class);
                startActivity(intent);
            }
            else{
                helpMessage.setText("Wrong Password. Try Again");
            }
        }
        else{
            helpMessage.setText("Username does not Exist");
        }

   }

}