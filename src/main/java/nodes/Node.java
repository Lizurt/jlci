package nodes;

import compiler.Scope;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Node {
    private final List<Node> childes = new ArrayList<>();
    private Scope scope;

    @Override
    public abstract String toString();

    public String toTreeishString() {
        StringBuilder buffer = new StringBuilder(50);
        toTreeishString(buffer, "", "");
        return buffer.toString();
    }

    private void toTreeishString(StringBuilder buffer, String selfPrefix, String childPrefix) {
        buffer.append(selfPrefix);
        buffer.append(toString());
        buffer.append('\n');
        List<Node> childes = getChildes();
        if (childes.size() == 0) {
            return;
        }
        for (int i = 0;; i++) {
            for (int j = i; j < childes.size(); j++) {
                if (childes.get(j) == null) {
                    i++;
                }
            }
            if (i >= childes.size()) {
                break;
            }
            if (i == childes.size() - 1 || childes.get(i + 1) == null) {
                childes.get(i).toTreeishString(buffer, childPrefix + "└── ", childPrefix + "    ");
                continue;
            }
            getChildes().get(i).toTreeishString(buffer, childPrefix + "├── ", childPrefix + "│   ");
        }
    }

    public abstract void compile(ClassWriter classWriter, MethodVisitor methodVisitor);

    public abstract void checkAndFixSemantic();

    public List<Node> getChildes() {
        return childes;
    }

    public void addChild(Node child) {
        childes.add(child);
    }

    public void removeChild(Node child) {
        childes.remove(child);
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(childes, node.childes) && Objects.equals(scope, node.scope);
    }

    @Override
    public int hashCode() {
        return Objects.hash(childes, scope);
    }
}
