import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Day03 {

    public static void main(String[] args) {


        File file = new File("C:\\Users\\julian\\IdeaProjects\\advent2022\\src\\input03");
        String[] array = new String[3];
        int sum = 0;
        int lowerA = 96;
        int upperA = 64 - 26;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = fileReader.readLine()) != null) {


                array[0] = line;
                array[1] = fileReader.readLine();
                array[2] = fileReader.readLine();
                Arrays.sort(array, Comparator.comparingInt(String::length));
                for (int i = 0; i < array[0].length(); i++) {
                    String compare = String.valueOf(array[0].charAt(i));
                    if (array[1].contains(compare)) {
                        if (array[2].contains(compare)) {
                            System.out.println(compare);
                            sum += (array[0].charAt(i) - (array[0].charAt(i) > 90 ? lowerA : upperA));
                            break;
                        }
                    }
                }

            }

        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println(sum);
    }

}

/*
 String first = line.substring(0, line.length() / 2);
                String second = line.substring(line.length() / 2);
                System.out.print(first+ " | ");
                System.out.print(second);

                for (int i = 0; i < first.length(); i++) {
                    if (first.contains(String.valueOf(second.charAt(i)))) {
                        System.out.println(" -> "+second.charAt(i) + " = "+ (second.charAt(i) - (second.charAt(i) > 90 ? lowerA : upperA)));
                        sum += (second.charAt(i) - (second.charAt(i) > 90 ? lowerA : upperA));
                        break;
                    }
                }
 */
