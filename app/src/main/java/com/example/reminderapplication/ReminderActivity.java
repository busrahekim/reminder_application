package com.example.reminderapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReminderActivity extends AppCompatActivity {

    Button saveButton, timeButton;
    EditText reminderText;
    String notificationTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        reminderText =  findViewById(R.id.editTitle);
        timeButton = findViewById(R.id.btnTime);
        saveButton =  findViewById(R.id.btnSubmit);


        timeButton.setOnClickListener(view -> selectTime());

       

        saveButton.setOnClickListener(view -> {
            String title = reminderText.getText().toString().trim();

            String time = timeButton.getText().toString().trim();

            if (title.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please Enter text", Toast.LENGTH_SHORT).show();
            } else {
                if (time.equals("time") ) {
                    Toast.makeText(getApplicationContext(), "Please select time", Toast.LENGTH_SHORT).show();
                } else {
                    processInsert(title, time);

                }
            }


        });
    }


    private void processInsert(String title, String time) {
        String result = new DBManager(this).addReminder(title, time);
        setAlarm(title, time);                                                               
        reminderText.setText("");
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    }

    private void selectTime() {                                                                    
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                notificationTime = i + ":" + i1;                                                        
                timeButton.setText(FormatTime(i, i1));                                                
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }

    public String FormatTime(int hour, int minute) {                                               

        String time;
        time = "";
        String formattedMinute;

        if (minute / 10 == 0) {
            formattedMinute = "0" + minute;
        } else {
            formattedMinute = "" + minute;
        }


        if (hour == 0) {
            time = "12" + ":" + formattedMinute + " AM";
        } else if (hour < 12) {
            time = hour + ":" + formattedMinute + " AM";
        } else if (hour == 12) {
            time = "12" + ":" + formattedMinute + " PM";
        } else {
            int temp = hour - 12;
            time = temp + ":" + formattedMinute + " PM";
        }
        
        return time;
    }


    private void setAlarm(String text, String time) {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);                   

        Intent intent = new Intent(getApplicationContext(), AlarmBroadcast.class);
        intent.putExtra("event", text);
        intent.putExtra("time", time);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String dateandtime = " " + notificationTime;
        DateFormat formatter = new SimpleDateFormat("d-M-yyyy hh:mm");
        try {
            Date date1 = formatter.parse(dateandtime);
            am.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);
            Toast.makeText(getApplicationContext(), "Alarm", Toast.LENGTH_SHORT).show();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Intent intentBack = new Intent(getApplicationContext(), HomeActivity.class);                
        intentBack.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentBack);                                                                 

    }
}