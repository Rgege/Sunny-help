package org.blue.helper.StringHelper.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Cluster;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ConditionalOnClass({ ShardedJedisPool.class})
@EnableConfigurationProperties(RedisProperties.class)
public class RedisAutoConfiguration {

    @Autowired
    private RedisProperties properties;

    /**
     * 连接池交由Spring创建 并且加锁  保证线程安全
     * @return
     */
    @PostConstruct
    private synchronized JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        RedisProperties.Pool props = this.properties.getPool();
        if(props !=null){
            config.setMaxTotal(props.getMaxActive());
            config.setMaxIdle(props.getMaxIdle());
            config.setMinIdle(props.getMinIdle());
            config.setMaxWaitMillis(props.getMaxWait());
        }else{
            // TODO
            config.setMaxTotal(50);
            config.setMaxIdle(30);
            config.setMinIdle(10);
            config.setMaxWaitMillis(500);
        }
        return config;
    }

    @Bean
    @ConditionalOnMissingBean(name = "shardedJedisPool")
    public ShardedJedisPool shardedJedisPool(){
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(jedisPoolConfig(), getShards());
        return shardedJedisPool;
    }

    @Bean
    @ConditionalOnMissingBean(name = "stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(){
        return new StringRedisTemplate(new JedisConnectionFactory(jedisPoolConfig()));
    }

//    @Bean
//    @ConditionalOnMissingBean(name = "jedisPool")
//    public JedisPool jedisPool() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        RedisProperties.Pool props = this.properties.getPool();
//        jedisPoolConfig.setMaxIdle(props.getMaxIdle());
//        jedisPoolConfig.setMaxWaitMillis(props.getMaxWait());
//
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, properties.getHost(), properties.getPort(), properties.getTimeout(), properties.getPassword());
//
//        return jedisPool;
//    }
    private List<JedisShardInfo> getShards(){
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();

        String url = properties.getUrl();
        if(!StringUtils.isEmpty(url)){
            JedisShardInfo jedisShardInfo = new JedisShardInfo(url);
            jedisShardInfo.setPassword(properties.getPassword());
            shards.add(jedisShardInfo);
        }

        String url2 = properties.getUrl2();
        if(!StringUtils.isEmpty(url2)){
            JedisShardInfo jedisShardInfo = new JedisShardInfo(url2);
            jedisShardInfo.setPassword(properties.getPassword());
            shards.add(jedisShardInfo);
        }

        if(CollectionUtils.isEmpty(shards)){
            Cluster cluster = properties.getCluster();
            if(cluster != null && cluster.getNodes() != null){
                for(String node : cluster.getNodes()){
                    if(!StringUtils.isEmpty(node) && node.split(":").length == 2){
                        String host = node.split(":")[0];
                        String port = node.split(":")[1];
                        JedisShardInfo jedisShardInfo = new JedisShardInfo(host,port);
                        jedisShardInfo.setPassword(properties.getPassword());
                        shards.add(jedisShardInfo);
                    }
                }
            }
        }

        return shards;
    }

    private RedisStandaloneConfiguration redisStandaloneConfiguration(){
        RedisStandaloneConfiguration redis=new RedisStandaloneConfiguration();
        redis.setHostName(properties.getHost());
        redis.setPassword(RedisPassword.of(properties.getPassword()));
        redis.setPort(properties.getPort());
        return redis;
    }
}

