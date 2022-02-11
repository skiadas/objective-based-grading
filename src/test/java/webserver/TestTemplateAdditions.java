package webserver;

import org.junit.Before;
import org.junit.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import static org.junit.Assert.assertEquals;

public class TestTemplateAdditions {

    private TemplateEngine engine;
    private Context context;

    @Before
    public void setUp() {
        engine = new TemplateEngine();
        context = new Context();
    }

    @Test
    public void canStartEngine() {
        assertEquals("Hello",
                     engine.process("Hello", context));
    }

    @Test
    public void canProcessExpressions() {
        assertEquals("4",
                     engine.process("[[2+2]]", context));
    }

    @Test
    public void canCallCustomExpressionMethod() {
        engine.addDialect(new MyDialect());
        assertEquals("/", engine.process("[[${#path.index()}]]", context));
    }

}
