package com.example.team.plugutils;

import android.annotation.SuppressLint;
import android.companion.DeviceFilter;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.provider.Telephony;
import android.support.v4.view.PagerAdapter;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.UUID;

/**
 * author : ZhiG
 * e-mail : 1120121044@163.com
 * date   : 2019/9/2615:03
 * desc   :
 * package: StuSpace:
 */
public class DeviceUuidFactory {
    private static final String DEVICE_UUID_FILE_NAME = ".tobindevid.conf";
    private static final String DEVICE_IMEI_FILE_NAME = ".tobinimei.conf";
    private static final String PREFS_FILE = "tobindevice_id.xml";
    private static final String PREFS_DEVICE_ID = "device_id";
    private static final String PREFS_IMEI = "imei";
    private static UUID uuid;
    private static String imei;
    private static DeviceUuidFactory deviceUuidFactory;

    public static DeviceUuidFactory getInstance(Context context) {
        if (deviceUuidFactory == null) {
            deviceUuidFactory = new DeviceUuidFactory(context);
        }
        return deviceUuidFactory;
    }

    @SuppressLint("MissingPermission")
    private DeviceUuidFactory(Context context) {
        if (uuid == null) {
            synchronized (DeviceUuidFactory.class) {
                if (uuid == null) {
                    String id = SharedPrefrenceUtils.getString(context, PREFS_DEVICE_ID);
                    String imeiStr = SharedPrefrenceUtils.getString(context, PREFS_IMEI);
                    if (TextUtils.isEmpty(id)) {
                        if (checkPremission(context, premission.WRITE_EXTERNAL_STORAGE)) {
                            id = recoverFromSD(DEVICE_UUID_FILE_NAME);
                        }
                    }
                    if (TextUtils.isEmpty(imeiStr)) {
                        if (checkPremission(context, premission.WRITE_EXTERNAL_STORAGE)) {
                            imeiStr = recoverFromSD(DEVICE_IMEI_FILE_NAME);
                        }
                    }
                    if (!TextUtils.isEmpty(imeiStr)) {
                        imei = imeiStr;
                        SharedPrefrenceUtils.saveString(context, PREFS_IMEI, imei);
                        if (checkPremission(context, premission.WRITE_EXTERNAL_STORAGE)) {
                            saveToSD(imei, DEVICE_IMEI_FILE_NAME);
                        }
                    }
                    if (!TextUtils.isEmpty(id)) {
                        SharedPrefrenceUtils.saveString(context, PREFS_DEVICE_ID, uuid.toString());
                        if (checkPremission(context, premission.WRITE_EXTERNAL_STORAGE)) {
                            saveToSD(uuid.toString(), DEVICE_UUID_FILE_NAME);
                        }
                    }

                }

            }
        } else {
            String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            String tmDevice, tmSerial;
            tmDevice = "";
            tmSerial = "";
            if (checkPremission(context, premission.READ_PHONE_STATE)) {
                TelephonyManager systemService = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                tmDevice = systemService.getDeviceId();
                tmSerial = systemService.getSimSerialNumber();

            }
            if (TextUtils.isEmpty(tmDevice) || TextUtils.isEmpty(tmSerial) || tmDevice.equals("000000000000000")) {
                if (!TextUtils.isEmpty(androidId) && !"9774d56d682e549c".equals(androidId) && Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) {
                    uuid = UUID.nameUUIDFromBytes(androidId.getBytes());
                } else {
                    uuid = UUID.randomUUID();
                }
            } else {
                uuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
            }
            if (!TextUtils.isEmpty(tmDevice)) {
                imei = tmDevice;
                SharedPrefrenceUtils.saveString(context, PREFS_IMEI, imei);
                if (checkPremission(context, premission.WRITE_EXTERNAL_STORAGE)) {
                    saveToSD(imei, DEVICE_IMEI_FILE_NAME);
                }

            }
            SharedPrefrenceUtils.saveString(context, PREFS_DEVICE_ID, uuid.toString());
            if (checkPremission(context, premission.WRITE_EXTERNAL_STORAGE)) {
                saveToSD(uuid.toString(), DEVICE_UUID_FILE_NAME);
            }
        }
    }

    private static void saveToSD(String uuid, String fileName) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File dirpath = new File(path);
        File targetFile = new File(dirpath, fileName);
        if (targetFile != null) {
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFile));
                outputStreamWriter.write(uuid);
                outputStreamWriter.flush();
                outputStreamWriter.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private boolean checkPremission(Context context, premission premName) {
        int i = context.checkCallingOrSelfPermission("android.permission." + premName.toString());
        return i == PackageManager.PERMISSION_GRANTED;
    }

    private enum premission {
        READ_PHONE_STATE,
        WRITE_EXTERNAL_STORAGE
    }

    private static String recoverFromSD(String fileName) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        //获得文件路径
        File dir = new File(path);
        File uuidFile = new File(dir, fileName);
        if (!dir.exists() || uuidFile.exists()) {
            return null;
        }
        try {
            FileReader fileReader = new FileReader(uuidFile);
            StringBuffer stringBuffer = new StringBuffer();
            char[] chars = new char[100];
            int readCount;
            while ((readCount = fileReader.read(chars)) > 0) {
                stringBuffer.append(chars, 0, readCount);
            }
            fileReader.close();
            return stringBuffer.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UUID getDeviceUuid() {
        return uuid;
    }
}
