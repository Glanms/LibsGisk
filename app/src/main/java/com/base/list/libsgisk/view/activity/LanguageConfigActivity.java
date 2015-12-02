package com.base.list.libsgisk.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.list.libsgisk.R;

import java.util.Locale;

/**
 * Created by Administrator on 2015/11/17.
 */
public class LanguageConfigActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvText;
    private ImageView ivImage;
    private Button btnCN;
    private Button btnEN;
    private Button btnTR;

//    private Locale locale;
    private Configuration config;
    private  Intent mIntent;
    private Intent broadCastIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_config);

        initView();
        initData();
    }

    private void initView(){
        tvText = (TextView)findViewById(R.id.tv_text);
        ivImage = (ImageView)findViewById(R.id.iv_test);
        btnCN = (Button)findViewById(R.id.btn_to_cn);
        btnEN = (Button)findViewById(R.id.btn_to_en);
        btnTR = (Button)findViewById(R.id.btn_to_tr);

        btnCN.setOnClickListener(this);
        btnEN.setOnClickListener(this);
        btnTR.setOnClickListener(this);
    }

    private void initData(){
        config = getResources().getConfiguration(); // 获取系统配置
//        locale = Locale.getDefault();  //默认配置
        tvText.setText(getString(R.string.test_text));
        ivImage.setImageResource(R.drawable.test_icon);

        mIntent  = getIntent();
        SmsManager smsManager = SmsManager.getDefault();
        BroadcastReceiver receiver  = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_to_cn:
                config.locale = Locale.CHINESE;
                mIntent.putExtra(MainActivity.COUNTRY_ID,100);
                setContent();
                break;
            case R.id.btn_to_en:
                config.locale = Locale.ENGLISH;
                mIntent.putExtra(MainActivity.COUNTRY_ID,101);
                setContent();
                break;
            case R.id.btn_to_tr:
//                config.locale = new Locale.Builder().setLanguage("tr").setRegion("TR").build(); // 要求 API >= 21
                config.locale = new Locale("tr","TR");
                setContent();
                break;
            default:
                setContent();
        }
    }

    private void setContent(){
        // 更新配置
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        // 更新内容
        tvText.setText(getString(R.string.test_text));
        ivImage.setImageResource(R.drawable.test_icon);
//        Context ctx = getApplicationContext();
//        PackageManager pm = getPackageManager();
//        ActivityManager am = (ActivityManager) ctx
//                .getSystemService(Activity.ACTIVITY_SERVICE);
//        // Find launcher and kill it
//        Intent i = new Intent(Intent.ACTION_MAIN);
//        i.addCategory(Intent.CATEGORY_HOME);
//        i.addCategory(Intent.CATEGORY_DEFAULT);
//        List<ResolveInfo> resolves = pm.queryIntentActivities(i,0);
//        for(ResolveInfo res : resolves){
//            if(res.activityInfo != null){
//                am.killBackgroundProcesses(res.activityInfo.packageName);
////                am.restartPackage(res.activityInfo.packageName);
//            }
//        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK,mIntent);
        super.onBackPressed();
    }
}
