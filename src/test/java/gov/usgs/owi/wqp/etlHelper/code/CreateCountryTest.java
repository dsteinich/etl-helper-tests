package gov.usgs.owi.wqp.etlHelper.code;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.usgs.owi.wqp.etlHelper.BaseSpringTest;
import gov.usgs.owi.wqp.etlHelper.DBIntegrationTest;
import gov.usgs.owi.wqp.etlHelper.dao.EtlHelperCodeDao;

@Category(DBIntegrationTest.class)
public class CreateCountryTest extends BaseSpringTest {

	@Resource
	EtlHelperCodeDao etlHelperCodeDao;
	
	@Test
	@DatabaseSetups({
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/country.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testData/storet/stationSum.xml"),
		@DatabaseSetup(connection="wqx", value="classpath:/testData/wqx/country.xml"),
		@DatabaseSetup(connection="nwisWsStar", value="classpath:/testData/nwisWsStar/country.xml")
	})
	@ExpectedDatabase(connection="wqp", value="classpath:/testResult/storet/country.xml", assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void storetTest() {
		etlHelperCodeDao.createCountry(STORET);
	}

}
