package com.morethanheroic.swords.security;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Encode a password with a pre-defined salt and security level.
 */
@Service
public class PasswordEncoder {

    private static final int ENCODING_STRENGTH = 256;
    private static final String SALT = "#&@@>#&&#@#si_ip3rs33cr37sh13t#&@@>#&&#@#";

    private final ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(ENCODING_STRENGTH);

    public String encodePassword(String password) {
        return shaPasswordEncoder.encodePassword(password, SALT);
    }
}
