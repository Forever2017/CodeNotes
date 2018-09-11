package joker.run.sqliteorm;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 通用DAO方法
 * @param <T>
 * */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class TDao<T> {
	private Dao mDao;
	private DBHelper helper;
	private final String TAG = "DB.TDao";
	/**
	 * @param context
	 * @param clazz 通用DAO方法对应的POJO类
	 */
	public TDao(Context context,Class clazz) {
		try {
			helper = DBHelper.getHelper(context);
			mDao = helper.getDao(clazz);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入单条数据,在没有ID字段的情况下使用最好
	 * @param tableBean 表单条映射数据实体
	 * @return int
	 */
	public int insert(T tableBean) {
		int result = -1;
		try {
			result = mDao.create(tableBean);
		} catch (SQLException e) {
			e.printStackTrace();
			Log.e(TAG, String.format(Locale.CHINESE, "数据添加失败,原因：%s", e.getMessage()));
		}
		return result;
	}

	/**新增或者更新一条记录（新增时如果存在，就会更新）
	 * eg:是否需要更新，通过对比ID来判断  id=true 这个字段如果相等，进行更新
	 * @param t
	 * @return
	 */
	public int insertOrUpdate(T t) {
		try {
			return mDao.createOrUpdate(t).getNumLinesChanged();
		} catch (SQLException e) {
			e.printStackTrace();
			Log.e(TAG, String.format(Locale.CHINESE, "数据添加失败,原因：%s", e.getMessage()));
			return 0;
		}
	}

	/**
	 * 删除表中指定数据(可用于无ID 参数情况下删除)
	 *
	 * @param tableBean 数据映射实体
	 * @return int
	 */
	public int delete(T tableBean) {
		int result = -1;
		try {
			result = mDao.delete(tableBean);
		} catch (SQLException e) {
			e.printStackTrace();
			Log.e(TAG, String.format(Locale.CHINESE, "删除表中 %s 数据失败,原因：%s", tableBean.getClass().getSimpleName(), e.getMessage()));
		}
		return result;
	}

	/**
	 * 通过id进行数据删除
	 *
	 * @param idValue 该表指定为ID的字段值
	 * @return int
	 */
	public int deleteById(String idValue) {
		int result = -1;
		try {
			result = mDao.deleteById(idValue);

		} catch (SQLException e) {
			e.printStackTrace();
			Log.e(TAG, String.format(Locale.CHINESE, "通过ID %s 删除数据失败,原因：%s", String.valueOf(idValue), e.getMessage()));
		}
		return result;
	}

	/**
	 * 指定列及该列值,删除该条数据
	 *
	 * @param columnName  列名
	 * @param columnValue 列值
	 * @return int
	 */
	public int deleteByColumn(String columnName, Object columnValue) {
		int result = -1;
		try {
			DeleteBuilder builder = mDao.deleteBuilder();
			builder.where().eq(columnName, columnValue);
			result = builder.delete();
		} catch (SQLException e) {
			e.printStackTrace();
			Log.e(TAG, String.format(Locale.CHINESE, "自定义删除指定列数据失败,原因：%s", e.getMessage()));
		}
		return result;
	}

	/**
	 * 删除表中所有数据
	 * @return int
	 */
	public int deleteAll() {
		int result = -1;
		try {
			result = mDao.deleteBuilder().delete();
		} catch (SQLException e) {
			e.printStackTrace();
			Log.e(TAG, String.format(Locale.CHINESE, "删除所有数据失败,原因：%s", e.getMessage()));
		}
		return result;
	}


	/**
	 * 更新表中数据
	 * @param tableBean 数据映射实体
	 * @return int
	 */
	public int update(T tableBean) {
		int result = -1;
		try {
			result = mDao.update(tableBean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 查询表中所有数据
	 *
	 * @return List<T>
	 */
	public List<T> queryAll() {
		List<T> tableBeans = new ArrayList<>();
		try {
			tableBeans.addAll(mDao.queryForAll());
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, String.format(Locale.CHINESE, "查询所有数据失败,原因：%s", e.getMessage()));
		}
		return tableBeans;
	}

	/**
	 * 通过ID查询指定数据
	 *
	 * @param idValue 该表指定为ID的字段值
	 * @return T
	 */
	public T queryById(String idValue) {
		T tableBean = null;
		try {
			tableBean = (T) mDao.queryForId(idValue);
		} catch (SQLException e) {
			e.printStackTrace();
			Log.e(TAG, String.format(Locale.CHINESE, "通过ID %s 查询数据失败,原因：%s", String.valueOf(idValue), e.getMessage()));
		}
		return tableBean;
	}


	/**
	 * 指定列、列值查询该条数据
	 *
	 * @param columnName  列名
	 * @param columnValue 列值
	 * @return List<T>
	 */
	public List<T> queryByColumn(String columnName, Object columnValue) {
		List<T> tableBeans = new ArrayList<>();
		try {
			QueryBuilder builder = mDao.queryBuilder();
			builder.where().eq(columnName, columnValue);
			tableBeans = builder.query();
		} catch (SQLException e) {
			e.printStackTrace();
			Log.e(TAG, String.format(Locale.CHINESE, "查询所有数据失败,原因：%s", e.getMessage()));
		}
		return tableBeans;
	}


	/**
	 * 查询表中数据总条数
	 *
	 * @return
	 */
	public long count() {
		long result = 0;
		try {
			result = mDao.countOf();
		} catch (SQLException e) {
			e.printStackTrace();
			Log.e(TAG, String.format(Locale.CHINESE, "查询数据总条数失败,原因：%s", e.getMessage()));
		}
		return result;
	}




}























