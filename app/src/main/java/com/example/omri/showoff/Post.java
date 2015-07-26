package com.example.omri.showoff;

import android.net.Uri;

import java.util.Date;

/**
 * Created by Asaf on 24-Jul-15.
 */
public class Post implements ShowoffItem {
    private String postId;
    private String userName;
    private String postText;
    private int likes;
    private Uri profileImage;
    private Uri postImage;
    private Date postedAt;

    public String getUserName() {
        return userName;
    }

    public String getPostText() {
        return postText;
    }

    public int getLikes() {
        return likes;
    }

    public Uri getPostImage() {
        return postImage;
    }

    public Uri getProfileImage() {
        return profileImage;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setProfileImage(Uri profileImage) {
        this.profileImage = profileImage;
    }

    public void setPostImage(Uri postImage) {
        this.postImage = postImage;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
    //delete this
    public String toString(){
        return  "User Name: " + getUserName() + "\n" +
                "Post Text: " + getPostText() + "\n" +
                "Likes: " + getLikes() + "\n" +
                "Profile Image Url: " + getProfileImage() + "\n" +
                "Post Image Url :" + getPostImage() + "\n";
    }
}
