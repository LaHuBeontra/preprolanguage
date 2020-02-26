package com.oracle.truffle.sl;

import com.oracle.truffle.sl.runtime.PreProContext;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotAccess;
import org.graalvm.polyglot.Source;

import java.io.ByteArrayOutputStream;
import java.net.URL;

public final class PreProPolyglotContext {

    private static final String ID = PreProLanguage.ID;
    private Context languageContext;
    private PreProContext runtimeContext;

    public PreProPolyglotContext(ByteArrayOutputStream os) {
        languageContext = Context.newBuilder()
                .allowPolyglotAccess(PolyglotAccess.ALL)
                .out(null != os ? os : System.out).build();
        languageContext.initialize(ID);
        languageContext.enter();
        runtimeContext = PreProLanguage.getCurrentContext();
    }

    public PreProPolyglotContext exportSymbol(String symbolName, Object value) {
        runtimeContext.exportSymbol(symbolName, value);
        return this;
    }

    public PreProPolyglotResult eval(String literalPreProProgram) {
        languageContext.eval(ID, literalPreProProgram);
        return new PreProPolyglotResult();
    }

    public PreProPolyglotResult eval(URL preProFileUrl) {
        languageContext.eval(Source.newBuilder(ID, preProFileUrl).buildLiteral());
        return new PreProPolyglotResult();
    }

    public final void cleanup() {
        if (languageContext != null) {
            languageContext.leave();
            languageContext.close();
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
