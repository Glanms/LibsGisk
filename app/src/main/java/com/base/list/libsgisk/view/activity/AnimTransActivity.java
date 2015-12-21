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
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/12/19.
 * Activity 间的过渡动画整理
 */
public class AnimTransActivity extends BaseActivity {

    @Bind(R.id.tv_title_part)
    TextView tvCommonTitle;
    @Bind(R.id.tv_desc_part)
    TextView tvCommonDesc;
    @Bind(R.id.type_over_actAnim)
    TextView btnOverActAnim;
    @Bind(R.id.type_manage_actAnim)
    TextView btnManageActAnim;
    @Bind(R.id.type_pre_actAnim)
    TextView btnPreActAnim;
    @Bind(R.id.btn_trans)
    Button btnTrans;

    private AlertDialog dialog;
    private String[] dialog_items;
    // transition animation main type
    private int mainType = 100;
    // transition animation sub type
    private int subType = 20;

    private static final int MTYPE_O = 100;
    private static final int MTYPE_M = 101;
    private static final int MTYPE_P = 102;

    private int enterAnim ;
    private int exitAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_trans);

        initView();
    }

    @Override
    protected void initCommon() {
        tvCommonTitle.setText(getString(R.string.title_anim_activity));
        tvCommonDesc.setText(getString(R.string.desc_anim_activity));
    }

    private void initView() {
      btnOverActAnim.setText(getString(R.string.title_type_over_animAct));
      btnManageActAnim.setText(getString(R.string.title_type_manage_animAct));
      btnPreActAnim.setText(getString(R.string.title_type_pre_animAct));
    }

    @OnClick(R.id.type_over_actAnim)
    void onShowOverAnim(){
        dialog_items = getResources().getStringArray(R.array.items_over);
        dialog = new AlertDialog.Builder(this,R.style.dialog_animAct)
                .setTitle(getString(R.string.title_type_over_animAct))
                .setSingleChoiceItems(dialog_items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
    }

    @OnClick(R.id.type_manage_actAnim)
    void onShowManageAnim(){
        dialog = new AlertDialog.Builder(this,R.style.dialog_animAct)
                .setTitle(getString(R.string.title_type_manage_animAct))
                .create();

    }

    @OnClick(R.id.type_pre_actAnim)
    void onShowPreDrawAnim(){
        dialog = new AlertDialog.Builder(this,R.style.dialog_animAct)
                .setTitle(getString(R.string.title_type_pre_animAct))
                .create();
    }

    @OnClick(R.id.btn_trans)
    void onClickTransBtn(){
        switch (mainType){
            case MTYPE_M:
            overTransToPage();
                break;
            case MTYPE_O:

                break;
            case MTYPE_P:
                break;
        }
    }

    private void overTransToPage(){
        Intent intent = new Intent(AnimTransActivity.this,TransTestActivity.class);
        startActivity(intent);
        overridePendingTransition(enterAnim, exitAnim);
    }

    private void manageTransToPage(){
        Intent intent = new Intent(AnimTransActivity.this, TransTestActivity.class);
        if(Build.VERSION.SDK_INT >= 19) {
            TransitionManager manager = new TransitionManager();
        }else {
            T.showShort(this,"SDK Version is unsupported.");
        }

    }

    private void preDrawTransToPage(){
        Intent intent = new Intent(AnimTransActivity.this, TransTestActivity.class);
        intent.putExtra("preAnimOpen",true);
        intent.putExtra("preDrawAnim",subType);
        startActivityForResult(intent, 300);
    }

}
