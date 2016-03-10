package gov.usgs.owi.wqp.etlHelper.main;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import gov.usgs.owi.wqp.etlHelper.BaseSpringTest;
import gov.usgs.owi.wqp.etlHelper.DBIntegrationTest;
import gov.usgs.owi.wqp.etlHelper.dao.EtlHelperMainDao;
import gov.usgs.owi.wqp.etlHelper.dao.UtilityDao;

@Category(DBIntegrationTest.class)
//@DatabaseSetups({
//	@DatabaseSetup("classpath:/testData/clearAll.xml"),
//	@DatabaseSetup("classpath:/testData/stationCount.xml")
//})
public class DropIndexesTest extends BaseSpringTest {

	@Resource
	UtilityDao utilityDao;
	
	@Resource
	EtlHelperMainDao etlHelperMainDao;
	
	@Test
	public void dropIndexesProjectDimStoret() {
		String testTable = PROJECT_DIM_SWAP + STORET;
		
		if (0 == utilityDao.getTableIndexCount(testTable)) {
			//We don't have any left overs, so create a couple
			utilityDao.createIndex("create index pds_test1 on " + testTable + "(code_value)");
			utilityDao.createIndex("create index pds_test2 on " + testTable + "(project_dim_value)");
		}
		
		//Try to drop them
		etlHelperMainDao.drop_indexes(testTable);
		assertEquals(0, utilityDao.getTableIndexCount(testTable));
		
		//And drop it again to see if we get errors (shouldn't)
		etlHelperMainDao.drop_indexes(testTable);
		assertEquals(0, utilityDao.getTableIndexCount(testTable));
	}

}
