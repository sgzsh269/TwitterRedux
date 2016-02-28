package com.sagarnileshshah.twitterclient.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.sagarnileshshah.twitterclient.fragments.TimelineFragment;

/**
 * Created by sshah on 2/27/16.
 */
public class ProfileFragmentPagerAdapter extends SmartFragmentStatePagerAdapter {

    private static final int PAGE_COUNT = 3;
    private static final String TAB_TITLES[] = new String[]{"Tweets", "Media", "Likes"};
    private Context mContext;
    private long mUserId = -1;

    public ProfileFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;

    }

    public ProfileFragmentPagerAdapter(FragmentManager fm, Context context, long userId) {
        super(fm);
        mContext = context;
        mUserId = userId;
    }

    @Override
    public Fragment getItem(int position) {
        String tab = TAB_TITLES[position];
        String timeline = "user";
        if(tab.equals("Tweets")) {
            timeline = "user";
        }
        return TimelineFragment.newInstance(timeline, mUserId);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }
}
