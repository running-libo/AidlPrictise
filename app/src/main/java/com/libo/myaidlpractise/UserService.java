package com.libo.myaidlpractise;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import androidx.annotation.Nullable;
import com.libo.myaidlpractise.bean.UserBean;

/**
 * create by libo
 * create on 2021/7/6
 * description
 */
public class UserService extends Service {

    private Binder binder = new AIDLService.Stub(){

        @Override
        public UserBean getUserBean(){
            return new UserBean("小王",23);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
