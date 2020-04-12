package com.stu.worktracer.es.type;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Document(indexName = "check_record", shards = 1, replicas = 1)
public class CheckRecord {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private Long uid;

    @Field(type = FieldType.Keyword)
    private Long companyId;

    @Field(type = FieldType.Keyword)
    private String username;

    @MultiField(
            mainField = @Field(type = FieldType.Keyword),
            otherFields = {
                    @InnerField(suffix = "ik", type = FieldType.Text, analyzer = "ik_max_word"),
                    @InnerField(suffix = "pinyin", type = FieldType.Text, analyzer = "pinyin")
            }
    )
    private String companyName;

    @Field(type = FieldType.Date)
    private Long checkInTime;

    @Field(type = FieldType.Date)
    private Long checkOutTime;

    public CheckRecord() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Long checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Long getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Long checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
}
