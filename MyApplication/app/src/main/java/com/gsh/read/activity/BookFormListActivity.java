package com.gsh.read.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.alibaba.fastjson.JSON;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.gsh.read.R;
import com.gsh.read.activity.adapter.MainListAdapter;
import com.gsh.read.common.utils.PageUtils;
import com.gsh.read.presenter.BookFormListPresenter;
import com.gsh.read.presenter.MainPresenter;
import com.gsh.read.view.IBookFormListMvpView;
import com.gsh.read.view.IMainMvpView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_book_form_list)
public class BookFormListActivity extends BaseActivity implements IBookFormListMvpView {

    private BookFormListPresenter presenter;

    @ViewInject(R.id.toolbar)
    private Toolbar toolbar;

    @ViewInject(R.id.spinner_book_form)
    private Spinner spinnerBookForm;

    @ViewInject(R.id.spinner_book_form_status)
    private Spinner spinnerBookFormStatus;

    @ViewInject(R.id.list_view)
    private ListView listView;

    @ViewInject(R.id.refreshLayout)
    private RefreshLayout  refreshLayout;

    @ViewInject(R.id.fab)
    private FloatingActionButton floatingBtn;

    private MainListAdapter adapter;

    private PageUtils pageUtils=new PageUtils();

    private boolean[] enableSpinner=new boolean[]{false,false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        presenter=new BookFormListPresenter(this);
        adapter=new MainListAdapter(this,new ArrayList<JSON>());
        listView.setAdapter(adapter);
        refreshLayout.autoRefresh();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageUtils.setPageIndex(0);
                presenter.queryList();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                pageUtils.setPageIndex(pageUtils.getPageIndex()+1);
                presenter.queryList();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(BookFormListActivity.this,ReadActivity.class));
            }
        });

        spinnerBookForm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(enableSpinner[0]) {
                    refreshLayout.autoRefresh();
                }else{
                    enableSpinner[0]=true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerBookFormStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(enableSpinner[1]) {
                    refreshLayout.autoRefresh();
                }else{
                    enableSpinner[1]=true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Event(R.id.fab)
    private void onClick(View view){
        switch (view.getId()){
            case R.id.fab:
                //new IntentIntegrator(BookFormListActivity.this).initiateScan(); //初始化扫描
                IntentIntegrator integrator = new IntentIntegrator(BookFormListActivity.this);
                // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setCaptureActivity(ScanActivity.class);
                integrator.setPrompt("请扫描"); //底部的提示文字，设为""可以置空
                integrator.setCameraId(0); //前置或者后置摄像头
                integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
                break;
        }
    }

    @Override
    public PageUtils getPageUtils() {
        return pageUtils;
    }

    @Override
    public void setData(List<JSON> mData) {
        if(pageUtils.getPageIndex()==0){
            adapter.setmData(mData);
        }else{
            adapter.addmData(mData);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void finishRefresh() {
        refreshLayout.finishRefresh();
    }

    @Override
    public void finishLoadMore() {
        refreshLayout.finishLoadMore();
    }

    //回调获取扫描得到的条码值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                showMessage("扫码取消！");
            } else {
                showMessage("扫描成功，条码值: " + result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_ewm:
                IntentIntegrator integrator = new IntentIntegrator(BookFormListActivity.this);
                // 设置要扫描的条码类型，ONE_D_CODE_TYPES：一维码，QR_CODE_TYPES-二维码
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setCaptureActivity(ScanActivity.class);
                integrator.setPrompt("请扫描"); //底部的提示文字，设为""可以置空
                integrator.setCameraId(0); //前置或者后置摄像头
                integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
