package com.example.finalexam.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.finalexam.db.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by computer on 18/12/2559.
 */

public class MemberList {

    private static MemberList mInstance;

    private Context mContext;

    private DatabaseHelper mHelper;

    private SQLiteDatabase mDb;

    private ArrayList<Member> mMemberList = new ArrayList<>();

    public static MemberList getInstance(Context context){
        if ( mInstance == null ){
            mInstance = new MemberList(context);
        }

        return mInstance;
    }

    private MemberList(Context context){
        this.mContext = context;
    }

    public void loadFromDatabase(){
        mMemberList.clear();

        mHelper = new DatabaseHelper(mContext);
        mDb = mHelper.getWritableDatabase();

        Cursor cursor = mDb.query(DatabaseHelper.TABLE_NAME, null,null, null, null, null, null);

        while( cursor.moveToNext() ){
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
            String username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PASSWORD));

            Member member = new Member(name, username, password);
            mMemberList.add(member);
        }
        cursor.close();
    }

    public ArrayList<Member> getMemberList() {
        return mMemberList;
    }

}
