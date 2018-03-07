package com.luyang.xmessage;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by luyang on 2018/3/7.
 */

public class Presenter implements Ipresenter {
    Iview view;
    IStudentService service;


    public Presenter(Iview view) {
        this.view = view;
    }

    @Override
    public void search() {
        //耗时操作，开启loading
        String info = view.getInput();
        Log.e("INFO",info);
        if (TextUtils.isEmpty(info)) {
            return;
        }
        service = new StudentService();
        String res = service.searchStudentInfo(info);
        Log.e("RES",res);
        //关闭loading
        view.setMessage(res);


    }
}
