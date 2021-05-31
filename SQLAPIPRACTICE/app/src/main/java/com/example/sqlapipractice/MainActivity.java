package com.example.sqlapipractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlapipractice.model.RequestUser;
import com.example.sqlapipractice.retrofit2.RetrofitClient;

import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText userFirstName, userLastName, userNameAge;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userFirstName =  findViewById(R.id.firstname);
        userLastName =  findViewById(R.id.lastName);
        userNameAge =  findViewById(R.id.age);
        register = findViewById(R.id.registerButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserDetailsOnDatabase(createUserRequest());
            }
        });
    }

    public RequestUser createUserRequest() {
        RequestUser requestUser = new RequestUser();
         requestUser.setUserFirstName(userFirstName.getText().toString());
         requestUser.setUserLastName(userLastName.getText().toString());
         requestUser.setUserAgeNumber(Integer.valueOf(userNameAge.getText().toString()));
         return requestUser;
    }


    public void saveUserDetailsOnDatabase(RequestUser requestUser) {
        Call<RequestUser> userResponse = RetrofitClient.addUserRequestData().createUserRequest(requestUser);
        userResponse.enqueue(new Callback<RequestUser>() {
            @Override
            public void onResponse(Call<RequestUser> call, Response<RequestUser> response) {
                
                if(response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "You're sucessfully registered", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Not successfully Registered", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RequestUser> call, Throwable t) {
                Toast.makeText(MainActivity.this, " Your error is: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    public void getUserData() {
        Call<List<RequestUser>> requestUserCall = RetrofitClient.getUserRequestData().getRequestData();
        requestUserCall.enqueue(new Callback<List<RequestUser>>() {
            @Override
            public void onResponse(Call<List<RequestUser>> call, Response<List<RequestUser>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "HTTP ERROR"+ response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RequestUser>> call, Throwable t) {

            }
        });
    }

    public void getUserJsonData(Response <List<RequestUser>> response) {
        List<RequestUser> requestUsers = response.body();

        for(RequestUser userRequest: requestUsers) {
            Toast.makeText(getApplicationContext(), "first name:" + userRequest.getUserFirstName() + "Last name: " + userRequest.getUserLastName() + "Age" + userRequest.getUserAgeNumber(), Toast.LENGTH_SHORT).show();
        }

    }



}