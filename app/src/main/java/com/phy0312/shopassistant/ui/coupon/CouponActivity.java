package com.phy0312.shopassistant.ui.coupon;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.phy0312.shopassistant.R;
import com.phy0312.shopassistant.ui.base.BaseActivity;

/**
 * description: <br/>
 * author: dingdongjin_91<br/>
 * date: 2014/12/19<br/>
 */
public class CouponActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(0, 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getDrawTitle());

        CouponFragment couponFragment = new CouponFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.flv_main_content, couponFragment).commit();
    }



    @Override
    protected int getSelfNavDrawerItem() {
        return BaseActivity.NAVDRAWER_ITEM_COUPON;
    }


    @Override
    public String getDrawTitle() {
        return getString(R.string.navdrawer_item_coupon);
    }



}
