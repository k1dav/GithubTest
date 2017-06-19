package com.example.k1dave6412.b10409020_hw5.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by k1dave6412 on 2017/5/29.
 */

public class Contract {
    public static final String AUTHORITY = "com.example.k1dave6412.b10409020_hw5";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+AUTHORITY);

    public static final String PATH_TASKS = "tasks";

    public static final class Entry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build();

        public static final String TABLE_NAME = "tasks";

        public static final String COLUMN_ACTIVITY = "activity";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_CHECK = "Oncheck";
    }
}
