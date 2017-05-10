package com.ms.n26;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.Predicate;

/**
 * Created by mshetkar on 5/9/2017.
 */
public class JsonDocument {
    private final Object document;
    private final Configuration configuration;

    public JsonDocument(Object document) {
        this.document = document;
        configuration = Configuration.defaultConfiguration().addOptions(Option.SUPPRESS_EXCEPTIONS);
    }

    public <T> T read(String jsonPath, Predicate... filters) {
        return JsonPath.using(configuration).parse(document).read(jsonPath, filters);
    }

    @Override
    public String toString() {
        return document.toString();
    }
}
