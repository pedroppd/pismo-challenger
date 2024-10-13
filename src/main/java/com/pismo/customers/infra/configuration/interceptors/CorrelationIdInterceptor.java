package com.pismo.customers.infra.configuration.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class CorrelationIdInterceptor implements HandlerInterceptor {

    private static final String CORRELATION_ID = "correlationId";
    private static final String CORRELATION_HEADER = "x-correlation-id";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        final var correlationId = request.getHeader(CORRELATION_HEADER);
        final var correlationIdGenerated = isEmpty(correlationId) ? UUID.randomUUID().toString() : correlationId;
        MDC.put(CORRELATION_ID, correlationIdGenerated);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(CORRELATION_ID);
    }
}
