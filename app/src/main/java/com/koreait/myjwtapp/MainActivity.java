  package com.koreait.myjwtapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.koreait.myjwtapp.interfaces.OnBlogListRefresh;
import com.koreait.myjwtapp.utils.FragmentType;

public class MainActivity extends AppCompatActivity implements OnBlogListRefresh {

    BlogListFragment blogListFragment;
    UserListFragment userListFragment;
    WebFragment webFragment;
    MyInfoFragment myInfoFragment;
    BottomNavigationView bottomNavigationView;

    @Override
    public void refresh(String msg) {

        addFragment(FragmentType.BLOG_LIST);
        blogListFragment.listRefresh();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent() != null) {
            String msg = getIntent().getStringExtra("msg");
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }

        initData();
        addMenuEventLister();
        addFragment(FragmentType.BLOG_LIST);
    }

    private void initData() {
        blogListFragment = new BlogListFragment();
        userListFragment = new UserListFragment();
        webFragment = new WebFragment(this);
        myInfoFragment = new MyInfoFragment();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }

    private void addMenuEventLister() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_1:
                        addFragment(FragmentType.BLOG_LIST);
                        break;
                    case R.id.page_2:
                        addFragment(FragmentType.USERS);
                        break;
                    case R.id.page_3:
                        addFragment(FragmentType.WEB);
                        break;
                    case R.id.page_4:
                        addFragment(FragmentType.MY_INFO);
                        break;
                }
                return true;
            }
        });
    }

    private void addFragment(FragmentType type) {
        Fragment fragment = null;
        switch (type) {
            case BLOG_LIST:
                fragment = blogListFragment;
                break;
            case USERS:
                fragment = userListFragment;
                break;
            case WEB:
                fragment = webFragment;
                break;
            case MY_INFO:
                fragment = myInfoFragment;
                break;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}