package com.midas.mobile3.mobile3;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.midas.mobile3.mobile3.db.BusinessDBHelper;

public class MainActivity extends AppCompatActivity {

    public static VoluntaryFragment voluntaryFragment;
    public static VoluntaryCheckFragment voluntaryCheckFragment;
    public static BusinessFragment businessFragment;
    public static ReportFragment reportFragment;
    public static PointFragment pointFragment;

    private long backPressedTime =0;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private FloatingActionButton fab;


    private CoordinatorLayout mainLayout;

    private final String menuNames[] = {"봉사활동", "활동내역", "기부하기","사업결과", "포인트"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLayout();
    }

    private void setLayout(){
        mainLayout = (CoordinatorLayout)findViewById(R.id.main_content) ;

        tabLayout = (TabLayout)findViewById(R.id.main_tab);
        for(String name : menuNames){
            tabLayout.addTab(tabLayout.newTab().setText(name));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.main_viewpager);

        TabPageAdapter pageAdapter = new TabPageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tabLayout.getPointerIcon()
                getAdminDialog();
            }
        });

        if( !Shared.getIsAdmin(this) ){
           fab.setVisibility(View.GONE);
        }
    }


    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() > backPressedTime+2000){
            Snackbar.make(mainLayout, "1번 더 누르면 종료됩니다.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            backPressedTime = System.currentTimeMillis();
        }else{
            finish();
        }
    }


    private class TabPageAdapter extends FragmentStatePagerAdapter{

        private int tabCount;


        public TabPageAdapter(FragmentManager fm, int tabCount){
            super(fm);
            this.tabCount=tabCount;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    if(voluntaryFragment==null){
                        voluntaryFragment = new VoluntaryFragment();
                    }
                    return voluntaryFragment;
                case 1:
                    if(voluntaryCheckFragment==null){
                        voluntaryCheckFragment = new VoluntaryCheckFragment();
                    }
                    return voluntaryCheckFragment;
                case 2:
                    if(businessFragment==null){
                        businessFragment = new BusinessFragment();
                    }
                    return businessFragment;
                case 3:
                    if(reportFragment==null){
                        reportFragment = new ReportFragment();
                    }
                    return reportFragment;

                case 4:
                    if(pointFragment==null){
                        pointFragment = new PointFragment();
                    }
                    return pointFragment;


            }
            return null;
        }

        @Override
        public int getCount() {
            return tabCount;
        }
    }



    private void getAdminDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_admin);

        final LinearLayout btnUser = (LinearLayout) dialog.findViewById(R.id.admin_btn_voluntary);
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdminVoluntaryActivity.class));
            }
        });

        final LinearLayout btnVolAdd = (LinearLayout) dialog.findViewById(R.id.admin_btn_voluntary_add);
        btnVolAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdminAddActivity.class));
            }
        });
        final LinearLayout btnBAdd = (LinearLayout) dialog.findViewById(R.id.admin_btn_business_add);
        btnBAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdminBusinessActivity.class));
            }
        });

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

            }
        });

        dialog.show();
    }
}

