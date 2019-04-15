package com.example.first.material;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.first.R;
import com.example.first.ui.Fruit;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName: FruitAdapter
 * @Description: 描述
 * @Author: 范琳琳
 * @CreateDate: 2019/4/3 14:53
 * @Version: 1.0
 */
public class FruitCardAdapter extends RecyclerView.Adapter<FruitCardAdapter.ViewHolder> {

    private Context mContext;
    private List<Fruit> mFruitList;

    public FruitCardAdapter(List<Fruit> fruitList){
        this.mFruitList = fruitList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
            fruitImage =itemView.findViewById(R.id.img_card_fruit);
            fruitName = itemView.findViewById(R.id.tv_card_fruit);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_fruit, parent,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();
            Fruit fruit = mFruitList.get(position);
            Intent intent = new Intent(mContext,FruitActivity.class);
            intent.putExtra(FruitActivity.FRUIT, fruit);
//                intent.putExtra(FruitActivity.FRUIT_NAME, fruit.getName());
//                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.getImageId());
            mContext.startActivity(intent);
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImage);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }


}
