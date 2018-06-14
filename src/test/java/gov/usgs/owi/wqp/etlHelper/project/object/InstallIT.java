package gov.usgs.owi.wqp.etlHelper.project.object;

import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

public class InstallIT extends BaseTest {

	@Test
	@DatabaseSetups({
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/install.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testData/storet/projectObject.xml")
	})
	@ExpectedDatabase(connection="wqp", value="classpath:/testResult/storet/projectObject.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void storetTest() {
		//Need the correct indexes to do the swap
		etlHelperMainDao.dropRI(STORET);
		etlHelperProjectObjectDao.dropIndexes(STORET);
		etlHelperProjectObjectDao.createIndexes(STORET);

		etlHelperProjectObjectDao.install(STORET);
	}

}
