package gov.usgs.owi.wqp.etlHelper.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EtlHelperCodeDao extends BaseDao {

	public static final String NS = "etl_helper_code.";

	@Autowired
	public EtlHelperCodeDao(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
	}

	public void createTables(String tableSuffix) {
		getSqlSession().update(NS + "create_tables", tableSuffix);
	}

	public void createAssemblage(String tableSuffix) {
		getSqlSession().update(NS + "create_assemblage", tableSuffix);
	}

	public void createCharName(String tableSuffix) {
		getSqlSession().update(NS + "create_char_name", tableSuffix);
	}

	public void createCharType(String tableSuffix) {
		getSqlSession().update(NS + "create_char_type", tableSuffix);
	}

	public void createCountry(String tableSuffix) {
		getSqlSession().update(NS + "create_country", tableSuffix);
	}

	public void createCounty(String tableSuffix) {
		getSqlSession().update(NS + "create_county", tableSuffix);
	}

	public void createOrganization(String tableSuffix) {
		getSqlSession().update(NS + "create_organization", tableSuffix);
	}

	public void createProjectDim(String tableSuffix) {
		getSqlSession().update(NS + "create_project_dim", tableSuffix);
	}

	public void createProject(String tableSuffix) {
		getSqlSession().update(NS + "create_project", tableSuffix);
	}

	public void createSampleMedia(String tableSuffix) {
		getSqlSession().update(NS + "create_sample_media", tableSuffix);
	}

	public void createSiteType(String tableSuffix) {
		getSqlSession().update(NS + "create_site_type", tableSuffix);
	}

	public void createState(String tableSuffix) {
		getSqlSession().update(NS + "create_state", tableSuffix);
	}

	public void createTaxaName(String tableSuffix) {
		getSqlSession().update(NS + "create_taxa_name", tableSuffix);
	}

	public void analyzeTables(String tableSuffix) {
		getSqlSession().update(NS + "analyze_tables", tableSuffix);
	}

	public void install(String tableSuffix) {
		getSqlSession().update(NS + "install", tableSuffix);
	}

}
