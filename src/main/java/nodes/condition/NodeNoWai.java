package nodes.condition;

import nodes.NodeStatements;
import parser.PatternConstants;

public class NodeNoWai extends NodeStatements {
    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.NO_WAI);
    }
}
