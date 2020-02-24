package com.oracle.truffle.sl.test;

import com.oracle.truffle.sl.interop.PreProPolyglotContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class PreProAbstractTest {

    PreProPolyglotContext context;

    @BeforeEach
    void setUp() {
        context = new PreProPolyglotContext();
    }

    @AfterEach
    void tearDown() {
        context.cleanup();
    }
}
