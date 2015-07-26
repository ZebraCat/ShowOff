package com.example.omri.showoff.Helpers;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.omri.showoff.MyAdapter;
import com.example.omri.showoff.Post;
import com.example.omri.showoff.PostDataManager;
import com.example.omri.showoff.R;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

/**
 * Created by Asaf on 25-Jul-15.
 */
public class PostAdapter extends ArrayAdapter<Post> implements MyAdapter{

    private List<Post> values;
    private Context context;
    private PostDataManager dataManager;

    @Override
    public void getResults(List<Post> list) {
        values.addAll(list);
        this.notifyDataSetChanged();
        Log.d("Adapter", "In get results");
    }

    public void getNextPosts(){
        if(values.size() > 0)
            dataManager.fetchNextPosts(values.get(values.size() - 1).getPostedAt());
        else
            dataManager.fetchNextPosts(new Date(System.currentTimeMillis()));
    }

    static class ViewHolder{
        public TextView userName;
        public TextView postText;
        public ImageView postPic;
        public ImageView profilePic;
        public Button loveItButton;
        public Button commentButton;
        public TextView likes;
        public TextView postedAt;
    }

    public PostAdapter(Context context,List<Post> values){
        super(context, R.layout.post_item,values);
        this.context = context;
        this.values = values;
        dataManager = new PostDataManager(this);
    }

    public View getView(final int position,View convertView,ViewGroup parent){
        View rowView = convertView;
        if(rowView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.post_item,parent,false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.userName = ((TextView) rowView.findViewById(R.id.post_user_name));
            viewHolder.profilePic = ((ImageView) rowView.findViewById(R.id.post_profile_pic));
            viewHolder.postPic = ((ImageView) rowView.findViewById(R.id.post_image));
            viewHolder.postText = ((TextView) rowView.findViewById(R.id.post_text));
            viewHolder.commentButton = ((Button) rowView.findViewById(R.id.comment_button));
            viewHolder.loveItButton = ((Button) rowView.findViewById(R.id.love_it_button));
            viewHolder.loveItButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!v.isSelected()){
                        v.setSelected(true);
                        ((Button)v).setText("Hate It!");
                        ((Button)v).setTextColor(Color.parseColor("#FF0000"));//red color
                        dataManager.updateLoveIt(values.get(position).getPostId(),1);
                    }
                    else{
                        v.setSelected(false);
                        ((Button)v).setText("Love It!");
                        ((Button)v).setTextColor(Color.parseColor("#00CC00"));//green color
                        dataManager.updateLoveIt(values.get(position).getPostId(),-1);
                    }
                }
            });
            viewHolder.likes = ((TextView) rowView.findViewById(R.id.post_likes));
            viewHolder.postedAt = ((TextView) rowView.findViewById(R.id.post_posted_at));
            rowView.setTag(viewHolder);
        }
        Log.d("Adapter", "In getView()");

        ViewHolder holder = (ViewHolder)rowView.getTag();
        holder = PostViewHolderBuilder.build(holder, values.get(position),context);

        return rowView;
    }

    private static class PostViewHolderBuilder{

        /**
         * a method that takes a view holder and builds it
         * @return the built post viewholder
         */
        public static ViewHolder build(ViewHolder postViewHolder,Post post,Context context){

            postViewHolder.postText.setText(post.getPostText());
            Log.d("Builder",post.getPostText());
            Log.d("Builder",postViewHolder.postText.getText().toString());
            postViewHolder.userName.setText(post.getUserName());
            if(post.getLikes() > 0)
                postViewHolder.likes.setText(String.valueOf(post.getLikes()) + " People Love It!");

            //TO DO - put placeholder in picasso
            Picasso.with(context).load(post.getProfileImage()).into(postViewHolder.profilePic);
            Picasso.with(context).load(post.getPostImage()).into(postViewHolder.postPic);
            Log.d("Adapter", "In build");


            return postViewHolder;
        }
    }

}
