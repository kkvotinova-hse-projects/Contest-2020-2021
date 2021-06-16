import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main (String args[]) {
        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.nextLine();
        String text = scanner.nextLine();
        KnuthMorrisPrattAlgorithm(pattern, text);
    }

    public static int[] calculatePrefixFunction(String input) {
        int[] prefixFunction = new int[input.length()];
        int k = 0;
        for (int q = 1; q < input.length(); q++) {
            char target = input.charAt(q);
            while (k > 0 && input.charAt(k) != target) {
                k = prefixFunction[k - 1];
            }
            if (input.charAt(k) == target) {
                k++;
            }
            prefixFunction[q] = k;
        }
        return prefixFunction;
    }

    static void calculatePrefixTemplate(String pattern, int[] prefixTemplate) {
        int currentPrefixTemplate = 0;
        prefixTemplate[0] = 0;

        for (int i = 1; i < pattern.length();) {
            if (pattern.charAt(i) == pattern.charAt(currentPrefixTemplate)) {
                currentPrefixTemplate++;
                prefixTemplate[i] = currentPrefixTemplate;
                i++;
            }
            else {
                if( currentPrefixTemplate != 0 )
                    currentPrefixTemplate = prefixTemplate[currentPrefixTemplate - 1];
                else {
                    prefixTemplate[i] = 0;
                    i++;
                }
            }
        }
    }

    static void KnuthMorrisPrattAlgorithm(String pattern, String text) {
        List<Integer> matches = new ArrayList<>();

        int[] prefixTemplate = new int[pattern.length()];
        calculatePrefixTemplate(pattern, prefixTemplate);

        int indexText=0, indexPattern=0;
        while (indexText < text.length()) {
            if (pattern.charAt(indexPattern) == text.charAt(indexText)) {
                indexPattern++;
                indexText++;
            }

            if (indexPattern == pattern.length()) {
                matches.add(indexText-indexPattern);
                indexPattern = prefixTemplate[indexPattern - 1];
            }
            else if (pattern.charAt(indexPattern) != text.charAt(indexText)) {
                if (indexPattern != 0) {
                    indexPattern = prefixTemplate[indexPattern - 1];
                }
                else {
                    indexText++;
                }
            }
        }

        printResult(prefixTemplate, calculatePrefixFunction(pattern + "\0" + text), matches);
    }

    public static void printResult(int[] prefixTemplate, int[] prefixFunction, List<Integer> matches) {
        System.out.print("Prefix template: ");
        for (Integer value : prefixTemplate) {
            System.out.print(value + " ");
        }

        System.out.print("\nPrefix function: ");
        for (Integer value : prefixFunction) {
            System.out.print(value + " ");
        }

        System.out.print("\nMatches: ");
        for (Integer value : matches) {
            System.out.print(value + " ");
        }
    }
}
