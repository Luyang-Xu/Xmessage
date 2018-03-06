package com.luyang.xmessage;

import android.os.Bundle;

import com.luyang.common.app.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }
}
