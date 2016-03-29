package br.com.chfmr.appmotos.motorcycleProfessional.model;

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
public class MotorcyclerProfessional implements Serializable {

    public Integer idMotoProfessional;
    public String nameMotoProfessional;
    public String nameMotoCompany;
    public String cellPhone;
    public String whatsApp;
    public String addressCompany;
    public String addressDistrictCompany;
    public String expediente;
    public String urlLogoMarcaCompany;
    public Boolean is24Hours;
    public Boolean hasWhatsApp;

    public static final String ENVIROMENT_URL = "http://decasamento.online/";
    public static final String ADVERTISER_URL_JSON = ENVIROMENT_URL + "food-category/motocyrcler-company";
    public static String OBJECT_JSON_MOTO_COMPANY = "motoCompany";

    public MotorcyclerProfessional(Integer idMotoProfessional,
                                   String nameMotoProfessional,
                                   String nameMotoCompany,
                                   String cellPhone,
                                   String whatsApp,
                                   String addressCompany,
                                   String addressDistrictCompany,
                                   String expediente,
                                   String urlLogoMarcaCompany,
                                   Boolean is24Hours,
                                   Boolean hasWhatsApp
    ){

        this.idMotoProfessional = idMotoProfessional;
        this.nameMotoProfessional = nameMotoProfessional;
        this.nameMotoCompany = nameMotoCompany;
        this.cellPhone = cellPhone;
        this.whatsApp = whatsApp;
        this.addressCompany = addressCompany;
        this.addressDistrictCompany = addressDistrictCompany;
        this.expediente = expediente;
        this.urlLogoMarcaCompany = urlLogoMarcaCompany;
        this.is24Hours = is24Hours;
        this.hasWhatsApp = hasWhatsApp;
    }

    public static List<MotorcyclerProfessional> searchMotorcyclerProfessionalJson(){

        try {
            HttpURLConnection connecting = AppHttp.connect(ADVERTISER_URL_JSON);
            int response = connecting.getResponseCode();

            Log.i("APPGUIA", "response connect:" + response);

            if(response == HttpURLConnection.HTTP_OK){
                InputStream is = connecting.getInputStream();
                JSONObject json = new JSONObject(AppHttp.bytesToString(is));
                Log.i("APPBUS", "searchAdvertiserJson" + json);
                return readJsonMotorcyclerProfessional(json);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static List<MotorcyclerProfessional> readJsonMotorcyclerProfessional(JSONObject json) throws JSONException {

        List<MotorcyclerProfessional> listMotorcyclerCompany = new ArrayList<MotorcyclerProfessional>();

        try {

            String objectJson = OBJECT_JSON_MOTO_COMPANY;

            JSONArray jsonMotorcyclerCompany = json.getJSONArray(objectJson);
            Log.i("APP", "jsonMotorcyclerCompany:" + jsonMotorcyclerCompany.length());

            for (int count = 0; count < jsonMotorcyclerCompany.length(); count++) {

                Log.i("jsonMotorcyclerCompany", "jsonMotorcyclerCompany contador:" + count);
                JSONObject objectMotorcyclerCompany = jsonMotorcyclerCompany.getJSONObject(count);
                //Log.i("APP jsonMotorcyclerCompany", "jsonMotorcyclerCompany:" + objectMotorcyclerCompany);

                MotorcyclerProfessional advertiser = new MotorcyclerProfessional(
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
