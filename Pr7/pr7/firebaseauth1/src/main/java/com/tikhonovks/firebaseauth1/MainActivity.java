package com.tikhonovks.firebaseauth1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText emailEditText;
    private EditText passwordTextEdit;
    private TextView statusTextView;
    private FirebaseAuth auth;

    private Button signInBtn;
    private Button signOutBtn;
    private Button createAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.email_input);
        passwordTextEdit = findViewById(R.id.password_input);
        statusTextView = findViewById(R.id.status_text_view);

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.create_account_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        auth = FirebaseAuth.getInstance();

    }
    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.create_account_button) {
            createAccount(emailEditText.getText().toString(),
                    passwordTextEdit.getText().toString());
        } else if (i == R.id.sign_in_button) {
            signIn(emailEditText.getText().toString(),
                    passwordTextEdit.getText().toString());
        } else if (i == R.id.sign_out_button){
            signOut();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            statusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            findViewById(R.id.email_input).setVisibility(View.GONE);
            findViewById(R.id.password_input).setVisibility(View.GONE);
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.create_account_button).setVisibility(View.GONE);
            findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);

        } else {
            statusTextView.setText(R.string.signed_out);
            findViewById(R.id.password_input).setVisibility(View.VISIBLE);
            findViewById(R.id.email_input).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.create_account_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
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
                        FirebaseUser user = auth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }

    private void signOut() {
        auth.signOut();
        updateUI(null);
    }

    private void signIn(String email, String password) {
        if (!validateForm()) {
            return;
        }
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                    if (!task.isSuccessful()) {
                        statusTextView.setText(R.string.auth_failed);
                    }
                });
    }


}