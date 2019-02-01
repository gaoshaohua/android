package com.gsh.read.model.database;

import com.gsh.read.common.vo.response.BookFormVo;
import com.gsh.read.model.http.impl.HttpRequestImpl;

import org.xutils.DbManager;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.x;

public class MyDbManager {

    private static DbManager singleton;

    private MyDbManager() {}



    public static DbManager getInstance() {
        if (singleton == null) {
            synchronized (HttpRequestImpl.class) {
                //本地数据的初始化
                DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                        .setDbName("read_db.db") //设置数据库名
                        .setDbVersion(3) //设置数据库版本,每次启动应用时将会检查该版本号,
                        // 发现数据库版本低于这里设置的值将进行数据库升级并触发DbUpgradeListener
                        .setAllowTransaction(true) //设置是否开启事务,默认为false关闭事务
                        .setTableCreateListener(new DbManager.TableCreateListener() {
                            @Override
                            public void onTableCreated(DbManager dbManager, TableEntity<?> tableEntity) {
                            }
                        })
                        .setDbOpenListener(new DbManager.DbOpenListener() {
                            @Override
                            public void onDbOpened(DbManager db) {
                                // 开启WAL, 对写入加速提升巨大
                                db.getDatabase().enableWriteAheadLogging();
                            }
                        })
                        // 设置数据库创建时的Listener
                        .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                            @Override
                            public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                                // TODO: ...
                                // db.addColumn(...);
                                try {
                                    db.dropTable(BookFormVo.class);
                                } catch (DbException e) {
                                    e.printStackTrace();
                                }
                                // ...
                                // or
                                // db.dropDb();
                            }
                        }); //设置数据库升级时的Listener,这里可以执行相关数据库表的相关修改,比如alter语句增加字段等
                // .setDbDir(null);//设置数据库.db文件存放的目录,默认为包名下databases目录下
                singleton = x.getDb(daoConfig);
            }
        }
        return singleton;
    }



}
