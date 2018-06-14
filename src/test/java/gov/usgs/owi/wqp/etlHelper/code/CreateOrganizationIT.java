package gov.usgs.owi.wqp.etlHelper.code;

import javax.annotation.Resource;

import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.usgs.owi.wqp.etlHelper.BaseSpringTest;
import gov.usgs.owi.wqp.etlHelper.dao.EtlHelperCodeDao;

public class CreateOrganizationIT extends BaseSpringTest {

	@Resource
	EtlHelperCodeDao etlHelperCodeDao;

	@Test
	@DatabaseSetups({
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/organization.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testData/storet/station.xml")
	})
	@ExpectedDatabase(connection="wqp", value="classpath:/testResult/storet/organization.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void storetTest() {
		etlHelperCodeDao.createOrganization(STORET);
	}

}
