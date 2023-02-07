package com.example.leaguemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ViewHolder> {

    Context context;
    List<Model> listData;

    public ModelAdapter(Context context, List<Model> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelAdapter.ViewHolder holder, int position) {
    if(listData!=null && listData.size()>0){
        Model model=listData.get(position);
        holder.date.setText(model.getDate());
        holder.m1.setText(model.getTeam1());
        holder.m2.setText(model.getTeam2());

    }
    else{
        return;
    }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date,m1,m2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            m1=itemView.findViewById(R.id.m1);
            m2=itemView.findViewById(R.id.m2);
        }
    }
}
