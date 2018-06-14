package gov.usgs.owi.wqp.etlHelper.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EtlHelperProjectDao extends BaseDao {

	@Autowired
	public EtlHelperProjectDao(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
	}

	public void dropIndexes(String table_name) {
		getSqlSession().delete("etl_helper_project.drop_indexes", table_name);
	}

	public void createIndexes(String table_suffix) {
		getSqlSession().update("etl_helper_project.create_indexes", table_suffix);
	}

	public void analyzeTables(String table_suffix) {
		getSqlSession().update("etl_helper_project.analyze_tables", table_suffix);
	}

	public Boolean validateTransformation(String table_suffix) {
		Map<String, Object> parms = new HashMap<>();
		parms.put("table_suffix", table_suffix);
		getSqlSession().selectOne("etl_helper_project.validate_transformation", parms);
		return Boolean.valueOf(parms.get("valid").toString());
	}

	public void install(String table_suffix) {
		getSqlSession().update("etl_helper_project.install", table_suffix);
	}

}
