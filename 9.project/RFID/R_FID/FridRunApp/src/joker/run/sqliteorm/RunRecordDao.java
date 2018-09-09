package joker.run.sqliteorm;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

public class RunRecordDao {

	private Context context;
	private Dao<RunRecord, Integer> RunRecordDao;
	private DBHelper helper;

	public RunRecordDao(Context context) {
		this.context = context;
		try {
			helper = DBHelper.getHelper(context);
			RunRecordDao = helper.getDao(RunRecord.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 增加一个用户
	 *
	 * @param RunRecord
	 */
	public void add(RunRecord RunRecord) {
		try {
			RunRecordDao.create(RunRecord);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * 增加一个用户
	 *
	 * @param RunRecord
	 */
	public void addOrUpdate(RunRecord RunRecord) {
		try {
			
			if(queryEpc(RunRecord.getEpc()) == null){
				RunRecordDao.create(RunRecord);
				
			}else{
				RunRecordDao.update(RunRecord);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	//删除一条记录
	public void del(RunRecord RunRecord) {
		try {
			RunRecordDao.delete(RunRecord);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	//更新一条记录
	public void update(RunRecord RunRecord) {
		try {
			RunRecordDao.update(RunRecord);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	//更新一条记录
	public List<RunRecord> queryList() {
		List<RunRecord> RunRecordList = null;
		try {
			RunRecordList = RunRecordDao.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return RunRecordList;
	}

	//通过EPC查询一条记录
	public RunRecord queryEpc(String Epc) {
		try {
			List<RunRecord> list =  RunRecordDao.queryBuilder().where().eq("epc", Epc).query();
			return list.size()>0?list.get(0):null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//...other operations

}
