package gov.usgs.owi.wqp.etlHelper.project;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CreateIndexesIT extends BaseTest {

	@Test
	public void createIndexesTest() {

		//Make sure we start fresh
		//Make sure PK is removed - otherwise index drop will fail.
		etlHelperMainDao.dropRI(STORET);
		etlHelperMainDao.dropIndexes(TEST_TABLE);
		assertEquals(0, utilityDao.getTableIndexCount(TEST_TABLE));

		//Try to add them
		etlHelperProjectDao.createIndexes(STORET);
		assertEquals(3, utilityDao.getTableIndexCount(TEST_TABLE));

	}

}
