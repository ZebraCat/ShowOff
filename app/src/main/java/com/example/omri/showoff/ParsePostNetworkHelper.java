package com.example.omri.showoff;

import android.net.Uri;
import android.util.Log;

import com.example.omri.showoff.Helpers.QueryParams;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asaf on 24-Jul-15.
 */
public class ParsePostNetworkHelper implements NetworkHelper {
    private DataManager dataManager;
    private final int GET_PREV_POSTS = 0;
    private final int UPDATE_LIKES = 0;
    private final int PAGE_SIZE = 8;
    private boolean processingFetch;
    private boolean proccessingUpdate;

    public ParsePostNetworkHelper(DataManager dataManager){
        this.dataManager = dataManager;
        processingFetch = false;
        proccessingUpdate = false;
    }

    @Override
    public void update(QueryParams params) {
        final QueryParams parameters = params;
        if(params.getQueryNum() == UPDATE_LIKES){
            if(!proccessingUpdate){
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
                query.getInBackground(params.getObjectId(), new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject parseObject, ParseException e) {
                        parseObject.put("likes",parseObject.getInt("likes") + parameters.getAmount());
                        parseObject.saveInBackground();
                    }
                });
            }
        }
    }

    public void fetch(QueryParams params) {
        if(params.getQueryNum() == GET_PREV_POSTS){
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
            query.whereLessThan("updatedAt", params.getDate());
            query.setLimit(PAGE_SIZE);
            query.orderByDescending("updatedAt");

            if (!processingFetch) {
                processingFetch = true;
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, ParseException e) {
                        if (e == null) {
                            if (list.size() == 0) {
                                processingFetch = false;
                                Log.d("QUERY", "size is 0");
                                return;
                            }
                            processingFetch = false;
                            List<Post> posts = ParseToPostTransformer.trasformPosts(list);
                            dataManager.getResults(posts);
                        } else {
                            Log.d("QUERY", e.toString());
                        }
                    }
                });
            }
        }
    }

    private static class ParseToPostTransformer{

        public static List<Post> trasformPosts(List<ParseObject> parsePosts){

            List<Post> transformedPosts = new ArrayList<>();
            for(ParseObject object: parsePosts){
                Post post = new Post();
                post.setPostId(object.getObjectId());
                post.setLikes(object.getInt("likes"));
                post.setPostImage(Uri.parse(object.getParseFile("postPicture").getUrl()));
                post.setPostText(object.getString("postText"));
                post.setUserName(getPostUserName(object));
                post.setProfileImage(Uri.parse(getProfileImage(object)));
                post.setPostedAt(object.getCreatedAt());
                transformedPosts.add(post);
            }
            for(Post post: transformedPosts){
                Log.d("Post",post.toString());
            }
            return transformedPosts;
        }

        private static String getPostUserName(ParseObject object){
            ParseUser user = object.getParseUser("owner");
            String userName = "";
            try{
                user.fetchIfNeeded();
                userName = user.getString("username");
            }catch(ParseException e){
                Log.d("Exception",e.toString());
            }
            return userName;
        }

        private static String getProfileImage(ParseObject object){
            ParseUser user = object.getParseUser("owner");
            String url = "";
            try{
                user.fetchIfNeeded();
                url = user.getParseFile("profilePicture").getUrl();
            }catch(ParseException e) {
                Log.d("Exception", e.toString());
            }
            return url;
        }
    }
}
