package com.monsterlin.gankclient.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.monsterlin.gankclient.R;
import com.monsterlin.gankclient.activity.ArticleActivity;

/**
 * Created by monsterLin on 7/15/2016.
 */
public class AboutFragment extends Fragment {

    private TextView tv_intro  ;
    private CardView card_codes ;
    private String url = "https://github.com/monsterLin/GankClient";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about,container,false);
        tv_intro= (TextView) view.findViewById(R.id.tv_intro);
        card_codes= (CardView) view.findViewById(R.id.card_codes);

        tv_intro.setText("如果你喜欢，还请多多赏赐："+"\n"+"小猴子的支付宝：15762186585"+"\n"+"小猴子多多感谢亲的支持");
        card_codes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(getContext(), ArticleActivity.class);
                webIntent.putExtra("url", url);
                startActivity(webIntent);
            }
        });
        return view;
    }

}
