package nodes.expression.binar;

import nodes.Node;
import nodes.expression.PatternConstants;

public class NodeModOf extends NodeBinaryExpression {
    public NodeModOf(Node leftOperand, Node rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.MOD_OF);
    }
}
