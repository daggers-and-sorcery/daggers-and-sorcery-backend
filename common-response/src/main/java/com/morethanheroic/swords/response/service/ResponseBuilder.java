package com.morethanheroic.swords.response.service;


import com.morethanheroic.swords.response.domain.Response;

public interface ResponseBuilder<T extends ResponseBuilderConfiguration> {

    Response build(T responseConfiguration);
}