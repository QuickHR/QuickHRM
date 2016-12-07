package com.example.exceloid_android.exceloid_mobileapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Exceloid-Android on 26-09-2016.
 */

public class Address_Information extends Fragment {

    TextView address,defaults,present, permanent,location_type,more_button;
    TextView line1,line2,city_name,region,region_name,country,postal_code,postal_add;
    JSONParser jsonParser;
    String user_name,pass_word;
    LinearLayout layout;
    private Animation animShow;
    String address_String="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
       View addres=inflater.inflate(R.layout.address_information,container,false);

        address=(TextView)addres.findViewById(R.id.address);
        defaults=(TextView)addres.findViewById(R.id.defaults);
        present=(TextView)addres.findViewById(R.id.present);
        permanent=(TextView)addres.findViewById(R.id.permanent);
        location_type=(TextView)addres.findViewById(R.id.location_type);
        line1=(TextView)addres.findViewById(R.id.addressline1);
        line2=(TextView)addres.findViewById(R.id.addressline2);
        city_name=(TextView)addres.findViewById(R.id.cityname);
        region=(TextView)addres.findViewById(R.id.region);
        region_name=(TextView)addres.findViewById(R.id.regionname);
        country=(TextView)addres.findViewById(R.id.country);
        postal_code=(TextView)addres.findViewById(R.id.postalcode);
        postal_add=(TextView)addres.findViewById(R.id.postaladd);
        more_button=(TextView)addres.findViewById(R.id.more_button);
        layout=(LinearLayout)addres.findViewById(R.id.layout_visibility);
        animShow = AnimationUtils.loadAnimation(getContext(), R.anim.view_show);

        more_button.setVisibility(View.INVISIBLE);

        jsonParser=new JSONParser();

        DatabaseHandler db = new DatabaseHandler(getContext());
        HashMap<String, String> user = db.getUserDetails();
        user_name = user.get("user_name");
        pass_word = user.get("pass_word");

        // Toast.makeText(getContext(), user_name+"="+pass_word, Toast.LENGTH_SHORT).show();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ConnectivityManager cn=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf=cn.getActiveNetworkInfo();
        if(nf != null && nf.isConnected()==true ) {
            //   new GetProfile_Information().execute();

            if(address_String!=null&&address_String.trim().length()!=0){

                getString_AddressData();

            }else {
                address_Information();
            }
        }else {
           // Toast.makeText(getContext(), "Network Not Available", Toast.LENGTH_SHORT).show();
        }

        more_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.startAnimation(animShow);
                layout.setVisibility(View.VISIBLE);
                moreAddress_Information();
            }
        });

    return  addres;
    }

    public void address_Information() {

        JSONArray valuesobject = jsonParser.getJSONFromUrl(IPAddress_Class.URL_PROFILE_INFORMATION + "l=" + user_name + "&p=" + pass_word + "");
        try {

            if(valuesobject.length()>0) {
                JSONObject objectvalues = valuesobject.getJSONObject(2);

                if(objectvalues.length()>0){

                JSONObject json = objectvalues.getJSONObject("address");

                    address_String=""+json;

                    getString_AddressData();

            }else{ }}else{ }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getString_AddressData(){

        try{

            JSONObject reader=new JSONObject(address_String);

            if(!reader.isNull("default")){
                if(!reader.getString("default").equals("null")){
                    if(reader.getString("default").toString().length()>0){
                        defaults.setText(reader.getString("default"));
                    }else{
                        defaults.setText("- - -");
                    }
                }else{   defaults.setText("- - -"); }
            }else{       defaults.setText("- - -");  }

            if(!reader.isNull("present")){
                if(!reader.getString("present").equals("null")){
                    if(reader.getString("present").toString().length()>0){
                        present.setText(reader.getString("present"));
                    }else{
                        present.setText("- - -");
                    }
                }else{present.setText("- - -");     }
            }else{    present.setText("- - -");     }

            if(!reader.isNull("permanent")){
                if(!reader.getString("permanent").equals("null")){
                    if(reader.getString("permanent").toString().length()>0){
                        permanent.setText(reader.getString("permanent"));
                    }else{
                        permanent.setText("- - -");
                    }
                }else{  permanent.setText("- - -");   }
            }else{      permanent.setText("- - -");   }

            if(!reader.isNull("locationType")){
                if(!reader.getString("locationType").equals("null")){
                    if(reader.getString("locationType").toString().length()>0){
                        location_type.setText(reader.getString("locationType"));
                    }else{
                        location_type.setText("- - -");
                    }
                }else{  location_type.setText("- - -");   }
            }else{      location_type.setText("- - -");   }

            JSONObject object1 = reader.getJSONObject("location");

            if(!object1.isNull("cityname")){
                if(!object1.getString("cityname").equals("null")){
                    if(object1.getString("cityname").toString().length()>0){
                        address.setText(object1.getString("cityname"));
                    }else{
                        address.setText("- - -");
                    }
                }else{  address.setText("- - -");   }
            }else{      address.setText("- - -");   }
            more_button.setVisibility(View.VISIBLE);

        }catch (Exception e){

        }

    }

    public void moreAddress_Information() {

      //  JSONArray valuesobject = jsonParser.getJSONFromUrl(IPAddress_Class.URL_PROFILE_INFORMATION + "l=" + user_name + "&p=" + pass_word + "");
        try {

          //  JSONObject objectvalues = valuesobject.getJSONObject(2);

           // JSONObject json = objectvalues.getJSONObject("address");

            JSONObject json_obj=new JSONObject(address_String);
            JSONObject object1=json_obj.getJSONObject("location");

            if(!object1.isNull("addressline1")){
                if(!object1.getString("addressline1").equals("null")){
                    if(object1.getString("addressline1").toString().length()>0){
                        line1.setText(object1.getString("addressline1"));
                    }else{
                        line1.setText("- - -");
                    }
                }else{   line1.setText("- - -"); }
            }else{       line1.setText("- - -");  }

            if(!object1.isNull("addressline2")){
                if(!object1.getString("addressline2").equals("null")){
                    if(object1.getString("addressline2").toString().length()>0){
                        line2.setText(object1.getString("addressline2"));
                    }else{
                        line2.setText("- - -");
                    }
                }else{line2.setText("- - -");     }
            }else{    line2.setText("- - -");     }

            if(!object1.isNull("cityname")){
                if(!object1.getString("cityname").equals("null")){
                    if(object1.getString("cityname").toString().length()>0){
                        city_name.setText(object1.getString("cityname"));
                    }else{
                        city_name.setText("- - -");
                    }
                }else{  city_name.setText("- - -");   }
            }else{      city_name.setText("- - -");   }

            if(!object1.isNull("region")){
                if(!object1.getString("region").equals("null")){
                    if(object1.getString("region").toString().length()>0){
                        region.setText(object1.getString("region"));
                    }else{
                        region.setText("- - -");
                    }
                }else{  region.setText("- - -");   }
            }else{      region.setText("- - -");   }

            if(!object1.isNull("regionname")){
                if(!object1.getString("regionname").equals("null")){
                    if(object1.getString("regionname").toString().length()>0){
                        region_name.setText(object1.getString("regionname"));
                    }else{
                        region_name.setText("- - -");
                    }
                }else{  region_name.setText("- - -");   }
            }else{      region_name.setText("- - -");   }

            if(!object1.isNull("country")){
                if(!object1.getString("country").equals("null")){
                    if(object1.getString("country").toString().length()>0){
                        country.setText(object1.getString("country"));
                    }else{
                        country.setText("- - -");
                    }
                }else{  country.setText("- - -");   }
            }else{      country.setText("- - -");   }

            if(!object1.isNull("postalcode")){
                if(!object1.getString("postalcode").equals("null")){
                    if(object1.getString("postalcode").toString().length()>0){
                        postal_code.setText(object1.getString("postalcode"));
                    }else{
                        postal_code.setText("- - -");
                    }
                }else{  postal_code.setText("- - -");   }
            }else{      postal_code.setText("- - -");   }

            if(!object1.isNull("postaladd")){
                if(!object1.getString("postaladd").equals("null")){
                    if(object1.getString("postaladd").toString().length()>0){
                        postal_add.setText(object1.getString("postaladd"));
                    }else{
                        postal_add.setText("- - -");
                    }
                }else{  postal_add.setText("- - -");   }
            }else{      postal_add.setText("- - -");   }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
