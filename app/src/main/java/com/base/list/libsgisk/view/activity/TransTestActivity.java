package com.base.list.libsgisk.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateInterpolator;
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
    public final static int STYPE_SCALE_TOP_BOTTOM = 2001; //center unfold to top and bottom
    public final static int STYPE_SCALE_LEFT_RIGHT = 2002; //center unfold to left and right
    public final static int STYPE_ALPHA = 2003;  // alpha animation 0.1f to 1.0f
    public final static int STYPE_4 = 2004;

    @Bind(R.id.btn_back_trans_test)
    Button btnBackTransTest;
    @Bind(R.id.root_view_trans)
    LinearLayout rootView;

    private int animType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_test);
        ButterKnife.bind(this);
        //接收上个页面传过来的动画类型
        Intent mIntent = getIntent();
        if (mIntent.getBooleanExtra("preAnimOpen", false) == true) {
            animType = mIntent.getIntExtra("preDrawAnim", 0);

            //第一次启动调用
            if (savedInstanceState == null) {
                rootView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        rootView.getViewTreeObserver().removeOnPreDrawListener(this);
                        startRootAnimation(animType);
                        return true;
                    }
                });
            }
        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_back_trans_test)
    void onClickBack() {
        TransTestActivity.this.finish();
    }

    /**
     * 设置动画效果
     */
    private void startRootAnimation(int type) {
        switch (type) {
            case STYPE_SCALE_TOP_BOTTOM:
                scaleTBAnim();
                break;
            case STYPE_ALPHA:
                alphaAnim();
                break;
            case STYPE_SCALE_LEFT_RIGHT:
                break;
            case STYPE_4:
                break;
            default:
                LogHelper.d("TransAnim", "No type matched!");
        }
    }

    // center to top and bottom
    private void scaleTBAnim() {
        rootView.setScaleY(0.1f);
        rootView.setPivotY(rootView.getY() + rootView.getHeight() / 2); //中间开始扩展

        rootView.animate()
                .scaleY(1.0f)
                .setDuration(1000)
                .setInterpolator(new AccelerateInterpolator())
                .start();
    }

    private void alphaAnim() {
        rootView.setAlpha(0.1f);
        rootView.animate()
                .setDuration(1000)
                .alpha(1.0f)
                .start();
    }

}
