package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public MaterialButton loginCardButton;
    public MaterialButton signupCardButton;
    public MaterialButton loginButton;
    public MaterialButton signupButton;
    public MaterialCardView loginCardView;
    public MaterialCardView signupCardView;
    public MaterialButton closeLoginCard;
    public MaterialButton closeSignupCard;
    public BackendCall backendCall;

    public TextInputEditText signupName;
    public TextInputEditText signupEmail;
    public TextInputEditText signupPhone;
    public TextInputEditText signupPassword;
    public TextInputEditText loginEmail;
    public TextInputEditText loginPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://foodorder.localtunnel.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loginCardButton = (MaterialButton) findViewById(R.id.loginCardButton);
        signupCardButton = (MaterialButton) findViewById(R.id.signupCardButton);
        loginButton = (MaterialButton) findViewById(R.id.loginButton);
        signupButton = (MaterialButton) findViewById(R.id.signupButton);
        loginCardView = (MaterialCardView) findViewById(R.id.loginCardView);
        closeLoginCard = (MaterialButton) findViewById(R.id.closeLoginCard);
        signupCardView = (MaterialCardView) findViewById(R.id.signupCardView);
        closeSignupCard = (MaterialButton) findViewById(R.id.closeSignupCard);
        signupName = (TextInputEditText) findViewById(R.id.signupName);
        signupEmail = (TextInputEditText) findViewById(R.id.signupEmail);
        signupPhone = (TextInputEditText) findViewById(R.id.signupPhone);
        signupPassword = (TextInputEditText) findViewById(R.id.signupPassword);
        loginEmail = (TextInputEditText) findViewById(R.id.loginEmail);
        loginPassword = (TextInputEditText) findViewById(R.id.loginPassword);



        backendCall = retrofit.create(BackendCall.class);

        loginCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCardView.setVisibility(View.VISIBLE);
            }
        });

        closeLoginCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCardView.setVisibility(View.INVISIBLE);
            }
        });

        signupCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupCardView.setVisibility(View.VISIBLE);
            }
        });

        closeSignupCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupCardView.setVisibility(View.INVISIBLE);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email,password;
                email = loginEmail.getText().toString();
                password = loginPassword.getText().toString();

                LoginModel loginModel = new LoginModel(email , password);
                loginCall(loginModel);

            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name,email,phone,password;
                name = signupName.getText().toString();
                email = signupEmail.getText().toString();
                phone = signupPhone.getText().toString();
                password = signupPassword.getText().toString();

                SignUpModel signUpModel = new SignUpModel(name , email , phone , password);

                signUpCall(signUpModel);

            }
        });

    }

    private void signUpCall(SignUpModel signUpModel)
    {
        Call<UserModel> call = backendCall.creatUser(signUpModel);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if(response.code()!=200)
                {
                    Log.e("Response Status: " , "Not Successful");
                    return;
                }

                UserModel userModel = response.body();

                Log.e("UserModel: " , userModel.getId());
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

                Log.e("Error Occured: " , t.getMessage());
            }
        });
    }

    private void loginCall(LoginModel loginModel)
    {
        Call<UserModel> call = backendCall.loginUser(loginModel);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                if(!response.isSuccessful())
                {
                    Log.e("Response status: " , "Invalid Details" + response.code());
                    return;
                }

                UserModel userModel = response.body();

                Log.e("UserModel:" , userModel.getId());
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

                Log.e("Error Occured: " , t.getMessage());

            }
        });
    }
}
