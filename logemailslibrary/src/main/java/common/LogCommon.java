package common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import service.SendLogEmailService;

/**
 * Created by Administrator on 2017/2/4 0004.
 */

public class LogCommon {

    public static String testAppName;//对应的app名字
    public static String dumpLogFilePosition;

    public static String LOG_DIR;

    public static final int DEFAULTDELAYTIME = 1500;
    public static final int DEFAULTREPEATTIME = 200000;

    public static String getLogName() {
        return LOG_NAME;
    }


    public static void setAllLogName(String logName) {
        testAppName = logName;
        LOG_NAME = getCurrentDateString() + testAppName +  ".txt";
    }

    public static void setAllDumpLogFilePosition(String position) {
        dumpLogFilePosition = position;
        LOG_DIR = findFilePath(dumpLogFilePosition + "/log/");
    }

    public static String getLogDir() {
        return LOG_DIR;
    }

    public static void setLogDir(String logDir) {
        LOG_DIR = logDir;
    }

    public static String LOG_NAME;

    public static String MAILSERVERHOST;
    public static String MAILSERVERPORT;
    public static String FROMADDRESS;
    public static String TOADDRESS;
    public static String USERNAME;

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static void setPASSWORD(String PASSWORD) {
        LogCommon.PASSWORD = PASSWORD;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static void setAllUSERNAME(String USERNAME) {
        LogCommon.USERNAME = LogCommon.TOADDRESS = LogCommon.FROMADDRESS = USERNAME;
    }

    public static String PASSWORD;
    public static String SUBJECT;

    public static volatile boolean isCacheLog;//是否启用缓存日志到本地

    public static boolean isSendLog() {
        return isSendLog;
    }

    public static void setIsSendLog(boolean isSendLog) {
        LogCommon.isSendLog = isSendLog;
    }

    public static boolean isCacheLog() {
        return isCacheLog;
    }

    public static void setIsCacheLog(boolean isCacheLog) {
        LogCommon.isCacheLog = isCacheLog;
    }

    public static volatile boolean isSendLog;//是否启用发送日志到邮箱

    public static volatile boolean isSend;//防止同一日志发送多次

    public static void ConfigInfo() {
        testAppName = "APP";
        dumpLogFilePosition = "rayee";
        LOG_DIR = findFilePath(dumpLogFilePosition + "/log/");
        LOG_NAME = getCurrentDateString() + testAppName +  ".txt";
        MAILSERVERHOST = "smtp.163.com";
        MAILSERVERPORT = "25";
        FROMADDRESS = "lokihat@163.com";
        TOADDRESS = "lokihat@163.com";
        USERNAME = "lokihat@163.com";
        PASSWORD = "lokihat456";
        SUBJECT = testAppName + "崩溃信息";
        isSend = false;
        isCacheLog = false;
        isSendLog = false;
    }

    private static String getCurrentDateString() {
        String result = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date nowDate = new Date();
        result = sdf.format(nowDate);
//        result = "xxxx";
        return result;
    }
    private static String findFilePath(String fileName) {
        String filePath;
        boolean hasSDCard = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (hasSDCard) {
            filePath = Environment.getExternalStorageDirectory().toString()
                    + File.separator + fileName;
        } else
            filePath = Environment.getDownloadCacheDirectory().toString()
                    + File.separator + fileName;
        return filePath;
    }

    public static void startSendEmails(Context context, String ex) {
        Intent intent = new Intent(context, SendLogEmailService.class);
        Bundle bundle = new Bundle();
        bundle.putString("ex", ex);
        intent.putExtras(bundle);
        context.startService(intent);
    }

}
