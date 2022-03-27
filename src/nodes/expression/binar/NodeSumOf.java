package nodes.expression.binar;

import nodes.Node;
import nodes.expression.PatternConstants;

public class NodeSumOf extends NodeBinaryExpression {
    public NodeSumOf(Node leftSide, Node rightSide) {
        super(leftSide, rightSide);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.SUM_OF);
    }
}
