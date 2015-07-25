package com.example.omri.showoff;

import android.net.Uri;
import android.util.Log;

import com.example.omri.showoff.Helpers.QueryParams;
import com.parse.FindCallback;
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
    private final int PAGE_SIZE = 8;
    private boolean processing;

    public ParsePostNetworkHelper(DataManager dataManager){
        this.dataManager = dataManager;
        processing = false;
    }

    public void fetch(QueryParams params) {
        if(params.getQueryNum() == GET_PREV_POSTS){
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
            query.whereLessThan("updatedAt", params.getDate());
            query.setLimit(PAGE_SIZE);
            query.orderByDescending("updatedAt");

            if (!processing) {
                processing = true;
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, ParseException e) {
                        if (e == null) {
                            if (list.size() == 0) {
                                processing = false;
                                Log.d("QUERY", "size is 0");
                                return;
                            }
                            processing = false;
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
