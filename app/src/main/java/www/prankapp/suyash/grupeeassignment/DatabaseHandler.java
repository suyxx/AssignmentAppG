package www.prankapp.suyash.grupeeassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "assignment.db";
    public static final int DATABASE_VERSION = 1;
    public static final String imageUrl = "imageUrl";
    public static final String date = "date";
    public static final String tablename = "assignment";
    public static final String KEY_ID = "_id";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + tablename + " (" +
                KEY_ID + " TEXT PRIMARY KEY AUTOINCREMENT, " +
                date + " TEXT NOT NULL, " +
                imageUrl + " TEXT NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tablename);
        onCreate(db);
    }

    public boolean insertDateAndUrl(String timedate, String imageUrldog) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(date, timedate);
        contentValues.put(imageUrl, imageUrldog);
        long result = db.insert(tablename, null, contentValues);
        db.close();
        if (result == -1) {
            return false;
        } else
            return true;
    }


    public ArrayList<String> returnDate() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> dates = new ArrayList<String>();
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT ( " + date + " ) " +
                "FROM " + tablename, null);
        while (cursor.moveToNext()) {
            String sp = cursor.getString(cursor.getColumnIndex(date));
            if (sp != null) {
                dates.add(sp.trim());
            }
        }
        cursor.close();
        db.close();
        return dates;
    }

    public ArrayList<String> returnPicData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> imageUrls = new ArrayList<String>();
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT ( " + imageUrl + " ) " +
                "FROM " + tablename, null);
        while (cursor.moveToNext()) {
            String sp = cursor.getString(cursor.getColumnIndex(imageUrl));
            if (sp != null) {
                imageUrls.add(sp.trim());
            }
        }
        cursor.close();
        db.close();
        return imageUrls;
    }

}















