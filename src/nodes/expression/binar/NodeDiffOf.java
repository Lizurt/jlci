package nodes.expression.binar;

import nodes.Node;

public class NodeDiffOf extends NodeBinaryExpression {
    public NodeDiffOf(Node leftSide, Node rightSide) {
        super(leftSide, rightSide);
    }

    @Override
    public String toString() {
        return "DIFF OF";
    }
}
