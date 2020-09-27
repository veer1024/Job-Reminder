package com.vaishnavas.countdown;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
RelativeLayout layout;
    ListView taskview;
    EditText task;
    FloatingActionButton button;
    int x=0;
    int Day,Year,Month,Hour,Minute;
    final ArrayList<taskjob> taskarraylist = new ArrayList<>();
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       button = (FloatingActionButton) findViewById(R.id.add);
        task = (EditText)  findViewById(R.id.tasktodo);
       layout = (RelativeLayout)  findViewById(R.id.addtasklayout);
        taskview = (ListView) findViewById(R.id.tasklist);

       showingtasklist();

   }

    public void addtime(View view) {

        if(x==0){
           layout.setGravity(RelativeLayout.CENTER_VERTICAL);
           layout.setVisibility(View.VISIBLE);
            button.setRotation((float) 45.0);
           x = 1;
       }else{

           layout.setVisibility(View.INVISIBLE);
            button.setRotation((float) 0.0);
            x = 0;
       }


    }

    public void register(View view) {
  layout.setVisibility(View.INVISIBLE);
    }

    public void datepick(View view) {
        // Get Current Date
       final Calendar c = Calendar.getInstance();
        Year = c.get(Calendar.YEAR);
        Month = c.get(Calendar.MONTH);
        Day = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Toast.makeText(MainActivity.this,"day:"+dayOfMonth + "Month:" +dayOfMonth+"year:"+year,Toast.LENGTH_LONG).show();


                    }
                }, Year, Month, Day);
        datePickerDialog.show(); }

    public void timepick(View view) {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        Hour = c.get(Calendar.HOUR_OF_DAY);
        Minute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

Toast.makeText(MainActivity.this,"Hours:"+hourOfDay + "Minute:" +minute,Toast.LENGTH_LONG).show();
                    }
                }, Hour, Minute, false);
        timePickerDialog.show(); }

    public static class settimer extends BaseAdapter {
        static ArrayList<taskjob> taskjob;
        // public ArrayList<AdaptersItem> listnewsDataAdpater ;
        Context mContext;

        public settimer(Context mContext, ArrayList<taskjob> listnewsDataAdpater) {
            this.mContext = mContext;
            this.taskjob = listnewsDataAdpater;
        }


        @Override
        public int getCount() {
            return taskjob.size();
        }

        @Override
        public Object getItem(int position) {
            return taskjob.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.timerlist_ticket, parent, false);
            }
            taskjob k = taskjob.get(position);
            if (k.taskname.isEmpty() || k.timeleft.isEmpty()) {
                //< get controls >
                TextView task = (TextView) convertView.findViewById(R.id.task);
                TextView timeremains = (TextView) convertView.findViewById(R.id.timeremains);
//</ get controls >
                Toast.makeText(mContext,"details are not filled correctly",Toast.LENGTH_LONG).show();
            } else {
                //< get controls >
                TextView task = (TextView) convertView.findViewById(R.id.task);
                TextView timeremains = (TextView) convertView.findViewById(R.id.timeremains);
//</ get controls >
task.setText(k.taskname);
timeremains.setText(k.timeleft);
            }


            //  (adding animation in listview
            // Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.fade_in);
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_left);
            //   Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.shake);
            //   Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.slide_left);
            //  Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.slide_up);
            convertView.startAnimation(animation);
            convertView.setTag(k.taskname);
            return convertView;
        }

    }
    public static class taskjob {
        //----------------< fritzbox_Contacts() >----------------
        public static String taskname = "";

        public static String timeleft = "";
        //---------------</ fritzbox_Contacts() >----------------
    }

    public void showingtasklist() {
       for(int i=0;i<100;i++){
         taskjob Taskjob = new taskjob();
           Taskjob.taskname = "your task";
           Taskjob.timeleft = "timerwillbehere";
           taskarraylist.add(Taskjob);
       }
        settimer Adapter = new settimer(this, taskarraylist);
        taskview.setAdapter(Adapter);
        taskview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, final int i, long l) {



            }
        });
    }
}