package webserver;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

import java.util.Set;

class MyDialect implements IExpressionObjectDialect {
    public String getName() {
        return "pathDialect";
    }

    public IExpressionObjectFactory getExpressionObjectFactory() {
        return new MyExpressionObjectFactory();
    }

    private static class MyExpressionObjectFactory implements IExpressionObjectFactory {
        public Set<String> getAllExpressionObjectNames() {
            return Set.of("path");
        }

        public Object buildObject(IExpressionContext context, String expressionObjectName) {
            return new Path();
        }

        public boolean isCacheable(String expressionObjectName) {
            return true;
        }
    }
}
