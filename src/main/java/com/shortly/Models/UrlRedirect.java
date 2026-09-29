package com.shortly.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlRedirect {
    private String shortCode;

    private String longUrl;
    
    private boolean isActive;

    @DynamoDbPartitionKey
    public String getShortCode() {
        return this.shortCode;
    }
}
