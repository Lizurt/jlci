package nodes.io;

import nodes.Node;
import nodes.expression.NodeExpression;
import parser.PatternConstants;

public class NodeVisible extends Node {
    private NodeExpression expression;

    public NodeVisible(NodeExpression expression) {
        this.expression = expression;
        addChild(expression);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.VISIBLE);
    }

    public NodeExpression getExpression() {
        return expression;
    }

    public void setExpression(NodeExpression expression) {
        this.expression = expression;
        getChildes().set(0, expression);
    }
}
