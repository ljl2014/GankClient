package com.monsterlin.gank.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monsterlin.gank.R;
import com.monsterlin.gank.bean.Results;
import com.monsterlin.gank.biz.OnItemClickListener;
import com.monsterlin.gank.viewholder.PicsHolder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;


/**
 * Created by monsterLin on 7/15/2016.
 */
public class PicsAdapter extends RecyclerView.Adapter<PicsHolder> {

    private ArrayList<Results> picsList;
    private Context mContext;
    private LayoutInflater mInflater;

    public OnItemClickListener mOnItemClickListener;


    public PicsAdapter(ArrayList<Results> picsList, Context mContext) {
        this.picsList = picsList;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public PicsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_gank, parent, false);
        PicsHolder picsHolder = new PicsHolder(view);
        return picsHolder;
    }

    @Override
    public void onBindViewHolder(final PicsHolder holder, int position) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader.getInstance().displayImage(picsList.get(position).getUrl(), holder.iv_pic, options);

        if (mOnItemClickListener != null) {
            holder.iv_pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int LayoutPosition = holder.getLayoutPosition();
                    mOnItemClickListener.OnItemClick(LayoutPosition, holder.itemView);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return picsList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public Results getResult (int position){
        return picsList.get(position);
    }
}

