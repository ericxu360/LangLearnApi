package com.example.langlearnapi.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.IOException;

@Value
@ConfigurationProperties("langlearn.okta")
public class OktaClientProperties {
    String clientId;
    String clientSecret;
    String audience;
    String grantType;
    String url;
}