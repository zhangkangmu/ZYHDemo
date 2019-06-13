/*
 * Copyright (c) 2013. wyouflf (wyouflf@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.study.hong.zyhdemo.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * zyh的LogUtils
 */
public class LogUtil {

    private static boolean DEBUG = true;
    public static void v(String tag, String msg){
        logger("v",tag,msg);
    }

    public static void d(String tag, String msg){
        logger("d",tag,msg);
    }

    public static void i(String tag, String msg){
        logger("i",tag,msg);
    }

    public static void w(String tag, String msg){
        logger("w",tag,msg);
    }

    public static void e(String tag, String msg){
        logger("e",tag,msg);
    }

    private static void logger(String priority, String tag, String msg){
        if (!DEBUG){
            return;
        }
        switch (priority){
            case "v":
                Log.v(tag,msg);
                break;
            case "d":
                Log.d(tag,msg);
                break;
            case "i":
                Log.i(tag,msg);
                break;
            case "w":
                Log.w(tag,msg);
                break;
            default:
                Log.e(tag,msg);
        }
    }
}
