package com.toong.androidswipetorefreshrecyclerview;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.toong.androidswipetorefreshrecyclerview.adapter.MyRecyclerViewAdapter;
import com.toong.androidswipetorefreshrecyclerview.model.Item;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener{
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView mRecyclerView;
    ArrayList<Item> data;
    MyRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setTitle("One Item Type Recycler List");

        mRecyclerView = (RecyclerView) findViewById(R.id.itemsRecyclerView);

        data = new ArrayList<>();
        data.add(new Item("a", "b"));
        data.add(new Item("c", "b"));

        // set up the RecyclerView
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, data);
        adapter.setClickListener(this);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });
    }

    void refreshItems() {
        // Load items
        // ...

        data.add(new Item("a", "b"));
        data.add(new Item("c", "b"));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Load complete
                onItemsLoadComplete();
            }
        }, 2000);

    }

    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...

        // Stop refresh animation
        mSwipeRefreshLayout.setRefreshing(false);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
