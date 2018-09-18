package com.example.soring.bandcv12;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.soring.bandcv12.Model.Request_FCM_Token;
import com.example.soring.bandcv12.Model.Response_Check;
import com.example.soring.bandcv12.Util.RetrofitClient;
import com.google.firebase.iid.FirebaseInstanceId;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    RadioGroup rg;
    RadioButton rb;
    DatePicker datePicker;
    Button loginButton;

    int id;
    String gender, year, month, day;

    SharedPreferences user_info;
    private final int MY_PERMISSIONS_REQUEST_BODY_SENSORS = 1;
    private boolean authInProgress = false;
    private static final String AUTH_PENDING = "auth_state_pending";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* 앱이 이미 피트니스 API에 대한 승인을 시도하고 있는지 확인 */
        if (savedInstanceState != null) {
            authInProgress = savedInstanceState.getBoolean(AUTH_PENDING);
        }

        /* Sensor API: 센서를 주기적으로 관찰함으로써 실시간으로 원하는 데이터를 앱에 보여줌
         * Recoding API: 데이터를 백그라운드에서 자동으로 저장
         * History API: 저장된 데이터들을 원하는 형식에 따라 불러옴
         * Session API: ??
         * Bluetooth low energy API: 안드로이드 디바이스와 블루투스로 연결될 수 있는 운동 보조 기구들을 위한 API
         * */

        // ※STEP1. Google Api Client 초기화 -> onStart()

        /* PERMISSION CHECK */
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.BODY_SENSORS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.BODY_SENSORS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.BODY_SENSORS},
                        MY_PERMISSIONS_REQUEST_BODY_SENSORS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

        }
        rg = findViewById(R.id.radio_group);

        datePicker = findViewById(R.id.date_picker);
        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int dateofYear, int monthOfYear, int dayOfMonth) {
                        year = String.valueOf(dateofYear);
                        month = String.valueOf(monthOfYear);
                        day = String.valueOf(dayOfMonth);
                    }
                });

        loginButton = findViewById(R.id.login_submit_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = rg.getCheckedRadioButtonId();
                rb = findViewById(id);

                gender = rb.getText().toString();
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

                                    String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                                    Log.e("FCM refreshedToken@@@", "" + refreshedToken);

                                    Request_FCM_Token request_fcm_token = new Request_FCM_Token();
                                    request_fcm_token.setUser_id("test");
                                    request_fcm_token.setUser_token(refreshedToken);

                                    //로그인할때 FCM 토큰이랑 사용자 아이디 서버에 뿌리는 부분(DB에 들어감)
                                    Call<Response_Check> response = RetrofitClient.getInstance().getService().Send_FCM_Token(request_fcm_token);
                                    response.enqueue(new Callback<Response_Check>() {
                                        @Override
                                        public void onResponse(Call<Response_Check> call, Response<Response_Check> response) {
                                            Log.e("onResponse called", "success");
                                        }

                                        @Override
                                        public void onFailure(Call<Response_Check> call, Throwable t) {
                                            Log.e("onFailure called", "" + t.toString());
                                        }
                                    });
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

    @Override
    protected void onStart() {
        super.onStart();
        getUserPreferences();
        Log.e("age",year+"ff");
        if(!year.equals("")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Log.e("dd","a");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_BODY_SENSORS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
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

    private void getUserPreferences() {
        user_info = getSharedPreferences("user_info", MODE_PRIVATE);
        gender = user_info.getString("gender","");
        year = user_info.getString("year","");
    }
}