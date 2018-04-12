package mx.com.dloza.crudcar.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;
import mx.com.dloza.crudcar.model.Car;

/**
 * Created by Daniel on 9/16/2017.
 */

public class CarDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "car.db";
    private static final int DATABASE_VERSION = 3 ;
    public static final String TABLE_NAME = "Car";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CAR_NAME = "name";
    public static final String COLUMN_CAR_MODEL = "model";
    public static final String COLUMN_CAR_MARK = "mark";
    public static final String COLUMN_CAR_TRANSMISSION = "transmission";
    public static final String COLUMN_CAR_COMBUSTIBLE = "combustible";
    public static final String COLUMN_CAR_YEAR = "year";
    public static final String COLUMN_CAR_IMAGE = "image";


    public CarDBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CAR_NAME + " TEXT NOT NULL, " +
                COLUMN_CAR_MODEL + " TEXT NOT NULL, " +
                COLUMN_CAR_MARK + " TEXT NOT NULL, " +
                COLUMN_CAR_TRANSMISSION + " TEXT NOT NULL, " +
                COLUMN_CAR_COMBUSTIBLE + " TEXT NOT NULL, " +
                COLUMN_CAR_YEAR + " NUMBER NOT NULL," +
                COLUMN_CAR_IMAGE + " BLOB NOT NULL);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
    /**create record**/
    public void saveNewCar(Car car) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CAR_NAME, car.getName());
        values.put(COLUMN_CAR_MODEL, car.getModel());
        values.put(COLUMN_CAR_MARK, car.getMark());
        values.put(COLUMN_CAR_TRANSMISSION, car.getTransmission());
        values.put(COLUMN_CAR_COMBUSTIBLE, car.getCombustible());
        values.put(COLUMN_CAR_YEAR, car.getYear());
        values.put(COLUMN_CAR_IMAGE, car.getImage());

        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    /**Query records, give options to filter results**/
    public List<Car> carList(String filter) {
        String query;
        if(filter.equals("")){
            //regular query
            query = "SELECT  * FROM " + TABLE_NAME;
        }else{
            //filter results by filter option provided
            query = "SELECT  * FROM " + TABLE_NAME + " ORDER BY "+ filter;
        }

        List<Car> carLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Car car;

        if (cursor.moveToFirst()) {
            do {
                car = new Car();

                car.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                car.setName(cursor.getString(cursor.getColumnIndex(COLUMN_CAR_NAME)));
                car.setModel(cursor.getString(cursor.getColumnIndex(COLUMN_CAR_MODEL)));
                car.setMark(cursor.getString(cursor.getColumnIndex(COLUMN_CAR_MARK)));
                car.setTransmission(cursor.getString(cursor.getColumnIndex(COLUMN_CAR_TRANSMISSION)));
                car.setCombustible(cursor.getString(cursor.getColumnIndex(COLUMN_CAR_COMBUSTIBLE)));
                car.setYear(cursor.getString(cursor.getColumnIndex(COLUMN_CAR_YEAR)));
                car.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_CAR_IMAGE)));
                carLinkedList.add(car);
            } while (cursor.moveToNext());
        }


        return carLinkedList;
    }

    /**Query only 1 record**/
    public Car getCar(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE _id="+ id;
        Cursor cursor = db.rawQuery(query, null);

        Car receivedCar = new Car();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            receivedCar.setName(cursor.getString(cursor.getColumnIndex(COLUMN_CAR_NAME)));
            receivedCar.setModel(cursor.getString(cursor.getColumnIndex(COLUMN_CAR_MODEL)));
            receivedCar.setMark(cursor.getString(cursor.getColumnIndex(COLUMN_CAR_MARK)));
            receivedCar.setTransmission(cursor.getString(cursor.getColumnIndex(COLUMN_CAR_TRANSMISSION)));
            receivedCar.setCombustible(cursor.getString(cursor.getColumnIndex(COLUMN_CAR_COMBUSTIBLE)));
            receivedCar.setCombustible(cursor.getString(cursor.getColumnIndex(COLUMN_CAR_YEAR)));
            receivedCar.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_CAR_IMAGE)));
        }

        return receivedCar;

    }


    /**delete record**/
    public void deleteCarRecord(long id, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE _id='"+id+"'");
        Toast.makeText(context, "Deleted successfully.", Toast.LENGTH_SHORT).show();

    }

    /**update record**/
    public void updateCarRecord(long carId, Context context, Car updatedcar) {
        SQLiteDatabase db = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        db.execSQL("UPDATE  "+TABLE_NAME+" SET name ='"+ updatedcar.getName() + "', model ='" + updatedcar.getModel()
                + "', mark ='"+ updatedcar.getMark() +"', transmission ='"+ updatedcar.getTransmission() +
                "', combustible ='"+ updatedcar.getCombustible() +"', year ='"+ updatedcar.getYear() +"', image ='"+ updatedcar.getImage() +
                "'  WHERE _id='" + carId + "'");
        Toast.makeText(context, "Updated successfully.", Toast.LENGTH_SHORT).show();


    }




}
