import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Day06 {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\julian\\IdeaProjects\\advent2022\\src\\input06");

        Queue<Character> queue = new LinkedList<>();

        int count = 0;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            for (char c : fileReader.readLine().toCharArray()) {

                queue.add(c);
                System.out.println(queue.stream().toList());
                if (queue.size() == 14) {

                    if (queue.stream().distinct().count() == 14) {
                        System.out.println(count+1);
                        break;
                    }

                    queue.poll();
                }
                count++;
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        //TODO auslesen
       // System.out.println(count);
    }
}
