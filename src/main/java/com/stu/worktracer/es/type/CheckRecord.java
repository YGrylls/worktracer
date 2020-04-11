package com.stu.worktracer.es.type;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Document(indexName = "check_record")
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

    @Field(type = FieldType.Date, format = DateFormat.none)
    private Long checkInTime;

    @Field(type = FieldType.Date)
    private Long checkOutTime;

}
