package nodes.io;

import nodes.Node;
import parser.PatternConstants;

public class NodeNoWai extends Node {
    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.NO_WAI);
    }
}
