package Utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/2/5 0005.
 */

public class InerUtils {
    /**
     * 判断邮箱是否合法
     * author:jianwen.li
     */
    public static boolean isEmailAddressValid(String emailAddress) {
        if(TextUtils.isEmpty(emailAddress))
            return false;
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(emailAddress);
        return matcher.matches();
    }
}
