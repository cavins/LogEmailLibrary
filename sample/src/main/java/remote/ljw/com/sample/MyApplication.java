package remote.ljw.com.sample;

import android.app.Application;

import common.DumpUtils;

/**
 * ljw：Administrator on 2017/3/27 0027 14:47
 */
public class MyApplication extends Application{

    //在onCreate方法设置需要定制的功能

    /**
     * 更多功能请查看源码
     */
    @Override
    public void onCreate() {
        super.onCreate();

        DumpUtils.getInstance().initialize(getApplicationContext());//初始化插件
        DumpUtils.getInstance().setEnabledSendLog(true);//开启发送日志邮件
        DumpUtils.getInstance().setEnabledCacheLog(true);//开启缓存日志到本地

    }
}
