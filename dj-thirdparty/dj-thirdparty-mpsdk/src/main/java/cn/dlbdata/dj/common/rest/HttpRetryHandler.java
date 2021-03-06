package cn.dlbdata.dj.common.rest;

import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpRetryHandler implements HttpRequestRetryHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRetryHandler.class);

    private final DefaultHttpRequestRetryHandler defaultHttpRequestRetryHandler;

    public HttpRetryHandler(DefaultHttpRequestRetryHandler defaultHttpRequestRetryHandler) {
        Preconditions.checkNotNull(defaultHttpRequestRetryHandler);
        this.defaultHttpRequestRetryHandler = defaultHttpRequestRetryHandler;
    }

    @Override
    public boolean retryRequest(IOException e, int i, HttpContext httpContext) {
        LOGGER.debug("Http request retrying, last error message : {}, retry time : {}.", e.getMessage(), i);
        return defaultHttpRequestRetryHandler.retryRequest(e, i, httpContext);
    }
}
