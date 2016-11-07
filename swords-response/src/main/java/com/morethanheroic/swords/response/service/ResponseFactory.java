package com.morethanheroic.swords.response.service;

import com.morethanheroic.response.domain.Response;
import com.morethanheroic.swords.response.domain.CharacterRefreshResponse;
import com.morethanheroic.swords.response.domain.StatusBasedPartialResponse;
import com.morethanheroic.swords.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Create new {@link CharacterRefreshResponse} objects based on the data provided in the {@link UserEntity}.
 */
@Service
@RequiredArgsConstructor
public class ResponseFactory {

    private static final boolean SUCCESSFUL_REQUEST = true;
    private static final boolean FAILED_REQUEST = false;

    private final CharacterDataFactory characterDataFactory;

    public CharacterRefreshResponse newResponse(UserEntity userEntity) {
        return new CharacterRefreshResponse(characterDataFactory.newInstance(userEntity));
    }

    public Response successfulResponse(final UserEntity userEntity) {
        final Response response = newResponse(userEntity);

        response.setData("result", StatusBasedPartialResponse.builder()
                .successful(SUCCESSFUL_REQUEST)
                .build()
        );

        return response;
    }

    public Response failedResponse(final UserEntity userEntity) {
        final Response response = newResponse(userEntity);

        response.setData("result", StatusBasedPartialResponse.builder()
                .successful(FAILED_REQUEST)
                .build()
        );

        return response;
    }

    /**
     * @deprecated use {@link #successfulResponse(UserEntity)} instead.
     */
    @Deprecated
    public CharacterRefreshResponse newSuccessfulResponse(UserEntity userEntity) {
        final CharacterRefreshResponse response = newResponse(userEntity);

        response.setData("success", SUCCESSFUL_REQUEST);

        return response;
    }
}
