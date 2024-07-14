package com.project.productsservice.elasticsearch.config;


import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.net.ssl.SSLContext;


@Configuration
@EnableElasticsearchRepositories(basePackages = "com.project.productsservice.elasticsearch.repositories")
public class ClientConfig extends ElasticsearchConfiguration {

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder().connectedTo("localhost:9200")
                .usingSsl(buildSSlContext())
                .withBasicAuth("elastic", "password")
                .build();
    }

    private static SSLContext buildSSlContext(){
        try{
            return new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build();
        }catch(Exception e){
            throw new RuntimeException();
        }
    }

}