package gov.usgs.owi.wqp.etlHelper.project;

import javax.annotation.Resource;

import gov.usgs.owi.wqp.etlHelper.BaseSpringTest;
import gov.usgs.owi.wqp.etlHelper.dao.EtlHelperProjectDao;

public abstract class BaseTest extends BaseSpringTest {

	@Resource
	protected EtlHelperProjectDao etlHelperProjectDao;

	protected static final String TEST_TABLE = PROJECT_DATA_SWAP + STORET;

}
