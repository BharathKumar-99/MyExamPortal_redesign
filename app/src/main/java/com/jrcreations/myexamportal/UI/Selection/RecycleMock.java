package com.jrcreations.myexamportal.UI.Selection;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jrcreations.myexamportal.R;
import com.jrcreations.myexamportal.UI.Home.Btn;
import com.jrcreations.myexamportal.UI.Home.RecycelviewAdapter;

import java.util.List;

public class RecycleMock  extends RecyclerView.Adapter<RecycleMock.ViewHolder>{

    private Context context;
    private List<MockTestModel> list;

    public RecycleMock(Context context, List<MockTestModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public RecycleMock.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.mocktestrow, parent, false);
        return new RecycleMock.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MockTestModel users = list.get(position);

        holder.start.setText("Start Quiz");
        holder.start.setOnClickListener(v->{
            Intent i = new Intent(context, UiSelection.class);
            i.putExtra("key",holder.start.getText().toString());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        });
        holder.name.setText(users.getName());
        holder.marks.setText(users.getMarks());
        holder.time.setText(users.getTime());
        holder.questions.setText(users.getQuestions());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button start;
        TextView name,questions,time,marks;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            start=itemView.findViewById(R.id.startquiz);
            name=itemView.findViewById(R.id.name);
            time=itemView.findViewById(R.id.time);
            marks=itemView.findViewById(R.id.marks);
            questions=itemView.findViewById(R.id.questions);
        }
    }
}

