package com.wwish.demo.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wwish.demo.R;
import com.wwish.frame.PageIndexer;
import com.wwish.frame.fragment.BaseFragment;
import com.wwish.frame.adapter.MyFragmentPagerAdapter;
import com.wwish.frame.viewpager.BaseViewPager;


public class HomePageFragment extends BaseFragment implements PageIndexer {
    private final static String TAG = HomePageFragment.class.getSimpleName();
    public final static int MSG_REFRESH_BANNER = 1;
    public final static String IS_SHOW_PUSH_DIALOG = "is_show_dialog";
    public final static String PUSH_DIALOG_TITLE = "dialog_title";
    public final static String PUSH_DIALOG_MSG = "dialog_message";
    private int mViewIndex = TAB_VIEW_00;
    private MyFragmentStatePagerAdapter myFragmentStatePagerAdapter;
    private BaseFragment[] baseFragments;
    public BaseViewPager baseViewPager;
    public ImageView searchBtn;
    public FrameLayout moreBtn;
    public TextView perfTab;
    public TextView musicHallTab;
    public TextView findTab;
    public ImageView perfNewFlag;
    public ImageView findNewFlag;
    public ImageView moreNewFlag;
    private FragmentManager fragmentManager;

    private boolean mIsFirstIn = true;

    public static final int MY_MUSIC_NEW_FLAG = 0x0001;

    public static final int FIND_NEW_FLAG = 0x0002;

    public static final int MORE_NEW_FLAG = 0x0003;

    public interface OnNewFlagChangedLitener {

        public void onNewFlagShow(int newFlag);

        public void onNewFlagHide(int newFlag);
    }

    private OnNewFlagChangedLitener mOnNewFlagChangedLitener = new OnNewFlagChangedLitener() {

        @Override
        public void onNewFlagShow(int newFlag) {
            switch (newFlag) {
                case MY_MUSIC_NEW_FLAG:
                    perfNewFlag.setVisibility(View.VISIBLE);
                    break;
                case FIND_NEW_FLAG:
                    findNewFlag.setVisibility(View.VISIBLE);
                    break;
                case MORE_NEW_FLAG:
                    moreNewFlag.setVisibility(View.VISIBLE);
                    break;
            }
        }

        @Override
        public void onNewFlagHide(int newFlag) {
            switch (newFlag) {
                case MY_MUSIC_NEW_FLAG:
                    perfNewFlag.setVisibility(View.GONE);
                    break;
                case FIND_NEW_FLAG:
                    findNewFlag.setVisibility(View.GONE);
                    break;
                case MORE_NEW_FLAG:
                    moreNewFlag.setVisibility(View.GONE);
                    break;
            }
        }

    };

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getHostActivity().getLayoutInflater().inflate(R.layout.fragment_homepage, null);
        baseViewPager = (BaseViewPager)view.findViewById(R.id.main_desk_fragment_pager);
        searchBtn = (ImageView)view.findViewById(R.id.main_desk_title_search_btn);
        moreBtn =  (FrameLayout)view.findViewById(R.id.main_desk_title_more_btn);
        perfTab = (TextView)view.findViewById(R.id.main_desk_title_tab_mymusic);
        musicHallTab = (TextView)view.findViewById(R.id.main_desk_title_tab_musichall);
        findTab = (TextView)view.findViewById(R.id.main_desk_title_tab_find);
        perfNewFlag = (ImageView)view.findViewById(R.id.homepage_tab_performance_new_flag);
        findNewFlag = (ImageView)view.findViewById(R.id.main_desk_title_tab_find_new_flag);
        moreNewFlag = (ImageView)view.findViewById(R.id.main_desk_title_more_new_flag);

        initView();
        return view;
    }

    private void initView() {
        baseFragments = new BaseFragment[3];
        TestFirstFragment fragment1 = new TestFirstFragment();
        baseFragments[0] = fragment1;
        fragment1.setRetainInstance(true);
        TestSecondFragment fragment2 = new TestSecondFragment();
        fragment2.setRetainInstance(true);
//        fragment2.setBg(Color.RED);
        baseFragments[1] = fragment2;
        TestThirdFragment fragment3 = new TestThirdFragment();
        fragment3.setRetainInstance(true);
        baseFragments[2] = fragment3;
        myFragmentStatePagerAdapter = new MyFragmentStatePagerAdapter(fragmentManager);
        new setAdapterTask().execute();
        baseViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (position >= 0 && position < myFragmentStatePagerAdapter.getCount() && position != mViewIndex) {
                    setSelectedTab(position);
                }
                BaseFragment f = baseFragments[position];
                if (position == 0) {
                    perfNewFlag.setVisibility(View.GONE);
                } else if (position == 2) {
                    findNewFlag.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {// 滑动pager的时候触发

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        perfTab.setOnClickListener(mTabClickListener);
        musicHallTab.setOnClickListener(mTabClickListener);
        findTab.setOnClickListener(mTabClickListener);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        moreBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }

    private View.OnClickListener mTabClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int position = 0;
            if (v == perfTab) {
                position = 0;
            } else if (v == musicHallTab) {
                position = 1;
            } else if (v == findTab) {
                position = 2;
            }
            setSelectedTab(position);
            baseViewPager.setCurrentItem(position);
        }
    };

    public void gotoSelectedTab(int position) {
        setSelectedTab(position);
        baseViewPager.setCurrentItem(position);
    }

    private void setSelectedTab(int position) {
        mViewIndex = position;
        perfTab.setTextColor(0x66FFFFFF);
        musicHallTab.setTextColor(0x66FFFFFF);
        findTab.setTextColor(0x66FFFFFF);
        switch (mViewIndex) {
            case 0:
                perfTab.setTextColor(0xFFFFFFFF);
                break;
            case 1:
                musicHallTab.setTextColor(0xFFFFFFFF);;
                break;
            case 2:
                findTab.setTextColor(0xFFFFFFFF);
                break;
        }
    }

    @Override
    protected void resume() {
        if (mIsFirstIn) {
            mIsFirstIn = false;
            return;
        }

        for (Fragment f : baseFragments) {
            if (f.isAdded()) {
                f.onResume();
            }
        }
    }

    public BaseFragment getViewPage(int index) {
        if (baseFragments != null && index >= 0 && index < baseFragments.length) {
            return baseFragments[index];
        }
        return null;
    }

    @Override
    protected void stop() {
        for (Fragment f : baseFragments) {
            if (f.isAdded()) {
                f.onStop();
            }
        }

        mIsFirstIn = true;
    }

    @Override
    protected void pause() {
        for (BaseFragment f : baseFragments) {
            if (f.isAdded()) {
                f.onPause();
            }
        }
    }

    @Override
    protected void start() {
        for (Fragment f : baseFragments) {
            if (f.isAdded()) {
                f.onStart();
            }
        }
    }

    @Override
    public void onEnterAnimationEnd(Animation animation) {

    }

    @Override
    public void clearView() {

    }

    @Override
    public void clear() {

    }

    @Override
    protected void initData(Bundle data) {
        fragmentManager = getChildFragmentManager();
        int gotoAppIndex = data.getInt(APP_INDEX_KEY, TAB_VIEW_00);
        if (gotoAppIndex != TAB_VIEW_00 && (gotoAppIndex - APP_MAIN_1) >= TAB_VIEW_00
                && (gotoAppIndex - APP_MAIN_1) < TAB_VIEW_02 + 1) {
            mViewIndex = gotoAppIndex - APP_MAIN_1;
        }
        else {
            mViewIndex = TAB_VIEW_00;
        }
    }

    public class MyFragmentStatePagerAdapter extends MyFragmentPagerAdapter {
        public MyFragmentStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return baseFragments.length;
        }

        @Override
        public Fragment getItem(int position) {
            return baseFragments[position];
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

    }

    private class setAdapterTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            baseViewPager.setAdapter(myFragmentStatePagerAdapter);
            baseViewPager.setOffscreenPageLimit(myFragmentStatePagerAdapter.getCount() + 1);
            baseViewPager.postDelayed(new Runnable() {

                @Override
                public void run() {
                    gotoSelectedFragment();
                }
            }, 300);
        }
    }

    private void gotoSelectedFragment() {
        if (baseFragments != null && baseFragments.length > mViewIndex) {
            setSelectedTab(mViewIndex);
            baseViewPager.setCurrentItem(mViewIndex);
        }
    }
}
