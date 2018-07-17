package com.example.soring.bandcv12;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Value;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.result.DataSourcesResult;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements OnDataPointListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final int REQUEST_OAUTH = 1;
    private static final String AUTH_PENDING = "auth_state_pending";
    private boolean authInProgress = false;
    private GoogleApiClient mApiClient;
    private String TAG = "MainActivty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            authInProgress = savedInstanceState.getBoolean(AUTH_PENDING);
        }

        /* Sensor API: 센서를 주기적으로 관찰함으로써 실시간으로 원하는 데이터를 앱에 보여줌
         * Recoding API: 데이터를 백그라운드에서 자동으로 저장
         * History API: 저장된 데이터들을 원하는 형식에 따라 불러옴
         * Session API: ??
         * Bluetooth low energy API: 안드로이드 디바이스와 블루투스로 연결될 수 있는 운동 보조 기구들을 위한 API
         * */
        mApiClient = new GoogleApiClient.Builder(this)
                .addApi(Fitness.SENSORS_API)
                // 사용자에게 이 App이 그들의 데이터에 엑세스 할 승인을 요청
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE)) // 피트니스 범위 설정(읽기, 쓰기)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        Log.e(TAG, "mApiClient 생성(63line)");

        /* onCreate 내부에서 걸음수를 조회하기 위해 필요한 피트니스 권한 객체 생성(구글 로그인시 옵션으로 요청하기 위해 생성)
         * TYPE_STEP_COUNT_DELTA: 단위시간별 걸음수
         * AGGREGATE_STEP_COUNT_DELTA: ★뭘까요?★ */
        FitnessOptions fitnessOptions = FitnessOptions.builder()
                .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                //.addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                // .addDataType(DataType.TYPE_HEART_RATE_BPM, FitnessOptions.ACCESS_READ)
                .build();
        Log.e(TAG, "fitnessOptions 생성(72line)");

        /* 이 때 권한을 얻고나면 onActivityResult()가 콜백함수로 호출됨 */
    }

    @Override
    protected void onStart() {
        super.onStart();
        mApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Fitness.SensorsApi.remove(mApiClient, this)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        if (status.isSuccess()) {
                            mApiClient.disconnect();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "onActivityResult() 내부(99line)");
        if (requestCode == REQUEST_OAUTH) {
            authInProgress = false;
            /* 권한 얻기? 로그인? 성공 */
            if (resultCode == RESULT_OK) {
                if (!mApiClient.isConnecting() && !mApiClient.isConnected()) {
                    mApiClient.connect();
                    Log.e("GoogleFit", "Request_OK");
                    Log.e(TAG, "mApiClient.connect() 호출(107line)");

                }
            } else if (resultCode == RESULT_CANCELED) {
                Log.e("GoogleFit", "RESULT_CANCELED");
            }
        } else {
            Log.e("GoogleFit", "requestCode NOT request_oauth");
        }
    }

    @Override
    public void onDataPoint(DataPoint dataPoint) {
        for (final Field field : dataPoint.getDataType().getFields()) {
            final Value value = dataPoint.getValue(field);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG, "onDataPoint 호출(125line)");
                    Log.e(TAG, "bpmValue:" + value);
                    TextView bpmValue = (TextView) findViewById(R.id.bpmValue);
                    bpmValue.setText(value.toString());
                    Toast.makeText(getApplicationContext(), "Field: " + field.getName() + " Value: " + value, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(AUTH_PENDING, authInProgress);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    //사용자가 요청 된 데이터에 대한 액세스 권한을 부여한 후 앱의 목적에 맞게 원하는 GoogleApi클라이언트 (예 : HistoryClient역사적인 운동 데이터를 읽거나 쓸 수 있음)를 만듭니다 .
    // Google API Client가 접속했다는 콜백을 받으면 onconnect() 실행됨
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.e(TAG, "onConnected() 내부(147line)");

        DataSourcesRequest dataSourceRequest = new DataSourcesRequest.Builder()
                .setDataTypes(DataType.TYPE_STEP_COUNT_DELTA)
                .setDataSourceTypes(DataSource.TYPE_RAW)
                .build();
        /*DataSourcesRequest dataSourceRequest = new DataSourcesRequest.Builder()
                .setDataTypes(DataType.TYPE_HEART_RATE_BPM)
                .setDataSourceTypes(DataSource.TYPE_RAW)
                .build();*/

        Log.e(TAG, "dataSourceRequest 생성(158line)");

        ResultCallback<DataSourcesResult> dataSourcesResultCallback = new ResultCallback<DataSourcesResult>() {

            @Override
            public void onResult(DataSourcesResult dataSourcesResult) {
                Log.e(TAG, "들어옴");
                Log.e(TAG, "dataSourcesResult.getDataSources() size" + dataSourcesResult.getDataSources().size());

                for (DataSource dataSource : dataSourcesResult.getDataSources()) {

                    if (DataType.TYPE_STEP_COUNT_DELTA.equals(dataSource.getDataType())) {
                        registerFitnessDataListener(dataSource, DataType.TYPE_STEP_COUNT_DELTA);
                    /*if (DataType.TYPE_HEART_RATE_BPM.equals(dataSource.getDataType())) {
                        registerFitnessDataListener(dataSource, DataType.TYPE_HEART_RATE_BPM);*/
                    }
                    Log.e(TAG, "Data source found: " + dataSource.toString());
                    Log.e(TAG, "Data Source type: " + dataSource.getDataType().getName());

                }
            }
        };

        //registerFitnessDataListener(dataSource, DataType.TYPE_HEART_RATE_BPM);

        Fitness.SensorsApi.findDataSources(mApiClient, dataSourceRequest)
                .setResultCallback(dataSourcesResultCallback);
    }

    private void registerFitnessDataListener(DataSource dataSource, DataType dataType) {
        Log.e(TAG, "registerFitnessDataListener 내부(178line)");

        //데이터의 변화를 추적하는 요청 생성
        SensorRequest request = new SensorRequest.Builder()
                .setDataSource(dataSource) // 사용할 데이터 소스
                .setDataType(dataType) // 사용할 데이터 유형
                .setSamplingRate(3, TimeUnit.SECONDS) // 3초마다 샘플링
                .build();
        Log.e(TAG, "request 생성(185line)");

        // 주기적으로 관찰할 리스너 등록(클라이언트와 요청을 넣음)
        Fitness.SensorsApi.add(mApiClient, request, this)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {

                        if (status.isSuccess()) {
                            Log.e("GoogleFit", "SensorApi successfully added");
                            Log.e(TAG, "주기적으로 관찰할 SensorAPI 생성(195line)");

                        }
                    }
                });

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e("GoogleFit", "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (!authInProgress) {
            try {
                authInProgress = true;
                connectionResult.startResolutionForResult(MainActivity.this, REQUEST_OAUTH);
            } catch (IntentSender.SendIntentException e) {
            }
        } else {
            Log.e("GoogleFit", "authInProgress");
        }
    }
}