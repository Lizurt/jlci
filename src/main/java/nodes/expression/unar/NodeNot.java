package nodes.expression.unar;

import nodes.Node;

public class NodeNot extends NodeUnaryExpression {
    public NodeNot(Node operand) {
        super(operand);
    }

    @Override
    public String toString() {
        return "NOT";
    }
}
