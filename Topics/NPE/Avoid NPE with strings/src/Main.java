import java.util.Scanner;


public class Main {

    public static String concatStrings(String str1, String str2) {
        // concatenate the strings
        return str1 == null && str2 == null ? "" :
            str1 == null ? str2 : str2 == null ? str1 : str1.concat(str2);
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();
        str1 = "null".equalsIgnoreCase(str1) ? null : str1;
        str2 = "null".equalsIgnoreCase(str2) ? null : str2;

        System.out.println(concatStrings(str1, str2));
    }
}
