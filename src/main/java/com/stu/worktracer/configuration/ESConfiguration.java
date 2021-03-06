package com.stu.worktracer.configuration;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.core.ElasticsearchEntityMapper;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.EntityMapper;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ESConfiguration extends ElasticsearchConfigurationSupport {

    @Value("${elasticsearch.address}")
    private String address;

    @Value("${elasticsearch.port}")
    private String port;

    @Value("${elasticsearch.clustername}")
    private String clustername;

    @PostConstruct
    public void setProperties() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    @Bean
    public Client elasticsearchClient() throws UnknownHostException {
        Settings settings = Settings.builder().put("cluster.name", clustername).build();
        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(new TransportAddress(InetAddress.getByName(address), Integer.valueOf(port)));
        return client;
    }

    @Bean(name = {"elasticsearchOperations", "elasticsearchTemplate"})
    public ElasticsearchTemplate elasticsearchTemplate() throws UnknownHostException {
        return new ElasticsearchTemplate(elasticsearchClient(), entityMapper());
    }

    // use the ElasticsearchEntityMapper
    @Bean
    @Override
    public EntityMapper entityMapper() {
        ElasticsearchEntityMapper entityMapper = new ElasticsearchEntityMapper(elasticsearchMappingContext(),
                new DefaultConversionService());
        entityMapper.setConversions(elasticsearchCustomConversions());
        return entityMapper;
    }


}
