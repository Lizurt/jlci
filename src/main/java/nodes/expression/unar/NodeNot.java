package nodes.expression.unar;

import nodes.Node;
import nodes.expression.NodeExpression;

public class NodeNot extends NodeUnaryExpression {
    public NodeNot(NodeExpression operand) {
        super(operand);
    }

    @Override
    public String toString() {
        return "NOT";
    }
}
