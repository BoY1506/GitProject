package com.zhou.test.ormlite;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.zhou.test.R;
import com.zhou.test.ormlite.bean.User;
import com.zhou.test.ormlite.db.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * ORMLite关系型数据库框架练习
 * Created by zhou on 2016/7/28.
 */
public class ORMLiteTest extends Activity {

    @InjectView(R.id.add_btn)
    Button addBtn;
    @InjectView(R.id.delete_btn)
    Button deleteBtn;
    @InjectView(R.id.update_btn)
    Button updateBtn;
    @InjectView(R.id.query_btn)
    Button queryBtn;
    @InjectView(R.id.user_lv)
    ListView userLv;

    private List<String> userInfoList;
    private ArrayAdapter<String> adapter;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ormlite_test);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        dbHelper = DatabaseHelper.getHelper(this);
        userInfoList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userInfoList);
        userLv.setAdapter(adapter);
        queryAllUser(null);
    }

    /**
     * 查询所有user
     */
    private void queryAllUser(List<User> users) {
        try {
            userInfoList.clear();
            if (users == null) {
                users = dbHelper.getDao(User.class).queryForAll();
            }
            for (User user : users) {
                userInfoList.add("id:" + user.getUserId() + "  name:" + user.getUserName() + "  sex:"
                        + user.getUserSex() + "  age:" + user.getUserAge());
            }
            adapter.notifyDataSetChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.add_btn, R.id.delete_btn, R.id.update_btn, R.id.query_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_btn:
                addUser();
                break;
            case R.id.delete_btn:
                deleteUser();
                break;
            case R.id.update_btn:
                updateUser();
                break;
            case R.id.query_btn:
                queryUser();
                break;
        }
    }

    /**
     * 增加用户
     */
    private void addUser() {
        View view = getLayoutInflater().inflate(R.layout.dialog_dp_option, null);
        view.findViewById(R.id.id_et).setVisibility(View.GONE);
        final EditText nameEt = (EditText) view.findViewById(R.id.name_et);
        final EditText sexEt = (EditText) view.findViewById(R.id.sex_et);
        final EditText ageEt = (EditText) view.findViewById(R.id.age_et);
        new AlertDialog.Builder(this)
                .setView(view)
                .setTitle("增加用户")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        User user1 = new User(getText(nameEt) + "1", getText(sexEt) + "1", Integer.parseInt(getText(ageEt)));
                        User user2 = new User(getText(nameEt) + "2", getText(sexEt) + "2", Integer.parseInt(getText(ageEt)));
                        User user3 = new User(getText(nameEt) + "3", getText(sexEt) + "3", Integer.parseInt(getText(ageEt)));
                        List<User> list = new ArrayList<User>();
                        list.add(user1);
                        list.add(user2);
                        list.add(user3);
                        try {
//                            dbHelper.getUserDao().create(user);
                            dbHelper.getDao(User.class).create(list);
                            queryAllUser(null);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }).show();
    }

    /**
     * 删除用户
     */
    private void deleteUser() {
        View view = getLayoutInflater().inflate(R.layout.dialog_dp_option, null);
        final EditText idEt = (EditText) view.findViewById(R.id.id_et);
        view.findViewById(R.id.name_et).setVisibility(View.GONE);
        view.findViewById(R.id.sex_et).setVisibility(View.GONE);
        view.findViewById(R.id.age_et).setVisibility(View.GONE);
        new AlertDialog.Builder(this)
                .setView(view)
                .setTitle("删除用户")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int id = Integer.parseInt(getText(idEt));
                        try {
//                            dbHelper.getUserDao().deleteById(id);
                            DeleteBuilder<User, Integer> builder = dbHelper.getDao(User.class).deleteBuilder();
                            builder.where().between("_age", 20, 25);
                            builder.delete();
                            queryAllUser(null);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }).show();
    }

    /**
     * 修改用户
     */
    private void updateUser() {
        View view = getLayoutInflater().inflate(R.layout.dialog_dp_option, null);
        final EditText idEt = (EditText) view.findViewById(R.id.id_et);
        final EditText nameEt = (EditText) view.findViewById(R.id.name_et);
        final EditText sexEt = (EditText) view.findViewById(R.id.sex_et);
        final EditText ageEt = (EditText) view.findViewById(R.id.age_et);
        new AlertDialog.Builder(this)
                .setView(view)
                .setTitle("修改用户")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        User user = new User(getText(nameEt), getText(sexEt), Integer.parseInt(getText(ageEt)));
                        try {
//                            user.setUserId(Integer.parseInt(getText(idEt)));
//                            dbHelper.getUserDao().update(user);
                            UpdateBuilder<User, Integer> b = dbHelper.getDao(User.class).updateBuilder();
                            b.updateColumnValue("_sex", "男二号").where().eq("_sex", "男");
                            b.update();
//                           dbHelper.getUserDao().updateRaw("update table user ")
                            queryAllUser(null);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }).show();
    }

    /**
     * 查询用户
     */
    private void queryUser() {
        View view = getLayoutInflater().inflate(R.layout.dialog_dp_option, null);
        final EditText idEt = (EditText) view.findViewById(R.id.id_et);
        final EditText nameEt = (EditText) view.findViewById(R.id.name_et);
        final EditText sexEt = (EditText) view.findViewById(R.id.sex_et);
        final EditText ageEt = (EditText) view.findViewById(R.id.age_et);
        new AlertDialog.Builder(this)
                .setView(view)
                .setTitle("查询用户")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            int id = Integer.parseInt(getText(idEt));
                            String name = getText(nameEt);
                            String sex = getText(sexEt);
                            int age = Integer.parseInt(getText(ageEt));
                            if (id != 0) {
                                List<User> users = new ArrayList<User>();
                                users.add((User) dbHelper.getDao(User.class).queryForId(id));
                                queryAllUser(users);
                                return;
                            } else {
                                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(sex)) {
                                    Map<String, Object> map = new HashMap<String, Object>();
                                    map.put("_name", name);
                                    map.put("_sex", sex);
                                    List<User> users = dbHelper.getDao(User.class).queryForFieldValues(map);
                                    queryAllUser(users);
                                    return;
                                } else {
                                    List<User> age1 = dbHelper.getDao(User.class).queryBuilder().where().le("_age", 10).query();
                                    queryAllUser(age1);
                                    return;
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }).show();
    }

    private String getText(EditText et) {
        return et.getText().toString().trim();
    }

}
