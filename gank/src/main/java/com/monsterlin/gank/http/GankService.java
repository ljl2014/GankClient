package com.monsterlin.gank.http;


import com.monsterlin.gank.bean.Data;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by monsterLin on 7/15/2016.
 */
public interface GankService {

    @GET("/api/data/{type}/{number}/{page}")
    Observable<Data> getDatas(@Path("type") String type, @Path("number") int number, @Path("page") int page);

}
