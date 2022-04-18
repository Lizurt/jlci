package nodes.io;

import nodes.Node;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import parser.PatternConstants;
import nodes.expression.indivisible.identifiers.NodeIdentifier;

public class NodeGimmeh extends Node {
    private NodeIdentifier identifier;

    public NodeGimmeh(NodeIdentifier identifier) {
        this.identifier = identifier;
        addChild(identifier);
    }

    public NodeIdentifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(NodeIdentifier identifier) {
        this.identifier = identifier;
        getChildes().set(0, identifier);
    }

    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.GIMMEH);
    }

    @Override
    public void compile(ClassWriter classWriter, MethodVisitor methodVisitor) {

    }

    @Override
    public void checkAndFixSemantic() {
        identifier.setScope(getScope());
        identifier.checkAndFixSemantic();
    }
}
