package com.sample.multiplechoicequiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xcode on 2017-04-05.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "questions_database";
    // Current version of database
    private static final int DATABASE_VERSION = 1;
    // Name of table
    private static final String TABLE_QUESTION = "questions";
    // All fields used in database table
    private static final String KEY_ID = "id";
    private static final String QUESTION = "question";
    private static final String OP1 = "option1";
    private static final String OP2 = "option2";
    private static final String OP3 = "option3";
    private static final String OP4 = "option4";
    private static final String ANSWER = "answer";

    public static String TAG = "my_tag";

    // Client Table Create Query in this string
    private static final String CREATE_TABLE_QUESTION = "CREATE TABLE "
            + TABLE_QUESTION + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + QUESTION + " TEXT,"
            + OP1 + " TEXT, " + OP2 + " TEXT, " + OP3 + " TEXT," + OP4 + " TEXT, " + ANSWER +" TEXT);";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUESTION); // create client table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_QUESTION); // drop table if exists
        onCreate(db);
    }

    public long addQuestionDetail(QuestionSet set) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(QUESTION, set.question);
        values.put(OP1, set.op1);
        values.put(OP2, set.op2);
        values.put(OP3, set.op3);
        values.put(OP4, set.op4);
        values.put(ANSWER, set.answer);

        // insert row in client table

        long insert = db.insert(TABLE_QUESTION, null, values);
        return insert;
    }

    public QuestionSet getQuestion(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        // SELECT * FROM client WHERE id = ?;
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTION + " WHERE "
                + KEY_ID + " = " + id;
        Log.d("queryRun", selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        QuestionSet set = new QuestionSet();
        set.id = c.getInt(c.getColumnIndex(KEY_ID));
        set.question = c.getString(c.getColumnIndex(QUESTION));
        set.op1 = c.getString(c.getColumnIndex(OP1));
        set.op2 = c.getString(c.getColumnIndex(OP2));
        set.op3 = c.getString(c.getColumnIndex(OP3));
        set.op4 = c.getString(c.getColumnIndex(OP4));
        set.answer = c.getString(c.getColumnIndex(ANSWER));


        return set;
    }

    public List<QuestionSet> getAllQuestionsList() {
        List<QuestionSet> questionArrayList = new ArrayList<QuestionSet>();

        String selectQuery = "SELECT  * FROM " + TABLE_QUESTION;
        Log.d(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                QuestionSet set = new QuestionSet();
                set.id = c.getInt(c.getColumnIndex(KEY_ID));
                set.question = c.getString(c.getColumnIndex(QUESTION));
                set.op1 = c.getString(c.getColumnIndex(OP1));
                set.op2 = c.getString(c.getColumnIndex(OP2));
                set.op3 = c.getString(c.getColumnIndex(OP3));
                set.op4 = c.getString(c.getColumnIndex(OP4));
                set.answer = c.getString(c.getColumnIndex(ANSWER));


                // adding to Clients list
                questionArrayList.add(set);
            } while (c.moveToNext());
        }
        Log.v("OKAY", DatabaseUtils.dumpCursorToString(c));
        return questionArrayList;
    }
}
