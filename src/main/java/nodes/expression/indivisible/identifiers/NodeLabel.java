package nodes.expression.indivisible.identifiers;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.util.InputMismatchException;

public class NodeLabel extends NodeIdentifier {
    public NodeLabel(String name) {
        super(name);
    }

    @Override
    public void semanticCheckIfExists() {
        if (getScope().tryGetLabelByName(getName()) != null) {
            return;
        }

        throw new InputMismatchException("Unknown label: \"" + getName() + "\".");
    }

    @Override
    public void semanticCheckIfNotExists() {
        if (getScope().tryGetLabelByName(getName()) == null) {
            return;
        }

        throw new InputMismatchException("A label \"" + getName() + "\" is already defined in the scope.");
    }

    @Override
    public void compile(ClassWriter classWriter, MethodVisitor methodVisitor) {

    }
}
