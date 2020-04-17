package com.stu.worktracer.es;

import com.stu.worktracer.es.type.CheckRecord;
import com.stu.worktracer.es.type.Company;
import com.stu.worktracer.es.type.WelfareRecord;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.BulkOptions;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ESService {

    @Resource(name = "elasticsearchTemplate")
    private ElasticsearchTemplate template;

    private int MAX_TIME_RANGE_LIMIT = 8192;

    public void createIndex(String indexName, Class<?> clazz) {
        template.createIndex(indexName);
        template.putMapping(clazz);
    }

    public void indexCompany(Company company) {
        index("company", company);
    }

    public void indexCheckRecordBulk(List<CheckRecord> list) {
        indexBulk("check_record", list);
    }

    public void indexWelfare(WelfareRecord welfareRecord) {
        index("welfare_record", welfareRecord);
    }

    public List<Company> searchCompany(String searchText, int page, int size) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder().withIndices("company").withPageable(PageRequest.of(page, size));
        QueryBuilder queryBuilder;
        if (!StringUtils.isEmpty(searchText)) {
            queryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name.ik", searchText).boost(2.0f))
                    .should(QueryBuilders.matchQuery("name.pinyin", searchText));
        } else {
            queryBuilder = QueryBuilders.matchAllQuery();
        }
        return template.queryForList(builder.withQuery(queryBuilder).build(), Company.class);

    }

    public List<CheckRecord> queryCheckRecordWeek(Long companyId) {
        long to = getZeroHour().getTime();
        long from = to - 24 * 60 * 60 * 1000 * 7L;
        return queryTime("check_record", companyId, CheckRecord.class, "checkInTime", from, to);

    }

    public List<CheckRecord> queryCheckRecordMonth(Long companyId) {
        long to = getZeroHour().getTime();
        long from = to - 24 * 60 * 60 * 1000 * 30L;
        return queryTime("check_record", companyId, CheckRecord.class, "checkInTime", from, to);
    }

    public List<WelfareRecord> queryWelfareThisMonth(Long companyId) {
        long to = getZeroHour().getTime();
        long from = to - 24 * 60 * 60 * 1000 * 30L;
        return queryTime("welfare_record", companyId, WelfareRecord.class, "surveyTime", from, to);
    }

    private Date getZeroHour() {
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        return now.getTime();
    }


    public <T> List<T> queryTime(String indexName, Long id, Class<T> clazz, String timeField, Long from, Long to) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder().withIndices(indexName).withPageable(PageRequest.of(0, MAX_TIME_RANGE_LIMIT));
        RangeQueryBuilder rangeBuilder = QueryBuilders.rangeQuery(timeField).from(from).to(to);
        BoolQueryBuilder boolRangeBuilder = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("companyId", id)).must(rangeBuilder);
        return template.queryForList(builder.withQuery(boolRangeBuilder).build(), clazz);
    }

    public <T> List<T> queryTimePage(String indexName, Long id, Class<T> clazz, String timeField, Long from, Long to, int page, int size) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder().withIndices(indexName).withPageable(PageRequest.of(page, size));
        RangeQueryBuilder rangeBuilder = QueryBuilders.rangeQuery(timeField).from(from).to(to);
        BoolQueryBuilder boolRangeBuilder = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("companyId", id)).must(rangeBuilder);
        return template.queryForList(builder.withQuery(boolRangeBuilder).build(), clazz);
    }

    private void index(String indexName, Object object) {
        IndexQueryBuilder builder = new IndexQueryBuilder();
        builder.withIndexName(indexName);
        builder.withObject(object);
        template.index(builder.build());
    }

    private void indexBulk(String indexName, List<?> objectList) {
        BulkOptions.BulkOptionsBuilder builder = BulkOptions.builder();
        BulkOptions options = builder.build();
        List<IndexQuery> list = new ArrayList<>(objectList.size());
        for (Object o : objectList) {
            IndexQueryBuilder indexBuilder = new IndexQueryBuilder();
            list.add(indexBuilder.withIndexName(indexName).withObject(o).build());
        }
        template.bulkIndex(list, options);
    }
}
