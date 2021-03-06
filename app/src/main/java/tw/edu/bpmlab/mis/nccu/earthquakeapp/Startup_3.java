package tw.edu.bpmlab.mis.nccu.earthquakeapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class Startup_3 extends Activity {

    private GestureDetector gesture;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_startup_3);

        final SharedPreferences magnitude= getSharedPreferences("magnitude", 0);
        final SharedPreferences.Editor editor = magnitude.edit();

        gesture = new GestureDetector(new SwipeGestureDetector());
        final ImageButton three = (ImageButton) findViewById(R.id.three);
        final ImageButton four = (ImageButton) findViewById(R.id.four);
        final ImageButton five = (ImageButton) findViewById(R.id.five);
        final ImageButton six = (ImageButton) findViewById(R.id.six);
        final Button start = (Button) findViewById(R.id.start);

        editor.clear().commit();

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                three.setBackgroundResource(R.drawable.three_pressed);
                four.setBackgroundResource(R.drawable.four_unclicked);
                five.setBackgroundResource(R.drawable.five_unclicked);
                six.setBackgroundResource(R.drawable.six_unclicked);

                editor.clear();
                magnitude.edit().putInt("btnChecked", 3).commit();
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                three.setBackgroundResource(R.drawable.three_unclicked);
                four.setBackgroundResource(R.drawable.four_pressed);
                five.setBackgroundResource(R.drawable.five_unclicked);
                six.setBackgroundResource(R.drawable.six_unclicked);

                editor.clear();
                magnitude.edit().putInt("btnChecked", 4).commit();

            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                three.setBackgroundResource(R.drawable.three_unclicked);
                four.setBackgroundResource(R.drawable.four_unclicked);
                five.setBackgroundResource(R.drawable.five_pressed);
                six.setBackgroundResource(R.drawable.six_unclicked);

                editor.clear();
                magnitude.edit().putInt("btnChecked", 5).commit();

            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                three.setBackgroundResource(R.drawable.three_unclicked);
                four.setBackgroundResource(R.drawable.four_unclicked);
                five.setBackgroundResource(R.drawable.five_unclicked);
                six.setBackgroundResource(R.drawable.six_pressed);

                editor.clear();
                magnitude.edit().putInt("btnChecked", 6).commit();

            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.start:
                        Intent intent = new Intent(Startup_3.this, Startup_4.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        break;
                }

            }


        });




    }
    public boolean onTouchEvent(MotionEvent event) {
        if (gesture.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void onLeft() {
        Intent intent = new Intent(Startup_3.this, Startup_4.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    //private void onRight() {
    //    Intent myIntent = new Intent(Startup_3.this, Startup_4.class);
    //    startActivity(myIntent);
    //    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    //}

    private class SwipeGestureDetector  extends GestureDetector.SimpleOnGestureListener
    {
        private static final int SWIPE_MIN_DISTANCE = 120;
        private static final int SWIPE_MAX_OFF_PATH = 200;
        private static final int SWIPE_THRESHOLD_VELOCITY = 200;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                float diffAbs = Math.abs(e1.getY() - e2.getY());
                float diff = e1.getX() - e2.getX();

                if (diffAbs > SWIPE_MAX_OFF_PATH)
                    return false;

                //Left swipe
                if (diff > SWIPE_MIN_DISTANCE&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Startup_3.this.onLeft();
                }
                // Right swipe

                //else if (-diff > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                //    Startup_3.this.onRight();
                //}
            }
            catch (Exception e) {
                Log.e("Startup_3", "Error on gestures");
            }
            return false;
        }
    }

}
