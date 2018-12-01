package test.craftsvilla.com.craftsvillatest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;


public class DbHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "Info.db";

    public static final String TABLENAME = "Checkout_data";
    private static final int DBVERSION = 1;
    private static final String ID = "id";
    private static final String ADVERTISE = "advertise";
    private static final String ADDIMAGE = "ad_image";
    private static final String NAME = "name";
    private static final String DESC = "description";
    private static final String QUANTITY = "quantity";
    private static final String PRICE = "price";
    private static final String TAX = "tax";
    private static final String DELIVERYFEE = "deliverfee";


    public DbHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " +
                TABLENAME + "("
                + ADVERTISE + " TEXT,"
                + ADDIMAGE + " TEXT,"
                + NAME + " TEXT,"
                + DESC + " TEXT,"
                + QUANTITY + " TEXT,"
                + PRICE + " TEXT,"
                + TAX + " TEXT,"
                + DELIVERYFEE + " TEXT,"
                + ID + " TEXT);");


    }


    public void insertData(SQLiteDatabase db, String advertise, String image, String name, String desc, int price, int tax, int delivery, int quant, String id) {

        ContentValues values = new ContentValues();
        values.put(ADVERTISE, advertise);
        values.put(ADDIMAGE, image);
        values.put(NAME, name);
        values.put(DESC, desc);
        values.put(PRICE, price);
        values.put(TAX, String.valueOf(tax));
        values.put(PRICE, String.valueOf(price));
        values.put(DELIVERYFEE, String.valueOf(delivery));
        values.put(QUANTITY, String.valueOf(quant));
        values.put(ID, id);
        db.insert(TABLENAME, null, values);
        Log.d("dbhelper", "insertData: inserted");


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }


    public ArrayList<Advertise> GetAllData(SQLiteDatabase sqLiteDatabase) {
        ArrayList<Advertise> result = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLENAME, null);


        while (c.moveToNext()) {
            result.add(new Advertise(
                            c.getString(c.getColumnIndex(ADVERTISE)),
                            c.getString(c.getColumnIndex(ADDIMAGE)),
                            c.getString(c.getColumnIndex(NAME)),
                            c.getString(c.getColumnIndex(DESC)),
                            Integer.parseInt(c.getString(c.getColumnIndex(QUANTITY))),
                            Integer.parseInt(c.getString(c.getColumnIndex(PRICE))),
                            Integer.parseInt(c.getString(c.getColumnIndex(TAX))),
                            Integer.parseInt(c.getString(c.getColumnIndex(DELIVERYFEE))),
                            c.getString(c.getColumnIndex(ID))
                    )
            );
        }
        c.close();
        return result;
    }


    public void DeleteRecords(SQLiteDatabase sqLiteDatabase, String id) {
        String sql = "DELETE FROM " + TABLENAME + " WHERE " + ID + "=" + "'" + id + "'";
        sqLiteDatabase.execSQL(sql);
    }


    public void UpdateRecords(SQLiteDatabase sqLiteDatabase,String advertise, String image, String name, String desc, int price, int tax, int delivery, int quant, String id) {


        sqLiteDatabase.execSQL("UPDATE  " + TABLENAME +
                " SET " + ADVERTISE + " = '" + advertise + "', "
                + ADDIMAGE + " = '" + image + "', "
                + NAME + " = '" + name + "', "
                + DESC + " = '" + desc + "', "
                + PRICE + " = '" + String.valueOf(price) + "', "
                + TAX + " = '" + String.valueOf(tax) + "', "
                + DELIVERYFEE + " = '" + String.valueOf(delivery) + "', "
                + QUANTITY + " = '" + String.valueOf(quant) + "', "
                + " WHERE " + ID + " = '" + id + "';");


    }


    public void DeleteAllRecords(SQLiteDatabase sqLiteDatabase) {
        String sql = "DELETE FROM " + TABLENAME;
        sqLiteDatabase.execSQL(sql);
    }


    public void dropdatabases(SQLiteDatabase sqLiteDatabase, String table_name) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS table_name");

    }


}
