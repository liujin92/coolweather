package co.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import co.db.CoolWeatherOpenHelper;
import co.model.City;
import co.model.Province;

/**
 * Created by ubuntu on 16-6-14.
 */
public class CoolWeatherDB {

    public static final String DB_NAME = "cool_weather";
    public static final int VERSION = 1;
    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;

    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper ch = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = ch.getWritableDatabase();
    }

    public synchronized static CoolWeatherDB getInstance(Context context) {
        if (coolWeatherDB == null) {
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }

    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues cv = new ContentValues();
            cv.put("province_name", province.getProvinceName());
            cv.put("province_code", province.getProviceCode());
            db.insert("Province", null, cv);
        }
    }

    public List<Province> loadProvinve() {
        List<Province> list = new ArrayList<Province>();
        Cursor c = db.query("Province", null, null, null, null, null,null);
        if (c.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(c.getInt(c.getColumnIndex("id")));
                province.setProvinceName(c.getString(c.getColumnIndex("province_name")));
                province.setProviceCode(c.getString(c.getColumnIndex("province_code")));
                list.add(province);
            } while (c.moveToNext());
        }
        return list;
    }

    public void saveCity(City city) {
        if (city != null) {
            ContentValues cv = new ContentValues();
            cv.put("province_id", city.getProvince_id());
            cv.put("city_name", city.getCityName());
            cv.put("city_code", city.getCityCode());
            db.insert("City", null, cv);
        }
    }

    public List<City> loadCity() {
        List<City> list = new ArrayList<City>();
        Cursor c = db.query("City", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                City city = new City();
                city.setCityCode(c.getString(c.getColumnIndex("city_code")));
                city.setProvince_id(c.getInt(c.getColumnIndex("province_id")));
                city.setCityName(c.getString(c.getColumnIndex("city_name")));
                list.add(city);
            } while (c.moveToNext());
        }
        return list;
    }
}
