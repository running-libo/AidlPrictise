package com.libo.myaidlpractise;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;
import com.example.aidlmodule.UserService;
import com.example.aidlmodule.bean.UserBean;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //客户端绑定服务端
        Intent intent1 = new Intent(this, UserService.class);
        bindService(intent1, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 创建ServiceConnection对象，绑定服务连接上Service后，使用IBinder对象，
     * IGetUser.Stub.asInterface(service)获取接口，调用接口方法远程调用服务端的方法进行处理，获取想要的结果。
     */
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AIDLService aidlService = AIDLService.Stub.asInterface(service);
            try {
                UserBean userBean = aidlService.getUserBean();
                Toast.makeText(getApplication(),"收到服务端进程数据：用户：" + userBean.getName() + "------ 年龄：" + userBean.getAge(),Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //退出解绑
        unbindService(serviceConnection);
    }
}