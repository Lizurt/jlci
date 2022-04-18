package compiler;

import java.util.ArrayList;
import java.util.List;

public class Scope {
    private Scope parentScope;
    private ScopeManager scopeManager;
    private List<Identifier> variables = new ArrayList<>();
    private List<Identifier> functions = new ArrayList<>();
    private List<Identifier> labels = new ArrayList<>();

    public Scope(Scope parentScope, ScopeManager scopeManager) {
        this.parentScope = parentScope;
        this.scopeManager = scopeManager;
    }

    public Scope(Scope parentScope) {
        this.parentScope = parentScope;
        this.scopeManager = parentScope.getScopeManager();
    }

    public Scope(ScopeManager scopeManager) {
        this.scopeManager = scopeManager;
    }

    private Identifier tryGetIdentifierByName(String name, List<Identifier> identifiers) {
        for (Identifier identifier : identifiers) {
            if (identifier.getName().equals(name)) {
                return identifier;
            }
        }
        if (parentScope == null) {
            return null;
        }
        return parentScope.tryGetIdentifierByName(name, identifiers);
    }

    public Identifier tryGetVariableByName(String name) {
        return tryGetIdentifierByName(name, getVariables());
    }

    public Identifier tryGetFunctionByName(String name) {
        return tryGetIdentifierByName(name, getFunctions());
    }

    public Identifier tryGetLabelByName(String name) {
        return tryGetIdentifierByName(name, getLabels());
    }

    public void unsafelyAddVariableToScope(Identifier identifier) {
        variables.add(identifier);
    }

    public void unsafelyAddLabelToScope(Identifier identifier) {
        labels.add(identifier);
    }

    public void unsafelyAddFunctionToScope(Identifier identifier) {
        functions.add(identifier);
    }

    public Scope getParentScope() {
        return parentScope;
    }

    public void setParentScope(Scope parentScope) {
        this.parentScope = parentScope;
    }

    public ScopeManager getScopeManager() {
        return scopeManager;
    }

    public void setScopeManager(ScopeManager scopeManager) {
        this.scopeManager = scopeManager;
    }

    public List<Identifier> getVariables() {
        return variables;
    }

    public void setVariables(List<Identifier> variables) {
        this.variables = variables;
    }

    public List<Identifier> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Identifier> functions) {
        this.functions = functions;
    }

    public List<Identifier> getLabels() {
        return labels;
    }

    public void setLabels(List<Identifier> labels) {
        this.labels = labels;
    }
}
