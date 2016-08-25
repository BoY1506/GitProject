package com.zhou.gitproject.cityselect2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * 数据库增删改查实现类
 * Created by zhou on 2015/12/26.
 */
public class MySqliteManager {

    private MySqliteHelper helper = null;
    private volatile static MySqliteManager sqliteManager;//volatile关键字禁止指令重排序优化

    //单例模式
    public static MySqliteManager getManagerInstance(Context context) {
        if (null == sqliteManager) {
            //第一重检查
            synchronized (MySqliteManager.class) {
                //此时同步方法只会在第一次执行
                if (null == sqliteManager) {
                    //第二重检查
                    sqliteManager = new MySqliteManager(context);
                }
            }
        }
        return sqliteManager;
    }

    private MySqliteManager(Context context) {
        helper = new MySqliteHelper(context);
    }

    /**
     * 插入单行记录
     *
     * @param tableName 表名
     * @param values    数据项
     * @return 是否成功
     */
    public boolean singleInsert(String tableName, ContentValues values) {
        SQLiteDatabase db = null;
        long rowId = -1;
        if (values != null) {
            try {
                db = helper.getWritableDatabase();
                rowId = db.insert(tableName, null, values);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (db != null) {
                    db.close();
                }
            }
        }
        //等于-1表示失败
        return rowId != -1;
    }

    /**
     * 插入多行记录
     *
     * @param tableName 表名
     * @param arrayList 数据项
     * @return 是否成功
     */
    public boolean multiInsert(String tableName, ArrayList<ContentValues> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            SQLiteDatabase db = null;
            long rowId = -1;
            try {
                db = helper.getWritableDatabase();
                //开始事务
                db.beginTransaction();
                for (int i = 0; i < arrayList.size(); i++) {
                    rowId = db.insert(tableName, null, arrayList.get(i));
                    if (rowId == -1) {
                        //插入一行数据失败，则行id的值为-1,然后结束事务,返回false
                        db.endTransaction();
                        db.close();
                        return false;
                    }
                }
                db.setTransactionSuccessful();
                //由事务的标志决定是提交事务还是回滚事务
                db.endTransaction();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (db != null) {
                    db.close();
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 替换单行记录
     *
     * @param tableName 表名
     * @param values    数据项
     * @return 是否成功
     */
    public boolean singleReplace(String tableName, ContentValues values) {
        SQLiteDatabase db = null;
        long rowId = -1;
        if (values != null) {
            try {
                db = helper.getWritableDatabase();
                rowId = db.replace(tableName, null, values);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (db != null) {
                    db.close();
                }
            }
        }
        //等于-1表示失败
        return rowId != -1;
    }

    /**
     * 删除n行记录
     *
     * @param tableName   表名
     * @param whereClause 条件语句
     * @param whereArgs   条件子句的参数
     * @return 返回受影响的记录的条数
     */
    public boolean delete(String tableName, String whereClause, String whereArgs[]) {
        int count = -1;
        SQLiteDatabase db = null;
        try {
            db = helper.getWritableDatabase();
            count = db.delete(tableName, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        //如果失败返回值为-1，返回false
        return count != -1;
    }

    /**
     * 更新数据库
     *
     * @param tableName   表名
     * @param values      想更新的数据
     * @param whereClause 条件子句
     * @param whereArgs   子句的参数
     * @return 返回受影响的记录数
     */
    public boolean update(String tableName, ContentValues values, String whereClause, String whereArgs[]) {
        SQLiteDatabase db = null;
        int count = -1;
        try {
            db = helper.getWritableDatabase();
            count = db.update(tableName, values, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        //如果失败返回值为-1，返回false
        return count != -1;
    }

    /**
     * 查询
     *
     * @param tableName     表名
     * @param columns       要查询出来的列名
     * @param whereClause   查询子句
     * @param selectionArgs 查询子句的参数
     * @param groupBy       groupBy
     * @param having        having
     * @param orderBy       orderBy
     * @param limit         limit
     * @return
     */
    public ArrayList<String[]> query(String tableName, String columns[], String whereClause,
                                     String selectionArgs[], String groupBy, String having, String orderBy, String limit) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        int columnCount = 0;
        ArrayList<String[]> result = null;
        try {
            db = helper.getReadableDatabase();
            result = new ArrayList<>();
            cursor = db.query(tableName, columns, whereClause, selectionArgs, groupBy, having, orderBy, limit);
            columnCount = cursor.getColumnCount();//获得列数
            while (cursor.moveToNext()) {
                String rowData[] = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    rowData[i] = cursor.getString(i);
                }
                result.add(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return result;
    }

    /**
     * 直接执行SQL语句的查询
     *
     * @param sql SQL语句
     * @return ArrayList<Object[]>集合
     */
    public ArrayList<String[]> query2(String sql) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        int columnCount = 0;
        ArrayList<String[]> result = null;
        try {
            db = helper.getReadableDatabase();
            result = new ArrayList<>();
            cursor = db.rawQuery(sql, null);
            columnCount = cursor.getColumnCount();//获得列数
            while (cursor.moveToNext()) {
                String rowData[] = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    rowData[i] = cursor.getString(i);
                }
                result.add(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return result;
    }

}
