

package GameObject;

import Engine.GameDisplay;
import Engine.Physics;
import Game.Board;
import Game.Game;
import java.util.ArrayList;
import java.util.Arrays;


public class TNTGuy extends Unit {
   
    public String unitClass = "TNTGuy";
    public double FOV = 90;
    //[0] is posX, [1] is posY
    public int[] tntPos = {0, 0, 0, 0, 0, 0, 0, 0};
    public boolean[] tntPlaced = {false, false, false, false};
    public boolean[] tntPrimed = {false, false, false, false};
    public int[] tntPrimeDate1 = new int[3];
    public int[] tntPrimeDate2 = new int[3];
    public int[] tntPrimeDate3 = new int[3];
    public int[] tntPrimeDate4 = new int[3];
    public ArrayList<int[]> tntPrimeDate = new ArrayList<int[]>(Arrays.asList(tntPrimeDate1, tntPrimeDate2, tntPrimeDate3, tntPrimeDate4));
    public String attack0Name = "Activate TNT";
    public String attack1Name = "Throw TNT";
    public String attack2Name = "Attack 2";
    public String attack3Name = "Attack 3";
    public double[] attack0Range = new double[0];
    public double[] attack1Range = {4};
    public double[] attack2Range = new double[0];
    public double[] attack3Range = new double[0];
    public double attack0FOV = 90;
    public double attack1FOV = 90;
    public double attack2FOV = 90;
    public double attack3FOV = 90;
    public boolean poisonable = true;
    
    public int maxHealth = 7;
    public int movement = 2;
    
    public TNTGuy(String teamName, int posX, int posY, int respawnPosX, int respawnPosY) {
        
        init(teamName, unitClass, posX, posY, maxHealth, movement, respawnPosX, respawnPosY, FOV, attack0Name, attack1Name, attack2Name, attack3Name, poisonable, attack0Range, attack1Range, attack2Range, attack3Range, attack0FOV, attack1FOV, attack2FOV, attack3FOV);
    }
    
    @Override
    public void update(int[] round) {
        
        super.update(round);
        
        //prime tnt
        for (int i = 0; i < tntPrimeDate.size(); i++)
            if (tntPrimeDate.get(i)[0] == round[0] && tntPrimeDate.get(i)[1] == round[1] && tntPrimeDate.get(i)[2] == round[2] && tntPlaced[i] == true)
                tntPrimed[i] = true;
    }
    
    @Override
    public void freeAttack(int x, int y) {
        
        for (int i = 0; 2 * i < tntPos.length; i++) {
            
            if (tntPos[2 * i] == x && tntPos[2 * i + 1] == y && tntPlaced[i]) {
                
                if (tntPrimed[i]) {
                    
                    if (Board.checkSquareUnit(tntPos[2 * i], tntPos[2 * i + 1]) instanceof Unit) {
                        
                        if (Board.checkSquareUnit(tntPos[2 * i], tntPos[2 * i + 1]).teamName != teamName) {
                            
                            damageTarget(3, Board.checkSquareUnit(tntPos[2 * i], tntPos[2 * i + 1]), null);
                        }
                    }
                    
                    if (Board.checkSquareUnit(tntPos[2 * i] + 1, tntPos[2 * i + 1]) instanceof Unit) {
                        
                        if (Board.checkSquareUnit(tntPos[2 * i] + 1, tntPos[2 * i + 1]).teamName != teamName) {
                            
                            damageTarget(2, Board.checkSquareUnit(tntPos[2 * i] + 1, tntPos[2 * i + 1]), null);
                        }
                    }
                    
                    if (Board.checkSquareUnit(tntPos[2 * i] - 1, tntPos[2 * i + 1]) instanceof Unit) {
                        
                        if (Board.checkSquareUnit(tntPos[2 * i] - 1, tntPos[2 * i + 1]).teamName != teamName) {
                            
                            damageTarget(2, Board.checkSquareUnit(tntPos[2 * i] - 1, tntPos[2 * i + 1]), null);
                        }
                    }
                    
                    if (Board.checkSquareUnit(tntPos[2 * i], tntPos[2 * i + 1] + 1) instanceof Unit) {
                        
                        if (Board.checkSquareUnit(tntPos[2 * i], tntPos[2 * i + 1] + 1).teamName != teamName) {
                            
                            damageTarget(2, Board.checkSquareUnit(tntPos[2 * i], tntPos[2 * i + 1] + 1), null);
                        }
                    }
                    
                    if (Board.checkSquareUnit(tntPos[2 * i], tntPos[2 * i + 1] - 1) instanceof Unit) {
                        
                        if (Board.checkSquareUnit(tntPos[2 * i], tntPos[2 * i + 1] - 1).teamName != teamName) {
                            
                            damageTarget(2, Board.checkSquareUnit(tntPos[2 * i], tntPos[2 * i + 1] - 1), null);
                        }
                    }
                }
                
                else {
                    
                    if (Board.checkSquareUnit(tntPos[2 * i], tntPos[2 * i + 1]) instanceof Unit) {
                        
                        if (Board.checkSquareUnit(tntPos[2 * i], tntPos[2 * i + 1]).teamName != teamName) {
                            
                            damageTarget(2, Board.checkSquareUnit(tntPos[2 * i], tntPos[2 * i + 1]), null);
                        }
                    }
                    
                    if (Board.checkSquareUnit(tntPos[2 * i] + 1, tntPos[2 * i + 1]) instanceof Unit) {
                        
                        if (Board.checkSquareUnit(tntPos[2 * i] + 1, tntPos[2 * i + 1]).teamName != teamName) {
                            
                            damageTarget(1, Board.checkSquareUnit(tntPos[2 * i] + 1, tntPos[2 * i + 1]), null);
                        }
                    }
                    
                    if (Board.checkSquareUnit(tntPos[2 * i] - 1, tntPos[2 * i + 1]) instanceof Unit) {
                        
                        if (Board.checkSquareUnit(tntPos[2 * i] - 1, tntPos[2 * i + 1]).teamName != teamName) {
                            
                            damageTarget(1, Board.checkSquareUnit(tntPos[2 * i] - 1, tntPos[2 * i + 1]), null);
                        }
                    }
                    
                    if (Board.checkSquareUnit(tntPos[2 * i], tntPos[2 * i + 1] + 1) instanceof Unit) {
                        
                        if (Board.checkSquareUnit(tntPos[2 * i], tntPos[2 * i + 1] + 1).teamName != teamName) {
                            
                            damageTarget(1, Board.checkSquareUnit(tntPos[2 * i], tntPos[2 * i + 1] + 1), null);
                        }
                    }
                    
                    if (Board.checkSquareUnit(tntPos[2 * i], tntPos[2 * i + 1] - 1) instanceof Unit) {
                        
                        if (Board.checkSquareUnit(tntPos[2 * i], tntPos[2 * i + 1] - 1).teamName != teamName) {
                            
                            damageTarget(1, Board.checkSquareUnit(tntPos[2 * i], tntPos[2 * i + 1] - 1), null);
                        }
                    }
                }
                
                tntPlaced[i] = false;
                tntPrimed[i] = false;
            }
        }
    }
    
    @Override
    public void attack1(int x, int y) {
        
        if (attacksLeft >= 1) {
            
            //range of 4
            if (Board.checkSquare(x, y) != 1 && (double)Physics.checkRange(posX, posY, x, y, attack1FOV, direction, teamName, false).get(0) > 0 && (double)Physics.checkRange(posX, posY, x, y, attack1FOV, direction, teamName, false).get(0) <= attack1Range[0]) {

                if (tntPlaced[0] == true) {

                    if (tntPlaced[1] == true) {

                        if (tntPlaced[2] == true) {

                            if (tntPlaced[3] == true) {
                                
                                tntPlaced[3] = true;
                                tntPrimed[3] = false;
                                tntPos[6] = x;
                                tntPos[7] = y;
                                tntPrimeDate4[0] = Game.round[0] + 1;
                                tntPrimeDate4[1] = Game.round[1];
                                tntPrimeDate4[2] = Game.round[2];
                                attacksLeft--;
                            }
                        }
                        
                        else {
                            
                            tntPlaced[2] = true;
                            tntPrimed[2] = false;
                            tntPos[4] = x;
                            tntPos[5] = y;
                            tntPrimeDate3[0] = Game.round[0] + 1;
                            tntPrimeDate3[1] = Game.round[1];
                            tntPrimeDate3[2] = Game.round[2];
                            attacksLeft--;
                        }
                    }
                    
                    else {
                        
                        tntPlaced[1] = true;
                        tntPrimed[1] = false;
                        tntPos[2] = x;
                        tntPos[3] = y;
                        tntPrimeDate2[0] = Game.round[0] + 1;
                        tntPrimeDate2[1] = Game.round[1];
                        tntPrimeDate2[2] = Game.round[2];
                        attacksLeft--;
                    }
                }
                
                else {
                    
                    tntPlaced[0] = true;
                    tntPrimed[0] = false;
                    tntPos[0] = x;
                    tntPos[1] = y;
                    tntPrimeDate1[0] = Game.round[0] + 1;
                    tntPrimeDate1[1] = Game.round[1];
                    tntPrimeDate1[2] = Game.round[2];
                    attacksLeft--;
                }
            }
        }
    }
    
    @Override
    public void attack2(int x, int y) {
        
        GameDisplay.consoleAddMessage("This unit does not have that attack", teamName);
    }
    
    @Override
    public void attack3(int x, int y) {
        
        GameDisplay.consoleAddMessage("This unit does not have that attack", teamName);
    }
}
