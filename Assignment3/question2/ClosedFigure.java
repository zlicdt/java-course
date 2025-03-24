package question2;

public class ClosedFigure {
	private int nEdges;
    public ClosedFigure(int nEdges) {
        this.nEdges = nEdges;
    }
    // Getters
    public int getEdges() {
        return nEdges;
    }
    // Default implementation
    public double perimeter() {
        return 0.0; // Default implementation or throw an exception if this should be overridden
    }
}