package gov.usgs.owi.wqp.etlHelper.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EtlHelperMainDao extends BaseDao {

	@Autowired
	public EtlHelperMainDao(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
	}

	public void dropIndexes(String table_name) {
		getSqlSession().delete("etl_helper_main.drop_indexes", table_name);
	}

	public void dropRI(String table_suffix) {
		getSqlSession().update("etl_helper_main.drop_ri", table_suffix);
	}

}
