package com.example.looting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "looting.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE session(id integer PRIMARY KEY, masuk text)");
        db.execSQL("CREATE TABLE user(id integer PRIMARY KEY AUTOINCREMENT, email text, katasandi text)");
        db.execSQL("INSERT INTO session(id, masuk) VALUES(1, 'kosong')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS session");
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    //check session
    public Boolean checkSession(String sessionValues) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM session WHERE masuk = ?", new String[]{sessionValues});
        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    //upgrade session
    public Boolean upgradeSession(String sessionValues, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("masuk", sessionValues);
        long update = db.update("session", contentValues, "id="+id, null);
        if (update == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    //insert user
    public Boolean insertUser(String email, String katasandi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("katasandi", katasandi);
        long insert = db.insert("user", null, contentValues);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    //check login
    public Boolean checkLogin(String email, String katasandi) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email = ? AND katasandi = ?", new String[]{email, katasandi});
        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    //queryData
    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    //insertData
    public void insertData(String namabarang, String deskbarang, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO BARANG VALUES (NULL, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, namabarang);
        statement.bindString(2, deskbarang);
        statement.bindBlob(3, image);

        statement.executeInsert();
    }

    //updateData
    public void updateData(String namabarang, String deskbarang, byte[] image, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE BARANG SET namabarang = ?, deskbarang = ?, image = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, namabarang);
        statement.bindString(2, deskbarang);
        statement.bindBlob(3, image);
        statement.bindDouble(4, (double)id);

        statement.execute();
        database.close();
    }

    //deleteData
    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM BARANG WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


}
