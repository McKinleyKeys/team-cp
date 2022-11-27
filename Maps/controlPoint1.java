

package Maps;

import java.util.ArrayList;
import java.util.Arrays;


public class controlPoint1 extends Map {
    
    //sizeX, sizeY, pos1X, pos1Y
    public static ArrayList mapInfo = new ArrayList(Arrays.asList(13, 25, 1, 1));
    public static int[] obstacle1 = {5, 1, 8, 2, 4};
    public static int[] obstacle2 = {5, 24, 8, 25, 5};
    public static int[] obstacle3 = {4, 4, 5, 4, 1};
    public static int[] obstacle4 = {7, 4, 8, 4, 1};
    public static int[] obstacle5 = {4, 4, 4, 5, 1};
    public static int[] obstacle6 = {4, 7, 8, 7, 1};
    public static int[] obstacle7 = {8, 4, 8, 7, 1};
    public static int[] obstacle8 = {10, 8, 10, 8, 1};
    public static int[] obstacle9 = {11, 7, 12, 7, 1};
    public static int[] obstacle10 = {10, 18, 10, 18, 1};
    public static int[] obstacle11 = {11, 19, 12, 19, 1};
    public static int[] obstacle12 = {4, 22, 5, 22, 1};
    public static int[] obstacle13 = {7, 22, 8, 22, 1};
    public static int[] obstacle14 = {4, 22, 4, 21, 1};
    public static int[] obstacle15 = {4, 19, 8, 19, 1};
    public static int[] obstacle16 = {8, 22, 8, 19, 1};
    public static int[] obstacle17 = {4, 11, 8, 11, 1};
    public static int[] obstacle18 = {4, 15, 8, 15, 1};
    public static int[] obstacle19 = {1, 11, 1, 15, 1};
    public static int[] obstacle20 = {2, 13, 2, 13, 1};
    public static int[] obstacle21 = {11, 10, 11, 16, 1};
    public static int[] obstacle22 = {13, 11, 13, 11, 1};
    public static int[] obstacle23 = {13, 15, 13, 15, 1};
    public static int[] decoration1 = {1, 3, 1, 4, 0};
    public static int[] decoration2 = {12, 3, 12, 3, 1};
    public static int[] decoration3 = {13, 4, 13, 4, 1};
    public static int[] decoration4 = {1, 23, 1, 22, 0};
    public static int[] decoration5 = {13, 22, 13, 22, 1};
    public static int[] decoration6 = {12, 23, 12, 23, 1};
    public static int[] controlPoint1 = {5, 12, 7, 14, 3};
    public static int[] healthPad1 = {13, 12, 13, 12, 2};
    public static int[] healthPad2 = {13, 14, 13, 14, 2};
    public static ArrayList<int[]> obstacles = new ArrayList<int[]>(Arrays.asList(obstacle1, obstacle2, obstacle3, obstacle4, obstacle5, obstacle6, obstacle7, obstacle8, obstacle9, obstacle10, obstacle11, obstacle12, obstacle13, obstacle14, obstacle15, obstacle16, obstacle17, obstacle18, obstacle19, obstacle20, obstacle21, obstacle22, obstacle23, decoration1, decoration2, decoration3, decoration4, decoration5, decoration6, controlPoint1, healthPad1, healthPad2));
    public static int[] blueArcherSpawn = {5, 2};
    public static int[] blueBuilderSpawn = {6, 2};
    public static int[] blueHealerSpawn = {7, 2};
    public static int[] blueKnightSpawn = {8, 2};
    public static int[] blueMageSpawn = {5, 1};
    public static int[] blueScoutSpawn = {6, 1};
    public static int[] blueSpySpawn = {7, 1};
    public static int[] blueTNTGuySpawn = {8, 1};
    public static ArrayList<int[]> blueSpawn = new ArrayList<int[]>(Arrays.asList(blueArcherSpawn, blueBuilderSpawn, blueHealerSpawn, blueKnightSpawn, blueMageSpawn, blueScoutSpawn, blueSpySpawn, blueTNTGuySpawn));
    public static int[] redArcherSpawn = {5, 24};
    public static int[] redBuilderSpawn = {6, 24};
    public static int[] redHealerSpawn = {7, 24};
    public static int[] redKnightSpawn = {8, 24};
    public static int[] redMageSpawn = {5, 25};
    public static int[] redScoutSpawn = {6, 25};
    public static int[] redSpySpawn = {7, 25};
    public static int[] redTNTGuySpawn = {8, 25};
    public static ArrayList<int[]> redSpawn = new ArrayList<int[]>(Arrays.asList(redArcherSpawn, redBuilderSpawn, redHealerSpawn, redKnightSpawn, redMageSpawn, redScoutSpawn, redSpySpawn, redTNTGuySpawn));
    
    public controlPoint1() {
        
        init(mapInfo, obstacles, blueSpawn, redSpawn);
    }
}
