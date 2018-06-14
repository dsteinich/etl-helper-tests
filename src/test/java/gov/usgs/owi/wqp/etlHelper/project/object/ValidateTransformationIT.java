package gov.usgs.owi.wqp.etlHelper.project.object;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;

public class ValidateTransformationIT extends BaseTest {

	@Test
	@DatabaseSetups({
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/projectObject.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testData/storet/validProjectObject.xml")
	})
	public void storetHappyTest() {
		assertFalse("End Job should be false on a valid etl", etlHelperProjectObjectDao.validateTransformation(STORET));
	}

	@Test
	@DatabaseSetups({
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/projectObject.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testData/storet/noEtlRowsProjectObject.xml")
	})
	public void storetNoETLRowsTest() {
		assertTrue("End Job should be true on an invalid etl", etlHelperProjectObjectDao.validateTransformation(STORET));
	}

	@Test
	@DatabaseSetups({
		@DatabaseSetup(connection="wqp", value="classpath:/testCleanup/storet/projectObject.xml"),
		@DatabaseSetup(connection="wqp", value="classpath:/testData/storet/noEtlRowsProjectObject.xml")
	})
	public void storetTooFewETLRowsTest() {
		assertTrue("End Job should be true on an invalid etl", etlHelperProjectObjectDao.validateTransformation(STORET));
	}

}
