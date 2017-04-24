package com.morethanheroic.swords.user.view.response.domain;

import com.morethanheroic.response.domain.PartialResponse;
import lombok.Builder;
import lombok.Getter;

/**
 * Contains the result of a login.
 */
@Getter
@Builder
public class LoginResultPartialResponse extends PartialResponse {

    private final boolean successful;
}
