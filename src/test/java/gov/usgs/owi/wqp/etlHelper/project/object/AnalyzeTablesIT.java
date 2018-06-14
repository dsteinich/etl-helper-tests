package gov.usgs.owi.wqp.etlHelper.project.object;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;

public class AnalyzeTablesIT extends BaseTest {

	@Test
	public void analyzeTablesTest() {
		etlHelperProjectObjectDao.analyzeTables(STORET);
		assertEquals("oldest last_analyzed date is more than 86 secounds ago", 1, 
				BigDecimal.valueOf(.001).compareTo(utilityDao.getLastAnalyzed(Arrays.asList(PROJECT_OBJECT_SWAP + STORET))));
	}

}
