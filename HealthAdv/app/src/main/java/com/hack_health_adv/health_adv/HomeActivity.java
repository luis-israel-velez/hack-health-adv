package com.hack_health_adv.health_adv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toast.makeText(HomeActivity.this,"Hello",Toast.LENGTH_SHORT).show();
        //SystemClock.sleep(timeInMills);
        //Thread.sleep(2000);
        /*try
        {
            Thread.sleep(1000);
            Toast.makeText(HomeActivity.this,"Hello",Toast.LENGTH_SHORT).show();
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }*/

    }
}
