package com.miki.myrealstartswoop;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.miki.intentcore.Bean.BookListBean;
import com.miki.intentcore.ConnManager;
import com.miki.myrealstartswoop.SaveListItemData.ItemListDiata;
import com.miki.myrealstartswoop.adapter.PageViewPagerAdapter;
import com.miki.myrealstartswoop.callback.ActivityCallBack;
import com.miki.myrealstartswoop.callback.FragmentCallback;
import com.miki.myrealstartswoop.fragment.book_collect;
import com.miki.myrealstartswoop.fragment.fragmet_web;
import com.miki.myrealstartswoop.fragment.seracher;
import com.miki.myrealstartswoop.viewmanger.ToolBarManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginApp extends AppCompatActivity implements View.OnClickListener,
        ViewPager.OnPageChangeListener, FragmentCallback, ConnManager.conWebCallBack {
    private Handler handler;
    private ToolBarManager toolBarManager;
    private Toolbar toolbar;
    private ImageButton[] imageButtons = new ImageButton[5];
    private List<Fragment> list = new ArrayList<>();
    private Fragment f1, f2, f3;
    private ViewPager viewPager;
    private ConnManager connManager;
    private AlertDialog alertDialog;
    private  boolean  conDump = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        toolbar = findViewById(R.id.toolbar);
        imageButtons[2] = findViewById(R.id.view_bottom_home);
        imageButtons[2].setOnClickListener(this);
        imageButtons[2].setEnabled(false);
        imageButtons[3] = findViewById(R.id.view_bottom_search);
        imageButtons[3].setOnClickListener(this);
        imageButtons[4] = findViewById(R.id.view_bottom_collect);
        imageButtons[4].setOnClickListener(this);
        viewPager = findViewById(R.id.view_pager);
        f1 = new fragmet_web();
        list.add(f1);
        f2 = new seracher();
        list.add(f2);
        f3 = new book_collect();
        list.add(f3);
        handler = new Handler();
        PageViewPagerAdapter adapter = new PageViewPagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(this);
        toolBarManager = new ToolBarManager(this, toolbar);
        toolBarManager.setToolbar(0);
        connManager = ConnManager.getConnManager(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (alertDialog != null)
            alertDialog.dismiss();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_bottom_home:
                viewPager.setCurrentItem(0);
                break;
            case R.id.view_bottom_search:
                viewPager.setCurrentItem(1);
                break;
            case R.id.view_bottom_collect:
                viewPager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        for (int size = 0; size < 3; size++) {
            if (i == size) {
                imageButtons[size + 2].setEnabled(false);
            } else {
                imageButtons[size + 2].setEnabled(true);
            }
        }
        transData(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public void toobarChange(int i) {
        switch (i) {
            case 0:
                imageButtons[0] = findViewById(R.id.title_back);
                imageButtons[1] = findViewById(R.id.title_settings);
                imageButtons[0].setOnClickListener(this);
                imageButtons[1].setOnClickListener(this);
                break;
            case 1:
                break;
            default:
                break;
        }
    }

    @Override
    public void CallBackFagment(Uri URI, String title) {
        if (title == null) {
            connManager.startCon(URI, toolBarManager.getTitle());
        } else {
            conDump = true;
            connManager.startCon(URI, title);
        }
    }

    @Override
    public void titleChange(String title) {
        toolBarManager.setTitle(title);
    }

    @Override
    public void needDataUpdate() {
        ActivityCallBack callBack = (ActivityCallBack) list.get(2);
        callBack.AsyThreaData(handler);
    }

    @Override
    public void onBackPressed() {
        ActivityCallBack callBack = (ActivityCallBack) list.get(viewPager.getCurrentItem());
        callBack.onBackPress();
    }

    public void transData(int i) {
        final ActivityCallBack callBack = (ActivityCallBack) list.get(viewPager.getCurrentItem());
        switch (i) {
            case 0:
                callBack.onWebDate(null);
                break;
            case 1:
                callBack.onWebDate(null);
                break;
            case 2:
                toolBarManager.setTitle(getResources().getString(R.string.book_collect_title));
                callBack.AsyThreaData(handler);
                break;
            default:
                break;
        }
    }

    @Override
    public void conSucess(String text) {

    }

    @Override
    public void conFail() {
        Toast.makeText(this,"任务忙，请稍后",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void SwoopSuccess(final BookListBean listBean) {
        if (listBean != null) {
            List<String> text = new ArrayList<>();
            List<String> url = new ArrayList<>();
            for (int i = 0; i < listBean.getList().size(); i++) {
                HashMap<String, String> map = listBean.getList().get(i);
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    text.add(entry.getKey());
                    url.add(entry.getValue());
                }
            }
            ItemListDiata.saveText.clear();
            ItemListDiata.saveUrl.clear();
            ItemListDiata.saveText.addAll(text);
            ItemListDiata.saveUrl.addAll(url);
            ItemListDiata.conChange=listBean.getBook_sort();
            ItemListDiata.book_name = listBean.getTitle();
            ItemListDiata.book_author = listBean.getBook_list_author();
            ItemListDiata.book_icon = listBean.getBook_list_icon();
            ItemListDiata.book_info = listBean.getBook_list_info();
            ItemListDiata.book_web = listBean.getBook_web();
            ItemListDiata.book_uri= listBean.getBook_uri();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (conDump) {
                            conDump=false;
                            Intent intent =new Intent();
                            intent.setClass(LoginApp.this,ReadingActivity.class);
                            LoginApp.this.startActivity(intent);
                        } else {
                            creatFlay(listBean);
                        }
                    } catch (Exception e) {
                        if (alertDialog != null) {
                            alertDialog.dismiss();
                        }
                    }
                }
            });
        } else return;
    }

    @Override
    public void needAsyThread() {
     //这里存在一个异步线程 网络api访问成功后可供回调。
    }

    private void creatFlay(final BookListBean bean) {//会出现窗体泄露
        alertDialog = new AlertDialog.Builder(this).setTitle(R.string.notify_skip)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCallBack callBack = (ActivityCallBack) list.get(1);
                        callBack.AsyThreaData(handler);
                        // callBack.onWebDate(bean);
                        viewPager.setCurrentItem(1);
                    }
                }).setNegativeButton(R.string.no, null).show();
    }
}