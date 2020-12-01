package com.example.DailyShoppingList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class MainActivity<AppCompatActivity, FirebaseAuth> extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Button btnLogin;
    private TextView signUp;

    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialog.setContentView();

        mAuth=FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        }


        mDialog=new ProgressDialog(this);



        email=findViewById(R.id.email_login);
        pass=findViewById(R.id.password_login);

        btnLogin=findViewById(R.id.btn_login);
        signUp=findViewById(R.id.signup_txt);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              String mEmail=email.getText().toString().trim();
              String mPass=pass.getText().toString().trim();

              if (TextUtils.isEmpty(mEmail)){
                  email.setError("Required Field..");
                  return;
              }
              if (TextUtils.isEmpty(mPass)){
                  pass.setError("Required Field..");
                  return;
              }

              mDialog.setMessage("Processing..");
              mDialog.show();

              mAuth.signInWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isSuccessful()){
                          startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                          Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();

                          mDialog.dismiss();
                      }
                      else {

                          Toast.makeText(getApplicationContext(),"Failed.",Toast.LENGTH_SHORT).show();
                          mDialog.dismiss();
                      }

                  }
              });


            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
            }
        });

    }
}
