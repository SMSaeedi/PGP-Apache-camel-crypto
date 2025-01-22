package com.pgp.encrypt;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EncryptionTest {
    CamelContext camelContext;

    @BeforeEach
    void setUp() {
        camelContext = new DefaultCamelContext();
    }

    @Test
    public void testEncryption() {
        final String publicKey = "file:M:\\PGP-decryptFile\\camel_certificates\\public.asc";
        final String in = "file:M:\\PGP-decryptFile\\pgp\\encrypt\\IN";
        final String out = "file:M:\\PGP-decryptFile\\pgp\\encrypt\\OUT";

        try {
            camelContext.addRoutes(new RouteBuilder() {
                public void configure() {
                    from(in)
                            .marshal().pgp(publicKey, "")
                            .to(out);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            camelContext.start();
            Thread.sleep(1000);
            camelContext.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}