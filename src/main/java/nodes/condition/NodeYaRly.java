package nodes.condition;

import nodes.NodeStatements;
import parser.PatternConstants;

public class NodeYaRly extends NodeStatements {
    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.YA_RLY);
    }
}
