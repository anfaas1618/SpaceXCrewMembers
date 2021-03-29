package com.anfaas.assignment.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.anfaas.assignment.Listener.Listener;
import com.anfaas.assignment.Modal.Crew;
import com.anfaas.assignment.R;

public class CrewAdapter extends PagedListAdapter<Crew, CrewAdapter.CrewViewHolder> {

    private Context context;
    private static Listener listener;
    public CrewAdapter(Context context,Listener listener)
    {
        super(itemCallback);
        this.listener=listener;
        this.context=context;
    }


    @NonNull
    @Override
    public CrewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CrewViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.each_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CrewViewHolder holder, int position) {
        Crew crew=getItem(position);
        holder.name.setText(crew.getCrew_name());
        holder.status.setText("status : "+crew.getStatus());
        Glide.with(context)
                .load(crew.getPoster())
                .into(holder.poster);
    }

    static class CrewViewHolder extends RecyclerView.ViewHolder{
        TextView name,status;
        ImageView poster;
        public CrewViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            status=itemView.findViewById(R.id.status);
            poster=itemView.findViewById(R.id.poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  listener.onClickItemListener(getAdapterPosition());
                }
            });
        }
    }

    private static DiffUtil.ItemCallback<Crew> itemCallback=new DiffUtil.ItemCallback<Crew>() {
        @Override
        public boolean areItemsTheSame(@NonNull Crew oldItem, @NonNull Crew newItem) {
            return oldItem.getDatabase_id() == newItem.getDatabase_id();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Crew oldItem, @NonNull Crew newItem) {
            return oldItem.equals(newItem);
        }
    };
}
