package com.snail.personinfo.logger;

import android.util.Log;

import com.snail.personinfo.BuildConfig;

public class Logger {
    public void LogErr(String tag, String message) {
        if (BuildConfig.USE_LOG) {
            Log.e(tag, message);
        }
    }

    public void LogWarn(String tag, String message) {
        if (BuildConfig.USE_LOG) {
            Log.w(tag, message);
        }
    }

    public void LogInfo(String tag, String message) {
        if (BuildConfig.USE_LOG) {
            Log.i(tag, message);
        }
    }

    public void LogDeb(String tag, String message) {
        if (BuildConfig.USE_LOG) {
            Log.d(tag, message);
        }
    }

    public void LogVerb(String tag, String message) {
        if (BuildConfig.USE_LOG) {
            Log.v(tag, message);
        }
    }


}
