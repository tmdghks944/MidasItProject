package com.midas.mobile3.mobile3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midas.mobile3.mobile3.controller.PointThingAdapter;

public class PointFragment extends Fragment {

    RecyclerView mRecyclerView;
    PointThingAdapter mAdapter;

    public PointFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_point, container, false);
        setLayout(v);
        return v;
    }
    private void setLayout(View v){
        mRecyclerView = (RecyclerView)v.findViewById(R.id.list);
        mAdapter = new PointThingAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
