package com.fbs.widgetdemo.utils.universaladapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiXiaoSong on 2017/1/10.
 *
 * @Describe 通用RecycleView适配器
 */

public abstract class UniversalAdapter<T> extends RecyclerView.Adapter<UniversalAdapter.UniversalVH> {
    private OnItemClickListener<T> listener;
    protected List<T> data;
    protected Context context;

    public UniversalAdapter(List<T> src, Context context) {
        data = new ArrayList<>();
        if (src != null) {
            this.data.addAll(src);
        }
        this.context = context;
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public void onBindViewHolder(UniversalAdapter.UniversalVH holder, int position) {
        holder.setPos(position);
        convert(holder, data.get(position), position);
    }

    @Override
    public void onBindViewHolder(UniversalAdapter.UniversalVH holder, int position, List<Object> payloads) {
        onBindViewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public UniversalVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UniversalVH<>(LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false));
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.listener = listener;
    }

    /**
     * 更新当前数据源
     *
     * @param newData
     */
    public void updateData(List<T> newData) {
        this.data.clear();
        this.data.addAll(newData);
        notifyDataSetChanged();
    }

    public void updateSomeOne(T data1, int pos) {
        this.data.remove(pos);
        this.data.add(pos, data1);
//        notifyItemChanged(pos);
        notifyDataSetChanged();
        Log.d("bhc", "updateSomeOne:刷新数据 ");
    }

    /**
     * 添加数据源
     *
     * @param newData
     */
    public void addData(List<T> newData) {
        this.data.addAll(newData);
        notifyDataSetChanged();
    }

    public class UniversalVH<T> extends RecyclerView.ViewHolder {
        private int pos;
        private SparseArray<View> views;

        public UniversalVH(final View itemView) {
            super(itemView);
            views = new SparseArray<>();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(data.get(pos), v,pos);
                    }
                }
            });
        }

        public View getView(int id) {
            View v = views.get(id);
            if (v == null) {
                v = itemView.findViewById(id);
                views.put(id, v);
            }
            return v;
        }

        public void setText(int id, String text) {
            ((TextView) getView(id)).setText(text);
        }
        public void setTextNew(int id, String text) {
            if(TextUtils.isEmpty(text)){
                getView(id).setVisibility(View.GONE);
            }else {
                ((TextView) getView(id)).setVisibility(View.VISIBLE);
                ((TextView) getView(id)).setText(text);
            }
        }

        public void ShowViewOrNot(int id, String text) {
            if(TextUtils.isEmpty(text)){
                ((TextView) getView(id)).setVisibility(View.GONE);
            }else {
                ((TextView) getView(id)).setVisibility(View.VISIBLE);
            }
        }
        public void showTextView(int id) {
            getView(id).setVisibility(View.VISIBLE);
        }

        public void setImage(int id, String url, boolean flag) {
//            if (flag) {
//                BitmapManager.INSTANCE.displayImage((ImageView) getView(id), url);
//            } else {//圆形
//                BitmapManager.AGENT.displayImageCircle((ImageView) getView(id), url);
//            }


        }

        public void setImageLocal(int id, int res) {
            ((ImageView) getView(id)).setImageResource(res);
        }


        public void setPos(int pos) {
            this.pos = pos;
        }
    }

    public abstract int getLayoutId(int type);

    public abstract void convert(UniversalVH vh, T data, int pos);

}
