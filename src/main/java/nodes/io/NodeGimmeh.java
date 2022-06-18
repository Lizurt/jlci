package nodes.io;

import compiler.Compiler;
import nodes.Node;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import parser.PatternConstants;
import nodes.expression.indivisible.identifiers.NodeIdentifier;

import java.util.Scanner;

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
        methodVisitor.visitVarInsn(Opcodes.ALOAD, Compiler.ID_SCANNER);
        methodVisitor.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                Scanner.class.getName().replace('.', '/'),
                "nextFloat",
                "()F",
                false
        );
        methodVisitor.visitVarInsn(
                Opcodes.FSTORE,
                identifier.getScope().tryGetVariableByName(identifier.getName()).getIndex()
        );
    }

    @Override
    public void checkAndFixSemantic() {
        identifier.setScope(getScope());
        identifier.checkAndFixSemantic();
    }
}
