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
import gov.usgs.owi.wqp.etlHelper.dao.UtilityDao;

@Category(DBIntegrationTest.class)
public class InstallTest extends BaseSpringTest {

	@Resource
	UtilityDao utilityDao;
	
	@Resource
	EtlHelperCodeDao etlHelperCodeDao;
	
	@Test
	@DatabaseSetups({
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/install.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testResult/storet/assemblage.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testResult/storet/charType.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testResult/storet/charName.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testResult/storet/country.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testResult/storet/county.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testResult/storet/organization.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testResult/storet/project.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testResult/storet/projectDim.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testResult/storet/sampleMedia.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testResult/storet/siteType.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testResult/storet/state.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testResult/storet/taxaName.xml")
	})
	@ExpectedDatabase(connection="wqp", value="classpath:/testResult/storet/install.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void storetTest() {
		if (0 == utilityDao.getTableIndexCount(PROJECT_DIM_SWAP + STORET)) {
			utilityDao.createIndex(PDS_STORET_CODE);
		}
		etlHelperCodeDao.install(STORET);
	}

}
