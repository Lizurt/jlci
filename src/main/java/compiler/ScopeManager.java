package compiler;

public class ScopeManager {
    private int indexCounter = 8;

    public int getAndProceedAvailableIndex() {
        return indexCounter++;
    }

    public int findOrGenerateIndexForVariable(Scope scope, String name) {
        Identifier identifier = scope.tryGetVariableByName(name);
        if (identifier == null) {
            identifier = new Identifier(name, getAndProceedAvailableIndex());
            scope.unsafelyAddVariableToScope(identifier);
        }
        return identifier.getIndex();
    }

    public int findOrGenerateIndexForLabel(Scope scope, String name) {
        Identifier identifier = scope.tryGetLabelByName(name);
        if (identifier == null) {
            identifier = new Identifier(name, getAndProceedAvailableIndex());
        }
        return identifier.getIndex();
    }

    public int findOrGenerateIndexForFunction(Scope scope, String name) {
        Identifier identifier = scope.tryGetFunctionByName(name);
        if (identifier == null) {
            identifier = new Identifier(name, getAndProceedAvailableIndex());
        }
        return identifier.getIndex();
    }
}
