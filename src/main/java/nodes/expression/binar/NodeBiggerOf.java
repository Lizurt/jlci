package nodes.expression.binar;

import nodes.expression.NodeExpression;

import nodes.Node;
import nodes.expression.NodeExpression;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import parser.PatternConstants;

public class NodeBiggerOf extends NodeBinaryExpression {
    public NodeBiggerOf(NodeExpression leftOperand, NodeExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.BIGGER_OF);
    }
}
