package common;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import Utils.InerUtils;
import logemils.ljw.com.logemailslibrary.EmailSender;
import logemils.ljw.com.logemailslibrary.LogFile;

/**
 * Created by jw.li on 2017/2/5 0005.
 */

public class DumpUtils {

    private String logContent;

    private static DumpUtils dumpUtils;

    private Context context;

    public static synchronized DumpUtils getInstance() {

        if (dumpUtils == null) {

            dumpUtils = new DumpUtils();
        }
        return dumpUtils;
    }

    /**
     * 定制自己的收件邮箱,目前只支持网易邮箱
     * author：jw.li
     */
    public void setEmailAddress(String emailAddress, String passWord) {
        if(InerUtils.isEmailAddressValid(emailAddress) && !TextUtils.isEmpty(passWord)) {
            LogCommon.setAllUSERNAME(emailAddress);
            LogCommon.setPASSWORD(passWord);
        }
    }

    /**
     * 定制自己的App信息，可在出现崩溃时区分是哪个APP出现的崩溃
     * author：.li
     */
    public void setDumpAppName(String appName) {
        if(!TextUtils.isEmpty(appName))
            LogCommon.setAllLogName(appName);
    }

    /**
     * 定制自己的崩溃存放位置，也就是文件夹的名字
     * author：jw.li
     */
    public void setDumpLogFilePosition(String position) {
        if(!TextUtils.isEmpty(position))
            LogCommon.setAllDumpLogFilePosition(position);
    }

    /**
     * 是否开启将日志存到本地
     * author：jw.li
     */
    public void setEnabledCacheLog(boolean isCache){
        LogCommon.setIsCacheLog(isCache);
    }

    /**
     * 是否开启发送日志到邮箱
     * author：jw.li
     */
    public void setEnabledSendLog(boolean isSend) {
        LogCommon.setIsSendLog(isSend);
    }

    /**
     * 开放给外部app的初始化接口，需要放在自定义的application类的oncreate方法里
     * author：jw.li
     * @param context
     */
    public void initialize(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(handler);
        LogCommon.ConfigInfo();
        this.context = context;
    }

    Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {

        @Override
        public synchronized void uncaughtException(Thread thread, Throwable ex) {
            //将日志写到文件
            if(LogCommon.isCacheLog())
                LogFile.writeErrorLog(ex);
            if(LogCommon.isSendLog()) {
                LogCommon.startSendEmails(context, ex.toString());
            }
            exit();
        }
    };

    private void exit() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
