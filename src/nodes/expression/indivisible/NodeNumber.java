package nodes.expression.indivisible;

import nodes.expression.NodeExpression;

public class NodeNumber extends NodeExpression {
    private double value;

    public NodeNumber(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
