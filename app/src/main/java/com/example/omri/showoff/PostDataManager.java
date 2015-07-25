package com.example.omri.showoff;

import com.example.omri.showoff.Helpers.QueryParams;

import java.util.Date;
import java.util.List;

/**
 * Created by Asaf on 25-Jul-15.
 */
public class PostDataManager implements DataManager {

    private NetworkHelper networkHelper;
    private MyAdapter adapter;

    public PostDataManager(MyAdapter adapter){
        this.adapter = adapter;
        networkHelper = new ParsePostNetworkHelper(this);
    }

    public void fetchNextPosts(Date lastPostedAt){
        QueryParams params = new QueryParams();
        params.setDate(lastPostedAt);
        params.setQueryNum(0);
        networkHelper.fetch(params);
    }
    @Override
    public void getResults(List<? extends ShowoffItem> results) {
        adapter.getResults((List<Post>)results);
    }
}
