package logemils.ljw.com.logemailslibrary;


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by jw.li on 2017/2/9 0009.
 */
public class MyAuthenticator extends Authenticator {
    String userName = null;
    String password = null;
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
    public MyAuthenticator(String username, String password) throws RuntimeException{
        this.userName = username;
        this.password = password;
    }
}
