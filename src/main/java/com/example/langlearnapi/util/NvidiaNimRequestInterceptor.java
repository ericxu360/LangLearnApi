package com.example.langlearnapi.util;

import com.example.langlearnapi.config.NvidiaNimProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NvidiaNimRequestInterceptor implements ClientHttpRequestInterceptor {
    private final NvidiaNimProperties nvidiaNimProperties;

    @Autowired
    public NvidiaNimRequestInterceptor(NvidiaNimProperties nvidiaNimProperties) {
        this.nvidiaNimProperties = nvidiaNimProperties;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
        requestWrapper.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + nvidiaNimProperties.getApiKey());
        requestWrapper.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json");
        requestWrapper.getHeaders().add(HttpHeaders.ACCEPT, "application/json");
        return execution.execute(requestWrapper, body);
    }
}
