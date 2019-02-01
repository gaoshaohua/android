package com.gsh.read.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.gsh.read.R;
import com.gsh.read.common.vo.response.CustomerVo;
import com.gsh.read.common.vo.response.ResultVo;
import com.gsh.read.presenter.ReadPresenter;
import com.gsh.read.view.IReadMvpView;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_read)
public class ReadActivity extends BaseActivity implements IReadMvpView {

    private ReadPresenter presenter;

    @ViewInject(R.id.toolbar)
    private Toolbar toolbar;
    @ViewInject(R.id.btn_read)
    private Button btnRead;
    @ViewInject(R.id.tv_code)
    private TextView tvCode;
    @ViewInject(R.id.tv_name)
    private TextView tvName;
    @ViewInject(R.id.tv_phone)
    private TextView tvPhone;
    @ViewInject(R.id.tv_address)
    private TextView tvAddress;
    @ViewInject(R.id.tv_bookform)
    private TextView tvBookform;
    @ViewInject(R.id.tv_read_user)
    private TextView tvReadUser;
    @ViewInject(R.id.tv_remark)
    private TextView tvRemark;
    @ViewInject(R.id.tv_last_code)
    private TextView tvLastCode;
    @ViewInject(R.id.et_read_code)
    private EditText et_read_code;

    @ViewInject(R.id.lineChart)
    private LineChart mLineChart;

    List<Entry> entries = new ArrayList<Entry>();

    private String consNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        presenter=new ReadPresenter(this);
        consNo=getIntent().getStringExtra("consNo");
        if(toolbar==null){
            Log.d(ReadActivity.class.getSimpleName(),"toolbar is null");
            return;
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finish();
            }
        });
        //显示边界
        mLineChart.setDrawBorders(false);

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(3, true);

        xAxis.setValueFormatter(new IAxisValueFormatter() {

            @Override
            public int getDecimalDigits() {
                return 0;
            }

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) value+"月";
            }
        });

        YAxis rightYAxis = mLineChart.getAxisRight();
        rightYAxis.setEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.queryUserByCode(consNo);
    }

    @Event(R.id.btn_read)
    private void onClick(View view){
        switch (view.getId()){
            case R.id.btn_read:
                presenter.uploadMetaRead("110207",getIntent().getStringExtra("consNo"),et_read_code.getText().toString());
                break;
        }
    }

    @Override
    public void setData(ResultVo mData) {
        tvCode.setText("户号："+mData.getConsNo());
        tvName.setText("户名：");
        tvPhone.setText("电话：");
        tvAddress.setText("地址：");
        tvBookform.setText("表册：");
        tvReadUser.setText("抄表员：");
        tvRemark.setText("备注：");
        tvLastCode.setText("上月底数：");
    }

    @Override
    public void setHistoryData(List<CustomerVo> mData) {
        //mData=mData.subList(mData.size()-4,mData.size());
        if(mData.size()>0){
            mLineChart.getXAxis().setLabelCount(mData.size(), true);
        }
        //设置数据
        entries.clear();
        for (int i = 0; i <mData.size(); i++) {
            entries.add(new Entry(Float.parseFloat(mData.get(i).getDateYM()), Float.parseFloat(mData.get(i).getNum())));
        }
        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "用水量");
        LineData data = new LineData(lineDataSet);
        mLineChart.setData(data);
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
                Intent intent = new Intent(ReadActivity.this,ReadActivity.class);
                consNo=result.getContents();
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
                IntentIntegrator integrator = new IntentIntegrator(ReadActivity.this);
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
