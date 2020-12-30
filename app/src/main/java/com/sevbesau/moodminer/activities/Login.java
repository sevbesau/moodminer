package com.sevbesau.moodminer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.sevbesau.moodminer.R;
import com.sevbesau.moodminer.model.Model;

import androidx.appcompat.app.AppCompatActivity;

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
/*
    mModel = Model.getInstance(LoginActivity.this.getApplication());

    final User user = (User) mModel.getUser();

    if (user != null && user.getRefreshToken() != null) {
      mModel.authenticate(new AbstractAPIListener() {
        @Override
        public void onToken(String accesToken) {
          user.setAccesToken(accesToken);
          Toast.makeText(LoginActivity.this, "Logged in!", Toast.LENGTH_LONG).show();
          setContentView(R.layout.main);
        }
      });
    }

    if (user != null && user.getEmail() != null) {
      mEmailField.setText(user.getEmail());
    }

    if (user != null && user.getAccesToken() != null) {
      setContentView(R.layout.main);
    }
  }

  public void onLogin(final View view) {
    String email = mEmailField.getText().toString();
    String password = mPasswordField.getText().toString();

    mModel.login(email, password, new AbstractAPIListener() {
      @Override
      public void onLogin(User user) {
        mModel.setUser(user);
        Toast.makeText(LoginActivity.this, "Logged in!", Toast.LENGTH_LONG).show();
        launchMainActivity(view);
      }
    });
  }


  public void launchMainActivity(View view) {
    Log.d(LOG_TAG, "Launching MainActivity");
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }

 */
  }

  public void launchSignupActivity(View view) {
    Log.d(LOG_TAG, "Launching SignupActivity");
    Intent intent = new Intent(this, Signup.class);
    startActivity(intent);
  }
}
