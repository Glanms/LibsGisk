package com.base.list.libsgisk.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;

import com.base.list.libsgisk.R;
import com.base.list.libsgisk.tools.LogHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/12/21.
 */
public class TransTestActivity extends BaseActivity {

    // 跳转动画类型
    public final static int STYPE_1 = 2001;
    public final static int STYPE_2 = 2002;
    public final static int STYPE_3 = 2003;
    public final static int STYPE_4 = 2004;

    @Bind(R.id.btn_back_trans_test)
    Button btnBackTransTest;
    @Bind(R.id.root_view_trans)
    LinearLayout rootView;

    private int animType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         //接收上个页面传过来的动画类型
        Intent mIntent = getIntent();
        if(mIntent.getBooleanExtra("preAnimOpen",false) == true){
            switch (mIntent.getIntExtra("preDrawAnim",0)){
                case STYPE_1:
                    break;
                case STYPE_2:
                    break;
                case STYPE_3:
                    break;
                case STYPE_4:
                    break;
                default:
                    LogHelper.d("TransAnim","No type matched!");
            }
        }
        //第一次启动调用
        if(savedInstanceState == null){
            rootView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    rootView.getViewTreeObserver().removeOnPreDrawListener(this);
                    startPreViewAnim();
                    return true;
                }
            });
        }
        setContentView(R.layout.activity_transition_test);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_back_trans_test)
    void onClickBack(){
        TransTestActivity.this.finish();
    }

    /**
     * 设置动画效果
     */
    private void startPreViewAnim(){

    }
}
