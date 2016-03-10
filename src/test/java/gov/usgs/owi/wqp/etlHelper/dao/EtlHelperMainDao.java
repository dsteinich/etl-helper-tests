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

	public void drop_indexes(String table_name) {
		getSqlSession().delete("etl_helper_main.drop_indexes", table_name);
	}

}
