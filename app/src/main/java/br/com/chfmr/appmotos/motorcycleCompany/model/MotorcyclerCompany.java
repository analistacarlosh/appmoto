package br.com.chfmr.appmotos.motorcycleCompany.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import br.com.chfmr.appmotos.commonApp.network.AppHttp;

/**
 * Created by carlosfmr on 25/02/15.
 */
public class MotorcyclerCompany implements Serializable {

    public Integer idMotoCompany;
    public Integer idSector;
    public String nameMotoCompany;
    public String describe;
    public Boolean hasEmail;
    public String facebook;
    public String phone;
    public String cellPhone;
    public String whatsApp;
    public String address;
    public String addressDistrict;
    public String expediente;
    public String urlLogoMarca;
    public Boolean is24Hours;
    public Boolean hasWhatsApp;
    public String email;

    public static final String ENVIROMENT_URL = "http://decasamento.online/";
    public static final String ADVERTISER_URL_JSON = ENVIROMENT_URL + "food-category/advertiser";
    public static String OBJECT_JSON_MOTO_COMPANY = "motoCompany";

    public MotorcyclerCompany(Integer idMotoCompany,
                              Integer idSector,
                              String nameMotoCompany,
                              String describe,
                              Boolean hasEmail,
                              String facebook,
                              String phone,
                              String cellPhone,
                              String whatsApp,
                              String address,
                              String addressDistrict,
                              String expediente,
                              String urlLogoMarca,
                              Boolean is24Hours,
                              Boolean hasWhatsApp,
                              String email
    ){

        this.idMotoCompany = idMotoCompany;
        this.idSector = idSector;
        this.nameMotoCompany = nameMotoCompany;
        this.describe = describe;
        this.hasEmail = hasEmail;
        this.facebook = facebook;
        this.phone = phone;
        this.cellPhone = cellPhone;
        this.whatsApp = whatsApp;
        this.address = address;
        this.addressDistrict = addressDistrict;
        this.expediente = expediente;
        this.urlLogoMarca = urlLogoMarca;
        this.is24Hours = is24Hours;
        this.hasWhatsApp = hasWhatsApp;
        this.email = email;
    }

    public static List<MotorcyclerCompany> searchMotorcyclerCompanyJson(){

        try {
            HttpURLConnection connecting = AppHttp.connect(ADVERTISER_URL_JSON);
            int response = connecting.getResponseCode();

            Log.i("APPGUIA", "response connect:" + response);

            if(response == HttpURLConnection.HTTP_OK){
                InputStream is = connecting.getInputStream();
                JSONObject json = new JSONObject(AppHttp.bytesToString(is));
                Log.i("APPBUS", "searchAdvertiserJson" + json);
                return readJsonMotorcyclerCompany(json);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static List<MotorcyclerCompany> readJsonMotorcyclerCompany(JSONObject json) throws JSONException {

        List<MotorcyclerCompany> listMotorcyclerCompany = new ArrayList<MotorcyclerCompany>();

        try {

            String objectJson = OBJECT_JSON_MOTO_COMPANY;

            JSONArray jsonMotorcyclerCompany = json.getJSONArray(objectJson);
            Log.i("APP", "jsonMotorcyclerCompany:" + jsonMotorcyclerCompany.length());

            for (int count = 0; count < jsonMotorcyclerCompany.length(); count++) {

                Log.i("jsonMotorcyclerCompany", "jsonMotorcyclerCompany contador:" + count);
                JSONObject objectMotorcyclerCompany = jsonMotorcyclerCompany.getJSONObject(count);
                //Log.i("APP jsonMotorcyclerCompany", "jsonMotorcyclerCompany:" + objectMotorcyclerCompany);

                MotorcyclerCompany advertiser = new MotorcyclerCompany(
                        objectMotorcyclerCompany.getInt("idMotoCompany"),
                        objectMotorcyclerCompany.getInt("idSector"),
                        objectMotorcyclerCompany.getString("nameMotoCompany"),
                        objectMotorcyclerCompany.getString("describe"),
                        objectMotorcyclerCompany.getBoolean("hasEmail"),
                        objectMotorcyclerCompany.getString("facebook"),
                        objectMotorcyclerCompany.getString("phone"),
                        objectMotorcyclerCompany.getString("cellPhone"),
                        objectMotorcyclerCompany.getString("whatsApp"),
                        objectMotorcyclerCompany.getString("address"),
                        objectMotorcyclerCompany.getString("addressDistrict"),
                        objectMotorcyclerCompany.getString("expediente"),
                        objectMotorcyclerCompany.getString("urlLogoMarca"),
                        objectMotorcyclerCompany.getBoolean("is24Hours"),
                        objectMotorcyclerCompany.getBoolean("hasWhatsApp"),
                        objectMotorcyclerCompany.getString("email")
                );

                listMotorcyclerCompany.add(advertiser);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listMotorcyclerCompany;
    }
}
