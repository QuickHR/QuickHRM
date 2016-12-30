package com.example.exceloid_android.quickhrmobileapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class TimeSheet_Activity extends Fragment implements OnClickListener{

    Button button;
    Spinner spinner;
    TextView one,two,three,four,five,six,seven,text_record,text_add_record,total_time,weekely_total_time;
    ListView record_ListView;
    String[] list=new String[]{"1 - 01-01-2016 to 07-01-2016","2 - 08-01-2016 to 14-01-2016","3 - 15-01-2016 to 21-01-2016","4 - 22-01-2016 to 28-01-2016"};
    String context[]={"Update","Delete"};
    Animation animShow;
    LinearLayout layout,create_record,show_record;
    String weekend_String,mon_String,tue_String,wed_String,thu_String,fri_String,sat_String,sun_String,update_Value,day_String;
    SQLiteDatabase database;
    private ArrayList<String> line_values_tasklist = new ArrayList<String >();
    private ArrayList<String> line_values_datalist = new ArrayList<String>();
    private ArrayList<String> line_values_Idlist = new ArrayList<String>();
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View time=inflater.inflate(R.layout.timesheet_activity,null);

        button=(Button)time.findViewById(R.id.sendfor_approval);
        spinner=(Spinner)time.findViewById(R.id.timesheet_spinner);
        one=(TextView)time.findViewById(R.id.one);
        two=(TextView)time.findViewById(R.id.two);
        three=(TextView)time.findViewById(R.id.three);
        four=(TextView)time.findViewById(R.id.four);
        five=(TextView)time.findViewById(R.id.five);
        six=(TextView)time.findViewById(R.id.six);
        seven=(TextView)time.findViewById(R.id.seven);
        text_add_record=(TextView)time.findViewById(R.id.text_add_record);
        total_time=(TextView)time.findViewById(R.id.total_time);
        weekely_total_time=(TextView)time.findViewById(R.id.weekely_total_time);
        record_ListView=(ListView)time.findViewById(R.id.record_listview);
        layout=(LinearLayout)time.findViewById(R.id.textview_layout);
        show_record=(LinearLayout)time.findViewById(R.id.showing_record);
        swipeRefreshLayout=(SwipeRefreshLayout)time.findViewById(R.id.swipe_container);
        one.setOnClickListener(this);two.setOnClickListener(this);three.setOnClickListener(this);
        four.setOnClickListener(this);five.setOnClickListener(this);six.setOnClickListener(this);seven.setOnClickListener(this);
        animShow = AnimationUtils.loadAnimation(getContext(), R.anim.translate);

        database = getActivity().openOrCreateDatabase("quickHRMTimeSheet", Context.MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS timeSheetValues (Weekend VARCHAR, Monday VARCHAR, Tuesday VARCHAR, Wednesday VARCHAR,Thursday VARCHAR, Friday VARCHAR, Saturday VARCHAR, Sunday VARCHAR, Client VARCHAR, Project VARCHAR, Task VARCHAR, Mon_Time VARCHAR, Tue_Time VARCHAR, Wed_Time VARCHAR, Thu_Time VARCHAR, Fri_Time VARCHAR, Sat_Time VARCHAR, Sun_Time VARCHAR, Description VARCHAR(3000), Id INTEGER PRIMARY KEY);");
        database.close();

        ArrayAdapter<String> adp=new ArrayAdapter<String>(getActivity(),R.layout.spinner,R.id.text_spinner,list);
        spinner.setAdapter(adp);

       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position1, long id) {

               weekend_String=parent.getItemAtPosition(position1).toString();
               int pos1=position1;

               for(int i=0;i<list.length;i++){

                   if(list[i].equals(weekend_String)){

                       if(pos1==0){
                           layout.startAnimation(animShow);
                           set_Text();
                           one.setText("1"); two.setText("2");  three.setText("3");
                           four.setText("4"); five.setText("5"); six.setText("6");
                           seven.setText("7");
                           day_String=one.getText().toString();
                           mon_String=one.getText().toString();tue_String=two.getText().toString();wed_String=three.getText().toString();
                           thu_String=four.getText().toString();fri_String=five.getText().toString();sat_String=six.getText().toString();sun_String=seven.getText().toString();
                           getting_DatabaseValues();
                       }else if(pos1==1){
                           layout.startAnimation(animShow);
                           set_Text();
                           one.setText("8"); two.setText("9"); three.setText("10");
                           four.setText("11"); five.setText("12");six.setText("13"); seven.setText("14");
                           day_String=one.getText().toString();
                           mon_String=one.getText().toString();tue_String=two.getText().toString();wed_String=three.getText().toString();
                           thu_String=four.getText().toString();fri_String=five.getText().toString();sat_String=six.getText().toString();sun_String=seven.getText().toString();
                           getting_DatabaseValues();
                       }else if(pos1==2){
                           layout.startAnimation(animShow);
                           set_Text();
                           one.setText("15");two.setText("16"); three.setText("17"); four.setText("18");
                           five.setText("19");six.setText("20"); seven.setText("21");
                           day_String=one.getText().toString();
                           mon_String=one.getText().toString();tue_String=two.getText().toString();wed_String=three.getText().toString();
                           thu_String=four.getText().toString();fri_String=five.getText().toString();sat_String=six.getText().toString();sun_String=seven.getText().toString();
                           getting_DatabaseValues();
                       }else if(pos1==3){
                           layout.startAnimation(animShow);
                           set_Text();
                           one.setText("22");  two.setText("23");three.setText("24");   four.setText("25");
                           five.setText("26");  six.setText("27"); seven.setText("28");
                           day_String=one.getText().toString();
                           mon_String=one.getText().toString();tue_String=two.getText().toString();wed_String=three.getText().toString();
                           thu_String=four.getText().toString();fri_String=five.getText().toString();sat_String=six.getText().toString();sun_String=seven.getText().toString();
                           getting_DatabaseValues();
                       }
                   }else{
                   }            }        }
           @Override
           public void onNothingSelected(AdapterView<?> parent) {
           }
       });
        text_add_record.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(weekend_String!=null && weekend_String.trim().length()!=0 && mon_String!=null && mon_String.trim().length()!=0 &&
                   tue_String!=null && tue_String.trim().length()!=0 && wed_String!=null && wed_String.trim().length()!=0 && thu_String!=null && thu_String.trim().length()!=0 &&
                   fri_String!=null && fri_String.trim().length()!=0 && sat_String!=null && sat_String.trim().length()!=0 && sun_String!=null && sun_String.trim().length()!=0){

                    Bundle b = new Bundle();
                    Intent in=new Intent(getContext(),Edit_TimeSheetActivity.class);
                    b.putString("weekend",weekend_String);
                    b.putString("monday",mon_String);
                    b.putString("tuesday",tue_String);
                    b.putString("wednesday",wed_String);
                    b.putString("thursday",thu_String);
                    b.putString("friday",fri_String);
                    b.putString("saturday",sat_String);
                    b.putString("sunday",sun_String);
                    in.putExtras(b);
                    startActivity(in);
                }else{
                    Toast.makeText(getContext(),"Please check your fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

        record_ListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {

                update_Value=parent.getItemAtPosition(pos).toString();
                registerForContextMenu(record_ListView);
                return false;
            }
        });

        record_ListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int topRowVerticalPosition =
                        (record_ListView == null || record_ListView.getChildCount() == 0) ?
                                0 : record_ListView.getChildAt(0).getTop();
                swipeRefreshLayout.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                ( new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        getting_DatabaseValues();
                    }
                }, 2000);
            }
        });

        return time;
    }

  @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setHasOptionsMenu(true);
    }

    public void getting_DatabaseValues(){
        Double double_Total_time=0.0;
        Double weekely_Total_time=0.0;

        database = getActivity().openOrCreateDatabase("quickHRMTimeSheet", Context.MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("SELECT * FROM timeSheetValues", null);
        line_values_tasklist.clear(); line_values_datalist.clear();line_values_Idlist.clear();
        try{
            if(cursor.getCount()!=0&&cursor!=null){
                if(cursor.moveToFirst()) {
                    do {
                        String s0=cursor.getString(0);
                        String s1=cursor.getString(1);
                        String s2=cursor.getString(2);
                        String s3=cursor.getString(3);
                        String s4=cursor.getString(4);
                        String s5=cursor.getString(5);
                        String s6=cursor.getString(6);
                        String s7=cursor.getString(7);
                        if(s0.equals(weekend_String)){
                            if(s1.equals(day_String)){
                               if(cursor.getString(11).toString()!=null && cursor.getString(11).toString().trim().length()!=0 && !cursor.getString(11).toString().equals("0")){
                                    line_values_tasklist.add(cursor.getString(8) + "/" + cursor.getString(9) + "/" + cursor.getString(10));
                                    line_values_datalist.add(cursor.getString(11));
                                    line_values_Idlist.add(cursor.getString(19));
                                    DecimalFormat twoDForm = new DecimalFormat("#.##");
                                    double_Total_time = double_Total_time + (Double.parseDouble(cursor.getString(11)));
                                    double_Total_time=Double.valueOf(twoDForm.format(double_Total_time));
                               }
                            }else if(s2.equals(day_String)){
                                if(cursor.getString(12).toString()!=null && cursor.getString(12).toString().trim().length()!=0 && !cursor.getString(12).toString().equals("0")){
                                    line_values_tasklist.add(cursor.getString(8) + "/" + cursor.getString(9) + "/" + cursor.getString(10));
                                    line_values_datalist.add(cursor.getString(12));
                                    line_values_Idlist.add(cursor.getString(19));
                                    DecimalFormat twoDForm = new DecimalFormat("#.##");
                                    double_Total_time = double_Total_time + (Double.parseDouble(cursor.getString(12)));
                                    double_Total_time=Double.valueOf(twoDForm.format(double_Total_time));
                                }}
                            else if(s3.equals(day_String)){
                                if(cursor.getString(13).toString()!=null && cursor.getString(13).toString().trim().length()!=0 && !cursor.getString(13).toString().equals("0")){
                                    line_values_tasklist.add(cursor.getString(8) + "/" + cursor.getString(9) + "/" + cursor.getString(10));
                                    line_values_datalist.add(cursor.getString(13));
                                    line_values_Idlist.add(cursor.getString(19));
                                    DecimalFormat twoDForm = new DecimalFormat("#.##");
                                    double_Total_time = double_Total_time + (Double.parseDouble(cursor.getString(13)));
                                    double_Total_time=Double.valueOf(twoDForm.format(double_Total_time));
                                }  }
                            else if(s4.equals(day_String)){
                                if(cursor.getString(14).toString()!=null && cursor.getString(14).toString().trim().length()!=0 && !cursor.getString(14).toString().equals("0")){
                                    line_values_tasklist.add(cursor.getString(8) + "/" + cursor.getString(9) + "/" + cursor.getString(10));
                                    line_values_datalist.add(cursor.getString(14));
                                    line_values_Idlist.add(cursor.getString(19));
                                    DecimalFormat twoDForm = new DecimalFormat("#.##");
                                    double_Total_time = double_Total_time + (Double.parseDouble(cursor.getString(14)));
                                    double_Total_time=Double.valueOf(twoDForm.format(double_Total_time));
                                }
                            }
                            else if(s5.equals(day_String)){
                                if(cursor.getString(15).toString()!=null && cursor.getString(15).toString().trim().length()!=0 && !cursor.getString(15).toString().equals("0")){
                                    line_values_tasklist.add(cursor.getString(8) + "/" + cursor.getString(9) + "/" + cursor.getString(10));
                                    line_values_datalist.add(cursor.getString(15));
                                    line_values_Idlist.add(cursor.getString(19));
                                    DecimalFormat twoDForm = new DecimalFormat("#.##");
                                    double_Total_time = double_Total_time + (Double.parseDouble(cursor.getString(15)));
                                    double_Total_time=Double.valueOf(twoDForm.format(double_Total_time));
                                }
                            }
                            else if(s6.equals(day_String)){
                                if(cursor.getString(16).toString()!=null && cursor.getString(16).toString().trim().length()!=0 && !cursor.getString(16).toString().equals("0")){
                                    line_values_tasklist.add(cursor.getString(8) + "/" + cursor.getString(9) + "/" + cursor.getString(10));
                                    line_values_datalist.add(cursor.getString(16));
                                    line_values_Idlist.add(cursor.getString(19));
                                    DecimalFormat twoDForm = new DecimalFormat("#.##");
                                    double_Total_time = double_Total_time + (Double.parseDouble(cursor.getString(16)));
                                    double_Total_time=Double.valueOf(twoDForm.format(double_Total_time));
                                }
                            }
                            else if(s7.equals(day_String)){
                                if(cursor.getString(17).toString()!=null && cursor.getString(17).toString().trim().length()!=0 && !cursor.getString(17).toString().equals("0")){
                                    line_values_tasklist.add(cursor.getString(8) + "/" + cursor.getString(9) + "/" + cursor.getString(10));
                                    line_values_datalist.add(cursor.getString(17));
                                    line_values_Idlist.add(cursor.getString(19));
                                    DecimalFormat twoDForm = new DecimalFormat("#.##");
                                    double_Total_time = double_Total_time + (Double.parseDouble(cursor.getString(17)));
                                    double_Total_time=Double.valueOf(twoDForm.format(double_Total_time));
                                }
                            }
                            weekely_Total_time=weekely_Total_time + (Double.parseDouble(cursor.getString(11))) +  (Double.parseDouble(cursor.getString(12))) + (Double.parseDouble(cursor.getString(13))) + (Double.parseDouble(cursor.getString(14))) + (Double.parseDouble(cursor.getString(15))) + (Double.parseDouble(cursor.getString(16))) + (Double.parseDouble(cursor.getString(17)));
                            DecimalFormat twoDForm = new DecimalFormat("#.##");
                            weekely_Total_time=Double.valueOf(twoDForm.format(weekely_Total_time));

                        }else{

                        }
                    } while (cursor.moveToNext());
                    CustomListview disadpt = new CustomListview(getContext(), line_values_Idlist,line_values_tasklist, line_values_datalist);
                    record_ListView.setAdapter(disadpt);
                    cursor.close();
                }        }
            else{
                CustomListview disadpt = new CustomListview(getContext(),line_values_Idlist,line_values_tasklist,line_values_datalist);
                record_ListView.setAdapter(disadpt);
                double_Total_time=0.0;
                weekely_Total_time=0.0;
            }
            String string_Total_time=""+double_Total_time;
            total_time.setText(string_Total_time);
            weekely_total_time.setText(""+weekely_Total_time);
        }catch (Exception e){
            Toast.makeText(getContext(),""+e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private class CustomListview extends BaseAdapter {

        private Context mContext;
        ArrayList<String> listItems1;
        ArrayList<String> listItems2;
        ArrayList<String> listItems3;

        public CustomListview(Context c, ArrayList<String> listItems1, ArrayList<String> listItems2,ArrayList<String> listItems3) {
            this.mContext = c;

            this.listItems1 = listItems1;
            this.listItems2 = listItems2;
            this.listItems3 = listItems3;
        }

        @Override
        public int getCount() {
            return listItems1.size();
        }

        @Override
        public Object getItem(int position) {
            //return listItems1.get(position;
            return listItems1.get(position);
        }

        @Override
        public long getItemId(int position) {
            //return position;
            return position;
        }
        public View getView(final int pos, View child, ViewGroup parent) {
            final Holder mHolder;
            LayoutInflater layoutInflater;
            if (child == null) {
                layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                child = layoutInflater.inflate(R.layout.record_listview, null);
                mHolder = new Holder();
                mHolder.tv1 = (TextView) child.findViewById(R.id.first_textview);
                mHolder.tv2 = (TextView) child.findViewById(R.id.second_textview);
                mHolder.tv3 = (TextView) child.findViewById(R.id.id_textview);
                child.setTag(mHolder);
            } else {
                mHolder = (Holder) child.getTag();
            }
            mHolder.tv3.setText(listItems1.get(pos));
            mHolder.tv1.setText(listItems2.get(pos));
            mHolder.tv2.setText(listItems3.get(pos));
            return child;
        }
        public class Holder {
            TextView tv1;
            TextView tv2;
            TextView tv3;
        }            }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Update");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Delete");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Update"){
            if(weekend_String!=null && weekend_String.trim().length()!=0 && mon_String!=null && mon_String.trim().length()!=0 &&
                    tue_String!=null && tue_String.trim().length()!=0 && wed_String!=null && wed_String.trim().length()!=0 && thu_String!=null && thu_String.trim().length()!=0 &&
                    fri_String!=null && fri_String.trim().length()!=0 && sat_String!=null && sat_String.trim().length()!=0 && sun_String!=null && sun_String.trim().length()!=0){

                Bundle b = new Bundle();
                Intent in=new Intent(getContext(),Update_TimeSheetActivity.class);
                b.putString("weekend",weekend_String);
                b.putString("monday",mon_String);
                b.putString("tuesday",tue_String);
                b.putString("wednesday",wed_String);
                b.putString("thursday",thu_String);
                b.putString("friday",fri_String);
                b.putString("saturday",sat_String);
                b.putString("sunday",sun_String);
                b.putString("id",update_Value);
                in.putExtras(b);
                startActivity(in);

            }else{
                Toast.makeText(getContext(),"Please check your fields",Toast.LENGTH_SHORT).show();
            }
        }
        else if(item.getTitle()=="Delete"){
            AlertDialog.Builder adb = new AlertDialog.Builder(getContext());
            adb.setTitle("Delete?");
            adb.setMessage("Are you sure you want to delete item");
            adb.setNegativeButton("Cancel", null);
            adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    database = getActivity().openOrCreateDatabase("quickHRMTimeSheet", Context.MODE_PRIVATE, null);
                    Cursor cursor = database.rawQuery("SELECT * FROM timeSheetValues", null);
                    if (cursor != null && cursor.getCount() != 0) {
                        database.execSQL("DELETE FROM timeSheetValues WHERE Id='"+update_Value+"'");
                        cursor.moveToFirst();
                    }
                    cursor.close();
                    database.close();
                    getting_DatabaseValues();
                }
            });
            adb.show();
        }else{
            return false;
        }
        return true;
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.one:
                one.setBackground(getResources().getDrawable(R.drawable.green_circle));
                one.setTextColor(getResources().getColor(R.color.colorWhite));
                two.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                two.setTextColor(getResources().getColor(R.color.colorBlack));
                three.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                three.setTextColor(getResources().getColor(R.color.colorBlack));
                four.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                four.setTextColor(getResources().getColor(R.color.colorBlack));
                five.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                five.setTextColor(getResources().getColor(R.color.colorBlack));
                six.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                six.setTextColor(getResources().getColor(R.color.colorBlack));
                seven.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                seven.setTextColor(getResources().getColor(R.color.colorBlack));
                day_String=one.getText().toString();
                getting_DatabaseValues();
                break;
            case R.id.two:
                one.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                one.setTextColor(getResources().getColor(R.color.colorBlack));
                two.setBackground(getResources().getDrawable(R.drawable.green_circle));
                two.setTextColor(getResources().getColor(R.color.colorWhite));
                three.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                three.setTextColor(getResources().getColor(R.color.colorBlack));
                four.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                four.setTextColor(getResources().getColor(R.color.colorBlack));
                five.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                five.setTextColor(getResources().getColor(R.color.colorBlack));
                six.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                six.setTextColor(getResources().getColor(R.color.colorBlack));
                seven.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                seven.setTextColor(getResources().getColor(R.color.colorBlack));
                day_String=two.getText().toString();
                getting_DatabaseValues();
                break;
            case R.id.three:
                one.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                one.setTextColor(getResources().getColor(R.color.colorBlack));
                two.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                two.setTextColor(getResources().getColor(R.color.colorBlack));
                three.setBackground(getResources().getDrawable(R.drawable.green_circle));
                three.setTextColor(getResources().getColor(R.color.colorWhite));
                four.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                four.setTextColor(getResources().getColor(R.color.colorBlack));
                five.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                five.setTextColor(getResources().getColor(R.color.colorBlack));
                six.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                six.setTextColor(getResources().getColor(R.color.colorBlack));
                seven.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                seven.setTextColor(getResources().getColor(R.color.colorBlack));
                day_String=three.getText().toString();
                getting_DatabaseValues();
                break;
            case R.id.four:
                one.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                one.setTextColor(getResources().getColor(R.color.colorBlack));
                two.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                two.setTextColor(getResources().getColor(R.color.colorBlack));
                three.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                three.setTextColor(getResources().getColor(R.color.colorBlack));
                four.setBackground(getResources().getDrawable(R.drawable.green_circle));
                four.setTextColor(getResources().getColor(R.color.colorWhite));
                five.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                five.setTextColor(getResources().getColor(R.color.colorBlack));
                six.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                six.setTextColor(getResources().getColor(R.color.colorBlack));
                seven.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                seven.setTextColor(getResources().getColor(R.color.colorBlack));
                day_String=four.getText().toString();
                getting_DatabaseValues();
                break;
            case R.id.five:
                one.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                one.setTextColor(getResources().getColor(R.color.colorBlack));
                two.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                two.setTextColor(getResources().getColor(R.color.colorBlack));
                three.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                three.setTextColor(getResources().getColor(R.color.colorBlack));
                four.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                four.setTextColor(getResources().getColor(R.color.colorBlack));
                five.setBackground(getResources().getDrawable(R.drawable.green_circle));
                five.setTextColor(getResources().getColor(R.color.colorWhite));
                six.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                six.setTextColor(getResources().getColor(R.color.colorBlack));
                seven.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                seven.setTextColor(getResources().getColor(R.color.colorBlack));
                day_String=five.getText().toString();
                getting_DatabaseValues();
                break;
            case R.id.six:
                one.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                one.setTextColor(getResources().getColor(R.color.colorBlack));
                two.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                two.setTextColor(getResources().getColor(R.color.colorBlack));
                three.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                three.setTextColor(getResources().getColor(R.color.colorBlack));
                four.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                four.setTextColor(getResources().getColor(R.color.colorBlack));
                five.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                five.setTextColor(getResources().getColor(R.color.colorBlack));
                six.setBackground(getResources().getDrawable(R.drawable.green_circle));
                six.setTextColor(getResources().getColor(R.color.colorWhite));
                seven.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                seven.setTextColor(getResources().getColor(R.color.colorBlack));
                day_String=six.getText().toString();
                getting_DatabaseValues();
                break;
            case R.id.seven:
                one.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                one.setTextColor(getResources().getColor(R.color.colorBlack));
                two.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                two.setTextColor(getResources().getColor(R.color.colorBlack));
                three.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                three.setTextColor(getResources().getColor(R.color.colorBlack));
                four.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                four.setTextColor(getResources().getColor(R.color.colorBlack));
                five.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                five.setTextColor(getResources().getColor(R.color.colorBlack));
                six.setBackground(getResources().getDrawable(R.drawable.leave_circle));
                six.setTextColor(getResources().getColor(R.color.colorBlack));
                seven.setBackground(getResources().getDrawable(R.drawable.green_circle));
                seven.setTextColor(getResources().getColor(R.color.colorWhite));
                day_String=seven.getText().toString();
                getting_DatabaseValues();
                break;
            default:
                break;
        }
    }

    public void set_Text(){
        one.setBackground(getResources().getDrawable(R.drawable.green_circle));
        one.setTextColor(getResources().getColor(R.color.colorWhite));
        two.setBackground(getResources().getDrawable(R.drawable.leave_circle));
        two.setTextColor(getResources().getColor(R.color.colorBlack));
        three.setBackground(getResources().getDrawable(R.drawable.leave_circle));
        three.setTextColor(getResources().getColor(R.color.colorBlack));
        four.setBackground(getResources().getDrawable(R.drawable.leave_circle));
        four.setTextColor(getResources().getColor(R.color.colorBlack));
        five.setBackground(getResources().getDrawable(R.drawable.leave_circle));
        five.setTextColor(getResources().getColor(R.color.colorBlack));
        six.setBackground(getResources().getDrawable(R.drawable.leave_circle));
        six.setTextColor(getResources().getColor(R.color.colorBlack));
        seven.setBackground(getResources().getDrawable(R.drawable.leave_circle));
        seven.setTextColor(getResources().getColor(R.color.colorBlack));
    }

    @Override
    public void onResume() {
        getting_DatabaseValues();
        super.onResume();
    }
}
