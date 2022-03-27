package nodes.expression.binar;

import nodes.Node;
import nodes.expression.PatternConstants;

public class NodeAnyOf extends NodeBinaryExpression {
    public NodeAnyOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.ANY_OF);
    }
}
