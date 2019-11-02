import fr.teuteuf.ButtonColor;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static java.lang.String.format;

public class Agent2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Arrays.stream(ButtonColor.values()).forEach((firstColor) -> {
            Arrays.stream(ButtonColor.values()).forEach((secondColor) -> {
                System.err.println(scanner.nextLine());
                System.out.println(format("%s %s", secondColor, firstColor));
            });
        });
    }
}
