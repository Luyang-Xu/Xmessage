package com.luyang.xmessage;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.luyang.common.app.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity{


    @Override
    protected void initData() {
        super.initData();

    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        presenter = new Presenter(this);
//    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }
}
