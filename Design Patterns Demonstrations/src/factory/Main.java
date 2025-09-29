package factory;

// Factory pattern: create shapes based on type
interface Shape { void draw(); }
class Circle implements Shape { public void draw() { System.out.println("Drawing Circle"); } }
class Rectangle implements Shape { public void draw() { System.out.println("Drawing Rectangle"); } }

class ShapeFactory {
    public static Shape create(String type) {
        switch(type.toLowerCase()) {
            case "circle": return new Circle();
            case "rectangle": return new Rectangle();
            default: throw new IllegalArgumentException("Unknown shape: " + type);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Shape s1 = ShapeFactory.create("circle"); s1.draw();
        Shape s2 = ShapeFactory.create("rectangle"); s2.draw();
    }
}
