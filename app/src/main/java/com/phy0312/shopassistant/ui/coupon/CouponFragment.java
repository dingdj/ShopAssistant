package com.phy0312.shopassistant.ui.coupon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.phy0312.shopassistant.R;
import com.phy0312.shopassistant.ui.base.UIUtil;
import com.phy0312.shopassistant.adapter.CouponAdapter;
import com.phy0312.shopassistant.data.DataManager;
import com.phy0312.shopassistant.db.Coupon;
import com.phy0312.shopassistant.tools.ThreadUtil;
import com.phy0312.shopassistant.view.PullToRefreshLayout;
import com.phy0312.shopassistant.view.smoothprogressbar.SmoothProgressBar;
import com.phy0312.shopassistant.view.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;


public class CouponFragment extends Fragment implements PullToRefreshLayout.PullRefreshListener,
        AdapterView.OnItemClickListener{

    private PullToRefreshLayout ptl_container;
    private ListView lv_content;
    private ViewPager viewPager;
    private CirclePageIndicator indicator;
    List<ImageView> viewList = new ArrayList<ImageView>();
    private Handler handler;
    private Spinner sp_category_name;
    private Spinner sp_category_sort;
    private CouponAdapter adpter;


    public CouponFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_coupon, container, false);
        ptl_container = (PullToRefreshLayout)view.findViewById(R.id.ptl_container);
        lv_content = (ListView)view.findViewById(R.id.lv_content);
        ptl_container.setListView(lv_content);
        ptl_container.setUpProgressBar((SmoothProgressBar)view.findViewById(R.id.ptr_progress_up));
        ptl_container.setOnPullRefreshListener(this);

        LayoutInflater lif = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View headerView = lif.inflate(R.layout.list_header_coupon, lv_content, false);
        lv_content.addHeaderView(headerView);
        lv_content.setAdapter(null);
        lv_content.setOnItemClickListener(this);

        viewPager = (ViewPager)headerView.findViewById(R.id.pager);
        UIUtil.initAdsBanner(getActivity(), viewList, viewPager);
        indicator = (CirclePageIndicator)headerView.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        initSpinners(view);
        startLoad(false, false);
        return view;
    }

    private void initSpinners(View view) {
        sp_category_name = (Spinner)view.findViewById(R.id.sp_category_name);
        sp_category_sort = (Spinner)view.findViewById(R.id.sp_category_sort);

        sp_category_name.setAdapter(new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item,
                this.getResources().getStringArray(R.array.coupon_categoty)));

        sp_category_sort.setAdapter(new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item,
                this.getResources().getStringArray(R.array.coupon_order)));


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClassName(getActivity(), DetailCoupon.class.getName());
        try{
            getActivity().startActivity(intent);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRefreshingUp() {
        startLoad(true, false);
    }

    @Override
    public void onRefreshingBottom() {
        startLoad(false, true);
    }


    private void startLoad(final boolean isUp, final boolean isBottom) {
        ThreadUtil.executeMore(new Runnable() {

            @Override
            public void run() {
                final List<Coupon> list = DataManager.getCoupons();
                //更新数据
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (adpter == null) {
                            adpter = new CouponAdapter(CouponFragment.this.getActivity(), list);
                        } else {
                            adpter.setList(list);
                        }
                        lv_content.setAdapter(adpter);
                        adpter.notifyDataSetChanged();

                        if (isUp) {
                            ptl_container.setRefreshUpEnd();
                        }

                        if (isBottom) {
                            ptl_container.setRefreshingBottomEnd();
                        }
                    }
                });
            }
        });

    }
}