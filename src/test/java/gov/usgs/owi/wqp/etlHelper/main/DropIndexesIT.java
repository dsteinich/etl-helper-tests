package gov.usgs.owi.wqp.etlHelper.main;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gov.usgs.owi.wqp.etlHelper.BaseSpringTest;

//@DatabaseSetups({
//	@DatabaseSetup("classpath:/testData/clearAll.xml"),
//	@DatabaseSetup("classpath:/testData/stationCount.xml")
//})
public class DropIndexesIT extends BaseSpringTest {

	@Test
	public void dropIndexesProjectDimStoret() {
		String testTable = PROJECT_DIM_SWAP + STORET;

		if (0 == utilityDao.getTableIndexCount(testTable)) {
			//We don't have any left overs, so create a couple
			utilityDao.createIndex("create index pds_test1 on " + testTable + "(code_value)");
			utilityDao.createIndex("create index pds_test2 on " + testTable + "(project_dim_value)");
		}

		//Try to drop them
		etlHelperMainDao.dropIndexes(testTable);
		assertEquals(0, utilityDao.getTableIndexCount(testTable));

		//And drop it again to see if we get errors (shouldn't)
		etlHelperMainDao.dropIndexes(testTable);
		assertEquals(0, utilityDao.getTableIndexCount(testTable));
	}

}
