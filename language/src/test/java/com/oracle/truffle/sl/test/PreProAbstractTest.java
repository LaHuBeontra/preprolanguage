package com.oracle.truffle.sl.test;

import com.oracle.truffle.sl.PreProPolyglotContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

public abstract class PreProAbstractTest {

    PreProPolyglotContext context;
    private ByteArrayOutputStream os;

    @BeforeEach
    void setUp() {
        os = new ByteArrayOutputStream();
        context = new PreProPolyglotContext(os);
    }

    @AfterEach
    void tearDown() {
        context.cleanup();
    }

    /**
     * Converts a {@link ByteArrayOutputStream} content into UTF-8 String with UNIX line ends.
     */
    String toUnixString() {
        return os.toString(UTF_8).replace("\r\n", "\n");
    }
}
