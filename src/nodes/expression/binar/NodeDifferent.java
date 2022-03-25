package nodes.expression.binar;

import nodes.Node;

public class NodeDifferent extends NodeBinaryExpression {
    public NodeDifferent(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return "DIFFERENT OF";
    }
}
