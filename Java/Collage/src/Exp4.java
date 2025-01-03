class MyPoint{
    int x;
    int y;
    MyPoint(){
        this.x = 0;
        this.y = 0;
    }

    MyPoint(int x, int y){
        this.x = x;
        this.y = y;
    }

    void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    int[] getXY(){
        return new int[]{x, y};
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    double distance(int x, int y){
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

    double distance(){
        return distance(0, 0);
    }

    double distance(MyPoint other){
        return distance(other.x, other.y);
    }
}

public class Exp4 {
    public static void main(String[] args) {
        MyPoint p1 = new MyPoint();
        MyPoint p2 = new MyPoint(5, 7);
        System.out.println("Point 1 : " + p1);
        p1.setXY(4, 8);
        System.out.println("Point 1 after setXY() : " + p1.getXY()[0] + ", " + p1.getXY()[1]);
        System.out.println("Distance of Point 1 from Point 2 : " + p1.distance(p2));
        System.out.println("Distance of Point 2 from origin : " + p2.distance());
    }
}
