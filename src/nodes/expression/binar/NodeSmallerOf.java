package nodes.expression.binar;

import nodes.Node;
import nodes.expression.PatternConstants;

public class NodeSmallerOf extends NodeBinaryExpression {
    public NodeSmallerOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.SMALLER_OF);
    }
}
