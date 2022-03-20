package nodes.expression.binar;

import nodes.Node;

public class NodeSumOf extends NodeBinaryExpression {
    public NodeSumOf(Node leftSide, Node rightSide) {
        super(leftSide, rightSide);
    }

    @Override
    public String toString() {
        return "SUM OF";
    }
}
