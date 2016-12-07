package com.example.exceloid_android.exceloid_mobileapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Exceloid-Android on 04-10-2016.
 */

public class Dictionary_Information extends Fragment {

	ListView listView;
	JSONParser jsonParser;
	String user_name,pass_word,autoName,name,email,name1,email1;
    ArrayList<HashMap<String, String>> contactList;
	AutoCompleteTextView autoCompleteTextView;
	ArrayList<String> responseList;
    String dictionaty_String="";

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View dictionary=inflater.inflate(R.layout.dictionary_information,container,false);

		listView=(ListView)dictionary.findViewById(R.id.dictionary_listview);
		autoCompleteTextView=(AutoCompleteTextView)dictionary.findViewById(R.id.autoCompleteTextView);
		DatabaseHandler db = new DatabaseHandler(getContext());
		HashMap<String, String> user = db.getUserDetails();
		user_name = user.get("user_name");
		pass_word = user.get("pass_word");
        contactList = new ArrayList<>();
        responseList = new ArrayList<String>();
        jsonParser=new JSONParser();
        autoCompleteTextView.setThreshold(1);
		// Toast.makeText(getContext(), user_name+"="+pass_word, Toast.LENGTH_SHORT).show();
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		ConnectivityManager cn=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo nf=cn.getActiveNetworkInfo();
		if(nf != null && nf.isConnected()==true ) {
		  new GetProfile_Information().execute();
		 // dictionary_Information();
		}else {
			Toast.makeText(getContext(), "Network Not Available", Toast.LENGTH_SHORT).show();
		}
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String s1=parent.getItemAtPosition(position).toString();
                String[] s2=s1.split("=");
                String s3=s2[1];
               /* String[] s4=s3.split("=");
                String s5=s4[1];*/
                String names=s3.substring(0,s3.length()-1);
               // Toast.makeText(getContext(),""+names,Toast.LENGTH_SHORT).show();

                Bundle b = new Bundle();
                Intent in=new Intent(getContext(),Dictionary_Activity.class);
                b.putString("name",names);
                b.putInt("int", position);
                in.putExtras(b);
                startActivity(in);
            }
        });

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                autoName=""+s;
                textView_Data();
               }
        });
		return dictionary;
	}
	public class GetProfile_Information extends AsyncTask<Void, Void, Void> {

		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(getContext());
			progressDialog.setMessage("Getting Data...");
			progressDialog.setCancelable(false);
			progressDialog.show();
		}
		@Override
		protected Void doInBackground(Void... params) {

			JSONArray values_obj=jsonParser.getJSONFromUrl(IPAddress_Class.URL_DICTIONARY_INFORMATION+"l="+ user_name +"&p="+ pass_word +"");

			try{
                if(values_obj.length()>0){
                    dictionaty_String=""+values_obj;
                    responseList.clear();
                    contactList.clear();
				for (int i = 0; i < values_obj.length(); i++) {
					JSONObject c = values_obj.getJSONObject(i);

                    responseList.add(c.getString("firstName"));

                    if(!c.isNull("firstName")){
                        if(!c.getString("firstName").equals("null")){
                            if (c.getString("firstName").toString().length()>0){
                               name = c.getString("firstName");
                            }else{
                                name="- - -";
                            }
                        }else{  name="- - -";  }
                    }else{      name="- - -";  }

                    if(!c.isNull("email")){
                        if(!c.getString("email").equals("null")){
                            if (c.getString("email").toString().length()>0){
                                email = c.getString("email");
                            }else{
                                email="- - -";
                            }
                        }else{  email="- - -";  }
                    }else{      email="- - -";  }

					HashMap<String, String> contact = new HashMap<>();
					contact.put("name", name);
					//contact.put("email", email);

					contactList.add(contact);
				} }else{ }
			}catch (Exception e){
                e.printStackTrace();
			}return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// super.onPostExecute(values_object);

            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            ListAdapter adapter = new SimpleAdapter(getContext(), contactList,R.layout.dictionary_listview, new String[]{"name"}, new int[]{R.id.employ_name});
            listView.setAdapter(adapter);

        autoCompleteTextView.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, responseList));

            }
		}
	}
    public void textView_Data(){

        if(autoCompleteTextView.getText().toString()!=null&&autoCompleteTextView.getText().toString().trim().length()!=0){
            if(dictionaty_String!=null&&dictionaty_String.trim().length()!=0){
                listView.setAdapter(null);
                getDictionary_StrindData();
            }else{
              //  Toast.makeText(getContext(),"working11111",Toast.LENGTH_SHORT).show();
            }}else{
            if(dictionaty_String!=null&&dictionaty_String.trim().length()!=0){
                listView.setAdapter(null);
                dictionary_Information();
            }else{
                //  Toast.makeText(getContext(),"working11111",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void getDictionary_StrindData(){

        try{
            JSONArray js=new JSONArray(dictionaty_String);

            if(js.length()>0) {
                contactList.clear();
                for (int j = 0; j < js.length(); j++) {
                    JSONObject cs = js.getJSONObject(j);

                    if (autoName.equals(cs.getString("firstName"))) {
                        if(!cs.isNull("firstName")){
                            if(!cs.getString("firstName").equals("null")){
                                if (cs.getString("firstName").toString().length()>0){
                                    name1 = cs.getString("firstName");
                                }else{
                                    name1="- - -";
                                }
                            }else{  name1="- - -";  }
                        }else{      name1="- - -";  }

                        if(!cs.isNull("email")){
                            if(!cs.getString("email").equals("null")){
                                if (cs.getString("email").toString().length()>0){
                                    email1 = cs.getString("email");
                                }else{
                                    email1="- - -";
                                }
                            }else{  email1="- - -";  }
                        }else{      email1="- - -";  }

                        HashMap<String, String> contacts = new HashMap<>();
                        contacts.put("name", name1);
                        //contacts.put("email", email1);

                        contactList.add(contacts);
                    } else {

                    }
                }
                ListAdapter adapter1 = new SimpleAdapter(getContext(), contactList, R.layout.dictionary_listview, new String[]{"name"}, new int[]{R.id.employ_name});
                listView.setAdapter(adapter1);
            } else{ }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

     public void dictionary_Information(){

      //  JSONArray valuesobject=jsonParser.getJSONFromUrl(IPAddress_Class.URL_DICTIONARY_INFORMATION+"l="+ user_name +"&p="+ pass_word +"");

        try{
           // dictionaty_String=""+valuesobject;
            JSONArray js1=new JSONArray(dictionaty_String);
            contactList.clear();
            for (int g = 0; g < js1.length(); g++) {
                JSONObject c = js1.getJSONObject(g);

                if(!c.isNull("firstName")){
                    if(!c.getString("firstName").equals("null")){
                        if (c.getString("firstName").toString().length()>0){
                            name = c.getString("firstName");
                        }else{
                            name="- - -";
                        }
                    }else{  name="- - -";  }
                }else{      name="- - -";  }

                if(!c.isNull("email")){
                    if(!c.getString("email").equals("null")){
                        if (c.getString("email").toString().length()>0){
                            email = c.getString("email");
                        }else{
                            email="- - -";
                        }
                    }else{  email="- - -";  }
                }else{      email="- - -";  }

                HashMap<String, String> contact = new HashMap<>();
                contact.put("name", name);
                //contact.put("email", email);

                contactList.add(contact);
            }
            ListAdapter adapter = new SimpleAdapter(getContext(), contactList,R.layout.dictionary_listview, new String[]{"name"}, new int[]{R.id.employ_name});

            listView.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
