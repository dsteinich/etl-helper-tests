package gov.usgs.owi.wqp.etlHelper.dao;

import java.math.BigDecimal;
import java.util.Collection;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UtilityDao extends BaseDao {

	@Autowired
    public UtilityDao(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
	}

	public void createIndex(String createStatement) {
		getSqlSession().update("createIndex", createStatement);
	}

	public int getTableIndexCount(String tableName) {
		return getSqlSession().selectOne("countIndexes", tableName);
	}

	public BigDecimal getLastAnalyzed(Collection<String> tableList) {
		return getSqlSession().selectOne("lastAnalyzed", tableList);
	}

}
