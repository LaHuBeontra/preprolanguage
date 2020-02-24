package com.oracle.truffle.sl.test;

import com.oracle.truffle.sl.interop.PreProPolyglotContext;
import com.oracle.truffle.sl.runtime.types.PreProVector3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nd4j.linalg.factory.Nd4j;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Tests for Polyglot Execution of a .prepro file")
public class ExecutePreProFileTest extends PreProAbstractTest {

    @Test
    public void interopWorksSimple() {
        PreProVector3 p1 = new PreProVector3(Nd4j.create(new double[]{42, 0, 0, 1, 0, 0, 5, 0, 0, 6, 0, 0}, new int[]{4, 3}));
        PreProPolyglotContext.PreProPolyglotResult result =
                context.exportSymbol("p1", p1)
                        .execute(ClassLoader.getSystemResource("polyglotExecutionTest.prepro"));
        PreProVector3 returned = (PreProVector3) result.importSymbol("res");
        assertEquals(Nd4j.create(new double[]{1_764, 0, 0, 1, 0, 0, 25, 0, 0, 36, 0, 0}, new int[]{4, 3}), returned.timeSeries());
    }
}
