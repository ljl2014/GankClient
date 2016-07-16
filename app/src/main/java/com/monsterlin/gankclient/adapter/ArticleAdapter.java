package com.monsterlin.gankclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monsterlin.gankclient.R;
import com.monsterlin.gankclient.bean.Results;
import com.monsterlin.gankclient.biz.OnItemClickListener;
import com.monsterlin.gankclient.viewholder.ArticleHolder;

import java.util.ArrayList;

/**
 * Created by monsterLin on 7/16/2016.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleHolder> {
    private ArrayList<Results> articleList;
    private Context mContext;
    private LayoutInflater mInflater;

    public OnItemClickListener mOnItemClickListener;


    public ArticleAdapter(ArrayList<Results> picsList, Context mContext) {
        this.articleList = picsList;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_article_list, parent, false);
        ArticleHolder articleHolder = new ArticleHolder(view);
        return articleHolder;
    }

    @Override
    public void onBindViewHolder(final ArticleHolder holder, int position) {
        holder.tv_title.setText(articleList.get(position).getDesc());

        String dateString = articleList.get(position).getPublishedAt();
        holder.tv_date.setText(dateString.substring(0, 10));
        holder.tv_author.setText(articleList.get(position).getWho());

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
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
        return articleList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public Results getResult(int position) {
        return articleList.get(position);
    }
}
