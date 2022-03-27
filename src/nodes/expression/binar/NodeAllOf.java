package nodes.expression.binar;

import nodes.Node;
import nodes.expression.PatternConstants;

public class NodeAllOf extends NodeBinaryExpression {
    public NodeAllOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.ALL_OF);
    }
}
