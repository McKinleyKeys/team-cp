

package GameObject;

import Engine.GameDisplay;
import Engine.Physics;


public class Scout extends Unit {
    
    public String unitClass = "Scout";
    public double FOV = 90;
    public String attack0Name = "Free Attack";
    public String attack1Name = "Bow Attack";
    public String attack2Name = "Attack 2";
    public String attack3Name = "Attack 3";
    public double damageFalloffRange1 = 1.8;
    public double damageFalloffRange2 = 4;
    public double damageFalloffRange3 = 7;
    public double[] attack0Range = new double[0];
    public double[] attack1Range = {1.8, 4, 7};
    public double[] attack2Range = new double[0];
    public double[] attack3Range = new double[0];
    public double attack0FOV = 90;
    public double attack1FOV = 90;
    public double attack2FOV = 90;
    public double attack3FOV = 90;
    public boolean poisonable = true;
    
    public int maxHealth = 6;
    public int movement = 3;
    
    public Scout(String teamName, int posX, int posY, int respawnPosX, int respawnPosY) {
        
        init(teamName, unitClass, posX, posY, maxHealth, movement, respawnPosX, respawnPosY, FOV, attack0Name, attack1Name, attack2Name, attack3Name, poisonable, attack0Range, attack1Range, attack2Range, attack3Range, attack0FOV, attack1FOV, attack2FOV, attack3FOV);
        
        super.attack1Range = attack1Range;
    }
    
    @Override
    public void freeAttack(int x, int y) {
        
        GameDisplay.consoleAddMessage("This unit does not have that attack", teamName);
    }
    
    @Override
    public void attack1(int x, int y) {
        
        if (attacksLeft >= 1) {
            
            if ((double)Physics.checkRange(posX, posY, x, y, FOV, direction, teamName, true).get(0) <= damageFalloffRange1) {
                
                attack(4, x, y, damageFalloffRange1, FOV, null);
            }
            
            else if ((double)Physics.checkRange(posX, posY, x, y, FOV, direction, teamName, true).get(0) <= damageFalloffRange2) {
                
                attack(3, x, y, damageFalloffRange2, FOV, null);
            }
            
            else if ((double)Physics.checkRange(posX, posY, x, y, FOV, direction, teamName, true).get(0) <= damageFalloffRange3) {
                
                attack(2, x, y, damageFalloffRange3, FOV, null);
            }
            
            attacksLeft--;
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
