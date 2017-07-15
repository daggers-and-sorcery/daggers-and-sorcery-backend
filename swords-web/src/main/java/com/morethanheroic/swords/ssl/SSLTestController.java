package com.morethanheroic.swords.ssl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SSLTestController {

    private static final String SSL_TEST_KEY = "Wx4K4052A9rv3gKVZ2ZbLkJ8zjNvdWtYdy5-ZAiQpz8.DhlSGalxK13yIkzU6ASQdAKebooLmKK1OOE983uvcRo";

    @GetMapping("/.well-known/acme-challenge/Wx4K4052A9rv3gKVZ2ZbLkJ8zjNvdWtYdy5-ZAiQpz8")
    @ResponseBody
    public String sslTestString() {
        return SSL_TEST_KEY;
    }
}
