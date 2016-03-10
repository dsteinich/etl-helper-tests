package gov.usgs.owi.wqp.etlHelper.springinit;

import java.sql.SQLException;

import org.dbunit.ext.oracle.OracleDataTypeFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
@ComponentScan("gov.usgs.owi.wqp.etlHelper")
@PropertySource(value = "classpath:test.properties")
public class TestSpringConfig {

	@Autowired
	private Environment env;

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		Resource mybatisConfig = new ClassPathResource("/mybatis/dataMapperConfig.xml");
		sqlSessionFactory.setConfigLocation(mybatisConfig);
		sqlSessionFactory.setDataSource(wqpODS());
		Resource[] mappers = new PathMatchingResourcePatternResolver().getResources("mybatis/mappers/**/*.xml");
		sqlSessionFactory.setMapperLocations(mappers);
		return sqlSessionFactory;
	}

	@Bean
	public OracleDataSource wqpODS() throws SQLException {
		OracleDataSource ds = new OracleDataSource();
		ds.setURL(env.getProperty("jdbc.wqpCore.url"));
		ds.setUser(env.getProperty("jdbc.wqpCore.username"));
		ds.setPassword(env.getProperty("jdbc.wqpCore.password"));
		return ds;
	}

	@Bean
	public OracleDataSource wqxODS() throws SQLException {
		OracleDataSource ds = new OracleDataSource();
		ds.setURL(env.getProperty("jdbc.wqx.url"));
		ds.setUser(env.getProperty("jdbc.wqx.username"));
		ds.setPassword(env.getProperty("jdbc.wqx.password"));
		return ds;
	}

	@Bean
	public OracleDataSource nwisWsStarODS() throws SQLException {
		OracleDataSource ds = new OracleDataSource();
		ds.setURL(env.getProperty("jdbc.nwisWsStar.url"));
		ds.setUser(env.getProperty("jdbc.nwisWsStar.username"));
		ds.setPassword(env.getProperty("jdbc.nwisWsStar.password"));
		return ds;
	}
	
	//Beans to support DBunit for unit testing with Oracle.
	@Bean
	public DatabaseConfigBean dbUnitDatabaseConfig() {
		DatabaseConfigBean dbUnitDbConfig = new DatabaseConfigBean();
		dbUnitDbConfig.setDatatypeFactory(new OracleDataTypeFactory());
		dbUnitDbConfig.setSkipOracleRecyclebinTables(true);
		dbUnitDbConfig.setQualifiedTableNames(true);
		return dbUnitDbConfig;
	}
	
	@Bean
	public DatabaseDataSourceConnectionFactoryBean wqp() throws SQLException {
		DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection = new DatabaseDataSourceConnectionFactoryBean();
		dbUnitDatabaseConnection.setDatabaseConfig(dbUnitDatabaseConfig());
		dbUnitDatabaseConnection.setDataSource(wqpODS());
		dbUnitDatabaseConnection.setSchema("WQP_CORE");
		return dbUnitDatabaseConnection;
	}

	@Bean
	public DatabaseDataSourceConnectionFactoryBean wqx() throws SQLException {
		DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection = new DatabaseDataSourceConnectionFactoryBean();
		dbUnitDatabaseConnection.setDatabaseConfig(dbUnitDatabaseConfig());
		dbUnitDatabaseConnection.setDataSource(wqxODS());
		dbUnitDatabaseConnection.setSchema("WQX");
		return dbUnitDatabaseConnection;
	}

	@Bean
	public DatabaseDataSourceConnectionFactoryBean nwisWsStar() throws SQLException {
		DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection = new DatabaseDataSourceConnectionFactoryBean();
		dbUnitDatabaseConnection.setDatabaseConfig(dbUnitDatabaseConfig());
		dbUnitDatabaseConnection.setDataSource(nwisWsStarODS());
		dbUnitDatabaseConnection.setSchema("NWIS_WS_STAR");
		return dbUnitDatabaseConnection;
	}

}
