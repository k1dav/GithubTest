package com.example.k1dave6412.b10409020_hw4.data;

import android.provider.BaseColumns;

/**
 * Created by k1dave6412 on 2017/5/4.
 */

public class DB_Contract {
    public static final class DB_Entry implements BaseColumns {
        public static final String TABLE_NAME = "memberList";
        public static final String COLUMN_GUEST_NAME = "guestName";
        public static final String COLUMN_GUEST_AGES = "guestAges";
        public static final String COLUMN_GUEST_SEX = "guestGender";
    }
}
