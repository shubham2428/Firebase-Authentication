package com.example.android.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.support.v4.content.ContextCompat.startActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;
    private EditText Phoneno;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() !=null){

            finish();

            Intent intent=new Intent(getApplicationContext(),Profile.class);
            startActivity(intent);

        }

        editTextEmail=findViewById(R.id.edittextemail);
        editTextPassword=findViewById(R.id.editpassword);
        textViewSignin=findViewById(R.id.textviewsignin);
        Phoneno=findViewById(R.id.phone_no);

        buttonSignup=findViewById(R.id.buttonsignup);

        progressDialog=new ProgressDialog(this);
        buttonSignup.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if (v==buttonSignup){

            registerUser();
        }
        if (v==textViewSignin){

            startActivity(new Intent(this,Login.class));

        }
    }

    private void registerUser(){

        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        String phone=Phoneno.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"please enter email",Toast.LENGTH_LONG).show();



        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"please enter password",Toast.LENGTH_LONG).show();



        }
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this,"please enter phone number",Toast.LENGTH_LONG).show();



        }

        progressDialog.setMessage("Registerting please wait");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),Profile.class));


                        }else {

                            Toast.makeText(MainActivity.this,"Registeration Error",Toast.LENGTH_LONG).show();

                        }
                        progressDialog.dismiss();

                    }
                });



    }
}
