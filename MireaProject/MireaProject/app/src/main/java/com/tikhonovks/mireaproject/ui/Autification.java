package com.tikhonovks.mireaproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tikhonovks.mireaproject.MainActivity;
import com.tikhonovks.mireaproject.R;

public class Autification  extends AppCompatActivity implements View.OnClickListener{

    private EditText emailEditText;
    private EditText passwordTextEdit;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autification);

        emailEditText = findViewById(R.id.editTextEmail);
        passwordTextEdit = findViewById(R.id.editTextPassword);

        findViewById(R.id.buttonSigIn).setOnClickListener(this);
        findViewById(R.id.buttonCreate).setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user1 = auth.getCurrentUser();
        updateUI(user1);

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.buttonCreate) {
            createAccount(emailEditText.getText().toString(),passwordTextEdit.getText().toString());
        } else if (i == R.id.buttonSigIn) {
            signIn(emailEditText.getText().toString(), passwordTextEdit.getText().toString());
        }
    }


    private void updateUI(FirebaseUser user) {
        if (user != null) {
            openMainActivity();
        } else {

            findViewById(R.id.editTextPassword).setVisibility(View.VISIBLE);
            findViewById(R.id.editTextEmail).setVisibility(View.VISIBLE);
            findViewById(R.id.buttonSigIn).setVisibility(View.VISIBLE);
            findViewById(R.id.buttonCreate).setVisibility(View.VISIBLE);
        }
    }

    private boolean validateForm() {
        boolean valid = true;
        String email = emailEditText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Required.");
            valid = false;
        } else {
            emailEditText.setError(null);
        }
        String password = passwordTextEdit.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordTextEdit.setError("Required.");
            valid = false;
        } else {
            passwordTextEdit.setError(null);
        }
        return valid;
    }

    private void createAccount(String email, String password) {
        if (!validateForm()) {
            return;
        }
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        openMainActivity();
                    } else {
                        Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void signIn(String email, String password) {
        if (!validateForm()) {
            return;
        }
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        openMainActivity();
                    } else {
                        Toast.makeText(this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
