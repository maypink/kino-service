package org.users.exception.user;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class UserExceptionHandler implements ResponseErrorHandler {

    private List<HttpMessageConverter<?>> messageConverters;

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {

        return (response.getStatusCode().is4xxClientError() ||
                response.getStatusCode().is5xxServerError());
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        @SuppressWarnings({ "unchecked", "rawtypes" })
        HttpMessageConverterExtractor<UserErrorResponse> errorMessageExtractor =
                new HttpMessageConverterExtractor(UserErrorResponse.class, messageConverters);

        UserErrorResponse errorObject = errorMessageExtractor.extractData(response);

        throw new ResponseUserErrorException(
                ResponseEntity.status(response.getStatusCode())
                        .headers(response.getHeaders())
                        .body(errorObject)
        );

    }

    public void setMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        this.messageConverters = messageConverters;
    }
}
