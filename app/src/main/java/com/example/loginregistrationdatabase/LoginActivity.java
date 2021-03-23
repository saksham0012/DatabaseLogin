package com.example.loginregistrationdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText loginUsername , loginPassword;
    private Button loginButton,signupButton;
    private DataBaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.loginusername);
        loginPassword = findViewById(R.id.loginpassword);
        loginButton = findViewById(R.id.Continue);

        signupButton = findViewById(R.id.signupbutton);

        myDb = new DataBaseHelper(this);

        loginUser();

    }
    private void loginUser(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean var = myDb.checkUser(loginUsername.getText().toString() , loginPassword.getText().toString());
                if (var){
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this , MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Login Failed !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
