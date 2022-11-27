

package GameObject;

import Engine.GameDisplay;
import Engine.Physics;
import Game.Board;
import Game.Game;


public class Knight extends Unit {
    
    public String unitClass = "Knight";
    public double FOV = 90;
    public String attack0Name = "Free Attack";
    public String attack1Name = "Sword Attack";
    public String attack2Name = "Bow Attack";
    public String attack3Name = "Charge Attack";
    public double[] attack0Range = new double[0];
    public double[] attack1Range = {1.8};
    public double[] attack2Range = {4, 6};
    public double[] attack3Range = {5, 8};
    public double attack0FOV = 90;
    public double attack1FOV = 90;
    public double attack2FOV = 90;
    public double attack3FOV = 90;
    public double damageFalloffRange1 = 4;
    public double damageFalloffRange2 = 6;
    public boolean poisonable = true;
    public int chargeAttackCooldownTime;
    public int[] chargeAttackCooldownDate = new int[3];
    public int chargeDirX;
    public int chargeDirY;
    
    public int maxHealth = 8;
    public int movement = 1;
    
    public Knight(String teamName, int posX, int posY, int respawnPosX, int respawnPosY) {
        
        init(teamName, unitClass, posX, posY, maxHealth, movement, respawnPosX, respawnPosY, FOV, attack0Name, attack1Name, attack2Name, attack3Name, poisonable, attack0Range, attack1Range, attack2Range, attack3Range, attack0FOV, attack1FOV, attack2FOV, attack3FOV);
    }
    
    @Override
    public void update(int[] round) {
        
        super.update(round);
        
        if (chargeAttackCooldownDate[1] == round[1] && chargeAttackCooldownDate[2] == round[2] && chargeAttackCooldownTime > 0) {
            
            chargeAttackCooldownTime--;
            
            if (chargeAttackCooldownTime == 0 && alive)
                GameDisplay.consoleAddMessage(teamClass + "'s Charge Attack is ready to use", teamName);
        }
    }
    
    @Override
    public void freeAttack(int x, int y) {
        
        GameDisplay.consoleAddMessage("This unit does not have that attack", teamName);
    }
    
    @Override
    public void attack1(int x, int y) {
        
        if (attacksLeft >= 1) {
            
            attack(4, x, y, attack1Range[0], attack0FOV, null);
            attacksLeft--;
        }
    }
    
    @Override
    public void attack2(int x, int y) {
        
        if (attacksLeft >= 1) {
            
            if ((double)Physics.checkRange(posX, posY, x, y, FOV, direction, teamName, true).get(0) <= damageFalloffRange1) {
                
                attack(3, x, y, damageFalloffRange1, attack2FOV, null);
            }
            
            else if ((double)Physics.checkRange(posX, posY, x, y, FOV, direction, teamName, true).get(0) <= damageFalloffRange2) {
                
                attack(2, x, y, damageFalloffRange2, attack2FOV, null);
            }
            
            attacksLeft--;
        }
    }
    
    @Override
    public void attack3(int x, int y) {
        
        if (attacksLeft >= 1) {
            
            //make sure cooldown is 0 and knight can see target square
            if (chargeAttackCooldownTime == 0 && Physics.checkFOV(x - posX, y - posY, attack3FOV, direction)) {
                
                if (x == posX)
                    chargeDirX = 0;
                else
                    chargeDirX = (x - posX) / Math.abs(x - posX);
                
                if (y == posY)
                    chargeDirY = 0;
                else
                    chargeDirY = (y - posY) / Math.abs(y - posY);
                
                //only move straight, not diagonal
                if (chargeDirX == 0 || chargeDirY == 0) {
                    
                    int chargeLength = 0;
                
                    //move to selected square. max movement = 8
                    for (int i = 1; i <= Math.abs(x - posX) || i <= Math.abs(y - posY); i++) {
                        
                        chargeLength = i;
                        
                        //check for collision with obstacle
                        if (Board.checkSquare(posX + i * chargeDirX, posY + i * chargeDirY) == 1) {
                            
                            posX = posX + (i - 1) * chargeDirX;
                            posY = posY + (i - 1) * chargeDirY;
                            break;
                        }
                        
                        //check for collision with enemy unit
                        if (Board.checkSquareUnit(posX + i * chargeDirX, posY + i * chargeDirY) != null) {
                            
                            if (Board.checkSquareUnit(posX + i * chargeDirX, posY + i * chargeDirY).teamName != teamName) {
                            
                                posX = posX + (i - 1) * chargeDirX;
                                posY = posY + (i - 1) * chargeDirY;
                                attack(1, posX + 1 * chargeDirX, posY + 1 * chargeDirY, 1, 360, "slammed");
                                break;
                            }
                        }
                        
                        //check if max movement has been achieved
                        if (i == 8) {
                            
                            posX = posX + i * chargeDirX;
                            posY = posY + i * chargeDirY;
                            break;
                        }
                        
                        //check if charge is done
                        if (x == posX + i * chargeDirX && y == posY + i * chargeDirY) {
                            
                            posX = posX + i * chargeDirX;
                            posY = posY + i * chargeDirY;
                            attack(1, posX + 1 * chargeDirX, posY + 1 * chargeDirY, 1, 360, "slammed");
                            break;
                        }
                    }
                    
                    if (chargeLength <= attack3Range[0]) {
                        
                        //deal 4 damage to all adjacent squares
                        attack(4, posX, posY + 1, 1, 360, null);
                        attack(4, posX, posY - 1, 1, 360, null);
                        attack(4, posX + 1, posY, 1, 360, null);
                        attack(4, posX - 1, posY, 1, 360, null);
                    }
                    
                    else {
                        
                        //deal 3 damage to all adjacent squares
                        attack(3, posX, posY + 1, 1, 360, null);
                        attack(3, posX, posY - 1, 1, 360, null);
                        attack(3, posX + 1, posY, 1, 360, null);
                        attack(3, posX - 1, posY, 1, 360, null);
                    }
                    
                    attacksLeft--;
                    
                    chargeAttackCooldownTime += 5;
                    chargeAttackCooldownDate[0] = Game.round[0] + chargeAttackCooldownTime;
                    chargeAttackCooldownDate[1] = Game.round[1];
                    chargeAttackCooldownDate[2] = Game.round[2];
                }
            }
        }
    }
}
