package com.hx.api.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 * Created by Ro on 2018/4/24.
 */
@Configuration
public class ElasticsearchConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchConfig.class);

    /**
     * elk集群地址
     */
    @Value("${elasticsearch.ip}")
    private String hostName;
    /**
     * 端口
     */
    @Value("${elasticsearch.port}")
    private String port;
    /**
     * 集群名称
     */
    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    /**
     * 连接池
     */
    @Value("${elasticsearch.pool}")
    private String poolSize;



    @Bean
    public TransportClient init() {
        LOGGER.info("初始化开始。。。。。");
        TransportClient transportClient = null;
        System.out.println(hostName);
        /**
         * client.transport.ignore_cluster_name：设置为true时忽略集群名验证；
         * client.transport.ping_timeout：等待ping命令返回结果时间，默认为5秒；
         * client.transport.nodes_sampler_interval：节点之间互相ping，互连检测时间间隔；
         */

        try {
            // 配置信息
            Settings esSetting = Settings.builder()
                    .put("client.transport.sniff", true)//增加嗅探机制，找到ES集群
                    .put("thread_pool.search.size", Integer.parseInt(poolSize))//增加线程池个数，暂时设为5
                    .put("cluster.name", clusterName) //集群名称
                    .build();
            //配置信息Settings自定义,下面设置为EMPTY
            transportClient = new PreBuiltTransportClient(esSetting);
//            transportClient = new PreBuiltTransportClient(Settings.EMPTY);
            TransportAddress transportAddress = new InetSocketTransportAddress(InetAddress.getByName(hostName), Integer.valueOf(port));
            transportClient.addTransportAddresses(transportAddress);


            System.out.println("ElasticsearchClient 连接成功");

        } catch (Exception e) {
            LOGGER.error("elasticsearch TransportClient create error!!!", e);
        }

        return transportClient;
    }
}
