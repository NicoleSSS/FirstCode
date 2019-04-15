package com.example.first.ui.recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.first.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName: FruitAdapter
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/4/3 14:53
 * @Version: 1.0
 */
public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

    private List<String> mList;

    public WordAdapter(List<String> list){
        this.mList = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        Button word;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.word);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String word = mList.get(position);
        holder.word.setText(word);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
