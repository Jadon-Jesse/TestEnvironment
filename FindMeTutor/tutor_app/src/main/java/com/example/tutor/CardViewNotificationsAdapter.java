package com.example.tutor;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class CardViewNotificationsAdapter extends RecyclerView.Adapter<CardViewNotificationsAdapter.EventViewHolder> {

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView subName;
        TextView date;
        TextView time;
        ImageView session;
        TextView studentName ;
        TextView Description;
        Button confirm ;
        Button decline;
        RatingBar rate ;

        EventViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            subName = (TextView)itemView.findViewById(R.id.subject);
            date = (TextView)itemView.findViewById(R.id.date);
            time = (TextView)itemView.findViewById(R.id.time);
            session = (ImageView)itemView.findViewById(R.id.session);
            studentName = (TextView)itemView.findViewById(R.id.studentName) ;
            Description = (TextView)itemView.findViewById(R.id.Description) ;
            confirm = (Button)itemView.findViewById(R.id.btnConfirm) ;
            decline = (Button)itemView.findViewById(R.id.btnDecline) ;
            rate = (RatingBar) itemView.findViewById(R.id.ratingBar3) ;
        }
    }

    List<Notifications> list;
    Context context;

    CardViewNotificationsAdapter(List<Notifications> events, Context context){
        this.list = events;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_notifcations, viewGroup, false);
        EventViewHolder pvh = new EventViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final EventViewHolder eventViewHolder, final int i) {
        eventViewHolder.subName.setText(list.get(i).code + "-"+ list.get(i).subject);
        eventViewHolder.date.setText(list.get(i).date);
        eventViewHolder.time.setText(list.get(i).time);
        eventViewHolder.session.setImageResource(list.get(i).icon);
        eventViewHolder.studentName.setText(list.get(i).studentName + " "+ list.get(i).studentSurname);
        eventViewHolder.Description.setText(list.get(i).description);
        eventViewHolder.rate.setRating( Float.parseFloat(list.get(i).Rating.toString()));




        eventViewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(context, "Index position is: "+i, Toast.LENGTH_SHORT).show();
            }
        });

        eventViewHolder.decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences myprefs;
                myprefs =  context.getSharedPreferences("user",MODE_PRIVATE ) ;
                String id = myprefs.getString("tutor_id", null) ;

            tutor_decline connect2server = new tutor_decline(context,list.get(i).tutor_student_id,id, list.get(i).student_id) ;
                connect2server.execute() ;
                eventViewHolder.decline.setText("Declined");
                //  Toast.makeText(context, "Index position is: "+i, Toast.LENGTH_SHORT).show();
            }
        });

        eventViewHolder.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context, "Confirmed: "+i, Toast.LENGTH_SHORT).show();

                SharedPreferences myprefs;
                myprefs =  context.getSharedPreferences("user",MODE_PRIVATE ) ;
                String id = myprefs.getString("tutor_id", null) ;
                String tutor_name =  myprefs.getString("tutor_fname", null) ;
                String tutor_surname =  myprefs.getString("tutor_lname", null) ;

               // Toast.makeText(context, "tut: "+list.get(i).tutor_student_id, Toast.LENGTH_SHORT).show();

                tutor_confirm connect2server = new tutor_confirm(context,list.get(i).tutor_student_id,id, list.get(i).student_id) ;
                connect2server.execute();

                Toast.makeText(context, "Your agreement has been sent to " + list.get(i).studentName +" " + list.get(i).studentSurname, Toast.LENGTH_SHORT).show();
                eventViewHolder.confirm.setText("confirmed");
                eventViewHolder.confirm.setClickable(false);

                Toast.makeText(context, "Student Email " + list.get(i).Email, Toast.LENGTH_SHORT).show();


                String fromEmail = "FindmetutorSD@gmail.com";
                String fromPassword = "findmetutors";
                String toEmails = list.get(i).Email.toString();
                String adminEmail = "admin@gmail.com";
                String emailSubject = "Sent from FindMeTutor";
                String adminSubject = "App Registration Mail";
                String emailBody =
                                "Dear "+ list.get(i).studentName + " " + list.get(i).studentSurname
                                +"<br><br>"+ tutor_name.toString() +" " + tutor_surname.toString()
                                +" has accepted your request for:<br>Subject: "
                                + list.get(i).subject + "<br>Date: "+ list.get(i).date  +"<br>Time: "
                                + list.get(i).time + "<br>Description: " + list.get(i).description
                                +".<br><br>For more information about " + tutor_name.toString() +" " + tutor_surname.toString()
                                +" please log on to your FindMeTutor app.";
                String adminBody = "Your message";
                new SendMailTask(context).execute(fromEmail,
                        fromPassword, toEmails, emailSubject, emailBody);





                //  tutor_getsubject2 connect2server2 = new tutor_getsubject2(context,id) ;


            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
