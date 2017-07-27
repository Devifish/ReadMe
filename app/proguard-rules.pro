# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Android\SDK/tools/proguard/proguard-android.txt
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

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keepattributes Signature
-keepattributes Exceptions

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes *Annotation*,SourceFile,LineNumberTable

# 不混淆R类里及其所有内部static类中的所有static变量字段
-keepclassmembers class **.R$* {
  public static <fields>;
}

# 不混淆实现Parcelable接口的entity类
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# 不混淆引用的三方库
-keep public class * implements butterknife.Unbinder { public <init>(**, android.view.View); }
-keep class butterknife.**

-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }

-keep class org.jsoup.**

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
