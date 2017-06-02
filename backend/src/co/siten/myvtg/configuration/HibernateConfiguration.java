//package co.siten.myvtg.configuration;
//
//import java.sql.Connection;
//import java.util.Properties;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate4.HibernateTransactionManager;
//import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
///**
// * 
// * @author thomc
// *
// */
//@Configuration
//@EnableTransactionManagement
//@ComponentScan({ "co.siten.myvtg.configuration" })
//@PropertySource(value = { "classpath:database.properties" })
//@EnableJpaRepositories("co.siten.myvtg.dao")
//public class HibernateConfiguration {
//
//	@Autowired
//	private Environment environment;
//
//	@Bean
//	public LocalSessionFactoryBean myvtgSessionFactory() {
//		String schema = "myvtg";
//		return getLocalSessionFactoryBean(schema);
//	}
//
//	@Bean
//	public LocalSessionFactoryBean dataSessionFactory() {
//		String schema = "data";
//		return getLocalSessionFactoryBean(schema);
//	}
//
//	@Bean
//	public LocalSessionFactoryBean cmposSessionFactory() {
//		String schema = "cmpos";
//		return getLocalSessionFactoryBean(schema);
//	}
//
//	@Bean
//	public LocalSessionFactoryBean cmpreSessionFactory() {
//		String schema = "cmpre";
//		return getLocalSessionFactoryBean(schema);
//	}
//
//	@Bean
//	public LocalSessionFactoryBean precallSessionFactory() {
//		String schema = "precall";
//		return getLocalSessionFactoryBean(schema);
//	}
//
//	@Bean
//	public LocalSessionFactoryBean paymentSessionFactory() {
//		String schema = "payment";
//		return getLocalSessionFactoryBean(schema);
//	}
//
//	@Bean
//	public LocalSessionFactoryBean billingSessionFactory() {
//		String schema = "billing";
//		return getLocalSessionFactoryBean(schema);
//	}
//
//	private LocalSessionFactoryBean getLocalSessionFactoryBean(String schema) {
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
//		dataSource.setUrl(environment.getRequiredProperty(schema + ".jdbc.url"));
//		dataSource.setUsername(environment.getRequiredProperty(schema + ".jdbc.username"));
//		dataSource.setPassword(environment.getRequiredProperty(schema + ".jdbc.password"));
//
//		sessionFactory.setDataSource(dataSource);
//		sessionFactory.setPackagesToScan(new String[] { "co.siten.myvtg.model." + schema });
//		sessionFactory.setHibernateProperties(hibernateProperties());
//		return sessionFactory;
//	}
//
//	private Properties hibernateProperties() {
//		Properties properties = new Properties();
//		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
//		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
//		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
//		properties.put("hibernate.connection.isolation", environment.getProperty("hibernate.connection.isolation"));
//		String.valueOf(Connection.TRANSACTION_SERIALIZABLE);
//		return properties;
//	}
//
//	@Bean(name = "precalltx")
//	@Autowired
//	public HibernateTransactionManager precalltx() {
//		HibernateTransactionManager txManager = new HibernateTransactionManager();
//		txManager.setSessionFactory(precallSessionFactory().getObject());
//		return txManager;
//	}
//
//	@Bean(name = "datatx")
//	@Autowired
//	public HibernateTransactionManager datatx() {
//		HibernateTransactionManager txManager = new HibernateTransactionManager();
//		txManager.setSessionFactory(dataSessionFactory().getObject());
//		return txManager;
//	}
//
//	@Bean(name = "cmpostx")
//	@Autowired
//	public HibernateTransactionManager cmpostx() {
//		HibernateTransactionManager txManager = new HibernateTransactionManager();
//		txManager.setSessionFactory(cmposSessionFactory().getObject());
//		return txManager;
//	}
//
//	@Bean(name = "cmpretx")
//	@Autowired
//	public HibernateTransactionManager cmpretx() {
//		HibernateTransactionManager txManager = new HibernateTransactionManager();
//		txManager.setSessionFactory(cmpreSessionFactory().getObject());
//		return txManager;
//	}
//
//	@Bean(name = "myvtgtx")
//	@Autowired
//	public HibernateTransactionManager myvtgtx() {
//		HibernateTransactionManager txManager = new HibernateTransactionManager();
//		txManager.setSessionFactory(myvtgSessionFactory().getObject());
//		return txManager;
//	}
//
//	@Bean(name = "paymenttx")
//	@Autowired
//	public HibernateTransactionManager payment() {
//		HibernateTransactionManager txManager = new HibernateTransactionManager();
//		txManager.setSessionFactory(paymentSessionFactory().getObject());
//		return txManager;
//	}
//
//	@Bean(name = "billingtx")
//	@Autowired
//	public HibernateTransactionManager billing() {
//		HibernateTransactionManager txManager = new HibernateTransactionManager();
//		txManager.setSessionFactory(billingSessionFactory().getObject());
//		return txManager;
//	}
//
//}