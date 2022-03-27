package nodes.io;

import nodes.Node;
import parser.PatternConstants;

public class NodeYaRly extends Node {
    @Override
    public String toString() {
        return PatternConstants.astTreeSoutDictionary.get(PatternConstants.YA_RLY);
    }
}
