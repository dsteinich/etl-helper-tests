package gov.usgs.owi.wqp.etlHelper.project.object;

import javax.annotation.Resource;

import gov.usgs.owi.wqp.etlHelper.BaseSpringTest;
import gov.usgs.owi.wqp.etlHelper.dao.EtlHelperProjectObjectDao;

public abstract class BaseTest extends BaseSpringTest {

	@Resource
	protected EtlHelperProjectObjectDao etlHelperProjectObjectDao;

	protected static final String TEST_TABLE = PROJECT_OBJECT_SWAP + STORET;

}
