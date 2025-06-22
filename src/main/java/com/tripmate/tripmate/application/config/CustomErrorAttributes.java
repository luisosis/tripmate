package com.tripmate.tripmate.application.config;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Throwable error = getError(request);

        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("timestamp", Instant.now());
        errorAttributes.put("path", request.path());
        errorAttributes.put("message", error.getMessage());
        errorAttributes.put("error", error.getClass().getSimpleName());
        errorAttributes.put("status", determineStatus(error));

        return errorAttributes;
    }

    private int determineStatus(Throwable error) {
        if (error instanceof IllegalArgumentException) {
            return HttpStatus.BAD_REQUEST.value();
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
    }
}
