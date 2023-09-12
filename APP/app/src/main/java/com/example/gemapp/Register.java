package com.example.gemapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.reactivex.functions.Consumer;

import com.example.gemapp.Retrofit.INodeJS;
import com.example.gemapp.Retrofit.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class Register extends AppCompatActivity {

    Button btn_signup;
    public static INodeJS iNodeJS;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    EditText nom, prenom, email, password,num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        iNodeJS = RetrofitClient.getInstance().create(INodeJS.class);
        nom = findViewById(R.id.nom_edit);
        prenom = findViewById(R.id.prenom_edit);
        email = findViewById(R.id.email_edit);
        password = findViewById(R.id.password_edit);
        num = findViewById(R.id.numero_edit);
        btn_signup = findViewById(R.id.btn_register);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser(nom.getText().toString(), prenom.getText().toString(), email.getText().toString(), password.getText().toString(), num.getText().toString());
                Intent i = new Intent(Register.this,Login.class);
                startActivity(i);
            }
        });
    }

    private void registerUser(final String nom, final String prenom, final String email, final String password, final String num) {


        compositeDisposable.add(iNodeJS.registerUser(nom, prenom, email, password, num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @SuppressLint("SuspiciousIndentation")
                    @Override
                    public void accept(String s) throws Exception {
                        if (s!=null) {
                            Toast.makeText(Register.this, "User Registred Succesfully" + s, Toast.LENGTH_SHORT).show();
                            //navigation
                            Intent i = new Intent(Register.this, Login.class);
                            startActivity(i);
                        } else
                        Toast.makeText(Register.this, "Error , please try again" + s, Toast.LENGTH_SHORT).show();
                    }
                })
        );

    }
}