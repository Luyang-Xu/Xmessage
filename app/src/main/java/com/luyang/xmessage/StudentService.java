package com.luyang.xmessage;

/**
 * Created by luyang on 2018/3/7.
 */

public class StudentService implements IStudentService {
    @Override
    public String searchStudentInfo(String id) {
        return id+":"+id.hashCode();
    }
}
