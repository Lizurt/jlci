package nodes.expression.binar;

import nodes.Node;

public class NodeProduktOf extends NodeBinaryExpression {
    public NodeProduktOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return "PRODUKT OF";
    }
}
