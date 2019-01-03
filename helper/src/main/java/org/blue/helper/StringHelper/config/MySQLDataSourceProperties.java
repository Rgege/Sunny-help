package org.blue.helper.StringHelper.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jdbc")
public class MySQLDataSourceProperties extends BaseDataSourceProperties{

}
