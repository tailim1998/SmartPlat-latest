package my.edu.tarc.user.smartplat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin, buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.Register);
        buttonRegister = (Button) findViewById(R.id.SignUp);

        buttonLogin.setOnClickListener(this);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void userLogin() {
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        try {
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    Constants.URL_LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                if (!jsonObject.getBoolean("error")) {
                                    SharedPrefManager.getInstance(getApplicationContext())
                                            .userLogin(jsonObject.getInt("id"),
                                                    jsonObject.getString("username"),
                                                    jsonObject.getString("email"));

                                    Toast.makeText(getApplicationContext(),
                                            "User Login Successful",
                                            Toast.LENGTH_LONG).show();

                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            jsonObject.getString("message"),
                                            Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),
                                    error.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("password", password);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogin) {
            userLogin();
            System.out.println("Addidas Jacket: " + R.drawable.addidas_jacket);
            System.out.println("Adidas : " + R.drawable.adidas);
            System.out.println("Book Exhibi : " + R.drawable.book_exhibition);
            System.out.println("Cap : " + R.drawable.cap);
            System.out.println("Charity found : " + R.drawable.charity_foundation);
            System.out.println("Chatto : " + R.drawable.chatto_tea);
            System.out.println("Event1 : " + R.drawable.event1);
            System.out.println("Event2 : " + R.drawable.event2);
            System.out.println("Fossil : " + R.drawable.fossilwatch);
            System.out.println("Gloves : " + R.drawable.gloves);
            System.out.println("H&M : " + R.drawable.h_m);
            System.out.println("Maybank : " + R.drawable.maybank);
            System.out.println("MI8 : " + R.drawable.mi8);
            System.out.println("Music F : " + R.drawable.musicfiesta);
            System.out.println("Neon : " + R.drawable.neonnightrun);
            System.out.println("Nike : " + R.drawable.nike);
            System.out.println("Nike Air Max : " + R.drawable.nikeairmax);
            System.out.println("Service1 : " + R.drawable.service1);
            System.out.println("Service2 : " + R.drawable.service2);
            System.out.println("7 Eleven : " + R.drawable.seveneleven);
            System.out.println("U mobile : " + R.drawable.umobile);
            System.out.println("Viper Chall : " + R.drawable.viperchallenge);
            System.out.println("Zombie Run : " + R.drawable.zombiesrun);
            System.out.println("Carotino : " + R.drawable.carotino);
            System.out.println("Tealive : " + R.drawable.tealive);
        }
    }
}

