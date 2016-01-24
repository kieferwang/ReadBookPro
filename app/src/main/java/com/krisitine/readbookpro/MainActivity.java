package com.krisitine.readbookpro;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

public class MainActivity extends FragmentActivity {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout= (DrawerLayout) findViewById(R.id.id_drawerLayout);
        initEvents();
    }

    private void initEvents(){
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                //获取draweLayout中的第一个字布局，也就是布局中的RelativLayout
                //获取抽屉的view
                View mContent = drawerLayout.getChildAt(0);
                View view = drawerView;
                float scale = 1 - slideOffset;
                float rightScale = 0.8f + scale * 0.2f;


                if (drawerView.getTag().equals("LEFT")) {
                    float leftScale = 1 - 0.3f * scale;

                    //设置左边菜单滑动后的占据屏幕大小
                    ViewHelper.setScaleX(view, leftScale);
                    ViewHelper.setScaleY(view, leftScale);

                    // 设置菜单透明度

                    ViewHelper.setAlpha(view, 0.6f + 0.4f * (1 - scale));

                    //设置内容界面水平和垂直方向偏转量
                    //在滑动时内容界面的宽度为屏幕宽度减去菜单界面所占宽度
                    ViewHelper.setTranslationX(mContent, view.getMeasuredWidth() * (1 - scale));


                    //这只内容界面点击无效（比如有BUTTon就会点击无效）
                    mContent.invalidate();
                    //设置右边菜单滑动后的占据屏幕大小
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);


                }


            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }

        });
    }



}
