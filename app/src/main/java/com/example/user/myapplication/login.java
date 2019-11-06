package com.example.user.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText nombre,password;
    Button aceptar,cancelar,quiero;
    private ProgressDialog progressDialog;


    //Declaramos un objeto firebaseAuth
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        nombre= (EditText) findViewById(R.id.nombre);
        password= (EditText) findViewById(R.id.password);
        aceptar= (Button) findViewById(R.id.aceptar);
        cancelar= (Button) findViewById(R.id.cancelar);

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombres = nombre.getText().toString().trim();
                String passwordt  = password.getText().toString().trim();
                if (!TextUtils.isEmpty(nombres)&& !TextUtils.isEmpty(passwordt)) {
                    String nombres2 = nombre.getText().toString().trim();
                    String passwordt2 = password.getText().toString().trim();

                    firebaseAuth.signInWithEmailAndPassword(nombres2, passwordt2)
                            .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            //checking if success
                                            if (task.isSuccessful()) {
                                                nombre.setText("");
                                                password.setText("");
                                                Intent sig = new Intent(login.this, menu.class);
                                                startActivity(sig);
                                                Toast.makeText(login.this, "Bienvenido ", Toast.LENGTH_LONG).show();

                                            } else {

                                                Toast.makeText(login.this, "No se a autenticado correctamente ", Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    }
                            );
                }else{
                    Toast.makeText(login.this, "No se a autenticado correctamente ", Toast.LENGTH_LONG).show();
                }
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "adios.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
            }

}
