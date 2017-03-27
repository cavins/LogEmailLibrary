package service;

import android.app.Service;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import common.LogCommon;
import logemils.ljw.com.logemailslibrary.EmailSender;
import logemils.ljw.com.logemailslibrary.LogFile;
import logemils.ljw.com.logemailslibrary.MyAuthenticator;

/**
 * Created by jw.li on 2017/2/8 0008.
 */

public class SendLogEmailService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.e("ljwdump", "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("ljwdump", "onStartCommand");
        Bundle bundle = intent.getExtras();
        String ex = loadLog(bundle.get("ex").toString());
        sendDumpEmails(ex);
        try {
            Thread.sleep(10*1000);
            exit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("ljwdump", "服务异常返回值" + super.onStartCommand(intent, flags, startId));
        return super.onStartCommand(intent, flags, startId);
    }



    private void sendDumpEmails(final String logContent) {
        new Thread() {
            public void run() {
                Log.e("ljwdump", "email send start!");
                if(EmailSender.sendTextMail(logContent)) {
                    Log.e("ljwdump", "email send succeccful!");
                    LogFile.deleteLog();
                } else {
                    Log.e("ljwdump", "email send failed!");
                }
            }
        }.start();
    }
    private String loadLog(String e) {
        StringBuilder sb = new StringBuilder();
        sb.append(new Date().toString() + "----\n");
        sb.append(e);
        sb.append("----\n");
        String logContent = sb.toString();
        if(logContent == null)
            logContent = "无异常日志";

        return logContent;
    }

    private void exit() {
        Log.e("ljwdump", "服务结束");
        stopService(new Intent(this, SendLogEmailService.class));
    }
}
