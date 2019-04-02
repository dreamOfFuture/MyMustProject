package com.miki.myrealstartswoop.SQLiteDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLite extends SQLiteOpenHelper {
    public static final String SQname = "swoop_book.db ";
    public static int version = 1;
    public static final String tb_name = "tb_collect_book";
    public static SQLiteDatabase db;
    public static Context mYcontext;
    public static final String book_name = "book_name";
    public static final String book_url = "book_url";
    public static final String book_reading = "book_reading";
    public static final String book_sort = "book_sort";
    public static final String book_icon = "book_image";
    public static final String book_web="book_web";

    public MySQLite(Context context) {
        super(context, SQname, null, version);
        mYcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // db.execSQL("ALTER TABLE person ADD phone VARCHAR(12)");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public void insertData(String bookName, String bookUrl, String bookRead, String conSort, byte[] bookIcon,String bookWeb) {
        db = SQLiteDatabase.openDatabase(mYcontext.getDatabasePath(SQname).getPath(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        ContentValues values = new ContentValues();
        values.put(book_name, bookName);
        values.put(book_url, bookUrl);
        values.put(book_reading, bookRead);
        values.put(book_sort, conSort);
        values.put(book_icon, bookIcon);
        values.put(book_web,bookWeb);
        db.insert(tb_name, null, values);
        db.close();
    }

    public static void deleteData(String bookName) {
        db = SQLiteDatabase.openDatabase(mYcontext.getDatabasePath(SQname).getPath(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        db.execSQL("delete from tb_collect_book where book_name =?", new String[]{bookName});
        db.close();
    }

    public  void updateData(String size, String sort,String bookName ) {
        db = SQLiteDatabase.openDatabase(mYcontext.getDatabasePath(SQname).getPath(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        db.execSQL("update tb_collect_book set book_reading=?,book_sort=? where book_name =?" , new String[]{size, sort,bookName});
        db.close();
    }

    public static Cursor selectData(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select * from tb_collect_book", null);
        return cursor;
    }

    public void createTable() {
        db = SQLiteDatabase.openDatabase(mYcontext.getDatabasePath(SQname).getPath(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                tb_name + " (book_id integer primary key autoincrement,"
                + book_name + "  varchar(50),"
                + book_url + "   varchar(1000) unique,"
                + book_reading + "  integer,"
                + book_sort + "   integer,"
                + book_web + "  varcher(50),"
                + book_icon + "  BLOB NOT NULL)");
        db.close();
    }
    public  Cursor checkBook(String book_name){
            db = SQLiteDatabase.openDatabase(mYcontext.getDatabasePath(SQname).getPath(), null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Cursor cursor = db.query(tb_name,null,"book_name=?",new String[]{book_name},null,null,null);
            return  cursor;
    }
}
