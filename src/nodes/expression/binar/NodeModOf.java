package nodes.expression.binar;

import nodes.Node;

public class NodeModOf extends NodeBinaryExpression {
    public NodeModOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return "MOD OF";
    }
}
