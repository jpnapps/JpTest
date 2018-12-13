package com.jpndev.jpmusicplayer.roomdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.jpndev.jpmusicplayer.R;

import java.util.List;

/**
 * Created by Pavneet_Singh on 12/20/17.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.BeanHolder> {

    private List<MItem> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private OnMItemClick onNoteItemClick;

    public ItemAdapter(List<MItem> list, Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
        this.onNoteItemClick = (OnMItemClick) context;
    }


        @Override
    public BeanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.note_list_item,parent,false);
        return new BeanHolder(view);
    }

    @Override
    public void onBindViewHolder(BeanHolder holder, int position) {
        Log.e("bind", "onBindViewHolder: "+ list.get(position));
        holder.textViewTitle.setText(list.get(position).getName());
        holder.textViewContent.setText(list.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BeanHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewContent;
        TextView textViewTitle;
        public BeanHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewContent = itemView.findViewById(R.id.item_text);
            textViewTitle = itemView.findViewById(R.id.tv_title);
        }

        @Override
        public void onClick(View view) {
            onNoteItemClick.onMItemClick(getAdapterPosition());
        }
    }

    public interface OnMItemClick{
        void onMItemClick(int pos);
    }
}