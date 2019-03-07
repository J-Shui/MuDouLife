package com.mudoulife;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.mudoulife.common.Globals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Author : J-Shui[YJS]
 * DATE : 2018/12/5
 * email : jshui_920124@163.com
 * package_name : com.mudoulife
 * project : MuDouLife
 */
public class MuDouCrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "MuDouCrashHandler";
    private Thread.UncaughtExceptionHandler mDefaultHandler;// 系统默认的UncaughtException处理类
    private Context mContext;// 程序的Context对象
    private Map<String, String> info = new HashMap<String, String>();// 用来存储设备信息和异常信息

    private MuDouApplication mApp = null;

    /** 保证只有一个CrashHandler实例 */
    MuDouCrashHandler(Context context, MuDouApplication app) {
        init(context);
        mApp = app;
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        this.mContext = context;

        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();// 获取系统默认的UncaughtException处理器
        Thread.setDefaultUncaughtExceptionHandler(this);// 设置该CrashHandler为程序的默认处理器
    }
    /**
     * 当UncaughtException发生时会转入该重写的方法来处理
     */
    @Override
    public void uncaughtException(Thread t, Throwable ex) {
        Log.e(TAG, "uncaughtException: "+ex.getMessage()+", "+ex.getLocalizedMessage());
        try {
            new Thread() {
                public void run() {
//                    程序出现异常, 即将关闭...
                    Looper.prepare();
                    //Toast.makeText(mContext, "程序出现异常,准备重启...", 0).show();
                    Toast.makeText(mContext, "程序出现异常, 即将关闭...", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }.start();
            // 收集设备参数信息
            collectDeviceInfo(mContext);
            // 保存日志文件
            saveCrashInfo2File(ex);

            Thread.sleep(3000);
        } catch(Exception e) {
            e.printStackTrace();
        }

//        android.os.Process.killProcess(android.os.Process.myPid());
//
//        mDefaultHandler.uncaughtException(thread, ex);
        killApp();
    }
    /**
     * 收集设备参数信息
     *
     * @param context
     */
    public void collectDeviceInfo(Context context) {
        try {
            PackageManager pm = context.getPackageManager();// 获得包管理器
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_ACTIVITIES);// 得到该应用的信息，即主Activity
            if (pi != null) {
                String versionName = pi.versionName == null ? "null"
                        : pi.versionName;
                String versionCode = pi.versionCode + "";
                info.put("versionName", versionName);
                info.put("versionCode", versionCode);
            }
            Field[] fields = Build.class.getDeclaredFields();// 反射机制
            for (Field field : fields) {
                field.setAccessible(true);
                info.put(field.getName(), field.get("").toString());
                Log.d(TAG, field.getName() + ":" + field.get(""));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void saveCrashInfo2File(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : info.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\r\n");
        }
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        ex.printStackTrace(pw);
        Throwable cause = ex.getCause();
        // 循环着把所有的异常信息写入writer中
        while (cause != null) {
            cause.printStackTrace(pw);
            cause = cause.getCause();
        }
        pw.close();// 记得关闭
        String result = writer.toString();
        sb.append(result);
        // 将异常信息上传服务器
        Log.e(TAG, "当前的信息为"+sb.toString());

        // 保存文件
        String strPath = getAccessableStorageFolderFile();
        strPath += ("/" + Globals.APP_CRASH_FOLDER);
        if(!TextUtils.isEmpty(strPath)) {
            File folder = new File(strPath);
            if(!folder.exists())
                folder.mkdirs();
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd-HH-mm-ss");// 用于格式化日期,作为日志文件名的一部分
            String strTime = format.format(new Date(System.currentTimeMillis()));
            String fileName = Globals.APP_CRASH_FILE_TEMP_NAME  + strTime+".txt";
            try {
                File crashFile = new File(strPath, fileName);
                if(!crashFile.exists())
                    crashFile.createNewFile();
                Log.d(TAG, "save crash info to path:"+crashFile.getAbsolutePath());
                FileOutputStream fos = new FileOutputStream(crashFile.getAbsolutePath());
                fos.write(sb.toString().getBytes());
                fos.flush();
                fos.close();
            } catch(Exception e) {
                Log.e(TAG, "saveCrashInfo2File showError: "+e);
            }
        }
//        if(Environment.
//                getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            SimpleDateFormat format = new SimpleDateFormat(
//                     "yyyy-MM-dd-HH-mm-ss");// 用于格式化日期,作为日志文件名的一部分
//            String strTime = format.format(new Date(System.currentTimeMillis()));
//            String fileName = "lekan_kids_crash_"+strTime+".txt";
//            try {
//                //File dir = new File(Environment.getExternalStorageDirectory() + "//crash//");
//                File dir = mContext.getDir("crash", Context.MODE_WORLD_READABLE);
//                if(!dir.exists())
//                    dir.mkdir();
//                Log.d(TAG, "saveCrashInfo2File create folder:"+dir.getAbsolutePath());
//                File crashFile = new File(dir.getAbsoluteFile(), fileName);
//                if(!crashFile.exists())
//                    crashFile.createNewFile();
//                Log.d(TAG, "save crash info to path:"+crashFile.getAbsolutePath());
//                FileOutputStream fos = new FileOutputStream(crashFile.getAbsolutePath());
//                fos.write(sb.toString().getBytes());
//                fos.flush();
//                fos.close();
//            } catch(Exception e) {
//                Log.e(TAG, "saveCrashInfo2File showError: "+e);
//            }
//        }
        // String time = format.format(new Date());
        // String fileName = "crash.txt";
        // if (Environment.getExternalStorageState().equals(
        // Environment.MEDIA_MOUNTED)) {
        // try {
        // // File dir = new File(Environment.getExternalStorageDirectory()
        // // + "\\crash\\");
        // // Log.e("crashhandler", "getdir");
        // // File dir = new File(mContext.getFilesDir().getAbsolutePath());
        // // if (!dir.exists())
        // // dir.mkdir();
        // // FileOutputStream fos = new FileOutputStream(dir + fileName);
        // // fos.write(sb.toString().getBytes());
        // // fos.close();
        //
        // FileOutputStream
        // outStream=mContext.openFileOutput("crash.txt",Context.MODE_WORLD_READABLE);
        // outStream.write(sb.toString().getBytes());
        // outStream.close();
        // Toast.makeText(mContext.getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
        //
        // Socket s = new Socket("192.168.1.63", 10010);
        // //读取文件
        // FileInputStream inStream=mContext.openFileInput("crash.txt");
        // //发出数据
        // // PrintWriter out = new PrintWriter(s.getOutputStream(),true);
        //
        // OutputStream stream =s.getOutputStream();
        //
        // // ByteArrayOutputStream stream=new ByteArrayOutputStream();
        // byte[] buffer=new byte[1024];
        // int length=-1;
        // while((length=inStream.read(buffer))!=-1) {
        // // stream.write(buffer,0,length);
        // stream.write(buffer, 0, length);
        // }
        // Log.e("crashhandler", "output");
        //
        // return fileName;
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // }
    }

    private void killApp() {
        if(mApp != null) {
            mApp.stopCrashedApp();
            mApp = null;
        }
    }
    private String getAccessableStorageFolderFile() {
        String strPath = null;
        Map<String, String> envs = System.getenv();
        for(Map.Entry<String, String> entry : envs.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if(key.contains("STORAGE") && isFolderAccessable(value)) {
                strPath = value;
                break;
            }
        }
        if(TextUtils.isEmpty(strPath)) {
            strPath = getMountableFolder();
        }
        return strPath;
    }

    private String getMountableFolder() {
        String strPath = null;
        File mnt = new File("/mnt");
        if(mnt != null) {
            for(File file : mnt.listFiles()) {
                if(file != null &&
                        file.canExecute() &&
                        file.canRead() &&
                        file.canWrite()) {
                    strPath = file.getAbsolutePath();
                    break;
                }
            }
        }
        return strPath;
    }

    private boolean isFolderAccessable(String strPath) {
        boolean bAccessable = false;
        if(!TextUtils.isEmpty(strPath)) {
            File folder = new File(strPath);
            bAccessable = (folder != null) &&
                    folder.canExecute() &&
                    folder.canRead() &&
                    folder.canWrite();
        }
        return bAccessable;
    }

}
