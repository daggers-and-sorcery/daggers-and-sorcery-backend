package com.morethanheroic.swords.market.view.controller;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.market.service.CancelListingService;
import com.morethanheroic.swords.market.view.request.domain.CancelListingRequest;
import com.morethanheroic.swords.response.service.ResponseFactory;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CancelListingController {

    private final CancelListingService cancelListingService;
    private final ResponseFactory responseFactory;

    @PostMapping("/market/listing/cancel")
    public Response cancelListing(final UserEntity userEntity, @RequestBody @Valid final CancelListingRequest cancelListingRequest) {
        cancelListingService.cancelListing(userEntity, cancelListingRequest.getMarketEntityId());

        return responseFactory.successfulResponse(userEntity);
    }
}
