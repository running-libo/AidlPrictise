package com.example.aidlmodule;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.example.aidlmodule.bean.UserBean;
import com.libo.myaidlpractise.AIDLService;

/**
 * create by libo
 * create on 2021/7/6
 * description
 */
public class UserService extends Service {
    private UserServiceStub userServiceStub;

    @Override
    public void onCreate() {
        super.onCreate();
        userServiceStub = new UserServiceStub();
    }

    class UserServiceStub extends AIDLService.Stub {

        @Override
        public UserBean getUserBean() throws RemoteException {
            return new UserBean("小王", 23);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return userServiceStub;
    }
}
