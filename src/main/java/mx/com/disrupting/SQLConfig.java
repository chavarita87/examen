package mx.com.disrupting;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "mx.com.disrupting.repository", entityManagerFactoryRef = "disruptingEntityManager", transactionManagerRef = "disruptingTransactionManager")
public class SQLConfig {

	@Autowired
	private Environment env;

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean disruptingEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(disruptingDataSource());
		em.setPackagesToScan("mx.com.disrupting.entity");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("disrupting.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.show-sql", env.getProperty("disrupting.jpa.show-sql"));
		properties.put("hibernate.dialect", env.getProperty("disrupting.jpa.database-platform"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Primary
	@Bean
	public DataSource disruptingDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("disrupting.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("disrupting.datasource.url"));
		dataSource.setUsername(env.getProperty("disrupting.datasource.username"));
		dataSource.setPassword(env.getProperty("disrupting.datasource.password"));

		return dataSource;
	}

	@Primary
	@Bean
	public PlatformTransactionManager disruptingTransactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(disruptingEntityManager().getObject());
		return transactionManager;
	}
}
