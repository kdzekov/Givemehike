package com.example.kdzekov.helloworld;

import android.provider.BaseColumns;

/**
 * Created by KDzekov on 9/10/2017.
 */

public final class TableMountains {

    private TableMountains(){}

    public static class MountainEntry implements BaseColumns {
        public static final String TABLE_NAME = "Mountains";
        public static final String COUNTRY_CODE = "country_code";
        public static final String CODE = "code";
        public static final String NAME = "name";
    }

}
