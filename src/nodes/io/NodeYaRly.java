package nodes.io;

import nodes.Node;
import nodes.expression.NodeExpression;
import nodes.expression.PatternConstants;

public class NodeYaRly extends Node {
    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.YA_RLY);
    }
}
