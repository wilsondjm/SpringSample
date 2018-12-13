package me.vincent.spring.tutorial.domain;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document("subject")
public class Subject {

    @ApiModelProperty(hidden = true)
    @Id
    String id;

    @NotEmpty(message = "科目名称不能为Null")
    @ApiModelProperty(value = "科目名称")
    String subjectName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
