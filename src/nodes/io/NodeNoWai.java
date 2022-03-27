package nodes.io;

import nodes.Node;
import nodes.expression.PatternConstants;

public class NodeNoWai extends Node {
    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.NO_WAI);
    }
}
