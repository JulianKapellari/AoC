import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day05 {

    public static List<List<Character>> list = new ArrayList<>();

    public static void main(String[] args) {

        for (int i = 0; i < 9; i++) {
            list.add(new LinkedList<>());
        }

        File file = new File("C:\\Users\\julian\\IdeaProjects\\advent2022\\src\\input05");

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line = null;

            while (!((line = fileReader.readLine()).isEmpty())){
                initializeStacks(line);
            }

            while ((line = fileReader.readLine()) != null) {
                manipulateStack(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        String answer = "";
        for (int i = 0; i < list.size(); i++) {
            answer+=list.get(i).stream().findFirst().get();
        }
        System.out.println(answer);

    }

    private static void initializeStacks(String line) {

        for (int i = 1; i < line.length(); i+=4) {
            if(Character.isAlphabetic(line.charAt(i))){
                list.get((i-1)/4).add(line.charAt(i));
            }
        }

    }

    private static void manipulateStack(String line) {
        List<Integer> lineElements;
        lineElements = Arrays.stream(line.split("move|from|to")).filter(element -> !element.isEmpty()).map(element -> Integer.parseInt(element.trim())).toList();

        List<Character> characterList = new ArrayList<>();
        for (int i = 0; i < lineElements.get(0); i++) {
            characterList.add(list.get(lineElements.get(1) - 1).remove(0));
        }
        for (int i = characterList.size()-1; i >= 0; i--) {
            list.get(lineElements.get(2)-1).add(0,characterList.get(i));
        }
    }

}

