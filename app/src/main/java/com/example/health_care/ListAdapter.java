package com.example.health_care;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.annotations.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter {

    private Activity mContext;
    List<Store> st;

    public ListAdapter(Activity mContext, List<Store> st){
        super(mContext,R.layout.list_item,st);
        this.mContext = mContext;
        this.st = st;
    }

    @NonNull
    @Override
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
}