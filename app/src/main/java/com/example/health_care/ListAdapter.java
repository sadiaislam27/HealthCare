package com.example.health_care;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    //private Activity mContext;
    private final Context context;
    private List<Store> st= new ArrayList<>();


    public ListAdapter(Context context){
        this.context=context;

    }
    @SuppressLint("NotifyDataSetChanged")
    public void replaceData(List<Store> st){
        this.st=st;
        notifyDataSetChanged();
    }


    @NonNull
    /*@Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item,null,true);

        // View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);

        TextView heartRateLabel = listItemView.findViewById(R.id.heartRateLabel);
        TextView heartRate = listItemView.findViewById(R.id.heartRate);
        TextView systolicLabel = listItemView.findViewById(R.id.systolicLabel);
        TextView systolic = listItemView.findViewById(R.id.systolic);
        TextView diastolicLabel = listItemView.findViewById(R.id.diastolicLabel);
        TextView diastolic = listItemView.findViewById(R.id.diastolic);
        TextView dateLabel = listItemView.findViewById(R.id.dateLabel);
        TextView date = listItemView.findViewById(R.id.date);
        TextView timeLabel = listItemView.findViewById(R.id.timeLabel);
        TextView time = listItemView.findViewById(R.id.time);
        TextView commentLabel = listItemView.findViewById(R.id.commentLabel);
        TextView comment = listItemView.findViewById(R.id.comment);

        Store stt = st.get(position);

        heartRateLabel.setText("Heart Rate:");
        heartRate.setText(stt.getHeartRate());
        systolicLabel.setText("Systolic:");
        systolic.setText(stt.getSystolic());
        diastolicLabel.setText("Diastolic:");
        diastolic.setText(stt.getDiastolic());
        dateLabel.setText("Date:");
        date.setText(stt.getCurrentDate());
        timeLabel.setText("Time:");
        time.setText(stt.getTime());
        commentLabel.setText("Comment:");
        comment.setText(stt.getComment());

        return listItemView;
    }

     */



    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Store stt = st.get(position);


       // holder.heartRateLabel.setText("Heart Rate:");
        holder.heartRate.setText(st.get(position).getHeartRate());
       // holder.systolicLabel.setText("Systolic:");
        holder.systolic.setText(st.get(position).getSystolic());
       // holder.diastolicLabel.setText("Diastolic:");
        holder.diastolic.setText(st.get(position).getDiastolic());
       // holder.dateLabel.setText("Date:");
        holder.date.setText(st.get(position).getCurrentDate());
        //holder.timeLabel.setText("Time:");
        holder.time.setText(st.get(position).getTime());
       // holder.commentLabel.setText("Comment:");
        holder.comment.setText(st.get(position).getComment());

        holder.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Storing.class);
                intent.putExtra("store", st.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });


        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    String uid = auth.getUid();
                    FirebaseDatabase.getInstance().getReference().child("store").child(uid)
                            .child(st.get(holder.getAdapterPosition()).getKey()).removeValue();
                }
                catch (Exception ignored){}
            }
        });
        holder.buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Store data = st.get(holder.getAdapterPosition());
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("systolic", data.getSystolic());
                intent.putExtra("heartRate", data.getHeartRate());
                intent.putExtra("diastolic", data.getDiastolic());
                intent.putExtra("date", data.getCurrentDate());
                intent.putExtra("time", data.getTime());
                intent.putExtra("comment", data.getComment());
                context.startActivity(intent);
            }
        });


    }

    // Return the size of your data source (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return st.size();
    }

    // Define your ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView heartRateLabel,heartRate,systolicLabel,systolic,diastolicLabel,diastolic,dateLabel,date
                ,timeLabel,time,commentLabel,comment;

        private Button buttonEdit,buttonDelete,buttonDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             heartRateLabel = itemView.findViewById(R.id.heartRatelabel);
             heartRate = itemView.findViewById(R.id.heartRate);
             systolicLabel = itemView.findViewById(R.id.systoliclabel);
             systolic = itemView.findViewById(R.id.systolic);
             diastolicLabel = itemView.findViewById(R.id.diastoliclabel);
             diastolic = itemView.findViewById(R.id.diastolic);
             dateLabel = itemView.findViewById(R.id.Datelabel);
             date = itemView.findViewById(R.id.Date);
             timeLabel = itemView.findViewById(R.id.Timelabel);
             time = itemView.findViewById(R.id.Time);
             commentLabel = itemView.findViewById(R.id.commentlabel);
             comment = itemView.findViewById(R.id.comment);
             buttonEdit=itemView.findViewById(R.id.edit);
             buttonDelete=itemView.findViewById(R.id.delete);
             buttonDetails=itemView.findViewById(R.id.buttonDetails);


        }


    }

}