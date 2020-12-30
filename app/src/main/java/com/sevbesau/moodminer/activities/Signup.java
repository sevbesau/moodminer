package com.sevbesau.moodminer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.sevbesau.moodminer.R;
import com.sevbesau.moodminer.model.Model;

public class Signup extends AppCompatActivity {

  private static final String LOG_TAG = Signup.class.getSimpleName();

  private EditText mEmailField;
  private EditText mPasswordField;
  private EditText mConfirmPasswordField;
  private Model mModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.signup);

    mEmailField = findViewById(R.id.editTextEmail);
    mPasswordField = findViewById(R.id.editTextTextPassword);
    mConfirmPasswordField = findViewById(R.id.editTextTextPasswordConfirm);

    //mModel = Model.getInstance((SignupActivity.this.getApplication()));
  }

  public void onSignup(final View view) {
    String email = mEmailField.getText().toString();
    String password = mPasswordField.getText().toString();
    String confirmPassword = mConfirmPasswordField.getText().toString();

    /*
    mModel.signup(email, password, new AbstractAPIListener() {
      @Override
      public void onSignup(User user) {
        mModel.setUser(user);
        Toast.makeText(SignupActivity.this, "Account created!", Toast.LENGTH_LONG).show();
        launchLoginActivity(view);
      }
    });
     */
  }

  public void launchLoginActivity(View view) {
    Log.d(LOG_TAG, "Launching LoginActivity");
    Intent intent = new Intent(this, Login.class);
    startActivity(intent);
  }
}
