package com.example.soring.bandcv12;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soring.bandcv12.Model.Request_Oauth;
import com.example.soring.bandcv12.Model.Response_Oauth;
import com.example.soring.bandcv12.Util.RetrofitClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    RadioGroup rg;
    RadioButton rb;
    EditText yearEdit;
    Spinner monthSpinner;
    Spinner daySpinner;
    Button loginButton;

    int id;
    String gender, year, month, day;

    SharedPreferences user_info;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rg = findViewById(R.id.radio_group);

        yearEdit = findViewById(R.id.edit_year);

        monthSpinner = (Spinner) findViewById(R.id.spinner_month);
        ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(this, R.array.date_month, android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        daySpinner = (Spinner) findViewById(R.id.spinner_day);
        ArrayAdapter dayAdapter = ArrayAdapter.createFromResource(this, R.array.date_day, android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        loginButton = findViewById(R.id.login_submit_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = rg.getCheckedRadioButtonId();
                rb = findViewById(id);

                gender = rb.getText().toString();
                year = yearEdit.getText().toString();
                month = monthSpinner.getSelectedItem().toString();
                day = daySpinner.getSelectedItem().toString();
                if (year.equals("")) {
                    Toast.makeText(getApplicationContext(), "나이를 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("정보확인");
                    builder.setMessage("다음으로 넘어가시겟습니까?");
                    builder.setPositiveButton("예",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    saveUserPreferences(gender, year, month, day);
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                            }).setNegativeButton("아니오",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                   dialog.cancel();
                                }
                            });
                    builder.show();
                }
            }
        });

    }

    private void saveUserPreferences(String gender, String year, String month, String day) {
        user_info = getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = user_info.edit();
        editor.putString("gender", gender);
        editor.putString("year", year);
        editor.putString("month", month);
        editor.putString("day", day);
        Log.e("spinner", gender + " " + year + " " + month + " " + day);
        editor.commit();
    }



    private void removeAllPreferences() {
        user_info = getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = user_info.edit();
        editor.clear();
        editor.commit();
    }
}
