package compiler;

import nodes.Node;
import nodes.NodeRoot;

public class SemanticAnalyser {
    public void checkAndFixASTTree(NodeRoot nodeRoot) {
        nodeRoot.setScope(new Scope(new ScopeManager()));
        for (Node child : nodeRoot.getChildes()) {
            child.setScope(nodeRoot.getScope());
            child.checkAndFixSemantic();
        }
    }
}
