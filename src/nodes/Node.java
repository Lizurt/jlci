package nodes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Node {
    private final List<Node> childes = new ArrayList<>();

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
        Iterator<Node> iterator = getChildes().iterator();
        while (iterator.hasNext()) {
            Node child = iterator.next();
            if (iterator.hasNext()) {
                child.toTreeishString(buffer, childPrefix + "├── ", childPrefix + "│   ");
            } else {
                child.toTreeishString(buffer, childPrefix + "└── ", childPrefix + "    ");
            }
        }
    }

    public List<Node> getChildes() {
        return childes;
    }

    public void addChild(Node child)
    {
        childes.add(child);
    }
}
