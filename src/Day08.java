import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day08 {

    public static void main(String[] args) {

        int[][] array = new int[99][99];

        File file = new File("C:\\Users\\julian\\IdeaProjects\\advent2022\\src\\input08");
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line = null;
            int lineCounter = 0;
            while ((line = fileReader.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    array[lineCounter][i] = Integer.parseInt(String.valueOf(line.charAt(i)));
                }
                lineCounter++;
            }

            int height;
            int[] count = {0, 0, 0, 0};
            long scenicScore = 0;
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    height = array[i][j];
                    for (int k = i - 1; k >= 0; k--) {
                        count[0]++;
                        if (array[k][j] >= height) {
                            break;
                        }

                    }
                    for (int k = i + 1; k < 99; k++) {
                        count[1]++;
                        if (array[k][j] >= height) {
                            break;
                        }

                    }
                    for (int k = j - 1; k >= 0; k--) {
                        count[2]++;
                        if (array[i][k] >= height) {
                            break;
                        }

                    }
                    for (int k = j + 1; k < 99; k++) {
                        count[3]++;
                        if (array[i][k] >= height) {
                            break;
                        }

                    }


                    long mul = Arrays.stream(count).reduce(1, (a, b) -> a * b);

                    if (mul > scenicScore) {
                        scenicScore = mul;
                    }
                    count = new int[]{0,0,0,0};
                }
            }
            System.out.println(scenicScore);

        } catch (IOException e) {
            System.out.println(e);
        }


    }

}

/*
 for (int i = 1; i < array.length - 1; i++) {
                for (int j = 1; j < array[i].length - 1; j++) {
                    visible = false;
                    height = array[i][j];
                    //System.out.print(height);
                    for (int k = i - 1; k >= 0; k--) {
                        if (array[k][j] >= height) {
                            break;
                        }
                        if (k == 0) {
                            visible = true;
                        }
                    }
                    for (int k = i + 1; k < 99; k++) {
                        if (array[k][j] >= height) {
                            break;
                        }
                        if (k == 98) {
                            visible = true;
                        }
                    }
                    for (int k = j - 1; k >= 0; k--) {
                        if (array[i][k] >= height) {
                            break;
                        }
                        if (k == 0) {
                            visible = true;
                        }
                    }
                    for (int k = j + 1; k < 99; k++) {
                        if (array[i][k] >= height) {
                            break;
                        }
                        if (k == 98) {
                            visible = true;
                        }
                    }

                    if (visible == true) {
                        System.out.println(i+1 + " | " + (j+1) + " | " + array[i][j]);
                        count++;
                    }
                }
 */