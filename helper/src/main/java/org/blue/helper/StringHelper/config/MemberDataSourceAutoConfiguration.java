package org.blue.helper.StringHelper.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@EnableConfigurationProperties({C3P0Properties.class,  MySQLDataSourceProperties.class})
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
//@AutoConfigureAfter({OracleDataSourceProperties.class})
public class MemberDataSourceAutoConfiguration {
	
	@Configuration
	protected static class MemDSAutoConfiguration{
		
		private C3P0Properties c3p0Properties;
		private MySQLDataSourceProperties mySQLDataSourceProperties;
		
		public MemDSAutoConfiguration(C3P0Properties c3p0Properties,MySQLDataSourceProperties mySQLDataSourceProperties){
			this.c3p0Properties = c3p0Properties;
			this.mySQLDataSourceProperties = mySQLDataSourceProperties;
		}

		@Bean
		public DataSource dataSource(){
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			setDataSourceBasicProperties(dataSource, mySQLDataSourceProperties);
			setC3P0PoolProperties(dataSource);
			return dataSource;
		}

		
		private void setDataSourceBasicProperties(ComboPooledDataSource comboPooledDataSource, BaseDataSourceProperties baseDataSourceProperties){
			comboPooledDataSource.setUser(baseDataSourceProperties.getUsername());
			comboPooledDataSource.setPassword(baseDataSourceProperties.getPassword());
			try {
				comboPooledDataSource.setDriverClass(baseDataSourceProperties.getDriverClassName());
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			comboPooledDataSource.setJdbcUrl(baseDataSourceProperties.getUrl());
		}
		
		private void setC3P0PoolProperties(ComboPooledDataSource comboPooledDataSource){
			comboPooledDataSource.setMaxPoolSize(c3p0Properties.getMaxPoolSize());
			comboPooledDataSource.setMinPoolSize(c3p0Properties.getMinPoolSize());
			comboPooledDataSource.setInitialPoolSize(c3p0Properties.getInitialPoolSize());
			comboPooledDataSource.setMaxIdleTime(c3p0Properties.getMaxIdleTime());
			comboPooledDataSource.setCheckoutTimeout(c3p0Properties.getCheckoutTimeout());
			comboPooledDataSource.setAcquireIncrement(c3p0Properties.getAcquireIncrement());
			comboPooledDataSource.setAcquireRetryAttempts(c3p0Properties.getAcquireRetryAttempts());
			comboPooledDataSource.setAcquireRetryDelay(c3p0Properties.getAcquireRetryDelay());
			comboPooledDataSource.setAutoCommitOnClose(c3p0Properties.isAutoCommitOnClose());
			comboPooledDataSource.setAutomaticTestTable(c3p0Properties.getAutomaticTestTable());
			comboPooledDataSource.setBreakAfterAcquireFailure(c3p0Properties.isBreakAfterAcquireFailure());
			comboPooledDataSource.setIdleConnectionTestPeriod(c3p0Properties.getIdleConnectionTestPeriod());
			comboPooledDataSource.setTestConnectionOnCheckin(c3p0Properties.isTestConnectionOnCheckin());
			comboPooledDataSource.setTestConnectionOnCheckout(c3p0Properties.isTestConnectionOnCheckout());
		}

}
}
