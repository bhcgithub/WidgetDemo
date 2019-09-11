package com.fbs.widgetdemo.popout.card;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fbs.widgetdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2019/9/9
 */
public class PopOutAdapter extends RecyclerView.Adapter<PopOutAdapter.VH> {
    private final List<PopOutBean> mDatas;
    private PopOutItemListener itemListener;

    public interface PopOutItemListener {
        void onItemClick(PopOutBean outBean);
    }

    public void setOnPopOutItemListener(PopOutItemListener onPopOutItemListener) {
        itemListener = onPopOutItemListener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.popoutitem, parent, false);
        return new VH(inflate);
    }

    public PopOutAdapter(List<PopOutBean> data) {
        this.mDatas = data;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {
        holder.Im.setImageDrawable(mDatas.get(position).getDrawable());
        holder.II.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemListener!=null)
                    itemListener.onItemClick(mDatas.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        private final ImageView Im;
        private final LinearLayout II;

        public VH(@NonNull View itemView) {
            super(itemView);
            Im = (ImageView) itemView.findViewById(R.id.pop_item_iv);
            II= (LinearLayout) itemView.findViewById(R.id.ll_pop_item);
        }
    }
}
