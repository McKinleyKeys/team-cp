

package Maps;

import java.util.ArrayList;


public class Map {
    
    public static ArrayList mapInfo;
    public static ArrayList<int[]> obstacles;
    public static ArrayList<int[]> blueSpawn;
    public static ArrayList<int[]> redSpawn;
    
    public void init(ArrayList mapInfo, ArrayList<int[]> obstacles, ArrayList<int[]> blueSpawn, ArrayList<int[]> redSpawn) {
        
        this.mapInfo = mapInfo;
        this.obstacles = obstacles;
        this.blueSpawn = blueSpawn;
        this.redSpawn = redSpawn;
    }
    
    //returns sizeX, sizeY, pos1X, pos1Y
    public static ArrayList getMapInfo() {
        
        return mapInfo;
    }
    
    public static ArrayList<int[]> getObstacles() {
        
        return obstacles;
    }
    
    public static ArrayList<int[]> getBlueSpawn() {
        
        return blueSpawn;
    }
    
    public static ArrayList<int[]> getRedSpawn() {
        
        return redSpawn;
    }
}
