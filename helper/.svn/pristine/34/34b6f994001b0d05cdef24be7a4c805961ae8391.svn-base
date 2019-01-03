package org.blue.helper;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.couchbase.CouchbaseDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.couchbase.CouchbaseRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.ldap.LdapDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.ldap.LdapRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.solr.SolrRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastJpaDependencyAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketMessagingAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@SpringBootApplication(exclude = {MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        RedisAutoConfiguration.class,
        RedisRepositoriesAutoConfiguration.class,
        EmbeddedMongoAutoConfiguration.class,
        MongoAutoConfiguration.class,
        TransactionAutoConfiguration.class,
        MongoRepositoriesAutoConfiguration.class})
//@SpringBootApplication(exclude = {MongoAutoConfiguration.class,
//        MongoDataAutoConfiguration.class,
//        DataSourceAutoConfiguration.class,
//        DataSourceTransactionManagerAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class,
//        RabbitAutoConfiguration.class,
//        JpaRepositoriesAutoConfiguration.class,
//        LdapDataAutoConfiguration.class,
//        LdapRepositoriesAutoConfiguration.class,
//        Neo4jDataAutoConfiguration.class,
//        Neo4jRepositoriesAutoConfiguration.class,
//        SolrRepositoriesAutoConfiguration.class,
//        RedisAutoConfiguration.class,
//        RedisRepositoriesAutoConfiguration.class,
//        FreeMarkerAutoConfiguration.class,
//        HazelcastAutoConfiguration.class,
//        HazelcastJpaDependencyAutoConfiguration.class,
//        JndiDataSourceAutoConfiguration.class,
//        XADataSourceAutoConfiguration.class,
//        JndiConnectionFactoryAutoConfiguration.class,
//        LiquibaseAutoConfiguration.class,
//        EmbeddedMongoAutoConfiguration.class,
//        MongoAutoConfiguration.class,
////        FacebookAutoConfiguration.class,
////        LinkedInAutoConfiguration.class,
////        TwitterAutoConfiguration.class,
//        TransactionAutoConfiguration.class,
//        JtaAutoConfiguration.class,
////        WebSocketAutoConfiguration.class,
//        WebSocketMessagingAutoConfiguration.class,
//        WebServicesAutoConfiguration.class,
//        CassandraDataAutoConfiguration.class,
//        CassandraRepositoriesAutoConfiguration.class,
//        CouchbaseDataAutoConfiguration.class,
//        CouchbaseRepositoriesAutoConfiguration.class,
//        ElasticsearchAutoConfiguration.class,
//        ElasticsearchDataAutoConfiguration.class,
//        ElasticsearchRepositoriesAutoConfiguration.class,
//        JpaRepositoriesAutoConfiguration.class,
//        JmsAutoConfiguration.class,
//        MongoRepositoriesAutoConfiguration.class})
@ComponentScan(basePackages = {"org.blue.helper"})
@MapperScan(basePackages = {"org.blue.helper.StringHelper.persistence","org.blue.helper.core.util"})
@ImportResource({"classpath:/spring-aop.xml"})
@EnableAsync   //开启异步   搭配@Async使用
@EnableScheduling   //开启计划任务   搭配@Scheduled使用
public class HelperApplication {
    public static ApplicationContext ctx;
    public static void main(String[] args) {
        String[] devArgs = new String[1];
        devArgs[0] = "--spring.profiles.active=dev";
        if(0==args.length) {
            ctx = SpringApplication.run(HelperApplication.class, devArgs);
        }else {
            ctx=SpringApplication.run(HelperApplication.class, args);
        }
    }
}
