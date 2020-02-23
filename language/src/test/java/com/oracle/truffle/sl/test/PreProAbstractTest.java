package com.oracle.truffle.sl.test;

import com.oracle.truffle.sl.interop.PreProPolyglotContext;

public abstract class PreProAbstractTest {
    protected PreProPolyglotContext context;

    protected final void setup() {
        context = new PreProPolyglotContext();
    }

    protected final void cleanup() {
        context.cleanup();
    }
}
