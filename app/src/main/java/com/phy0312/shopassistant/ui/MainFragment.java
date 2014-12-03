package com.phy0312.shopassistant.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.phy0312.shopassistant.R;
import com.phy0312.shopassistant.ui.base.UIUtil;
import com.phy0312.shopassistant.ui.food.Food;
import com.phy0312.shopassistant.adapter.MainItemAdpter;
import com.phy0312.shopassistant.data.DataManager;
import com.phy0312.shopassistant.model.MainColumnGroup;
import com.phy0312.shopassistant.tools.AndroidUtil;
import com.phy0312.shopassistant.tools.ImageLoaderUtil;
import com.phy0312.shopassistant.tools.ThreadUtil;
import com.phy0312.shopassistant.view.PullToRefreshLayout;
import com.phy0312.shopassistant.view.smoothprogressbar.SmoothProgressBar;
import com.phy0312.shopassistant.view.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements PullToRefreshLayout.PullRefreshListener{


    private PullToRefreshLayout ptl_container;
    private ListView lv_content;
    private ViewPager viewPager;
    private CirclePageIndicator indicator;
    List<ImageView> viewList = new ArrayList<ImageView>();
    private Handler handler;
    private MainItemAdpter adpter;
    DisplayImageOptions options;
    TextView tv_food;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        handler = new Handler();

        options = ImageLoaderUtil.newDisplayImageOptionsInstance();

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ptl_container = (PullToRefreshLayout)view.findViewById(R.id.ptl_container);
        lv_content = (ListView)view.findViewById(R.id.lv_content);
        ptl_container.setListView(lv_content);
        SmoothProgressBar ptr_progress_up = (SmoothProgressBar)view.findViewById(R.id.ptr_progress_up);
        ptl_container.setUpProgressBar(ptr_progress_up);
        ptl_container.setOnPullRefreshListener(this);

        LayoutInflater lif = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View headerView = lif.inflate(R.layout.main_header, lv_content, false);

        lv_content.addHeaderView(headerView);
        lv_content.setAdapter(adpter);

        tv_food = (TextView)headerView.findViewById(R.id.tv_food);
        tv_food.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName(MainFragment.this.getActivity(), Food.class.getName());
                AndroidUtil.startActivity(MainFragment.this.getActivity(), intent);

            }
        });

        viewPager = (ViewPager)headerView.findViewById(R.id.pager);
        UIUtil.initAdsBanner(getActivity(), viewList, viewPager);
        indicator = (CirclePageIndicator)headerView.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        startLoad(false, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onRefreshingUp() {
        startLoad(true, false);
    }

    @Override
    public void onRefreshingBottom() {
        startLoad(false, true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 开始加载数据
     */
    private void startLoad(final boolean isUp, final boolean isBottom) {
        ThreadUtil.executeMore(new Runnable(){
            @Override
            public void run() {
                final List<MainColumnGroup> list = DataManager.GetMainColumnInfos();
                //更新数据
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(adpter == null) {
                            adpter = new MainItemAdpter(list, MainFragment.this.getActivity());
                        }else{
                            adpter.setList(list);
                        }
                        lv_content.setAdapter(adpter);
                        adpter.notifyDataSetChanged();

                        if(isUp) {
                            ptl_container.setRefreshUpEnd();
                        }

                        if(isBottom) {
                            ptl_container.setRefreshingBottomEnd();
                        }


                    }
                });
            }
        });
    }

}