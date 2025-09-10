public class Circle {
    private int x;
    private int y;
    private double radius;
    private int res;
    public Circle(int x, int y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.res = x + y;
    }
    @Override
    public String toString() {
        return "Circle{" +
                "x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                ", res=" + res +
                '}';
    }
    public static void main(String[] args) {
        Circle circle = new Circle(5, 10, 15.0);
        System.out.println(circle);
    }
}