package com.sevbesau.moodminer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.sevbesau.moodminer.R;
import com.sevbesau.moodminer.model.Model;
import com.sevbesau.moodminer.model.database.entities.User;

public class Login extends AppCompatActivity {

  private static final String LOG_TAG = Login.class.getSimpleName();

  private EditText mEmailField;
  private EditText mPasswordField;
  private Model mModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login);

    mEmailField = findViewById(R.id.editTextEmail);
    mPasswordField = findViewById(R.id.editTextTextPassword);
    mModel = Model.getInstance(Login.this.getApplication());

    mModel.getUser().observe(this, new Observer<User>() {
      @Override
      public void onChanged(@Nullable User user) {
        if (user != null) {
          if (user.email != null) {
            mEmailField.setText(user.email);
          }
          if (user.isLoggedIn) {
            launchMainActivity();
          }
        }
      }
    });
  }

  public void onLogin(View view) {
    String email = mEmailField.getText().toString();
    String password = mPasswordField.getText().toString();

    mModel.login(email, password, this);
  }

  public void launchMainActivity() {
    Log.d(LOG_TAG, "Launching MainActivity");
    Intent intent = new Intent(this, Main.class);
    startActivity(intent);
  }

  public void launchSignupActivity(View view) {
    Log.d(LOG_TAG, "Launching SignupActivity");
    Intent intent = new Intent(this, Signup.class);
    startActivity(intent);
  }
}
