package nodes.expression.binar;

import nodes.Node;

public class NodeQuoshuntOf extends NodeBinaryExpression {
    public NodeQuoshuntOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return "QUOSHUNT OF";
    }
}
