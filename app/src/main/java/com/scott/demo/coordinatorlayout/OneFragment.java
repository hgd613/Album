package com.scott.demo.coordinatorlayout;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scott.demo.R;

public class OneFragment extends Fragment {


    public static OneFragment newInstance() {
        OneFragment fragment = new OneFragment();
        return fragment;
    }

    public OneFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        RecyclerView recycle_view = (RecyclerView) view.findViewById(R.id.recycle_view);
        MyAdapter adapter = new MyAdapter(getActivity());

        //设置布局管理器
        recycle_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置adapter
        recycle_view.setAdapter(adapter);
        //设置Item增加、移除动画
        recycle_view.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
