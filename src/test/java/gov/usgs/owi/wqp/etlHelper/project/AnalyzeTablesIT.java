package gov.usgs.owi.wqp.etlHelper.project;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Test;

import gov.usgs.owi.wqp.etlHelper.dao.UtilityDao;

public class AnalyzeTablesIT extends BaseTest {

	@Resource
	UtilityDao utilityDao;


	@Test
	public void analyzeTablesTest() {
		etlHelperProjectDao.analyzeTables(STORET);
		assertEquals("oldest last_analyzed date is more than 86 secounds ago", 1, 
				BigDecimal.valueOf(.001).compareTo(utilityDao.getLastAnalyzed(Arrays.asList(PROJECT_DATA_SWAP + STORET))));
	}

}
