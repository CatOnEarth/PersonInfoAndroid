package com.snail.personinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.snail.personinfo.logger.Logger;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    Logger logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logger = new Logger();

        logger.LogInfo(TAG, "call onCreate");
    }
}