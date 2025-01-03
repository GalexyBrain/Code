package P2;

abstract class Shape{
    abstract double calculateArea();
    abstract double calculatePerimeter();
}

class Circle extends Shape{
    int r;
    Circle(int r){
        this.r = r;
    }

    @Override
    double calculateArea() {
        return Math.PI * r * r;
    }

    @Override
    double calculatePerimeter() {
        return 2 * Math.PI * r;
    }
}

class Triangle extends Shape{
    int s1, s2, s3;
    Triangle(int s1, int s2, int s3){
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    @Override
    double calculateArea() {
        double s = (double) (s1 + s2 + s3) / 2;
        return Math.sqrt(s * (s - s1) * (s - s2) * (s - s3));
    }

    @Override
    double calculatePerimeter() {
        return s1 + s2 + s3;
    }
}
