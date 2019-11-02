import fr.teuteuf.ButtonColor;
import fr.teuteuf.ColorCombination;

import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import static java.lang.String.format;

public class Agent1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Arrays.stream(ButtonColor.values()).forEach((firstColor) -> {
            Arrays.stream(ButtonColor.values()).forEach((secondColor) -> {
                System.err.println(scanner.nextLine());
                System.out.println(format("%s %s", firstColor, secondColor));
            });
        });
    }
}
