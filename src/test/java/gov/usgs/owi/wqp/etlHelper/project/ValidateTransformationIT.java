package gov.usgs.owi.wqp.etlHelper.project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;

public class ValidateTransformationIT extends BaseTest {

	@Test
	@DatabaseSetups({
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/projectData.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testData/storet/validProjectData.xml")
	})
	public void storetHappyTest() {
		assertFalse("End Job should be false on a valid etl", etlHelperProjectDao.validateTransformation(STORET));
	}

	@Test
	@DatabaseSetups({
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/projectData.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testData/storet/noEtlRowsProjectData.xml")
	})
	public void storetNoETLRowsTest() {
		assertTrue("End Job should be true on an invalid etl", etlHelperProjectDao.validateTransformation(STORET));
	}

	@Test
	@DatabaseSetups({
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/projectData.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testData/storet/noEtlRowsProjectData.xml")
	})
	public void storetTooFewETLRowsTest() {
		assertTrue("End Job should be true on an invalid etl", etlHelperProjectDao.validateTransformation(STORET));
	}

}
