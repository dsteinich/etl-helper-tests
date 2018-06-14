package gov.usgs.owi.wqp.etlHelper.code;

import javax.annotation.Resource;

import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.usgs.owi.wqp.etlHelper.BaseSpringTest;
import gov.usgs.owi.wqp.etlHelper.dao.EtlHelperCodeDao;

public class CreateSampleMediaIT extends BaseSpringTest {

	@Resource
	EtlHelperCodeDao etlHelperCodeDao;

	@Test
	@DatabaseSetups({
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/sampleMedia.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testData/storet/activitySum.xml")
	})
	@ExpectedDatabase(connection="wqp", value="classpath:/testResult/storet/sampleMedia.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void storetTest() {
		etlHelperCodeDao.createSampleMedia(STORET);
	}

}
