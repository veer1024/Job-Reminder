package com.vaishnavas.countdown;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class GlobalInfo {
    public static Map<String,String> taskdesc = new HashMap<>();
    public static Map<String,String> times = new HashMap<>();
    public static final String Textdata = "textdata";
    public static final String Timing = "timing";
    public static final String myprefrences = "myRef";
    public static String tasktodo = "";
    public static String time = "";
    Context context;
    SharedPreferences ShredRef;
    public  GlobalInfo(Context context){
        this.context=context;
        ShredRef=context.getSharedPreferences(myprefrences,Context.MODE_PRIVATE);
    }
    void SaveData(){
        String timingdes = "";
        String textdate="" ;
        for (Map.Entry  m:GlobalInfo.taskdesc.entrySet()){
            if (textdate.length()==0)
                textdate=m.getKey() + "%" + m.getValue();
           else
                textdate =textdate + "%" + m.getKey() + "%" + m.getValue();

        }
        for (Map.Entry  m:GlobalInfo.times.entrySet()){
            if (timingdes.length()==0)
                timingdes=m.getKey() + "%" + m.getValue();
            else
                timingdes =timingdes+ "%" + m.getKey() + "%" + m.getValue();

        }

        if (textdate.length()==0)
            textdate="empty";
        if(timingdes.length()==0)
        {
            timingdes="empty";
        }

        SharedPreferences.Editor editor=ShredRef.edit();
        editor.putString(Textdata,textdate);
        editor.putString(Timing,timingdes);
        editor.commit();
    }
    void LoadData(){
        taskdesc.clear();
        times.clear();

     tasktodo   = ShredRef.getString(Textdata,"empty");
       time =ShredRef.getString(Timing,"empty");
          if (!tasktodo.equals("empty")){
            String[] users=tasktodo.split("%");
            for (int i=0;i<users.length;i=i+2){
                taskdesc.put(users[i],users[i+1]);

            }
        }
        if (!time.equals("empty")){
            String[] people=time.split("%");
            for (int i=0;i<people.length;i=i+2){
                times.put(people[i],people[i+1]);

            }
        }


    }
}
