package com.example.tutor;

import android.app.Activity;
import android.content.Context;

/**
 * Created by admin on 07-Sep-16.
 */

public class Notifications {

    String tutor_student_id ;
    String student_id ;
    String subject;
    String code;
    String date ;
    String time ;
    String studentName ;
    String studentSurname ;
    String description ;
    String Email ;
    String Rating ;

    int icon;
    Context context;


    Notifications(String tsi,String sid,String subj, String cd, String d , String t, String sn , String ss , String desc, String email,String rate ,int i, Activity context)// Subjects obj)
    {
        this.tutor_student_id = tsi ;
        this.student_id = sid ;
        this.subject = subj;
        this.code = cd;
        this.date = d ;
        this.time = t ;
        this.studentName = sn ;
        this.studentSurname = ss ;
        this.description = desc ;
        this.context = context;
        this.Email = email ;
        this.Rating = rate ;

        this.icon = i ;
        // this.subjectObj = obj;

    }

    public void initializeData() {

//        list = new ArrayList<>();
//        String name = null;


    }
}