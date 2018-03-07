package com.luyang.common.app;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by luyang on 2018/3/6.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initWindows();

        if (initArgs(getIntent().getExtras())) {
            int layId = getContentLayoutId();
            //setcontentView之后使用butterknife
            setContentView(layId);
            initWiget();
            initData();
        } else {
            finish();
        }
    }

    protected void initWindows() {

    }

    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    protected abstract int getContentLayoutId();

    /**
     * ButterKnife是一个专注于Android系统的View注入框架，能够简化代码，再也不用写大量的findViewById以及setOnClickListener代码
     */
    protected void initWiget() {
        ButterKnife.bind(this);
    }

    protected void initData() {

    }


    /**
     * 按界面导航返回键时候的逻辑处理，关闭当前页面
     *
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onNavigateUp();
    }

    /**
     * 按home键返回键时逻辑处理,activity和fragment之间的关联
     */
    @Override
    public void onBackPressed() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null && fragments.size() > 0) {
            for (Fragment f : fragments) {
                if (f instanceof BaseFragment) {
                    if (((BaseFragment) f).onBackPressed()) {
                        return;
                    }
                }
            }
        }
        super.onBackPressed();
        finish();
    }
}
