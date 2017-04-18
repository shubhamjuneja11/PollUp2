package com.supergeek.pollup.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.supergeek.pollup.R;
import com.supergeek.pollup.models.NewPostModel;

import java.util.ArrayList;

/**
 * Created by Junejas on 4/19/2017.
 */

public class NewPostAdapter extends RecyclerView.Adapter<NewPostAdapter.MyViewHolder> {
    ArrayList<NewPostModel> data;
   public  static  int position;
    public NewPostAdapter(ArrayList<NewPostModel> data){
        this.data=data;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)  {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pollpost, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NewPostModel model=data.get(position);
        holder.ques.setText(model.getQues());
        holder.o1.setText(model.getOption1());
        holder.o2.setText(model.getOption2());
        holder.o3.setText(model.getOption3());
        holder.o4.setText(model.getOption4());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView ques,o1,o2,o3,o4;

        public MyViewHolder(View itemView) {
            super(itemView);
            ques=(TextView)itemView.findViewById(R.id.ques);
            o1=(TextView)itemView.findViewById(R.id.option1);
            o2=(TextView)itemView.findViewById(R.id.option2);
            o3=(TextView)itemView.findViewById(R.id.option3);
            o4=(TextView)itemView.findViewById(R.id.option4);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            position=getAdapterPosition();
        }
    }
}
