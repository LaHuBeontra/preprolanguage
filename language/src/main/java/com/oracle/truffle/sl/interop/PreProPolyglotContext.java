package com.oracle.truffle.sl.interop;

import com.oracle.truffle.sl.PreProLanguage;
import com.oracle.truffle.sl.runtime.PreProContext;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotAccess;

public final class PreProPolyglotContext {

    private static final String ID = PreProLanguage.ID;
    private final Context polyglotContext;
    private final PreProContext runtimeContext;

    public PreProPolyglotContext() {
        polyglotContext = Context.newBuilder().allowPolyglotAccess(PolyglotAccess.ALL).build();
        polyglotContext.initialize(ID);
        polyglotContext.enter();

        runtimeContext = PreProLanguage.getCurrentContext();
    }

    public PreProPolyglotContext exportSymbol(String symbolName, Object value) {
        runtimeContext.exportSymbol(symbolName, value);
        return this;
    }

    public PreProPolyglotResult execute(String preProProgram) {
        polyglotContext.eval(ID, preProProgram);
        polyglotContext.getBindings(ID).getMember("main").execute();
        return new PreProPolyglotResult();
    }

    public final void cleanup() {
        if (polyglotContext != null) {
            polyglotContext.leave();
            polyglotContext.close();
        }
    }

    public final class PreProPolyglotResult {

        private PreProPolyglotResult() {
        }

        public Object importSymbol(String symbolName) {
            return runtimeContext.importSymbol(symbolName);
        }
    }
}
