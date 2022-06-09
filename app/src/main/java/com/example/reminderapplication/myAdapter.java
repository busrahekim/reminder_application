package com.example.reminderapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.viewHolder> {
    ArrayList<Model> datable ;

    public myAdapter(ArrayList<Model> datable) {
        this.datable = datable;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_reminder_file, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.mTitle.setText(datable.get(position).getTitle());
        holder.mTime.setText(datable.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return datable.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {

        TextView mTitle, mTime;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.txtTitle);
            mTime = itemView.findViewById(R.id.txtTime);
        }
    }
}
