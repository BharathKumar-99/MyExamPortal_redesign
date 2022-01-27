package com.jrcreations.myexamportal.UI.Home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jrcreations.myexamportal.R;
import com.jrcreations.myexamportal.UI.Selection.UiSelection;

import java.util.List;

public class RecycelviewAdapter extends RecyclerView.Adapter<RecycelviewAdapter.ViewHolder>{

    private Context context;
    private List<Btn> list;

    public RecycelviewAdapter(Context context, List<Btn> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.mainbutton, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Btn users = list.get(position);

        holder.button.setText(users.getName());
        holder.button.setOnClickListener(v->{
            Intent i = new Intent(context, UiSelection.class);
            i.putExtra("key",holder.button.getText().toString());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button=itemView.findViewById(R.id.button);
        }
    }
}
