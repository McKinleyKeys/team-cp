

package Game;

import GameObject.Unit;
import java.util.ArrayList;


public class Board {
    
    public static ArrayList<int[]> obstacles;
    public static int sizeX;
    public static int sizeY;
    public static int x1;
    public static int y1;
    
    public Board(ArrayList<int[]> obstacles, int sizeX, int sizeY, int x1, int y1) {
        
        this.obstacles = obstacles;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.x1 = x1;
        this.y1 = y1;
    }
    
    public static int checkSquare(int posX, int posY) {
        
        //0 = air, 1 = wall, 2 = health pad, 3 = capture point, 4 = blue spawn, 5 = red spawn
        
        for (int[] i : obstacles) {
            
            if (((posX >= i[0] && posX <= i[2]) || (posX <= i[0] && posX >= i[2])) && ((posY >= i[1] && posY <= i[3]) || (posY <= i[1] && posY >= i[3])))
                return i[4];
        }
        return 0;
    }
    
    public static Unit checkSquareUnit(int posX, int posY) {
        
        for (Unit unit : Game.aliveUnits) {
            
            if (unit.posX == posX && unit.posY == posY) {
                
                return unit;
            }
        }
        
        return null;
    }
}
