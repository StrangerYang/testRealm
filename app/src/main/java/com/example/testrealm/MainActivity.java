package com.example.testrealm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;


/**
 * @author Admin
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRealm();


        txtShow = findViewById(R.id.txt_show);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_search).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
    }

    Realm realm;
    Integer index = 0;
    RealmAsyncTask transaction;

    private void initRealm() {
        realm = Realm.getInstance(MyApplication.config);

    }

    /**
     * 升级数据库
     */
//    class CustomMigration implements RealmMigration {
//        @Override
//        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
//            RealmSchema schema = realm.getSchema();
//            Log.e("MainActivity", "oldVersion：" + oldVersion + "----newVersion" + newVersion);
//            if (oldVersion == 1 && newVersion == 2) {
//                RealmObjectSchema personSchema = schema.get("Dog");
//
////                if (!personSchema.hasField("Dog")) {
////                    personSchema.addField("aihao", String.class, FieldAttribute.REQUIRED);
////                    oldVersion++;
////                }
//
//            }
//        }
//    }
    private void addRealm() {

        transaction = realm.executeTransactionAsync(realm -> {

            MyRealmDemo myDemo = realm.createObject(MyRealmDemo.class);

            myDemo.setUserName("名字");
            myDemo.setPwd(index + "");
            myDemo.getRemarks().add(index);
            index++;
        }, () -> {
            //成功回调
            Log.e("MainActivity", "成功回调" + index);
        }, error -> {
            //失败回调
            Log.e("MainActivity", "失败");
        });


        transaction = realm.executeTransactionAsync(realm -> {

            Dog dog = realm.createObject(Dog.class);

            dog.setName("哈士奇");
            dog.setAge(index + "岁");
            dog.setAihao("拆家" + index);
        }, () -> {
            //成功回调
            Log.e("MainActivity", "成功回调" + index);
        }, error -> {
            //失败回调
            Log.e("MainActivity", "失败");
        });
    }

    private void updateRealm() {
        transaction = realm.executeTransactionAsync(realm -> {
            RealmResults<MyRealmDemo> myList = realm.where(MyRealmDemo.class).findAll();
            if (myList != null && myList.size() > 0) {
                MyRealmDemo myDemo = myList.get(0);

                myDemo.setUserName("换名字");
                myDemo.setPwd("小球不得");
                myDemo.getRemarks().add(index);
                index++;
            } else {
                Log.e("MainActivity", "updateRealm没得东西，改毛线" + index);
                Toast.makeText(this, "updateRealm没得东西，改毛线", Toast.LENGTH_SHORT).show();
            }

        }, () -> {
            //成功回调
            Log.e("MainActivity", "成功回调" + index);
        }, error -> {
            //失败回调
            error.printStackTrace();
            Log.e("MainActivity", "失败" + error.getMessage());
        });
    }

    private void deleteRealm() {

        transaction = realm.executeTransactionAsync(realm -> {

            RealmResults<MyRealmDemo> myList = realm.where(MyRealmDemo.class).findAll();
            //删除第一个数据
            myList.get(0).deleteFromRealm();
//            myList.deleteFirstFromRealm();
//            //删除最后一个数据
//            myList.deleteLastFromRealm();
//            //删除位置为1的数据
//            myList.deleteFromRealm(1);
//            //删除所有数据
//            myList.deleteAllFromRealm();
        }, () -> {
            //成功回调
            Log.e("MainActivity", "deleteRealm删球了" + index);
        }, error -> {
            //失败回调
            Log.e("MainActivity", "deleteRealm没删拖");
        });
    }

    private void searchRealm() {

        RealmResults<MyRealmDemo> myList = realm.where(MyRealmDemo.class).findAll();
        String strText = "";
        if (myList != null && myList.size() > 0) {

            for (MyRealmDemo myRealmDemo : myList) {
                strText += "\n看什么玩意嘛：" + myRealmDemo.toString();
                Log.e("MainActivity", "看什么玩意嘛：" + myRealmDemo.toString());
                for (int intNum : myRealmDemo.getRemarks()) {
                    strText += "\n数组里的东西：" + intNum;
                    Log.e("MainActivity", "数组里的东西：" + intNum);
                }
            }

        } else {
            Log.e("MainActivity", "空的，代码错了，看锤子");
        }


        RealmResults<Dog> dogList = realm.where(Dog.class).findAll();
        for (Dog dog : dogList) {
            strText += "\n我的狗：" + dog.toString();
            Log.e("MainActivity", "我的狗：" + dog.toString());
        }

        txtShow.setText(strText);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                //添加
                addRealm();
                break;
            case R.id.btn_search:
                //查询
                searchRealm();
                break;
            case R.id.btn_delete:
                //删除
                deleteRealm();
                break;
            case R.id.btn_update:
                //修改
                updateRealm();
                break;
            default:

                break;
        }

    }


    @Override
    protected void onStop() {
        super.onStop();
        //取消当前的数据库操作状态
        if (transaction != null && !transaction.isCancelled()) {
            transaction.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭连接
        realm.close();
    }

}
