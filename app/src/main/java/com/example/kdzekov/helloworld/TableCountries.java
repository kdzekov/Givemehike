package com.example.kdzekov.helloworld;

import android.provider.BaseColumns;

/**
 * Created by KDzekov on 9/10/2017.
 */

public final class TableCountries {

    private TableCountries(){}

    /* Inner class that defines the table contents */
    public static class CountryEntry implements BaseColumns {
        public static final String TABLE_NAME = "Countries";
        public static final String CODE = "code";
        public static final String NAME = "name";
    }

}
