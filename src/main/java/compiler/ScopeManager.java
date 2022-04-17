package compiler;

public class ScopeManager {
    private int indexCounter = 0;

    public int getNextAvailableIndex() {
        return indexCounter++;
    }
}
