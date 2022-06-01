import java.util.Scanner;

public class Main {

    static String word = "javalove";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // javalove --------
        System.out.println("Угадай слово");
        int lengthWord = word.length();
        String maskWord = "-".repeat(lengthWord);
        System.out.println(maskWord);

        // --------
        // a
        // -a-a----
        // o
        // -a-a-o--
        do {
            System.out.println("Введите буква");
            char c = input.next().charAt(0);
            // a 2
            // w -1
            if (word.indexOf(c) >= 0) {
                System.out.println("Удача");
                for (char elem : word.toCharArray()) {
                    if (elem == c) {
                        maskWord = replaceMaskLetter(c, maskWord);
                    }
                }
                System.out.println(maskWord);
            } else {
                System.out.println("Промах, поробуй еще раз");
                System.out.println(maskWord);
            }
        } while (maskWord.contains("-"));
        System.out.println("Поздравляем ты выйграл");
    }

    /*
    -a-a---- j
    ja-a----
     */
    public static String replaceMaskLetter(char c, String maskWord) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == c) {
                stringBuilder.append(c);
            } else if (maskWord.charAt(i) != '-') {
                stringBuilder.append(maskWord.charAt(i));
            } else {
                stringBuilder.append("-");
            }
        }
        return stringBuilder.toString();
    }
}
