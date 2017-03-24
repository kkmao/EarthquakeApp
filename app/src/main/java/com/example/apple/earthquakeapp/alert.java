package com.example.apple.earthquakeapp;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.*;
import android.widget.*;
import java.util.Calendar;
import java.util.Date;
import android.os.Message;



public class alert extends AppCompatActivity {

    protected TextView Date;
    protected int thisYear;
    protected int thisMonth;
    protected String thisMonthEng;
    protected int thisDate;
    protected int thisHour;
    protected int thisMin;
    protected int thisSec;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_alert);
        getTime();

    }




    public void getTime(){
        Calendar dt = Calendar.getInstance();
        thisYear = dt.get(Calendar.YEAR);
        thisMonth = dt.get(Calendar.MONTH)+1;
        thisDate = dt.get(Calendar.DAY_OF_MONTH);
//        thisHour = dt.get(Calendar.HOUR_OF_DAY);
//        thisMin = dt.get(Calendar.MINUTE);
//        thisSec = dt.get(Calendar.SECOND);


        Date = (TextView) findViewById(R.id.Date);
        if (thisMonth==1){
            thisMonthEng = "Jan";
        }if (thisMonth==2){
            thisMonthEng = "Feb";
        }if (thisMonth==3){
            thisMonthEng = "Mar";
        }if (thisMonth==4){
            thisMonthEng = "Apr";
        }if (thisMonth==5){
            thisMonthEng = "May";
        }if (thisMonth==6){
            thisMonthEng = "Jun";
        }if (thisMonth==7){
            thisMonthEng = "Jul";
        }if (thisMonth==8){
            thisMonthEng = "Aug";
        }if (thisMonth==9){
            thisMonthEng = "Sep";
        }if (thisMonth==10){
            thisMonthEng = "Oct";
        }if (thisMonth==11){
            thisMonthEng = "Nov";
        }if (thisMonth==12){
            thisMonthEng = "Dec";
        }
        Date.setText(thisMonthEng+", "+Integer.toString(thisDate)+", "+Integer.toString(thisYear));
//        Date.setText(Integer.toString(thisYear)+"/" + Integer.toString(thisMonth)+"/" + Integer.toString(thisDate)+" "
//                + Integer.toString(thisHour)+":" + Integer.toString(thisMin)+":" + Integer.toString(thisSec)
        Date.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf"));




    }


}