package com.esm.diffutilsample.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.esm.diffutilsample.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<String> dataSource;

    public MyAdapter(List<String> dataSource) {
        this.dataSource = dataSource;
    }

    public void insertData(List<String> insertList) {
        /*
        This function will add new data to RecyclerView
         */
        MyDiffUtilCallback diffUtilCallback = new MyDiffUtilCallback(dataSource, insertList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);
        dataSource.addAll(insertList);
        diffResult.dispatchUpdatesTo(this);
    }

    public void updateData(List<String> newList) {
        MyDiffUtilCallback diffUtilCallback = new MyDiffUtilCallback(dataSource, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallback);

        dataSource.clear();
        dataSource.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }


    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        holder.my_text_view.setText(dataSource.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView my_text_view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            my_text_view = itemView.findViewById(R.id.my_text_view);
        }
    }
}
