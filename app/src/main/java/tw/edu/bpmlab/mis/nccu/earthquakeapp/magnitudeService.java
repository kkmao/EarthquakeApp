package tw.edu.bpmlab.mis.nccu.earthquakeapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;



/**
 * Created by User on 2017/7/17.
 */

public class magnitudeService extends Service implements SensorEventListener {

    private Timer timer = null;
    public double eqGal;
    public ArrayList<Double> eqGalData = new ArrayList<Double>();

    private SensorManager aSensorManager;
    private Sensor aSensor;
    public float gravity[] = new float[3];

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mEqDataReference;



    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "服務啟動中", Toast.LENGTH_SHORT).show();
        timer = new Timer();

        aSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        aSensor = aSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        aSensorManager.registerListener(this, aSensor, aSensorManager.SENSOR_DELAY_NORMAL);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mEqDataReference = mFirebaseDatabase.getReference().child("eqData");
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        timer.schedule(task, 1000, 1000);
        Toast.makeText(this, "服務開始", Toast.LENGTH_SHORT).show();
        return START_NOT_STICKY;
    }

    public void onDestory() {
        Toast.makeText(this, "end", Toast.LENGTH_SHORT).show();
        timer.cancel();
        timer.purge();
    }

    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            eqGal = Math.abs((Math.sqrt(Math.pow(gravity[0], 2) + Math.pow(gravity[1], 2) + Math.pow(gravity[2], 2)) - 9.81) * 100);
            eqGalData.add(eqGal);
            if (eqGalData.size() == 2) {
                if (eqGalData.get(1) / eqGalData.get(0) > Math.pow(Math.sqrt(10), 2)) {
                    mEqDataReference.push().setValue(eqGal);
//                    alert alert = new alert();
//                    EqData eqData = new EqData(alert.magnitude, alert.x, alert.y, eqGal, alert.time, alert.eqDataID);
//                    mEqDataReference.push().setValue(eqData);
                }
                eqGalData.remove(0);
            } else if (eqGalData.size() > 2) {
                eqGalData.remove(0);

            }

        }
    };

    @Override
    public void onSensorChanged(SensorEvent event) {
        gravity[0] = event.values[0];
        gravity[1] = event.values[1];
        gravity[2] = event.values[2];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
