package com.midas.mobile3.mobile3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midas.mobile3.mobile3.controller.ActiveThingAdapter;
import com.midas.mobile3.mobile3.controller.VoluntaryAdapter;

public class VoluntaryCheckFragment extends Fragment {

    // TODO : 리사이클러뷰 만들어야됨
    RecyclerView mRecyclerView;
    ActiveThingAdapter mAdapter;

    public VoluntaryCheckFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_voluntary_check, container, false);
        setLayout(v);

        return v;
    }

    private void setLayout(View v){
        mRecyclerView = (RecyclerView)v.findViewById(R.id.list);
        mAdapter = new ActiveThingAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    public void updateDataset(){
        mAdapter.updateDataset();
        mAdapter.notifyDataSetChanged();
    }

}
