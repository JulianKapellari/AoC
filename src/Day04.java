import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day04 {


    public static void main(String[] args) {


        File file = new File("C:\\Users\\julian\\IdeaProjects\\advent2022\\src\\input04");
        int count = 0;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = fileReader.readLine()) != null) {
                String[] pairs = line.split("[,-]");
                Range first = new Range(Integer.parseInt(pairs[0]), Integer.parseInt(pairs[1]));
                Range second = new Range(Integer.parseInt(pairs[2]), Integer.parseInt(pairs[3]));

               /* if(first.isConsumed(second) || second.isConsumed(first)){
                    System.out.println(Arrays.stream(pairs).toList().toString());
                    count++;
                }*/
                if(first.isOverlapped(second) || second.isOverlapped(first)){
                    System.out.println(Arrays.stream(pairs).toList().toString());
                    count++;
                }
            }
            System.out.println(count);

        } catch (IOException e) {
            System.out.println(e);
        }


    }
}

class Range{
    private int begin;
    private int end;

    public Range(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    public boolean isConsumed(Range range){
        return this.begin <= range.begin && this.end >= range.end;
    }

    public boolean isOverlapped(Range range){
        return ((range.end >= this.begin && range.end <= this.end) || (range.begin >= this.begin && range.begin <= this.end));
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
