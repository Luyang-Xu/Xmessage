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

public class MainActivity extends BaseActivity implements Iview{

    @BindView(R.id.edit_input)
    EditText edit_input;
    @BindView(R.id.btn_submit)
    Button btn_submit;
    @BindView(R.id.text_info)
    TextView text_info;


    private  Ipresenter presenter;

    @OnClick(R.id.btn_submit)
    public void search(){
        Toast.makeText(this,"CLICK",Toast.LENGTH_LONG).show();
        presenter.search();
    }

    @Override
    protected void initData() {
        super.initData();
        presenter = new Presenter(this);
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


    @Override
    public void setMessage(String info) {
        text_info.setText(info);
    }

    @Override
    public String getInput() {
        return edit_input.getText().toString();
    }
}
