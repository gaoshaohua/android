package com.gsh.read.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.gsh.read.R;
import com.gsh.read.activity.adapter.MainListAdapter;
import com.gsh.read.common.utils.PageUtils;
import com.gsh.read.common.vo.response.BookFormVo;
import com.gsh.read.model.database.MyDbManager;
import com.gsh.read.presenter.MainPresenter;
import com.gsh.read.view.IMainMvpView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements IMainMvpView {

    private MainPresenter presenter;

    @ViewInject(R.id.toolbar)
    private Toolbar toolbar;

    @ViewInject(R.id.list_view)
    private ListView listView;

    @ViewInject(R.id.refreshLayout)
    private RefreshLayout  refreshLayout;

    @ViewInject(R.id.fab)
    private FloatingActionButton floatingBtn;

    private MainListAdapter adapter;

    private PageUtils pageUtils=new PageUtils();

    public int currentPosition=0;

    public String consNo=null;

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    presenter.queryListFromNet();
                    break;
                case 1:
                    String result=msg.getData().getString("mData","[]");
                    presenter.querySuccess(result);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        presenter=new MainPresenter(this);
        adapter=new MainListAdapter(this,new ArrayList<BookFormVo>());
        listView.setAdapter(adapter);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.autoRefresh();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageUtils.setPageIndex(0);
                presenter.queryListFromDb(myHandler);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                pageUtils.setPageIndex(pageUtils.getPageIndex()+1);
                presenter.queryListFromNet();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentPosition=position;
                consNo=adapter.getItem(position).getConsNo();
                Intent intent = new Intent(MainActivity.this,ReadActivity.class);
                intent.putExtra("consNo",consNo);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(consNo!=null){
            try {
                BookFormVo vo = MyDbManager.getInstance().findById(BookFormVo.class,consNo);
                adapter.getmData().remove(currentPosition);
                adapter.getmData().add(currentPosition,vo);
                adapter.notifyDataSetChanged();
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }

    @Event(R.id.fab)
    private void onClick(View view){
        switch (view.getId()){
            case R.id.fab:
                //new IntentIntegrator(MainActivity.this).initiateScan(); //初始化扫描
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
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
    public void setData(List<BookFormVo> mData) {
        if(pageUtils.getPageIndex()==0){
            adapter.setmData(mData);
            refreshLayout.finishRefresh();
        }else{
            adapter.addmData(mData);
            refreshLayout.finishLoadMore();
        }
        adapter.notifyDataSetChanged();
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
                Intent intent = new Intent(MainActivity.this,ReadActivity.class);
                intent.putExtra("consNo",result.getContents());
                startActivity(intent);
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
                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
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
