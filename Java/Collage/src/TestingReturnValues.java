public class TestingReturnValues {
    static boolean returning(int a, int b){
        if(a > b)
            return true;
        else if(a < b)
            return false;
        System.out.println("lol");
        return false;
    }

    public static void main(String[] args) {
        System.out.println(returning(5, 3));
    }
}
