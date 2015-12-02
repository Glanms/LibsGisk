package com.base.list.libsgisk.view.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.base.list.libsgisk.R;
import com.base.list.libsgisk.entity.Constant;
import com.base.list.libsgisk.tools.CommonUtils;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    public static final String COUNTRY_ID = "country_id";
    private TextView tvMain;
    private ListView lvMain;
    private String[] labels  = null;
    private ArrayAdapter<String> arrayAdapter = null;
    private int counter = 0; //引导动画
    private ShowcaseView showcaseView;

    private Bundle s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

        btn1.setOnClickListener(this);
    }

    private void initView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        lvMain = (ListView) findViewById(R.id.list_main);
        tvMain = (TextView) findViewById(R.id.main_test);
        btn1 = (Button) findViewById(R.id.btn_yuyan);
        btn2 = (Button) findViewById(R.id.btn_next);

        View v = LayoutInflater.from(this).inflate(R.layout.layout_sv_btn, showcaseView,false);
        Button svEndBtn = (Button)v.findViewById(R.id.sv_test_btn) ;
        // 初始化ShowcaseView
        showcaseView = new ShowcaseView.Builder(this)
//                .withMaterialShowcase()
                .setTarget(new ViewTarget(toolbar))
                .setOnClickListener(this)
                .setStyle(R.style.CustomShowcaseTheme3)
//                .replaceEndButton(svEndBtn)
                .build();
        showcaseView.setButtonText("下一步");
        showcaseView.setContentText("ShowcaseView 引导页");
    }

    private void initData(){
        labels = getResources().getStringArray(R.array.labels_main);
       arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_main_item,
                R.id.item_main_label, labels);
        lvMain.setAdapter(arrayAdapter);
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        CommonUtils.turnToActivity(MainActivity.this,LanguageConfigActivity.class);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yuyan:
//                CommonUtils.turnToActivity(this, LanguageConfigActivity.class);
                Intent _intent = new Intent(this, LanguageConfigActivity.class);
                _intent.putExtra("country_id", 1);
                startActivityForResult(_intent, 200);
                break;
        }
        switch (counter){
            case 0:
                showcaseView.setShowcase(new ViewTarget(btn1), true);
                showcaseView.setContentTitle("按钮 1");
                showcaseView.setContentText("应用语言切换入口");
                break;
            case 1:
                showcaseView.setShowcase(new ViewTarget(btn2),true);
                showcaseView.setContentTitle("按钮 2");
                showcaseView.setContentText("其他的Demo入口");
                break;
            case 2:
                showcaseView.setTarget(Target.NONE);
                showcaseView.setContentTitle("引导完成");
                showcaseView.setContentText("接下来点击“关闭”按钮，开始使用吧！");
                showcaseView.setButtonText(getString(R.string.showcase_view_close));
                setAlpha(0.4f, btn2, btn1);
                break;
            case 3:
                showcaseView.hide();
                setAlpha(1.0f, btn2, btn1);
                break;
        }
        counter ++;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int country = data.getIntExtra(COUNTRY_ID, 0);
        if (requestCode == 200) {
            if (resultCode == RESULT_OK && country == 100) {

                setAppIcon(true);
            } else {
                setAppIcon(false);
            }
        }
    }

    private void setAlpha(float alpha, View... views) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            for (View view : views) {
                view.setAlpha(alpha);
            }
        }
    }

    //新增快捷方式
    private void shortcutAdd(String name, int number) {
        //设置快捷方式点击后要打开的Activity（主入口）
        Intent shortcutIntent = new Intent(getApplicationContext(), MainActivity.class);
        shortcutIntent.setAction(Constant.ACTION_PLAY);

        //这里创建了一个number的bitmap, 也可以设置自己想要的图表
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_new);
//        Paint paint = new Paint();
//        paint.setColor(0xFF808080); // gray
//        paint.setTextAlign(Paint.Align.CENTER);
//        paint.setTextSize(50);
//        new Canvas(bitmap).drawText("" + number, 50, 50, paint);

        //设置快捷方式
        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, bitmap);

        //创建快捷方式
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(addIntent);
    }

    //删除快捷方式
    private void shortcutDel(String name) {
        // Intent to be send, when shortcut is pressed by user ("launched")
        Intent shortcutIntent = new Intent(getApplicationContext(), MainActivity.class);
        shortcutIntent.setAction(Constant.ACTION_PLAY);

        // Decorate the shortcut
        Intent delIntent = new Intent();
        delIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        delIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        // Inform launcher to remove shortcut
        //删除快捷方式
        delIntent.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(delIntent);
    }

    //判断快捷方式是否存在
    public boolean isAddShortCut() {

        final ContentResolver cr = this.getContentResolver();

        int versionLevel = android.os.Build.VERSION.SDK_INT;
        String AUTHORITY = "com.android.launcher2.settings";

        //2.2以上的系统的文件文件名字是不一样的
        if (versionLevel >= 8) {
            AUTHORITY = "com.android.launcher2.settings";
        } else {
            AUTHORITY = "com.android.launcher.settings";
        }

        final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
                + "/favorites?notify=true");
        Cursor c = cr.query(CONTENT_URI,
                new String[]{"title", "iconResource"}, "title=?",
                new String[]{getString(R.string.app_name)}, null);

        if (c != null && c.getCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 动态设置应用图标
     *
     * @param iconId true 为中国  false 为英国  //todo 暂且定为boolean值，后来换为int值
     */
    private void setAppIcon(boolean iconId) {
        Context ctx = getApplicationContext();
        PackageManager pm = getPackageManager();
        ActivityManager am = (ActivityManager) ctx
                .getSystemService(Activity.ACTIVITY_SERVICE);
        Log.d("TAG:", "PackageName--->" + getComponentName());
        if (iconId) {

            // 对Manifest中的activity-alias值分别设置其enable属性
            pm.setComponentEnabledSetting(getComponentName(),
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP);
            pm.setComponentEnabledSetting(new ComponentName(getBaseContext(),
                            "com.base.list.libsgisk.MainActivity_EN"),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);
            Log.e("MainActivity", "设置了 CN");
        } else {

            pm.setComponentEnabledSetting(getComponentName(),
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP);
            pm.setComponentEnabledSetting(new ComponentName(getBaseContext(),
                            "com.base.list.libsgisk.MainActivity_CN"),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);

                    Log.e("MainActivity", "设置了 EN");

        }

        // Find launcher and kill it
  /*      Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        List<ResolveInfo> resolves = pm.queryIntentActivities(i, 0);
        for (ResolveInfo res : resolves) {
            if (res.activityInfo != null) {
//                am.killBackgroundProcesses(res.activityInfo.packageName);
                am.restartPackage(res.activityInfo.packageName);
            }
        }*/


        // Change ActionBar icon
//        getActivity().getActionBar().setIcon(iconId);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
