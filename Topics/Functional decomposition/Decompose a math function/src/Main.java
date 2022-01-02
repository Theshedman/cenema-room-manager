import java.util.Scanner;

class MultipleFunction {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double x = scanner.nextDouble();
        System.out.println(f(x));
    }

    public static double f(double x) {
        double f;

        if (x <= 0) {
            f = f1(x);
        } else if (x >= 1) {
            f = f3(x);
        } else {
            f = f2(x);
        }

        return f;
    }

    public static double f1(double x) {
        double x2 = x * x;

        return x2 + 1;
    }

    public static double f2(double x) {
        double x2 = x * x;

        return 1 / x2;
    }

    public static double f3(double x) {
        double x2 = x * x;

        return x2 - 1;
    }
}
