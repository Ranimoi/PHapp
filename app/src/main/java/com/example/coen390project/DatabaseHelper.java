package com.example.coen390project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String TAG = "DatabaseHelper";
    public DatabaseHelper(Context context)
    {
        super(context,Config.DATABASE_NAME,null,Config.DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_PROFILE = "CREATE TABLE " + Config.PH_TABLE_NAME +
                "(" + Config.COLUMN_PH_READING + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Config.COLUMN_MEASUREMENT_DATE + " TEXT NOT NULL)";
        Log.d(TAG, CREATE_TABLE_PROFILE);

        sqLiteDatabase.execSQL(CREATE_TABLE_PROFILE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public long insertpH(PH pH)
    {
        long tableID = -1;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Config.COLUMN_PH_READING, pH.getPH_VALUE());
        contentValues.put(Config.COLUMN_MEASUREMENT_DATE, pH.getMEASUREMENT_DATE());

        try
        {
            tableID = db.insertOrThrow(Config.PH_TABLE_NAME, null, contentValues);
        }
        catch (SQLException e)
        {
            Log.d(TAG,"EXCEPTION: " + e);
            Toast.makeText(context, "Operation Failed!: " + e, Toast.LENGTH_LONG).show();
        }
        finally {
            db.close();
        }
        return tableID;
    }

    public List<PH> getAllValues()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try
        {
            cursor = db.query(Config.PH_TABLE_NAME, null, null,null,null, null, null);
            if(cursor != null)
            {
                if(cursor.moveToFirst())
                {
                    List<PH> pHValues = new ArrayList<>();
                    Log.d(TAG,"Column" +cursor.getColumnIndex(Config.COLUMN_PH_READING));
                    do {
                        Float value = cursor.getFloat(cursor.getColumnIndex(Config.COLUMN_PH_READING));
                        String date = cursor.getString(cursor.getColumnIndex(Config.COLUMN_MEASUREMENT_DATE));

                        pHValues.add(new PH(value, date));

                    }while (cursor.moveToNext());
                    return pHValues;
                }
            }
        }
        catch (SQLException e)
        {
            Log.d(TAG,"EXCEPTION: " + e);
            Toast.makeText(context, "Operation Failed!: " + e, Toast.LENGTH_LONG).show();
        }
        finally {
            if(cursor != null)
                cursor.close();

            db.close();
        }
        return Collections.emptyList();

    }



}
