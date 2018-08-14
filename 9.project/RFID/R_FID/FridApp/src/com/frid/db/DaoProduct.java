package com.frid.db;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.frid.pojo.DBGsonProduct;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class DaoProduct {
	private Dao<DBGsonProduct, Integer> dao;  

	public DaoProduct(Context context) {  
		DatabaseHelper dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);  
		try {  
			dao = dbHelper.getDao(DBGsonProduct.class);  
		} catch (SQLException e) {  
			e.printStackTrace();  
		}  
	}  
	/**保存一条pojo*/
	/*public void setProduct(DBGsonProduct bean) {  
		try {  
			// 如果表中没有该用户则保存，根据主键是否相同来标示是否是同一用户  
			dao.createIfNotExists(bean);  
		} catch (SQLException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		}  
	} */ 
	/**保存一个list到数据库*/
	public void setProductList(List<DBGsonProduct> list) {  
		try {  
			// 如果表中没有该用户则保存，根据主键是否相同来标示是否是同一用户  
			dao.create(list);
		} catch (SQLException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		}  
	}  
	/**根据id获取pojo*/
	public DBGsonProduct getProductById(int id) {  
		try {  
			return dao.queryForId(id);  
		} catch (SQLException e) {  
			e.printStackTrace();  
			return null;  
		}  
	}  
	/**获取所有pojo*/
	public List<DBGsonProduct> getProductList() {  
		try {  
			return dao.queryForAll();
		} catch (SQLException e) {  
			e.printStackTrace();  
			return null;  
		}  

	}  
	/**删除所有内容(返回的是删除的条数)*/
	public int clear() {  
		try {  
			return dao.delete(getProductList());
		} catch (SQLException e) {  
			e.printStackTrace();  
		}
		return 0;  
	} 
	/**修改
	通过主键匹配来做修改，修改成功返回1，可直接写 >0 即可视为修改成功*/
	public int update(DBGsonProduct gp) {  
		try {  
			return dao.update(gp);
		} catch (SQLException e) {  
			e.printStackTrace();  
		}
		return 0;  
	} 
}
