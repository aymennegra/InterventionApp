package com.example.gemapp;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.example.gemapp.Retrofit.INodeJS;
import com.example.gemapp.Retrofit.RetrofitClient;
import com.example.gemapp.models.Employes;
import com.example.gemapp.ui.home.HomeFragment;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@SuppressWarnings("unchecked")
public class Login extends AppCompatActivity  {
    public static INodeJS iNodeJS;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    EditText login, password;
    SharedPreferences sharedPreferences;


    Button Go;


    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Go = findViewById(R.id.btn_cnx);

        password = findViewById(R.id.input_pass);
        login = findViewById(R.id.input_login);

        iNodeJS = RetrofitClient.getInstance().create(INodeJS.class);

        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = login.getText().toString();
                String p = password.getText().toString();
                if (e.equals("")) {
                    login.setError("login is empty");
                }
                if (p.equals("")) {
                    password.setError("password is empty");
                    //Toast.makeText(LoginActivity.this, "Check Your Entries!", Toast.LENGTH_SHORT).show();
                } else {

                    loginUser(e, p);
                }
            }
        });
    }



    private void loginUser(final String login, String password) {
        compositeDisposable.add(iNodeJS.loginEmploye(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (s!=null) {
                            loadClientData();

                            Intent i = new Intent(Login.this, MainActivity.class);
                            startActivity(i);
                        } else
                            Toast.makeText(Login.this, "" + s, Toast.LENGTH_SHORT).show(); //Show error from API
                    }
                })
        );
    }

    public void loadClientData() {
        sharedPreferences = getApplicationContext().getSharedPreferences("CurrentUser", Context.MODE_PRIVATE);
        String email2 = login.getText().toString();
        Call<Employes> call = iNodeJS.getUser(email2);
        call.enqueue(new Callback<Employes>() {
            @Override
            public void onResponse(Call<Employes> call, Response<Employes> response) {
                Employes emp = response.body();


                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("idUser", emp.getId());
                editor.apply();
                System.out.println(emp.getId()+"hhhhhhhhhhhhhh");
            }

            @Override
            public void onFailure(Call<Employes> call, Throwable t) {
            }
        });
    }

}