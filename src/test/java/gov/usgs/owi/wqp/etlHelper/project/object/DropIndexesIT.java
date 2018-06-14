package gov.usgs.owi.wqp.etlHelper.project.object;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DropIndexesIT extends BaseTest {

	@Test
	public void dropIndexesTest() {

		if (0 == utilityDao.getTableIndexCount(TEST_TABLE)) {
			//We don't have any left overs, so create a couple
			utilityDao.createIndex("create index " + TEST_TABLE + "_i1 on " + TEST_TABLE + "(project_identifier)");
			utilityDao.createIndex("create index " + TEST_TABLE + "_i2 on " + TEST_TABLE + "(organization)");
			//And make sure they are there
			assertEquals(2, utilityDao.getTableIndexCount(TEST_TABLE));
		} else {
			//Make sure PK is removed - otherwise index drop will fail.
			etlHelperMainDao.dropRI(STORET);
		}

		//Try to drop them
		etlHelperProjectObjectDao.dropIndexes(STORET);
		assertEquals(0, utilityDao.getTableIndexCount(TEST_TABLE));

		//And drop it again to see if we get errors (shouldn't)
		etlHelperProjectObjectDao.dropIndexes(STORET);
		assertEquals(0, utilityDao.getTableIndexCount(TEST_TABLE));
	}

}
