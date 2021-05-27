import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void RabinKarpAlgorithm(String pattern, String text, int q) {
        final int alphabetSize = 26;
        List<Integer> matches = new ArrayList<Integer>();
        List<Integer> spuriousHits = new ArrayList<Integer>();

        int patternSize = pattern.length();
        int textSize = text.length();
        int i, j;

        int patternHash = 0;
        int textHash = 0;
        int hash = 1;

        for (i = 0; i < patternSize - 1; i++)
            hash = (hash * alphabetSize) % q;

        for (i = 0; i < patternSize; i++) {
            patternHash = (alphabetSize * patternHash + pattern.charAt(i)) % q;
            textHash = (alphabetSize * textHash + text.charAt(i)) % q;
        }

        for (i = 0; i <= textSize - patternSize; i++) {
            if (patternHash == textHash) {
                for (j = 0; j < patternSize; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j))
                        break;
                }

                if (j == patternSize)
                    matches.add(i);
                else
                    spuriousHits.add(i);
            }

            if (i < textSize - patternSize) {
                textHash = (alphabetSize * (textHash - text.charAt(i) * hash) + text.charAt(i + patternSize)) % q;
                if (textHash < 0)
                    textHash = (textHash + q);
            }
        }
        printResult(matches, spuriousHits);
    }

    public static void printResult(List<Integer> matches, List<Integer> spuriousHits) {
        System.out.print("Matches: ");
        for (Integer value : matches) {
            System.out.print(value + " ");
        }

        System.out.print("\nSpurious hits: ");
        for (Integer value : spuriousHits) {
            System.out.print(value + " ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int q = Integer.parseInt(scanner.nextLine());
        String pattern = scanner.nextLine();
        String text = scanner.nextLine();
        RabinKarpAlgorithm(pattern, text, q);
    }
}
