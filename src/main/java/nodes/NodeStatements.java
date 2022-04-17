package nodes;

import compiler.Scope;

public class NodeStatements extends Node {
    @Override
    public String toString() {
        return "...";
    }

    @Override
    public void checkAndFixSemantic() {
        Scope scope = new Scope(getScope());
        for (Node statement : getChildes()) {
            statement.setScope(scope);
            statement.checkAndFixSemantic();
        }
    }
}
