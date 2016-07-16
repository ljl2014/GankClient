package com.monsterlin.gankclient.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.monsterlin.gankclient.R;

/**
 * Created by monsterLin on 7/16/2016.
 */
public class ArticleHolder extends RecyclerView.ViewHolder {
    public TextView tv_title , tv_date ,tv_author;
    public ArticleHolder(View itemView) {
        super(itemView);
        tv_title= (TextView) itemView.findViewById(R.id.tv_title);
        tv_date= (TextView) itemView.findViewById(R.id.tv_date);
        tv_author= (TextView) itemView.findViewById(R.id.tv_author);

    }
}
