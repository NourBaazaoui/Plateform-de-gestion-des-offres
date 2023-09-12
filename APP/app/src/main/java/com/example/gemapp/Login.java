package com.example.gemapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.gemapp.Retrofit.INodeJS;
import com.example.gemapp.Retrofit.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class Login extends AppCompatActivity  {
    public static INodeJS iNodeJS;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    //declaration mtaa Ui (Buttons,TextViews,EditText,...)
    EditText login, password;
    Button loginbtn,registerbtn;


    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginbtn = findViewById(R.id.btn_cnx);
        registerbtn = findViewById(R.id.btn_register);

        password = findViewById(R.id.input_pass);
        login = findViewById(R.id.input_login);

        iNodeJS = RetrofitClient.getInstance().create(INodeJS.class);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = login.getText().toString();
                String p = password.getText().toString();
                if (e.equals("")) {
                    login.setError("login is empty");
                }
                if (p.equals("")) {
                    password.setError("password is empty");
                    Toast.makeText(Login.this, "Check Your Entries!", Toast.LENGTH_SHORT).show();
                } else {

                    loginUser(e, p);
                }
            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Register.class);
                 startActivity(i);
            }
        });
    }



    private void loginUser(final String login, String password) {
        compositeDisposable.add(iNodeJS.loginUser(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (s!=null) {
                            //navigation mel login lel list mta3 jobs
                           Intent i = new Intent(Login.this, MainActivity.class);
                            startActivity(i);
                        } else
                            Toast.makeText(Login.this, "" + s, Toast.LENGTH_SHORT).show(); //Show error from API
                    }
                })
        );
    }



}