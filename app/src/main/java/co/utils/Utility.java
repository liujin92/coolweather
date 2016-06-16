package co.utils;

import android.text.TextUtils;

import co.model.City;
import co.model.County;
import co.model.Province;

/**
 * Created by ubuntu on 16-6-16.
 */
public class Utility {

    public synchronized static boolean handleProvinceResponse(CoolWeatherDB coolWeatherDB, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvince = response.split(",");
            if (allProvince != null && allProvince.length > 0) {
                for (String s : allProvince) {
                    String[] array = s.split("\\|");
                    Province province = new Province();
                    province.setProviceCode(array[0]);
                    province.setProvinceName(array[1]);
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    public synchronized static boolean handleCityResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCity = response.split(",");
            if (allCity != null && allCity.length != 0) {
                for (String s : allCity) {
                    String[] arrat = s.split("\\|");
                    City city = new City();
                    city.setCityName(arrat[1]);
                    city.setCityCode(arrat[0]);
                    city.setProvince_id(provinceId);
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    public synchronized static boolean handleContyResponse(CoolWeatherDB coolWeatherDB, String response, int cityId) {
        String[] allCounty = response.split(",");
        if (allCounty != null && allCounty.length != 0) {
            for (String s : allCounty) {
                String[] array = s.split("\\|");
                County county = new County();
                county.setCountyCode(array[0]);
                county.setCountyName(array[1]);
                county.setCity_id(cityId);
                coolWeatherDB.saveCounty(county);
            }
            return true;
        }
        return false;
    }
}
