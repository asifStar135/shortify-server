package com.shortly.Repository;

import com.shortly.Models.UrlRedirect;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;

import java.util.Optional;

@Repository
public class UrlRedirectRepo {
    private final DynamoDbTable<UrlRedirect> table;

    public UrlRedirectRepo(DynamoDbTable<UrlRedirect> table) {
        this.table = table;
    }

    public void putItem(UrlRedirect item) {
        table.putItem(item);
    }

    public Optional<UrlRedirect> getItem(String shortCode) {
        UrlRedirect item = table.getItem(
                Key.builder().partitionValue(shortCode).build()
        );

        return Optional.ofNullable(item);
    }

    public void deleteItem(String shortCode) {

        table.deleteItem(
                Key.builder()
                        .partitionValue(shortCode)
                        .build()
        );
    }
}
