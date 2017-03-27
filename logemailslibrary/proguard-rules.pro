# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-optimizationpasses 5
-dontusemixedcaseclassnames
-libraryjars  'D:\studiowork\LogEmails\logemailslibrary\libs\activation.jar'
-libraryjars  'D:\studiowork\LogEmails\logemailslibrary\libs\additionnal.jar'
-libraryjars  'D:\studiowork\LogEmails\logemailslibrary\libs\mail.jar'
-libraryjars 'D:\jdk1.7.0_80\jre\lib\rt.jar'
-libraryjars 'D:\AppData\Local\Android\sdk\platforms\android-23\android.jar'

-keep class common.DumpUtils{*;}
-keep public class * extends android.app.Service

-dontwarn javax.mail.**
-keep class javax.mail.** {*;}
-keep class java.**{*;}
   #class Utils.*

-keep public class [logemils.ljw.com.logemailslibrary].R$*{
    public static final int *;
}