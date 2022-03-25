package nodes.expression.binar;

import nodes.Node;

public class NodeBothSaem extends NodeBinaryExpression {
    public NodeBothSaem(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return "BOTH SEAM OF";
    }
}
