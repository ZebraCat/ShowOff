package com.example.omri.showoff;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.omri.showoff.Helpers.PostAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asaf on 25-Jul-15.
 */
public class PostFragment extends Fragment implements AbsListView.OnScrollListener{

    private ListView postListView;
    private PostAdapter mPostAdapter;
    private List<Post> postArray;
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PostFragment newInstance(int sectionNumber) {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PostFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.post_fragment,container,false);
        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        postArray = new ArrayList<>();
        postListView = (ListView)getActivity().findViewById(R.id.postListView);
        mPostAdapter = new PostAdapter(getActivity(),postArray);
        postListView.setAdapter(mPostAdapter);
        postListView.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        int lastItemIndex = firstVisibleItem + visibleItemCount;
        if(lastItemIndex == totalItemCount){
            Log.d("PostFragment", "Going to call get next posts");
            mPostAdapter.getNextPosts();
        }
    }
}
