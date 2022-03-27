package nodes.expression.binar;

import nodes.Node;
import nodes.expression.PatternConstants;

public class NodeDiffOf extends NodeBinaryExpression {
    public NodeDiffOf(Node leftSide, Node rightSide) {
        super(leftSide, rightSide);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.DIFF_OF);

    }
}
