package com.base.list.libsgisk.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.list.libsgisk.R;

import java.util.Locale;

/**
 * Created by Administrator on 2015/11/17.
 */
public class LanguageConfigActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvText;
    private ImageView ivImage;
    private Button btnCN;
    private Button btnEN;
    private Button btnTR;

//    private Locale locale;
    private Configuration config;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_to_cn:
                config.locale = Locale.CHINESE;

                setContent();
                break;
            case R.id.btn_to_en:
                config.locale = Locale.ENGLISH;

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
        getResources().updateConfiguration(config,getResources().getDisplayMetrics());
        // 更新内容
        tvText.setText(getString(R.string.test_text));
        ivImage.setImageResource(R.drawable.test_icon);
    }
}
