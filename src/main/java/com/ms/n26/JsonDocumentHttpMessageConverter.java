package com.ms.n26;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.spi.json.JsonProvider;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import static org.apache.commons.lang3.CharEncoding.UTF_8;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * Created by mshetkar on 5/9/2017.
 */
public class JsonDocumentHttpMessageConverter extends AbstractHttpMessageConverter<JsonDocument> {

    private final JsonProvider jsonProvider = Configuration.defaultConfiguration().jsonProvider();

    public JsonDocumentHttpMessageConverter() {
        super(APPLICATION_JSON, new MediaType("application", "*+json"));
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return JsonDocument.class == clazz;
    }

    @Override
    protected JsonDocument readInternal(Class<? extends JsonDocument> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return new JsonDocument(jsonProvider.parse(inputMessage.getBody(), UTF_8));
    }

    @Override
    protected void writeInternal(JsonDocument jsonDocument, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        throw new UnsupportedOperationException();
    }
}

