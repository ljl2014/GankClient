package com.monsterlin.gank.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.monsterlin.gank.R;


/**
 * Created by monsterLin on 7/15/2016.
 */
public class PicsHolder extends RecyclerView.ViewHolder {
    public ImageView iv_pic;

    public PicsHolder(View itemView) {
        super(itemView);
        iv_pic= (ImageView) itemView.findViewById(R.id.iv_pic);
    }
}
