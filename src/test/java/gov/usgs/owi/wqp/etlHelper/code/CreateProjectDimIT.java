package gov.usgs.owi.wqp.etlHelper.code;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.usgs.owi.wqp.etlHelper.BaseSpringTest;
import gov.usgs.owi.wqp.etlHelper.dao.EtlHelperCodeDao;
import gov.usgs.owi.wqp.etlHelper.dao.UtilityDao;

public class CreateProjectDimIT extends BaseSpringTest {

	@Resource
	UtilityDao utilityDao;

	@Resource
	EtlHelperCodeDao etlHelperCodeDao;

	@Test
	@DatabaseSetups({
		@DatabaseSetup(connection="wqp", value="classpath:/testData/storet/activitySum.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/projectDim.xml")
	})
	@ExpectedDatabase(connection="wqp", value="classpath:/testResult/storet/projectDim.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void storetTest() {
		etlHelperCodeDao.createProjectDim(STORET);
		assertEquals(1, utilityDao.getTableIndexCount(PROJECT_DIM_SWAP + STORET));
	}

}
