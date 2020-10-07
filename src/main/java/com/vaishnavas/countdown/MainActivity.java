package com.vaishnavas.countdown;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
RelativeLayout layout;
    ListView taskview;
    EditText task;
    EditText description;
    FloatingActionButton button;
    int x=0;
    int Day,Year,Month,Hour,Minute;
    int Dayi,Yeari,Monthi,Houri,Minutei;
int mi=0,hi=0,di=0,Mi=0,yi=0;
int ml=0,hl=0,dl=0,Ml=0,yl=0;
    Date starting;
    Date last;
  static final ArrayList<count> countdownlist = new ArrayList<>();
   final ArrayList<taskjob> taskarraylist = new ArrayList<>();
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       button = (FloatingActionButton) findViewById(R.id.add);
        task = (EditText)  findViewById(R.id.tasktodo);
       description = (EditText)  findViewById(R.id.taskdescription);

       layout = (RelativeLayout)  findViewById(R.id.addtasklayout);
        taskview = (ListView) findViewById(R.id.tasklist);
       GlobalInfo globalInfo = new GlobalInfo(MainActivity.this);
       globalInfo.LoadData();
       showingtasklist();
       final Handler handler = new Handler();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               settimer Adapter = new settimer(MainActivity.this, taskarraylist,countdownlist);
Adapter.notifyDataSetChanged();
handler.postDelayed(this,1000);
           }
       },1000);
       CountDownTimer countDownTimer = new CountDownTimer(24*60*60*1000,1000) {
           @Override
           public void onTick(long remainingmilis) {
               int i = 0;
               for(i=0;i<countdownlist.size();i++){
                   // Set the date for both of the calendar instance
                   count k = countdownlist.get(i);
                   GregorianCalendar calDate = new GregorianCalendar(k.Yeari, k.Monthi, k.Dayi,k.Houri,k.Minutei,00);
                   GregorianCalendar cal2 = new GregorianCalendar(k.Year, k.Month, k.Day,k.Hour,k.Minute,00);

                   // Get the represented date in milliseconds
                   long millis1 = calDate.getTimeInMillis();
                   long millis2 = cal2.getTimeInMillis();

                   // Calculate difference in milliseconds
                   long different = millis2 - millis1;
                   int diff = (int) different;
// Calculate difference in days
                   int diffDays = (int)diff / (24 * 60 * 60 * 1000);
                   diff = diff - diffDays*24*60*60*1000;
                   // Calculate difference in hours
                   int diffHours =(int) diff / (60 * 60 * 1000);
                   diff = diff - diffHours*60*60*1000;
                   // Calculate difference in minutes
                   int  diffMinutes =(int) diff / (60 * 1000);
                   diff = diff - diffMinutes*60*1000;
                   // Calculate difference in seconds
                   int diffSeconds = (int)  diff / 1000;

               }
           }

           @Override
           public void onFinish() {

           }
       };
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
public void Refresh(){
taskarraylist.clear();
    for (Map.Entry  m:GlobalInfo.taskdesc.entrySet()){

        taskjob Taskjob = new taskjob();
        Taskjob.taskname = m.getKey().toString();
        Taskjob.timeleft = m.getValue().toString();
        taskarraylist.add(Taskjob);
    }
    for (Map.Entry  m:GlobalInfo.times.entrySet()){


    }
}
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void register(View view) {
  layout.setVisibility(View.INVISIBLE);
       @SuppressLint("SimpleDateFormat") DateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/HH:mm");
      //  DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      String deadlinedate = Year +"/"+Month + "/" + Day+ "/"+ Hour +":"+Minute;
       String startingtime = Yeari +"/"+Monthi + "/" + Dayi+ "/"+ Houri +":"+Minutei;
        

// Set the date for both of the calendar instance
        GregorianCalendar calDate = new GregorianCalendar(Yeari, Monthi, Dayi,Houri,Minutei,00);
        GregorianCalendar cal2 = new GregorianCalendar(Year, Month, Day,Hour,Minute,00);

        // Get the represented date in milliseconds
        long millis1 = calDate.getTimeInMillis();
        long millis2 = cal2.getTimeInMillis();

        // Calculate difference in milliseconds
        long different = millis2 - millis1;
        int diff = (int) different;
// Calculate difference in days
        int diffDays = (int)diff / (24 * 60 * 60 * 1000);
        diff = diff - diffDays*24*60*60*1000;
        // Calculate difference in hours
        int diffHours =(int) diff / (60 * 60 * 1000);
      diff = diff - diffHours*60*60*1000;
        // Calculate difference in minutes
       int  diffMinutes =(int) diff / (60 * 1000);
     diff = diff - diffMinutes*60*1000;
        // Calculate difference in seconds
        int diffSeconds = (int)  diff / 1000;





        Toast.makeText(MainActivity.this, diffDays+":"+diffHours+":"+diffMinutes+":"+diffSeconds, Toast.LENGTH_SHORT).show();

       try {
           last = simpleDateFormat.parse(deadlinedate);
starting = simpleDateFormat.parse(startingtime);
           GlobalInfo globalInfo = new GlobalInfo(this);
           globalInfo.taskdesc.put(task.getText().toString(),last.toLocaleString());
         globalInfo.times.put(description.getText().toString(),starting.toLocaleString());
         // String countdown = "Remaining Time"+diffDays + " days"+" : "+diffHours+" hours"+" : "+diffMinutes+" minutes"+" : "+diffSeconds+" seconds";

           count count = new count();
           count.Year = Year;
           count.Month = Month;
           count.Day = Day;
           count.Hour = Hour;
           count.Minute = Year;
           count.Yeari = Yeari;
           count.Minutei = Minutei;
           count.Monthi = Monthi;
           count.Dayi = Dayi;
           count.Houri = Houri;
           countdownlist.add(count);
          globalInfo.SaveData();
          Refresh();
        } catch (ParseException e) {
            e.printStackTrace();
        }
task.setText(null);
       description.setText(null);
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
                        Year = year;
                        Month = monthOfYear +1;
                        Day = dayOfMonth;
                      //  Toast.makeText(MainActivity.this,"day:"+dayOfMonth + "Month:" +Month+"year:"+year,Toast.LENGTH_LONG).show();

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
                        Hour = hourOfDay;
                        Minute = minute;
//Toast.makeText(MainActivity.this,"Hours:"+hourOfDay + "Minute:" +minute,Toast.LENGTH_LONG).show();
                    }
                }, Hour, Minute, true);
        timePickerDialog.show(); }

    public void datepicki(View view) {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        Yeari = c.get(Calendar.YEAR);
        Monthi = c.get(Calendar.MONTH);
        Dayi = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Yeari = year;
                        Monthi = monthOfYear+1;
                        Dayi = dayOfMonth;
                     //   Toast.makeText(MainActivity.this,"day:"+dayOfMonth + "Month:" +Monthi+"year:"+year,Toast.LENGTH_LONG).show();
                    }
                }, Yeari, Monthi, Dayi);
        datePickerDialog.show();
   }

    public void timepicki(View view) {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        Houri = c.get(Calendar.HOUR_OF_DAY);
        Minutei = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
Houri = hourOfDay;
Minutei = minute;
                        Toast.makeText(MainActivity.this,"Hours:"+hourOfDay + "Minute:" +minute,Toast.LENGTH_LONG).show();
                    }
                }, Houri, Minutei, true);
        timePickerDialog.show();
   }

    public static class settimer extends BaseAdapter {
        static ArrayList<taskjob> taskjob;
        // public ArrayList<AdaptersItem> listnewsDataAdpater ;
        Context mContext;
static ArrayList<count> coundownlist2;
        public settimer(Context mContext, ArrayList<taskjob> listnewsDataAdpater,ArrayList<count> countarray) {
            this.mContext = mContext;
            this.taskjob = listnewsDataAdpater;
            this.coundownlist2 = countarray;
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
timeremains.setText(countdowncalculator(position));
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
    public static class count{
        public static int Hour = 0;
      public static int Day = 0;
        public static int Minute = 0;
        public static int Year = 0;
        public static int Month = 0;
        public static int Houri = 0;
        public static int Dayi = 0;
        public static int Minutei = 0;
        public static int Yeari = 0;
        public static int Monthi = 0;

    }
    public static class taskjob {
        //----------------< fritzbox_Contacts() >----------------
        public static String taskname = "";

        public static String timeleft = "";
        //---------------</ fritzbox_Contacts() >----------------
    }

    public void showingtasklist() {
       Refresh();
        settimer Adapter = new settimer(this, taskarraylist,countdownlist);
        taskview.setAdapter(Adapter);
        taskview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, final int i, long l) {



            }
        });
    }
    public static String countdowncalculator(int position){
        // Set the date for both of the calendar instance
        count m = countdownlist.get(position);
        GregorianCalendar calDate = new GregorianCalendar(m.Yeari, m.Monthi, m.Dayi,m.Houri,m.Minutei,00);
        GregorianCalendar cal2 = new GregorianCalendar(m.Year, m.Month, m.Day,m.Hour,m.Minute,00);

        // Get the represented date in milliseconds
        long millis1 = calDate.getTimeInMillis();
        long millis2 = cal2.getTimeInMillis();

        // Calculate difference in milliseconds
        long different = millis2 - millis1;
        int diff = (int) different;
// Calculate difference in days
        int diffDays = (int)diff / (24 * 60 * 60 * 1000);
        diff = diff - diffDays*24*60*60*1000;
        // Calculate difference in hours
        int diffHours =(int) diff / (60 * 60 * 1000);
        diff = diff - diffHours*60*60*1000;
        // Calculate difference in minutes
        int  diffMinutes =(int) diff / (60 * 1000);
        diff = diff - diffMinutes*60*1000;
        // Calculate difference in seconds
        int diffSeconds = (int)  diff / 1000;
        String countdown = "Remaining Time"+diffDays + " days"+" : "+diffHours+" hours"+" : "+diffMinutes+" minutes"+" : "+diffSeconds+" seconds";

        return countdown;
    }
}