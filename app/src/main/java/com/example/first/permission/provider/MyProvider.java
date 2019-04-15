package com.example.first.permission.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @ClassName: MyProvider
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/4/9 10:29
 * @Version: 1.0
 */
public class MyProvider extends ContentProvider {

    public static final int TABLE1_DIR = 0;
    public static final int TABLE1_ITEM = 1;
    public static final int TABLE2_DIR = 2;
    public static final int TABLE2_ITEM = 3;

    private static String VND_DIR = "vnd.android.cursor.dir/vnd.com.example.first.permission.provider";
    private static String VND_ITEM = "vnd.android.cursor.item/vnd.com.example.first.permission.provider";

    private static UriMatcher uriMatcher;

    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.first.permission.provider", "table1", TABLE1_DIR);
        uriMatcher.addURI("com.example.first.permission.provider", "table1/#", TABLE1_ITEM);
        uriMatcher.addURI("com.example.first.permission.provider", "table2", TABLE2_DIR);
        uriMatcher.addURI("com.example.first.permission.provider", "table2/#", TABLE2_ITEM);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:

                break;
            case TABLE1_ITEM:

                break;
            case TABLE2_DIR:

                break;
            case TABLE2_ITEM:

                break;
            default:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case TABLE1_DIR:
                return VND_DIR + "table1";
            case TABLE1_ITEM:
                return VND_ITEM + "table1";
            case TABLE2_DIR:
                return VND_DIR + "table2";
            case TABLE2_ITEM:
                return VND_ITEM + "table2";
            default:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
