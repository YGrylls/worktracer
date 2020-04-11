package com.stu.worktracer.es;

import com.stu.worktracer.es.type.Company;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ESService {

    @Resource(name = "elasticsearchTemplate")
    private ElasticsearchTemplate template;

    public void createIndex(){
        template.createIndex(Company.class);
        template.putMapping(Company.class);
    }

    public void index(String indexName, Object object){
        IndexQueryBuilder builder = new IndexQueryBuilder();
        builder.withIndexName(indexName);
        builder.withObject(object);
        template.index(builder.build());
    }
}
