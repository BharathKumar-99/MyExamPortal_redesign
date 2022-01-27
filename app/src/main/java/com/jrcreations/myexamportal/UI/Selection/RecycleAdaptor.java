package com.jrcreations.myexamportal.UI.Selection;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class RecycleAdaptor extends RecyclerView.Adapter<RecycleAdaptor.ViewHolder>  {

    private Context context;
    private List<com.jrcreations.myexamportal.UI.Selection.RowModel> list;

    public RecycleAdaptor(Context context, List<com.jrcreations.myexamportal.UI.Selection.RowModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
