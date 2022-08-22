package com.snail.personinfo.logger;

import android.util.Log;

import com.snail.personinfo.BuildConfig;

/** Class for custom log
 *
 */
public class Logger {

    /** Default constructor
     *
     */
    public Logger() {
        Log.i("Logger", "Create object Logger");
    }

    /** Custom error log
     *
     * @param tag TAG
     * @param message error message
     */
    public void LogErr(String tag, String message) {
        if (BuildConfig.USE_LOG) {
            Log.e(tag, message);
        }
    }

    /** Custom warning log
     *
     * @param tag TAG
     * @param message warning message
     */
    public void LogWarn(String tag, String message) {
        if (BuildConfig.USE_LOG) {
            Log.w(tag, message);
        }
    }

    /** Custom info log
     *
     * @param tag TAG
     * @param message info message
     */
    public void LogInfo(String tag, String message) {
        if (BuildConfig.USE_LOG) {
            Log.i(tag, message);
        }
    }

    /** Custom debug log
     *
     * @param tag TAG
     * @param message debug message
     */
    public void LogDeb(String tag, String message) {
        if (BuildConfig.USE_LOG) {
            Log.d(tag, message);
        }
    }

    /** Custom Verbose log
     *
     * @param tag TAG
     * @param message verbose message
     */
    public void LogVerb(String tag, String message) {
        if (BuildConfig.USE_LOG) {
            Log.v(tag, message);
        }
    }


}
