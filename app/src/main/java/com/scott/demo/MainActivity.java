package com.scott.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.scott.demo.album.AlbumImagesActivity;
import com.scott.demo.coordinatorlayout.CoordinatorLayoutActivity;
import com.scott.demo.drawlayout.DrawLayoutActivity;
import com.scott.demo.location.LocationActivity;
import com.scott.demo.notification.NotificationActivity;
import com.scott.demo.platformparam.PlatformparamActivity;
import com.scott.demo.slidePanelLayout.SlideActivity;
import com.scott.demo.tablayout.MenuActivity;
import com.scott.demo.translateactivity.TranslateActivity;
import com.scott.demo.viewflipper.ViewFlipperActivity;
import com.scott.demo.viewpager.ViewPagerAvtivity;
import com.scott.demo.windowSoftInputMode.EditActivity;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

import java.util.ArrayList;
import java.util.List;

import static com.tencent.android.tpush.XGPushManager.setTag;

public class MainActivity extends BaseActivity {

    private static String TAG = "TPush";
    private ListView lvList;
    private List<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 开启logcat输出，方便debug，发布时请关闭
         XGPushConfig.enableDebug(this, true);
        // 如果需要知道注册是否成功，请使用registerPush(getApplicationContext(), XGIOperateCallback)带callback版本
        // 如果需要绑定账号，请使用registerPush(getApplicationContext(),account)版本
        // 具体可参考详细的开发指南
        // 传递的参数为ApplicationContext
        Context context = getApplicationContext();
        XGPushManager.registerPush(context);

        // 2.36（不包括）之前的版本需要调用以下2行代码
        //Intent service = new Intent(context, XGPushService.class);
        //context.startService(service);


        // 其它常用的API：
        // 绑定账号（别名）注册：registerPush(context,account)或registerPush(context,account, XGIOperateCallback)，其中account为APP账号，可以为任意字符串（qq、openid或任意第三方），业务方一定要注意终端与后台保持一致。
        // 取消绑定账号（别名）：registerPush(context,"*")，即account="*"为取消绑定，解绑后，该针对该账号的推送将失效
        // 反注册（不再接收消息）：unregisterPush(context)
        setTag(context, TAG);
        // 删除标签：deleteTag(context, tagName)

        initView();
        initData();
    }

    @Override
    protected void initView() {
        super.initView();
        lvList = (ListView) findViewById(R.id.lv_list);

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                Class cla = null;
                switch(position) {
                    case 0:
                        cla = SlideActivity.class;
                        break;
                    case 1:
                        cla = ViewFlipperActivity.class;
                        break;
                    case 2:
                        cla = ViewPagerAvtivity.class;
                        break;
                    case 3:
                        cla = PlatformparamActivity.class;
                        break;
                    case 4:
                        cla = AlbumImagesActivity.class;
                        break;
                    case 5:
                        cla = LocationActivity.class;
                        break;
                    case 6:
                        break;
                    case 7:
                        cla = TranslateActivity.class;
                        break;
                    case 8:
                        cla = NotificationActivity.class;
                        break;
                    case 9:
                        cla = MenuActivity.class;
                        break;
                    case 10:
                        break;
                    case 11:
                        cla = DrawLayoutActivity.class;
                        break;
                    case 12:
                        cla = EditActivity.class;
                        break;
                    case 13:
                        cla = CoordinatorLayoutActivity.class;
                        break;
                    case 14:
                        break;
                    case 15:
                        break;
                    default:
                        break;
                }
                intent = new Intent(MainActivity.this, cla);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        datas = new ArrayList<String>();
        datas.add("SlidingPaneLayout");
        datas.add("ViewFliper");
        datas.add("ViewPager");
        datas.add("Platformparam");
        datas.add("Album");
        datas.add("Location");
        datas.add("未知");
        datas.add("TranslateActivity");
        datas.add("Notification");
        datas.add("TabLayout ViewPager");
        datas.add("Float action button");
        datas.add("DrawLayout");
        datas.add("WindowSoftInputMode");
        datas.add("CoordinatorLayout AppBarLayout");
        datas.add("CoordinatorLayout");
        datas.add("toolbar and appbarlayout");

        MyAdapter adapter = new MyAdapter();
        lvList.setAdapter(adapter);
    }

    private class MyAdapter extends BaseAdapter {

        private LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.layout_list_item, null);
                holder = new Holder();
                holder.tvDesc = (TextView) convertView.findViewById(R.id.tv_item_desc);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.tvDesc.setText(datas.get(position));
            return convertView;
        }
    }

    class Holder {
        private TextView tvDesc;
    }
}
