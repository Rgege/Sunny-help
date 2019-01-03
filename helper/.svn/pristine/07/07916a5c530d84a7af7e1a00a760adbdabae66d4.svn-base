package org.blue.helper.StringHelper.config;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.*;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Configuration
@AutoConfigureAfter({MemberDataSourceAutoConfiguration.class})
public class MemberDaoAutoConfiguration{
	
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource){
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
		dataSourceTransactionManager.setTransactionSynchronization(1);
		return dataSourceTransactionManager;
	}
	
//	@Bean
	public TransactionAttributeSource txAdvice(){
		NameMatchTransactionAttributeSource nameMatchTransactionAttributeSource = new NameMatchTransactionAttributeSource();
		nameMatchTransactionAttributeSource.setNameMap(getTransactionAttributeMap());
		return nameMatchTransactionAttributeSource;
	}
	
	private Map<String, TransactionAttribute> getTransactionAttributeMap(){
		// transaction attribute
		RuleBasedTransactionAttribute transactionAttribute = null;
		
		// roll back rule 
		List<RollbackRuleAttribute> rollbackRules = new LinkedList<RollbackRuleAttribute>();
		addRollbackRuleAttributesTo(rollbackRules, "Exception");
		ManagedMap<String, TransactionAttribute> transactionAttributeMap = new ManagedMap<String, TransactionAttribute>();
		
		// get*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setReadOnly(Boolean.valueOf("false"));
		transactionAttributeMap.put("get*", transactionAttribute);
		
		// select*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setReadOnly(Boolean.valueOf("false"));
		transactionAttributeMap.put("select*", transactionAttribute);
		
		// bind*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttributeMap.put("bind*", transactionAttribute);
		
		// add*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttributeMap.put("add*", transactionAttribute);
		
		// set*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttributeMap.put("set*", transactionAttribute);
		
		// save*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttributeMap.put("save*", transactionAttribute);
		
		// create*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttributeMap.put("create*", transactionAttribute);
		
		// modify*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttributeMap.put("modify*", transactionAttribute);
		
		// update*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttributeMap.put("update*", transactionAttribute);
		
		// cancle*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttributeMap.put("cancle*", transactionAttribute);
		
		// delete*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttributeMap.put("delete*", transactionAttribute);
		
		// merge*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttributeMap.put("merge*", transactionAttribute);
		
		// remove*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttributeMap.put("remove*", transactionAttribute);
		
		// overtime*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttributeMap.put("overtime*", transactionAttribute);
		
		// manualCancle*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttributeMap.put("manualCancle*", transactionAttribute);
		
		// des*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttributeMap.put("des*", transactionAttribute);
		
		// reg*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttributeMap.put("reg*", transactionAttribute);
		
		// pay*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttributeMap.put("pay*", transactionAttribute);
		
		// confirm*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttributeMap.put("confirm*", transactionAttribute);
		
		// return*
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttributeMap.put("return*", transactionAttribute);
		
		// *
		transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setPropagationBehaviorName(RuleBasedTransactionAttribute.PREFIX_PROPAGATION + "REQUIRED");
		transactionAttribute.setReadOnly(Boolean.valueOf("false"));
		transactionAttributeMap.put("*", transactionAttribute);
	
		return transactionAttributeMap;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource){
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setTypeAliasesPackage("org.blue.helper.StringHelper.persistence.entity.*");
		try {
			PathMatchingResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
//			sqlSessionFactoryBean.setConfigLocation(resolver.getResources("classpath:config/mybatis-config.xml")[0]);
			sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
			return sqlSessionFactoryBean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer(){
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("org.blue.helper.StringHelper.persistence");
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		return mapperScannerConfigurer;
		
	}
	
	private void addRollbackRuleAttributesTo(List<RollbackRuleAttribute> rollbackRules, String rollbackForValue) {
		String[] exceptionTypeNames = StringUtils.commaDelimitedListToStringArray(rollbackForValue);
		for (String typeName : exceptionTypeNames) {
			rollbackRules.add(new RollbackRuleAttribute(StringUtils.trimWhitespace(typeName)));
		}
	}
}
