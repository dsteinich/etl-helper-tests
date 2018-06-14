package gov.usgs.owi.wqp.etlHelper.code;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.annotation.ExpectedDatabases;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.usgs.owi.wqp.etlHelper.BaseSpringTest;
import gov.usgs.owi.wqp.etlHelper.dao.EtlHelperCodeDao;
import gov.usgs.owi.wqp.etlHelper.dao.UtilityDao;

public class CreateTablesIT extends BaseSpringTest {

	@Resource
	UtilityDao utilityDao;

	@Resource
	EtlHelperCodeDao etlHelperCodeDao;

	@Test
	@DatabaseSetups({
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/assemblage.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/charName.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/charType.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/country.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/county.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/organization.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/project.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/projectDim.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/sampleMedia.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/siteType.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/state.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/taxaName.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testData/storet/station.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testData/storet/stationSum.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testData/storet/resultSum.xml"),
		@DatabaseSetup(connection="wqx", value="classpath:/testData/wqx/country.xml"),
		@DatabaseSetup(connection="wqx", value="classpath:/testData/wqx/state.xml"),
		@DatabaseSetup(connection="wqx", value="classpath:/testData/wqx/county.xml"),
		@DatabaseSetup(connection="nwisWsStar", value="classpath:/testData/nwisWsStar/country.xml")
	})
	@ExpectedDatabases({
		@ExpectedDatabase(connection="wqp", value="classpath:/testResult/storet/assemblage.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED),
		@ExpectedDatabase(connection="wqp", value="classpath:/testResult/storet/charName.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED),
		@ExpectedDatabase(connection="wqp", value="classpath:/testResult/storet/charType.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED),
		@ExpectedDatabase(connection="wqp", value="classpath:/testResult/storet/country.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED),
		@ExpectedDatabase(connection="wqp", value="classpath:/testResult/storet/county.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED),
		@ExpectedDatabase(connection="wqp", value="classpath:/testResult/storet/organization.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED),
		@ExpectedDatabase(connection="wqp", value="classpath:/testResult/storet/project.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED),
		@ExpectedDatabase(connection="wqp", value="classpath:/testResult/storet/projectDim.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED),
		@ExpectedDatabase(connection="wqp", value="classpath:/testResult/storet/sampleMedia.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED),
		@ExpectedDatabase(connection="wqp", value="classpath:/testResult/storet/siteType.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED),
		@ExpectedDatabase(connection="wqp", value="classpath:/testResult/storet/state.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED),
		@ExpectedDatabase(connection="wqp", value="classpath:/testResult/storet/taxaName.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED),
	})
	public void storetTest() {
		etlHelperCodeDao.createTables(STORET);
		assertEquals(1, utilityDao.getTableIndexCount(PROJECT_DIM_SWAP + STORET));
	}

}
