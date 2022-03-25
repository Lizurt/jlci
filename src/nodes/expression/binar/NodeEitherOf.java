package nodes.expression.binar;

import nodes.Node;

public class NodeEitherOf extends NodeBinaryExpression {
    public NodeEitherOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return "EITHER OF";
    }
}
