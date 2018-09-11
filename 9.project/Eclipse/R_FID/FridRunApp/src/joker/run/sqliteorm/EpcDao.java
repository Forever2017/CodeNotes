package joker.run.sqliteorm;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class EpcDao {

	private Context context;
	private Dao<Epc, Integer> EpcDaoOpe;
	private DBHelper helper;

	public EpcDao(Context context) {
		this.context = context;
		try {
			helper = DBHelper.getHelper(context);
			EpcDaoOpe = helper.getDao(Epc.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 增加一个用户
	 *
	 * @param Epc
	 */
	 public void add(Epc Epc) {
		 try {
			 EpcDaoOpe.createOrUpdate(Epc);
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }

	 }
	 public int addList(List<Epc> Epc) {
		 try {
			
			 for (Epc e : Epc) {
				 EpcDaoOpe.createOrUpdate(e);
			 }
//			 return EpcDaoOpe.create(Epc);
				return 1;
		 } catch (SQLException e) {
			 e.printStackTrace();
			 return 0;
		 }
	 }

	 //删除一条记录
	 public void del(Epc Epc) {
		 try {
			 EpcDaoOpe.delete(Epc);
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }

	 }

	 //更新一条记录
	 public void update(Epc Epc) {
		 try {
			 EpcDaoOpe.update(Epc);
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }

	 }

	 //更新一条记录
	 public List<Epc> queryList() {
		 List<Epc> EpcList = null;
		 try {
			 EpcList = EpcDaoOpe.queryForAll();
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }

		 return EpcList;
	 }

	 //通过EPC查询一条记录
	 public Epc queryEpc(String Epc) {
		 try {
			 List<Epc> list =  EpcDaoOpe.queryBuilder().where().eq("epc", Epc).query();
			 return list.size()>0?list.get(0):null;
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		 return null;
	 }

	 //...other operations

}
