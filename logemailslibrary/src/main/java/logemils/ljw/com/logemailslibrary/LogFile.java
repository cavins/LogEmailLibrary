package logemils.ljw.com.logemailslibrary;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import android.os.Environment;
import android.util.Log;

import common.LogCommon;

public class LogFile {
//    public static final String getLogDir() = Util.getSDPath()+ "/pm/log/";
//    public static final String getLogName() = getCurrentDateString() + ".txt";

    public static void writeErrorLog(Throwable ex) {
        ByteArrayOutputStream baos = null;
        PrintStream printStream = null;
        FileOutputStream fo = null;
        try {
            baos = new ByteArrayOutputStream();
            printStream = new PrintStream(baos);
            ex.printStackTrace(printStream);
            byte[] data = baos.toByteArray();
            
            makesureDir();
            File f = new File(LogCommon.getLogDir(), LogCommon.getLogName());
            fo = new FileOutputStream(f, true);
            fo.write((new Date().toString() + "----\n").getBytes());
            fo.write(data);
            fo.write("----\n".getBytes());
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            close(fo);
            close(printStream);
            close(baos);
        }
    }
    
    private static void close(Closeable c) {
        if(c == null)
            return;
        try {
            c.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    private static void makesureDir() {
        File dir = new File(LogCommon.getLogDir());
        if(!dir.exists())
            dir.mkdirs();
    }
    
    public static boolean deleteLog() {
        File f = new File(LogCommon.getLogDir(), LogCommon.getLogName());
        return f.delete();
    }
    
    public static String loadLog() {
        File f = new File(LogCommon.getLogDir(), LogCommon.getLogName());
        if(! f.exists())
            return null;
        StringBuilder sbuilder = new StringBuilder();
        FileInputStream fin = null;
        BufferedReader br = null;
        try {
            fin = new FileInputStream(f);
            new InputStreamReader(fin);
            br = new BufferedReader(new InputStreamReader(fin));
            String line = null;
            while((line = br.readLine()) != null) {
                sbuilder.append(line);
                sbuilder.append("\n");
            }
            return sbuilder.toString();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(br != null)
                try {
                    br.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
        }
        return null;
    }
}
