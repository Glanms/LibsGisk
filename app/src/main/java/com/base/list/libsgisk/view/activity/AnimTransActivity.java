package com.base.list.libsgisk.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.transition.TransitionManager;
import android.widget.Button;
import android.widget.TextView;

import com.base.list.libsgisk.R;
import com.base.list.libsgisk.tools.T;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/12/19.
 * Activity 间的过渡动画整理1
 */
public class AnimTransActivity extends BaseActivity {

    @Bind(R.id.type_over_actAnim)
    TextView btnOverActAnim;
    @Bind(R.id.type_manage_actAnim)
    TextView btnManageActAnim;
    @Bind(R.id.type_pre_actAnim)
    TextView btnPreActAnim;
    @Bind(R.id.type_reaval_actAnim1)
    TextView btnReavalActAnim;
    @Bind(R.id.btn_trans)
    Button btnTrans;

    private AlertDialog dialog;
    private String[] dialog_items;
    // transition animation main type
    private int mainType = 100;
    // transition animation sub type
    private int subType = 0;

    private static final int MTYPE_O = 100;  //使用overridePendingTransition定义过渡动画
    private static final int MTYPE_M = 101;   //使用Transition管理动画
    private static final int MTYPE_P = 102;   //subActivity的view的preListener简单实现
    private static final int MTYPE_REAVAL = 103;   //activity到subActivity的reaval动画 1

    private int enterAnim;
    private int exitAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_trans);
        initCommon();
        initView();
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void initCommon() {
        TextView tvCommonTitle = (TextView)findViewById(R.id.tv_title_part);
        TextView tvCommonDesc = (TextView)findViewById(R.id.tv_desc_part);
        tvCommonTitle.setText(getString(R.string.title_anim_activity));
        tvCommonDesc.setText(getString(R.string.desc_anim_activity));
    }

    private void initView() {
//        btnOverActAnim.setText(getString(R.string.title_type_over_animAct));
//        btnManageActAnim.setText(getString(R.string.title_type_manage_animAct));
//        btnPreActAnim.setText(getString(R.string.title_type_pre_animAct));
    }

    @OnClick(R.id.type_over_actAnim)
    void onShowOverAnim() {
        dialog_items = getResources().getStringArray(R.array.items_over);
        dialog = new AlertDialog.Builder(this, R.style.dialog_animAct)
                .setTitle(getString(R.string.title_type_over_animAct))
                .setSingleChoiceItems(dialog_items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainType = MTYPE_M; //点击后才计入


                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    @OnClick(R.id.type_manage_actAnim)
    void onShowManageAnim() {
        dialog_items = getResources().getStringArray(R.array.items_manage);
        dialog = new AlertDialog.Builder(this, R.style.dialog_animAct)
                .setTitle(getString(R.string.title_type_manage_animAct))
                .setSingleChoiceItems(dialog_items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainType = MTYPE_O;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    @OnClick(R.id.type_pre_actAnim)
    void onShowPreDrawAnim() {
        dialog_items = getResources().getStringArray(R.array.items_pre_draw);
        dialog = new AlertDialog.Builder(this, R.style.dialog_animAct)
                .setTitle(getString(R.string.title_type_pre_animAct))
                .setSingleChoiceItems(dialog_items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainType = MTYPE_P;
                        switch (which) {
                            case 0:
                                subType = TransTestActivity.STYPE_SCALE_TOP_BOTTOM;
                                break;
                            case 1:
                                subType = TransTestActivity.STYPE_SCALE_LEFT_RIGHT;
                                break;
                            case 2:
                                subType = TransTestActivity.STYPE_ALPHA;

                                break;
                            case 3:
                                subType = TransTestActivity.STYPE_4;
                                break;
                        }
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    @OnClick(R.id.type_reaval_actAnim1)
    void onClickReavalBtn() {
        T.showShort(this, "选择Reaval Transition Activity Anim");
        mainType = MTYPE_REAVAL;

    }

    @OnClick(R.id.btn_trans)
    void onClickTransBtn() {
        switch (mainType) {
            case MTYPE_O:
                overTransToPage();
                break;
            case MTYPE_M:
                manageTransToPage();
                break;
            case MTYPE_P:
                preDrawTransToPage();
                break;
            case MTYPE_REAVAL:
                toReavalTransPage();
                break;
            default:
                T.showShort(AnimTransActivity.this,"先选择一种动画");
        }
    }

    private void overTransToPage() {
        Intent intent = new Intent(AnimTransActivity.this, TransTestActivity.class);
        startActivity(intent);
        overridePendingTransition(enterAnim, exitAnim);
    }

    private void manageTransToPage() {
        Intent intent = new Intent(AnimTransActivity.this, TransTestActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager manager = new TransitionManager();
        } else {
            T.showShort(this, "SDK Version is unsupported.");
        }

    }

    private void preDrawTransToPage() {
        Intent intent = new Intent(AnimTransActivity.this, TransTestActivity.class);
        intent.putExtra("preAnimOpen", true);
        intent.putExtra("preDrawAnim", subType);
        startActivity(intent);
    }

    private void toReavalTransPage() {
        Intent intent = new Intent(AnimTransActivity.this, RevealTransActivity.class);
        startActivity(intent);
    }

}
