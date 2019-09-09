package com.fbs.widgetdemo.popout;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fbs.widgetdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2019/9/9
 */
public class PopOutAdapter extends RecyclerView.Adapter<PopOutAdapter.VH> {
    private final List<Drawable> mDatas;

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate =LayoutInflater.from(parent.getContext()).inflate(R.layout.popoutitem,parent, false);
        return new VH(inflate);
    }

    public PopOutAdapter(List<Drawable> data) {
        this.mDatas=data;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
            holder.Im.setImageDrawable(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class VH extends RecyclerView.ViewHolder{
        private final ImageView Im;

        public VH(@NonNull View itemView) {
            super(itemView);
            Im=(ImageView) itemView.findViewById(R.id.pop_item_iv);
        }
    }
}
