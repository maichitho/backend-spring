package co.siten.myvtg.configuration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:database.properties" })
@EnableJpaRepositories(basePackages = "co.siten.myvtg.dao", entityManagerFactoryRef = "userEntityManager", transactionManagerRef = "userTransactionManager")

public class JpaConfiguration {
	@Autowired
	private Environment env;

	public JpaConfiguration() {
		super();
	}

	//

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean userEntityManager() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(userDataSource("myvtg"));
		em.setPackagesToScan(new String[] { "co.siten.myvtg.model.myvtg" });

		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		final HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.connection.isolation", env.getProperty("hibernate.connection.isolation"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Primary
	@Bean
	public DataSource userDataSource(String schema) {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getRequiredProperty(schema + ".jdbc.url"));
		dataSource.setUsername(env.getRequiredProperty(schema + ".jdbc.username"));
		dataSource.setPassword(env.getRequiredProperty(schema + ".jdbc.password"));

		return dataSource;
	}

	@Primary
	@Bean
	public PlatformTransactionManager userTransactionManager() {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(userEntityManager().getObject());
		return transactionManager;
	}
}
