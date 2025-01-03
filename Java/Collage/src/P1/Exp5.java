package P1;

class Shape{
    void draw(){
        System.out.println("Drawing shape");
    }

    void erase(){
        System.out.println("Erasing shape");
    }
}

class Circle extends Shape{
    void draw(){
        System.out.println("Drawing circle");
    }
    void erase(){
        System.out.println("Erasing circle");
    }
}

class Triangle extends Shape{
    void draw(){
        System.out.println("Drawing P1.Triangle");
    }
    void erase(){
        System.out.println("Erasing P1.Triangle");
    }
}

class Square extends Shape{
    void draw(){
        System.out.println("Drawing P1.Square");
    }
    void erase(){
        System.out.println("Erasing P1.Square");
    }
}

public class Exp5 {
    public static void main(String[] args) {
        Shape[] s = new Shape[3];
        s[0] = new Triangle();
        s[1] = new Circle();
        s[2] = new Square();

        for(Shape shape: s){
            shape.draw();
            shape.erase();
        }
    }
}
