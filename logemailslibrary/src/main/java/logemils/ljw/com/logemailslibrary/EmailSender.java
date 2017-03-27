package logemils.ljw.com.logemailslibrary;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.util.Log;


import common.LogCommon;


public class EmailSender {

//    private static String mailServerHost = "stmp.qq.com";
//    private static String mailServerPort = "465";
//    private static String fromAddress = "2171036689@qq.com";
//    private static String toAddress = "2171036689@qq.com";
//    private static String userName = "2171036689@qq.com";
//    private static String password = "a123456a";
//    private static String subject = "崩溃信息";

    public static boolean sendTextMail(String content) {
        MyAuthenticator auth = null;
        try {
            auth = new MyAuthenticator(LogCommon.getUSERNAME(), LogCommon.getPASSWORD());
        } catch (RuntimeException e) {
            Log.i("ljwdump", "尝试发送邮件异常..." + e.toString());
            e.printStackTrace();
        }
        Properties p = new Properties();
        p.put("mail.smtp.host", LogCommon.MAILSERVERHOST);
        p.put("mail.smtp.port", LogCommon.MAILSERVERPORT);
        p.put("mail.smtp.auth", "true");
        Session sendMailSession = Session.getDefaultInstance(p, auth);
        try {
            Log.i("ljwdump", "正在尝试发送邮件...");
            Message mailMessage = new MimeMessage(sendMailSession);
            Address from = new InternetAddress(LogCommon.FROMADDRESS);
            mailMessage.setFrom(from);
            Address[] tos = null;
            tos = new InternetAddress[1];
            tos[0] = new InternetAddress(LogCommon.TOADDRESS);
            mailMessage.setRecipients(Message.RecipientType.TO, tos);
            // 加上地区
            mailMessage.setSubject(LogCommon.SUBJECT);
            mailMessage.setSentDate(new Date());
            mailMessage.setText(content);
            Log.i("ljwdump", "邮件信息装填完成...");
            Transport.send(mailMessage);
            Log.i("ljwdump", "发送邮件完成...");
            return true;
        } catch(MessagingException e) {
            e.printStackTrace();
            Log.e("ljwdump", "邮件发送失败" + e.toString());
        }
        return false;
    }
    
}
