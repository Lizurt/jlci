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

    public List<Node> getChildes() {
        return childes;
    }

    public void addChild(Node child) {
        childes.add(child);
    }
}
