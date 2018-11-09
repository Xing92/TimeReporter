package com.xing.timereporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimeReporterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeReporterApplication.class, args);
	}

//	@Bean
//	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
//		return new JpaTransactionManager(emf);
//	}
//
//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
//			JpaVendorAdapter jpaVendorAdapter) {
//		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
//		bean.setDataSource(dataSource);
//		bean.setJpaVendorAdapter(jpaVendorAdapter);
//		bean.setPackagesToScan("com.xing.timereporter");
//		return bean;
//	}
//
//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
//		dataSource.setUrl("jdbc:mysql://localhost:3306/timereporter?useSSL=false");
//		dataSource.setUsername("testu");
//		dataSource.setPassword("testp");
//		return dataSource;
//	}
//
//	@Bean
//	public JpaVendorAdapter jpaVendorAdapter() {
//		HibernateJpaVendorAdapter bean = new HibernateJpaVendorAdapter();
//		bean.setDatabase(Database.MYSQL);
//		bean.setGenerateDdl(true);
//		bean.setShowSql(true);
//		return bean;
//	}

}
