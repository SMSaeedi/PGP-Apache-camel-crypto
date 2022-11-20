package com.example.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PGPApplicationTest {

    @Test
    public void testEncryption() throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        final String publicKey = "file:M:\\PGP-decryptFile\\camel_certificates\\public.asc";
        final String in = "file:M:\\PGP-decryptFile\\pgp\\encrypt\\IN";
        final String out = "file:M:\\PGP-decryptFile\\pgp\\encrypt\\OUT";

        camelContext.addRoutes(new RouteBuilder() {
            public void configure() {
                from(in)
                        .marshal().pgp(publicKey, "")
                        .to(out);
            }
        });
        camelContext.start();
        Thread.sleep(5000);
        camelContext.stop();
    }

    @Test
    public void testDecryption() throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        final String privateKey = "file:M:\\PGP-decryptFile\\camel_certificates\\private.asc";
        final String in = "file:M:\\PGP-decryptFile\\pgp\\decrypt\\IN";
        final String out = "file:M:\\PGP-decryptFile\\pgp\\decrypt\\OUT";

        //In case of huge file size to avoid OutOfMemory Java heap exception -->
        camelContext.getStreamCachingStrategy().setSpoolThreshold(64 * 1024);
        camelContext.getStreamCachingStrategy().setBufferSize(16 * 1024);
        camelContext.setStreamCaching(true);

        /*or on routes:
        from("file:inbox")
                .streamCaching()
                .to("bean:foo");*/
        // <--

        camelContext.addRoutes(new RouteBuilder() {
            public void configure() {
                from(in)
                        .unmarshal().pgp(privateKey, "", "123456789")
                        .to(out);
            }
        });
        camelContext.start();
        Thread.sleep(5000);
        camelContext.stop();
    }
}