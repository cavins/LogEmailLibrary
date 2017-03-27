package logemils.ljw.com.logemailslibrary;

import java.io.File;
import java.io.FileWriter;


import android.os.Environment;
import android.util.Log;

import common.LogCommon;

public class LogStore {

//	private static String getSDPath() {
//		File sdDir = null;
//		boolean sdCardExist = Environment.getExternalStorageState().equals(
//				Environment.MEDIA_MOUNTED);
//		if (sdCardExist) {
//            sdDir = Environment.getExternalStorageDirectory();
//        } else {
//            sdDir = new File(Config.FILE_PATH);
//        }
//		return sdDir.toString();
//	}

	public static String sdcard;

	public static void writeLog(String str) {
//		if (sdcard == null)
//			sdcard = getSDPath();

		try {
			File dir = new File(LogCommon.LOG_DIR);
			if (!dir.exists())
				dir.mkdirs();
			File f = new File(LogCommon.LOG_DIR + "netlog");
			if (!f.exists())
				f.createNewFile();
			FileWriter fw = new FileWriter(LogCommon.LOG_DIR + "netlog");
			fw.flush();
			fw.write(str);
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
