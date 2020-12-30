package com.sevbesau.moodminer.model.api;

import android.app.Application;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sevbesau.moodminer.model.database.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebAPI implements API {

  public static final String BASE_URL = "http://10.0.2.2:3000/";

  private RequestQueue mRequestQueue;
  private Application mApplication;
  private CookieStore mCookieStore;

  public WebAPI(Application application) {
    CookieManager cookieManager = new CookieManager();
    CookieHandler.setDefault(cookieManager);

    mCookieStore = cookieManager.getCookieStore();
    mRequestQueue = Volley.newRequestQueue(application);
    mApplication = application;
  }

  public void login(String email, String password, final APIListener listener) {
    final String url = BASE_URL + "login";
    JSONObject jsonObject = new JSONObject();

    try {
      Response.Listener succesListener = new Response.Listener<JSONObject>() {
        @Override
         public void onResponse(JSONObject res) {
          try {
            res.put("refreshToken", getJid());
            User user = User.getUser(res);
            listener.onLogin(user);
          } catch (JSONException error) {
            Toast.makeText(mApplication, "Login failed", Toast.LENGTH_LONG).show();
          }
        }
      };

      Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
          Toast.makeText(mApplication, "Login failed", Toast.LENGTH_LONG).show();
        }
      };

      jsonObject.put("email", email);
      jsonObject.put("password", password);

      JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, jsonObject, succesListener, errorListener) {
        @Override
        public Map<String, String> getHeaders() {
          Map<String, String> params = new HashMap<>();
          params.put("Content-type", "application/json");
          return params;
        }
      };
      mRequestQueue.add(req);
    } catch (JSONException e) {
      Toast.makeText(mApplication, "Login failed", Toast.LENGTH_LONG).show();
    }
  }

  public void signup(String email, String password, final APIListener listener) {
    final String url = BASE_URL + "signup";
    JSONObject jsonObject = new JSONObject();

    try {
      Response.Listener succesListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject res) {
          try {
            User user = User.getUser(res);
            listener.onSignup(user);
          } catch (JSONException error) {
            Toast.makeText(mApplication, "Signup failed 1", Toast.LENGTH_LONG).show();
          }
        }
      };

      Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
          Toast.makeText(mApplication, "Signup failed 2", Toast.LENGTH_LONG).show();
        }
      };

      jsonObject.put("email", email);
      jsonObject.put("password", password);

      JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, jsonObject, succesListener, errorListener) {
        @Override
        public Map<String, String> getHeaders() {
          Map<String, String> params = new HashMap<>();
          params.put("Content-type", "application/json");
          return params;
        }
      };
      mRequestQueue.add(req);
    } catch (JSONException e) {
      Toast.makeText(mApplication, "Signup failed 3", Toast.LENGTH_LONG).show();
    }
  }

  public void token(final APIListener listener) {
    final String url = BASE_URL + "token";

    Response.Listener succesListener = new Response.Listener<JSONObject>() {
      @Override
      public void onResponse(JSONObject res) {
        try {
          listener.onToken(res.getString("accesToken"));
        } catch (JSONException error) {
          Toast.makeText(mApplication, "Token failed 1", Toast.LENGTH_LONG).show();
        }
      }
    };

    Response.ErrorListener errorListener = new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Toast.makeText(mApplication, "Token failed 2", Toast.LENGTH_LONG).show();
      }
    };

    JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, null, succesListener, errorListener) {
      @Override
      public Map<String, String> getHeaders() {
        Map<String, String> params = new HashMap<>();
        params.put("Content-type", "application/json");
        return params;
      }
    };
    mRequestQueue.add(req);
  }

  private String getJid() {
    List<HttpCookie> cookies = mCookieStore.getCookies();
    for (HttpCookie cookie : cookies) {
      if (cookie.getName().equals("jid")) {
        return cookie.getValue();
      }
    }
    return null;
  }
}
