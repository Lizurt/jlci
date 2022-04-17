package nodes.expression.binar;

import nodes.expression.NodeExpression;

import nodes.Node;
import parser.PatternConstants;

public class NodeEitherOf extends NodeBinaryExpression {
    public NodeEitherOf(NodeExpression leftOperand, NodeExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.EITHER_OF);
    }
}
