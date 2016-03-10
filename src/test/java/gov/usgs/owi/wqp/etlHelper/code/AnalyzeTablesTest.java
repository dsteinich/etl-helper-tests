package gov.usgs.owi.wqp.etlHelper.code;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import gov.usgs.owi.wqp.etlHelper.BaseSpringTest;
import gov.usgs.owi.wqp.etlHelper.DBIntegrationTest;
import gov.usgs.owi.wqp.etlHelper.dao.EtlHelperCodeDao;
import gov.usgs.owi.wqp.etlHelper.dao.UtilityDao;

@Category(DBIntegrationTest.class)
public class AnalyzeTablesTest extends BaseSpringTest {

	@Resource
	UtilityDao utilityDao;

	@Resource
	EtlHelperCodeDao etlHelperCodeDao;

	@Test
	public void storetTest() {
		etlHelperCodeDao.analyzeTables(STORET);
		assertEquals("oldest last_analyzed date is more than 86 secounds ago", 1, 
				BigDecimal.valueOf(.001).compareTo(utilityDao.getLastAnalyzed(STORET_CODE_TABLES)));
	}

}
