package com.stu.worktracer.es.type;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Document(indexName = "welfare_record", shards = 1, replicas = 1)
public class WelfareRecord {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private Long uid;

    @Field(type = FieldType.Keyword)
    private Long companyId;

    @MultiField(
            mainField = @Field(type = FieldType.Keyword),
            otherFields = {
                    @InnerField(suffix = "ik", type = FieldType.Text, analyzer = "ik_max_word"),
                    @InnerField(suffix = "pinyin", type = FieldType.Text, analyzer = "pinyin")
            }
    )
    private String companyName;

    @Field(type = FieldType.Integer)
    private Integer welfare;

    @Field(type = FieldType.Date)
    private Long surveyTime;

    @Field(type = FieldType.Integer)
    private Integer percentFix;

    public WelfareRecord() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getWelfare() {
        return welfare;
    }

    public void setWelfare(Integer welfare) {
        this.welfare = welfare;
    }

    public Long getSurveyTime() {
        return surveyTime;
    }

    public void setSurveyTime(Long surveyTime) {
        this.surveyTime = surveyTime;
    }

    public Integer getPercentFix() {
        return percentFix;
    }

    public void setPercentFix(Integer percentFix) {
        this.percentFix = percentFix;
    }
}
