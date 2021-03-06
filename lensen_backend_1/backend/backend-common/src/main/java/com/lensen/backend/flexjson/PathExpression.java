package com.lensen.backend.flexjson;

import java.util.Arrays;


/**
 * This is an internal class for Flexjson.  It's used to match on fields it encounters
 * while walking the object graph.  Every expression is expressed in dot notation like foo.bar.baz.  Each term
 * between the dots is a field name in that parent object.  All expressions are relative to some parent object
 * within the context in which they are used.  Typically it is the object you're serializing.  Expressions may
 * also contain wildcards like *.class.
 */
public class PathExpression {
    private String[] expression;
    private boolean wildcard = false;
    private boolean included = true;

    public PathExpression(String expr, boolean anInclude) {
        expression = expr.split("\\.");
        wildcard = expr.indexOf('*') >= 0;
        included = anInclude;
    }

    public String toString() {
        StringBuffer builder = new StringBuffer();
        builder.append("[");
        for (int i = 0; i < expression.length; i++) {
            builder.append(expression[i]);
            if (i < expression.length - 1) {
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    public boolean matches(Path path) {
        int exprCurrentIndex = 0;
        int pathCurrentIndex = 0;
        while (pathCurrentIndex < path.length()) {
            String current = (String) path.getPath().get(pathCurrentIndex);
            if (exprCurrentIndex < expression.length && expression[exprCurrentIndex].equals("*")) {
                exprCurrentIndex++;
            } else if (exprCurrentIndex < expression.length && expression[exprCurrentIndex].equals(current)) {
                pathCurrentIndex++;
                exprCurrentIndex++;
            } else if (exprCurrentIndex - 1 >= 0 && expression[exprCurrentIndex - 1].equals("*")) {
                pathCurrentIndex++;
            } else {
                return false;
            }
        }
        if (exprCurrentIndex > 0 && expression[exprCurrentIndex - 1].equals("*")) {
            return pathCurrentIndex >= path.length() && exprCurrentIndex >= expression.length;
        } else {
            return pathCurrentIndex >= path.length() && path.length() > 0;
        }
    }

    public boolean isWildcard() {
        return wildcard;
    }

    public boolean isIncluded() {
        return included;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PathExpression that = (PathExpression) o;

        if (!Arrays.equals(expression, that.expression)) return false;

        return true;
    }

    public int hashCode() {
        return (expression != null ? expression.hashCode() : 0);
    }
}
