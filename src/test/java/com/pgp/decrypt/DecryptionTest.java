package com.pgp.decrypt;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DecryptionTest {
    CamelContext camelContext;

    @BeforeEach
    void setUp() {
        camelContext = new DefaultCamelContext();
    }

    @Test
    public void testDecryption() {
        final String privateKey = "file:M:\\PGP-decryptFile\\camel_certificates\\private.asc";
        final String in = "file:M:\\PGP-decryptFile\\pgp\\decrypt\\IN";
        final String out = "file:M:\\PGP-decryptFile\\pgp\\decrypt\\OUT";

        //In case of huge file size to avoid OutOfMemory Java heap exception -->
        camelContext.getStreamCachingStrategy().setSpoolThreshold(64 * 1024);
        camelContext.getStreamCachingStrategy().setBufferSize(16 * 1024);
        camelContext.setStreamCaching(true);

        try {
            camelContext.addRoutes(new RouteBuilder() {
                public void configure() {
                    from(in)
                            .unmarshal().pgp(privateKey, "", "123456789")
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