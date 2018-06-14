package gov.usgs.owi.wqp.etlHelper;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.owi.wqp.etlHelper.dao.EtlHelperMainDao;
import gov.usgs.owi.wqp.etlHelper.dao.UtilityDao;
import gov.usgs.owi.wqp.etlHelper.springinit.TestSpringConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSpringConfig.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
@DbUnitConfiguration(dataSetLoader=ColumnSensingFlatXMLDataSetLoader.class,
	databaseConnection={"wqp","wqx","nwisWsStar"})
public abstract class BaseSpringTest {

	//Data Source Suffixes
	public static final String STORET = "storet";

	//Swap Table Prefixes
	public static final String ASSEMBLAGE_SWAP = "assemblage_swap_";
	public static final String CHAR_NAME_SWAP = "char_name_swap_";
	public static final String CHAR_TYPE_SWAP = "char_type_swap_";
	public static final String COUNTRY_SWAP = "country_swap_";
	public static final String COUNTY_SWAP = "county_swap_";
	public static final String ORGANIZATION_SWAP = "organization_swap_";
	public static final String PROJECT_DATA_SWAP = "project_data_swap_";
	public static final String PROJECT_DIM_SWAP = "project_dim_swap_";
	public static final String PROJECT_OBJECT_SWAP = "project_object_swap_";
	public static final String PROJECT_SWAP = "project_swap_";
	public static final String SAMPLE_MEDIA_SWAP = "sample_media_swap_";
	public static final String SITE_TYPE_SWAP = "site_type_swap_";
	public static final String STATE_SWAP = "state_swap_";
	public static final String TAXA_NAME_SWAP = "taxa_name_swap_";

	//Swap Code Tables for Storet
	public static final Collection<String> STORET_CODE_TABLES;
	static {
		STORET_CODE_TABLES = new ArrayList<>();
		STORET_CODE_TABLES.add(ASSEMBLAGE_SWAP + STORET);
		STORET_CODE_TABLES.add(CHAR_NAME_SWAP + STORET);
		STORET_CODE_TABLES.add(CHAR_TYPE_SWAP + STORET);
		STORET_CODE_TABLES.add(COUNTRY_SWAP + STORET);
		STORET_CODE_TABLES.add(COUNTY_SWAP + STORET);
		STORET_CODE_TABLES.add(ORGANIZATION_SWAP + STORET);
		STORET_CODE_TABLES.add(PROJECT_DIM_SWAP + STORET);
		STORET_CODE_TABLES.add(PROJECT_SWAP + STORET);
		STORET_CODE_TABLES.add(SAMPLE_MEDIA_SWAP + STORET);
		STORET_CODE_TABLES.add(SITE_TYPE_SWAP + STORET);
		STORET_CODE_TABLES.add(STATE_SWAP + STORET);
		STORET_CODE_TABLES.add(TAXA_NAME_SWAP + STORET);
	}

	//Indexes for Storet
	public static final String PDS_STORET_CODE = "create bitmap index pds_storet_code on project_dim_swap_storet(code_value) parallel 4 nologging";

	@Resource
	protected UtilityDao utilityDao;

	@Resource
	protected EtlHelperMainDao etlHelperMainDao;

}
