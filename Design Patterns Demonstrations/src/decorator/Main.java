package decorator;

// Decorator pattern: Coffee with add-ons
interface Coffee { double cost(); String desc(); }
class SimpleCoffee implements Coffee { public double cost(){ return 5.0; } public String desc(){ return "Simple coffee"; } }
abstract class CoffeeDecorator implements Coffee {
    protected final Coffee coffee;
    CoffeeDecorator(Coffee c){ this.coffee = c; }
    public double cost(){ return coffee.cost(); }
    public String desc(){ return coffee.desc(); }
}
class Milk extends CoffeeDecorator {
    Milk(Coffee c){ super(c); }
    public double cost(){ return super.cost() + 1.0; }
    public String desc(){ return super.desc() + ", milk"; }
}
class Sugar extends CoffeeDecorator {
    Sugar(Coffee c){ super(c); }
    public double cost(){ return super.cost() + 0.5; }
    public String desc(){ return super.desc() + ", sugar"; }
}
public class Main {
    public static void main(String[] args) {
        Coffee c = new SimpleCoffee();
        c = new Milk(c);
        c = new Sugar(c);
        System.out.println(c.desc() + " -> cost: " + c.cost());
    }
}
