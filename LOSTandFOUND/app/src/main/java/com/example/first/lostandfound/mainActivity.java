package com.example.first.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.autofill.AutofillManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.first.lostandfound.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class mainActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    // The necessary instance variables to interact with the text fields and buttons.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    private static final int REQUEST_READ_CONTACTS = 0;

    // This object authenticates the login credentials. To use this I needed to use the library
    // found at this link: https://firebase.google.com/docs/android/setup?authuser=0
    // and put it in the gradle file for the app module. You should see it in there with a
    // detailed comment.

    /*Information for the FirebaseAuth object can be found here:
     * https://firebase.google.com/docs/reference/android/com/google/firebase/auth/FirebaseAuth.html#getCurrentUser()
     * */
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Check if a user is still logged in. This has to be commented out until the
        / activity to go to users homepage is made.
        if (mFirebaseAuth.getCurrentUser() != null) {
            // TODO: start the activity to show home page of user instead.
        }
        */


        mEmailView = findViewById(R.id.email);
        mFirebaseAuth = FirebaseAuth.getInstance();

        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });


        // The logic for the 'Sign in' button.
        Button mEmailSignInButton = (Button) findViewById(R.id.Login_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });


        // The logic for the 'Register' button.
        Button mRegisterButton = findViewById(R.id.SignUp_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    protected void attemptLogin() {

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString().trim();
        String password = mPasswordView.getText().toString().trim();

        // Check if text fields are empty. If so just alert user with a toast to stop being
        // stupid.

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter a valid email or password", Toast.LENGTH_LONG).show();
            return;
        }

        // Start the authentication process with firebase!
        Log.i(TAG, "Trying to login with credentials" + email + " : " +password);
        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // Verify if the login was successful.
                if (task.isSuccessful()) {
                    Toast.makeText(mainActivity.this, "Login successfully", Toast.LENGTH_LONG).show();

                    // Upon successful login, Start the next activity to go to next screen
                    // TODO: make an intent to start the next activity to take user to next screen.
                    Log.i(TAG, "Login was successful, terminating the login activity");
                    finish();


                } else {

                    // Login failed. Alert with a toast and clear the fields.
                    Toast.makeText(mainActivity.this, "Login failed. Invalid email or password.", Toast.LENGTH_LONG).show();

                    mEmailView.getText().clear();
                    mPasswordView.getText().clear();

                }
            }
        });
    }

    protected void registerUser() {
        Log.i(TAG, "Starting Registration activty");
        Intent intent = new Intent(this, signUpActivity.class);

        finish();
        startActivity(intent);
    }

}
