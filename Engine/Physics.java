

package Engine;

import Game.Board;
import java.util.ArrayList;
import GameObject.Unit;


public class Physics {
    
    public static int[] scanPath = {0, 0, 0};
    public static int tempPosX;
    public static int tempPosY;
    public static boolean path = false;
    public static int distX;
    public static int distY;
    public static int absDistX;
    public static int absDistY;
    public static int dirX;
    public static int dirY;
    public static double slope;
    public static int[] lineLastSquare = {0, 0};
    public static int[] lineNextSquare = {0, 0};
    public static boolean moveDiagonal;
    public static ArrayList testSquares = new ArrayList();
    public static double targetDirection;
    public static ArrayList checkRangeReturnValue = new ArrayList();
    
    public static int checkPath(int startPosX, int startPosY, int endPosX, int endPosY, int movement, String teamName, boolean invis) {
        
        if (movement <= 0)
            return 0;
        
        scanPath[0] = 0;
        scanPath[1] = 0;
        scanPath[2] = 0;
        
        path = false;
        
        while (true) {
            
            tempPosX = startPosX;
            tempPosY = startPosY;
            
            for (int i = 0; i <= movement - 1; i++) {
                
                if (scanPath[i] == 0)
                    tempPosY++;
                else if (scanPath[i] == 1)
                    tempPosX++;
                else if (scanPath[i] == 2)
                    tempPosY--;
                else if (scanPath[i] == 3)
                    tempPosX--;
                
                if (tempPosX == endPosX && tempPosY == endPosY)
                    path = true;
                
                if (Board.checkSquare(tempPosX, tempPosY) == 1) {
                    
                    path = false;
                    break;
                }
                
                //off board
                if (tempPosX < Board.x1 || tempPosX > Board.x1 + Board.sizeX - 1 || tempPosY < Board.y1 || tempPosY > Board.y1 + Board.sizeY - 1) {
                    
                    path = false;
                    break;
                }
                
                //check for collision with units
                if (Board.checkSquareUnit(tempPosX, tempPosY) instanceof Unit) {
                    
                    if (Board.checkSquareUnit(tempPosX, tempPosY).teamName != teamName && Board.checkSquareUnit(tempPosX, tempPosY).invis == false && !invis) {
                        
                        //cannot move through enemy units
                        path = false;
                        break;
                    }
                    
                    else
                        //can move through, but not stop on a friendly unit
                        path = false;
                }
                
                //System.out.println(tempPosX + ", " + tempPosY);
                
                if (path == true)
                    return i + 1;
            }
            
            scanPath[0]++;
            if (scanPath[0] >= 4) {
                
                scanPath[1]++;
            }
            if (scanPath[1] >= 4) {
                
                scanPath[2]++;
            }
            if (scanPath[2] >= 4)
                return 0;
            
            //checks if all possible combos have been tried
            if (scanPath[movement - 1] >= 4)
                return 0;
            
            for (int i = 0; i < scanPath.length; i++) {
                
                if (scanPath[i] >= 4)
                    scanPath[i] = 0;
            }
            
            //System.out.println(scanPath[0] + " " + scanPath[1] + " " + scanPath[2]);
        }    
    }
    
    public static ArrayList checkRange(int x1, int y1, int x2, int y2, double FOV, double direction, String teamName, boolean findUnit) {
        
        checkRangeReturnValue.clear();
        distX = x2 - x1;
        distY = y2 - y1;
        absDistX = Math.abs(distX);
        absDistY = Math.abs(distY);
        slope = (double)distY / (double)distX;
        if (distX == 0)
            dirX = 0;
        else
            dirX = distX / absDistX;
        if (distY == 0)
            dirY = 0;
        else
            dirY = distY / absDistY;
        lineLastSquare[0] = x1;
        lineLastSquare[1] = y1;
        //if the line is not diagonal, moveDiagonal = true makes it move diagonal 1 space
        moveDiagonal = false;
        
        //get rid of this after attacking has been tested
        //check if target is in FOV
//        targetDirection = Math.toDegrees(Math.atan2(Math.abs((double)distY), Math.abs((double)distX)));
//        //convert to true bearing
//        if (distX == absDistX && distY == absDistY)
//            targetDirection = 90 - targetDirection;
//        else if (distX == absDistX && distY != absDistY)
//            targetDirection += 90;
//        else if (distX != absDistX && distY != absDistY)
//            targetDirection = 90 - targetDirection + 180;
//        else if (distX != absDistX && distY == absDistY)
//            targetDirection += 270;
//        //compare to FOV
//        if (!(targetDirection <= direction + FOV/2 && targetDirection >= direction - FOV/2) && !(targetDirection <= direction + 360 + FOV/2 && targetDirection >= direction + 360 - FOV/2) && !(targetDirection <= direction - 360 + FOV/2 && targetDirection >= direction - 360 - FOV/2)) {
//            
//            checkRangeReturnValue.add(-1d);
//            return checkRangeReturnValue;
//        }
        
        if (checkFOV(distX, distY, FOV, direction) == false) {
            
            checkRangeReturnValue.add(-1d);
            return checkRangeReturnValue;
        }
        
        //check if sightline is not blocked
        //check if line is vertical
        if (dirX == 0) {
            
            for (int a = y1; a <= y1 + absDistY; a++) {
                
                //x1 + dirX * (-x1 + var) moves line back to original quadrant
                if (Board.checkSquare(x1, y1 + dirY * (-y1 + a)) == 1) {
                    
                    //System.out.println("obstacle");
                    checkRangeReturnValue.add(0d);
                    return checkRangeReturnValue;
                }
                
                //check if it has gone off the board
                if (x1 < Board.x1 || x1 > Board.x1 + Board.sizeX - 1 || y1 + dirY * (-y1 + a) < Board.y1 || y1 + dirY * (-y1 + a) > Board.y1 + Board.sizeY - 1) {
                    
                    //System.out.println("off board");
                    checkRangeReturnValue.add(0d);
                    return checkRangeReturnValue;
                }
                
                if (Board.checkSquareUnit(x1, y1 + dirY * (-y1 + a)) instanceof Unit && findUnit) {
                    
                    if (Board.checkSquareUnit(x1, y1 + dirY * (-y1 + a)).teamName != teamName) {
                        
                        checkRangeReturnValue.add(Math.sqrt(Math.pow(a - y1, 2)));
                        checkRangeReturnValue.add(x1);
                        checkRangeReturnValue.add(y1 + dirY * (-y1 + a));
                        return checkRangeReturnValue;
                    }
//CHANGE?               can friendly units shoot through each other? Right now they can't
                    
                    //make sure that friendly unit is not the original unit
                    else if (a != y1) {
                        
                        //System.out.println("friendly");
                        checkRangeReturnValue.add(0d);
                        return checkRangeReturnValue;
                    }
                }
            }
            
            if (findUnit)
                checkRangeReturnValue.add(0d);
            else
                checkRangeReturnValue.add(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
            return checkRangeReturnValue;
        }
        
        //if line is horizontal
        if (dirY == 0) {
            
            
            for (int a = x1; a <= x1 + absDistX; a++) {
                
                //x1 + dirX(-x1 + var) moves line back to original quadrant
                if (Board.checkSquare(x1 + dirX * (-x1 + a), y1) == 1) {
                    
                    //System.out.println("obstacle");
                    checkRangeReturnValue.add(0d);
                    return checkRangeReturnValue;
                }
                
                //check if it has gone off the board
                if (y1 < Board.y1 || y1 > Board.y1 + Board.sizeY - 1 || x1 + dirX * (-x1 + a) < Board.x1 || x1 + dirX * (-x1 + a) > Board.x1 + Board.sizeX - 1) {
                    
                    //System.out.println("off board");
                    checkRangeReturnValue.add(0d);
                    return checkRangeReturnValue;
                }
                
                if (Board.checkSquareUnit(x1 + dirX * (-x1 + a), y1) instanceof Unit && findUnit) {
                    
                    if (Board.checkSquareUnit(x1 + dirX * (-x1 + a), y1).teamName != teamName) {
                        
                        checkRangeReturnValue.add(Math.sqrt(Math.pow(a - x1, 2)));
                        checkRangeReturnValue.add(x1 + dirX * (-x1 + a));
                        checkRangeReturnValue.add(y1);
                        return checkRangeReturnValue;
                    }
//CHANGE?               can friendly units shoot through each other? Right now they can't
                    
                    //make sure that friendly unit is not the original unit
                    else if (a != x1) {
                        
                        //System.out.println("friendly");
                        checkRangeReturnValue.add(0d);
                        return checkRangeReturnValue;
                    }
                }
            }
            
            if (findUnit)
                checkRangeReturnValue.add(0d);
            else
                checkRangeReturnValue.add(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
            return checkRangeReturnValue;
        }
        
        //check if line is diagonal
        if (Math.abs(slope) == 1) {
            
            for (int i = 0; i <= absDistX; i++) {
                
                //System.out.println("i: " + i);
                //System.out.println("Square: " + (x1 + i * dirX) + " " + (y1 + i * dirY));
                if (Board.checkSquare(x1 + i * dirX, y1 + i * dirY) == 1) {
                    
                    //System.out.println("obstacle");
                    checkRangeReturnValue.add(0d);
                    return checkRangeReturnValue;
                }
                
                //check if it has gone off the board
                if (x1 + i * dirX < Board.x1 || x1 + i * dirX > Board.x1 + Board.sizeX - 1 || y1 + i * dirY < Board.y1 || y1 + i * dirY > Board.y1 + Board.sizeY - 1) {
                    
                    //System.out.println("off board");
                    checkRangeReturnValue.add(0d);
                    return checkRangeReturnValue;
                }
                
                if (Board.checkSquare(x1 + i * dirX, y1 + i * dirY + 1 * dirY) == 1 && Board.checkSquare(x1 + i * dirX + 1 * dirX, y1 + i * dirY) == 1 && !(x1 + i * dirX == x2 && y1 + i * dirY == y2)) {
                    
                    //System.out.println("diag");
                    checkRangeReturnValue.add(0d);
                    return checkRangeReturnValue;
                }
                
                if (Board.checkSquareUnit(x1 + i * dirX, y1 + i * dirY) instanceof Unit && findUnit) {
                    
                    
                    if (Board.checkSquareUnit(x1 + i * dirX, y1 + i * dirY).teamName != teamName) {
                        
                        //add distance
                        checkRangeReturnValue.add(Math.sqrt(2 * i * i));
                        //add coords
                        checkRangeReturnValue.add(x1 + i * dirX);
                        checkRangeReturnValue.add(y1 + i * dirY);
                        return checkRangeReturnValue;
                    }
                    
                    else if (i != 0) {
                        
                        checkRangeReturnValue.add(0d);
                        return checkRangeReturnValue;
                    }
                }
            }
            
            if (findUnit)
                checkRangeReturnValue.add(0d);
            else
                checkRangeReturnValue.add(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
            return checkRangeReturnValue;
        }
        
        //if line is not diagonal
        //move line to positive x positive y quadrant. change back later
        for (int i = 0; i <= absDistX; i++) {
            
            moveDiagonal = false;
            
            //first half of first square
            if (i == 0) {
                
                lineNextSquare[0] = x1;
                lineNextSquare[1] = (int)Math.floor(0.5 * Math.abs(slope) + 0.4999 + y1);
                //check if line is at a fourway intersection and needs to move diagonally
                if ((int)Math.floor(0.5 * Math.abs(slope) + 0.5 + y1) != lineNextSquare[1])
                    moveDiagonal = true;
                //System.out.println("first move: " + (x1 + dirX * (-x1 + lineNextSquare[0])) + " " + (y1 + dirY * (-y1 + lineNextSquare[1])));
            }
            
            //last square
            else if (x1 + i == x1 + Math.abs(distX)) {
                
                lineNextSquare[0] = x1 + absDistX;
                lineNextSquare[1] = y1 + absDistY;
                //System.out.println("last move: " + (x1 + dirX * (-x1 + lineNextSquare[0])) + " " + (y1 + dirY * (-y1 + lineNextSquare[1])));
            }
            
            //all inbetween squares.
            else {
                
                lineNextSquare[0] = lineLastSquare[0];
                lineNextSquare[1] = (int)Math.floor((lineNextSquare[0] - x1 + 0.5) * Math.abs(slope) + 0.4999 + y1);
                //check if line is at a fourway intersection and needs to move diagonally
                if ((int)Math.floor((lineNextSquare[0] - x1 + 0.5) * Math.abs(slope) + 0.5 + y1) != lineNextSquare[1])
                    moveDiagonal = true;
                //System.out.println("normal: " + (x1 + dirX * (-x1 + lineNextSquare[0])) + " " + (y1 + dirY * (-y1 + lineNextSquare[1])));
            }
            
            //check each square
            for (int a = lineLastSquare[1]; a <= lineNextSquare[1]; a++) {
                
                //System.out.print("Check: " + (x1 + dirX * (-x1 + lineNextSquare[0])) + " " + (y1 + dirY * (-y1 + a)) + ", ");
                //x1 + dirX(x1 - var) moves line back to original quadrant
                if (Board.checkSquare(x1 + dirX * (-x1 + lineNextSquare[0]), y1 + dirY * (-y1 + a)) == 1) {
                    
                    //System.out.println("obstacle");
                    checkRangeReturnValue.add(0d);
                    return checkRangeReturnValue;
                }
                
                //check if it has gone off the board
                if (x1 + dirX * (-x1 + lineNextSquare[0]) < Board.x1 || x1 + dirX * (-x1 + lineNextSquare[0]) > Board.x1 + Board.sizeX - 1 || y1 + dirY * (-y1 + a) < Board.y1 || y1 + dirY * (-y1 + a) > Board.y1 + Board.sizeY - 1) {
                    
                    //System.out.println("off board");
                    checkRangeReturnValue.add(0d);
                    return checkRangeReturnValue;
                }
                
                if (Board.checkSquareUnit(x1 + dirX * (-x1 + lineNextSquare[0]), y1 + dirY * (-y1 + a)) instanceof Unit && findUnit) {
                    
                    if (Board.checkSquareUnit(x1 + dirX * (-x1 + lineNextSquare[0]), y1 + dirY * (-y1 + a)).teamName != teamName) {
                        
                        checkRangeReturnValue.add(Math.sqrt(Math.pow(a - y1, 2) + Math.pow(lineNextSquare[0] - x1, 2)));
                        checkRangeReturnValue.add(x1 + dirX * (-x1 + lineNextSquare[0]));
                        checkRangeReturnValue.add(y1 + dirY * (-y1 + a));
                        return checkRangeReturnValue;
                    }
//CHANGE?               can friendly units shoot through each other? Right now they can't
                    
                    //make sure that friendly unit is not the original unit
                    else if (lineNextSquare[0] != x1 && a != y1) {
                        
                        //System.out.println("friendly");
                        checkRangeReturnValue.add(0d);
                        return checkRangeReturnValue;
                    }
                }
            }
            
            //check if line is trying to move through a blocked corner
            if (moveDiagonal == true)
                if (Board.checkSquare(x1 + dirX * (-x1 + lineNextSquare[0]), y1 + dirY * (-y1 + lineNextSquare[1] + 1)) == 1 && Board.checkSquare(x1 + dirX * (-x1 + lineNextSquare[0] + 1), y1 + dirY * (-y1 + lineNextSquare[1])) == 1) {
                    
                    //System.out.println("obstacle");
                    checkRangeReturnValue.add(0d);
                    return checkRangeReturnValue;
                }
            
            lineLastSquare[0] = lineNextSquare[0] + 1;
            lineLastSquare[1] = lineNextSquare[1];
            //if line needs to move diagonally 1 space
            if (moveDiagonal == true)
                lineLastSquare[1]++;
        }
        
        //System.out.println("nothing");
        if (findUnit)
            checkRangeReturnValue.add(0d);
        else
            checkRangeReturnValue.add(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
        return checkRangeReturnValue;
    }
    
    public static boolean checkFOV(int distX, int distY, double FOV, double direction) {
        
        targetDirection = Math.toDegrees(Math.atan2(Math.abs((double)distY), Math.abs((double)distX)));
        //convert to true bearing
        if (distX == Math.abs(distX) && distY == Math.abs(distY))
            targetDirection = 90 - targetDirection;
        else if (distX == Math.abs(distX) && distY != Math.abs(distY))
            targetDirection += 90;
        else if (distX != Math.abs(distX) && distY != Math.abs(distY))
            targetDirection = 90 - targetDirection + 180;
        else if (distX != Math.abs(distX) && distY == Math.abs(distY))
            targetDirection += 270;
        
        //compare to FOV
        if (!(targetDirection <= direction + FOV/2 && targetDirection >= direction - FOV/2) && !(targetDirection <= direction + 360 + FOV/2 && targetDirection >= direction + 360 - FOV/2) && !(targetDirection <= direction - 360 + FOV/2 && targetDirection >= direction - 360 - FOV/2))
            return false;
        
        return true;
    }
}