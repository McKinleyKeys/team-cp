

package GameObject;

import Engine.GameDisplay;
import Engine.Physics;


public class Archer extends Unit {
    
    public String unitClass = "Archer";
    public double FOV = 90;
    public String attack0Name = "Free Attack";
    public String attack1Name = "Bow Attack";
    public String attack2Name = "Charge Bow";
    public String attack3Name = "Attack 3";
    public double[] attack0Range = new double[0];
    public double[] attack1Range = {16};
    public double[] attack2Range = {16};
    public double[] attack3Range = new double[0];
    public double attack0FOV = 90;
    public double attack1FOV = 0;
    public double attack2FOV = 0;
    public double attack3FOV = 90;
    public int bowCharge;
    public double oldDirection;
    public boolean poisonable = true;
    
    public int maxHealth = 5;
    public int movement = 2;
    
    public Archer(String teamName, int posX, int posY, int respawnPosX, int respawnPosY) {
        
        init(teamName, unitClass, posX, posY, maxHealth, movement, respawnPosX, respawnPosY, FOV, attack0Name, attack1Name, attack2Name, attack3Name, poisonable, attack0Range, attack1Range, attack2Range, attack3Range, attack0FOV, attack1FOV, attack2FOV, attack3FOV);
    }
    
    @Override
    public void death() {
        
        super.death();
        bowCharge = 0;
    }
    
    @Override
    public void freeAttack(int x, int y) {
        
        GameDisplay.consoleAddMessage("This unit does not have that attack", teamName);
    }
    
    @Override
    public void attack1(int x, int y) {
        
        if (attacksLeft >= 1) {
            
            if (bowCharge > 0)
                //0 degree FOV
                attack((int)(2 + bowCharge * 1.67f), x, y, attack1Range[0], attack1FOV, null);
            else
                //normal FOV
                attack(2, x, y, 100, FOV, null);
            bowCharge = 0;
            attacksLeft--;
        }
    }
    
    @Override
    public void attack2(int x, int y) {
        
        if (attacksLeft >= 1) {
            
            if (bowCharge < 4) {
                
                bowCharge++;
                attacksLeft--;
                
                GameDisplay.consoleAddMessage(unitClass + " is charging bow.", teamName);
            }
        }
    }
    
    @Override
    public void attack3(int x, int y) {
        
        GameDisplay.consoleAddMessage("This unit does not have that attack", teamName);
    }
    
    @Override
    public void changeDirection(int x, int y) {
        
        oldDirection = direction;
        super.changeDirection(x, y);
        
        //if under 10 degrees: -1 to bowCharge
        if ((oldDirection >= direction - 10 && oldDirection <= direction + 10) || (oldDirection >= direction - 10 - 360 && oldDirection <= direction + 10 - 360) || (oldDirection >= direction - 10 + 360 && oldDirection <= direction + 10 + 360))
            bowCharge--;
        
        //if under 45 degrees: -2 to bowCharge
        else if ((oldDirection >= direction - 45 && oldDirection <= direction + 45) || (oldDirection >= direction - 45 - 360 && oldDirection <= direction + 45 - 360) || (oldDirection >= direction - 45 + 360 && oldDirection <= direction + 45 + 360))
            bowCharge -= 2;
        
        //if under 90 degrees: -3 to bowCharge
        else if ((oldDirection >= direction - 90 && oldDirection <= direction + 90) || (oldDirection >= direction - 90 - 360 && oldDirection <= direction + 90 - 360) || (oldDirection >= direction - 90 + 360 && oldDirection <= direction + 90 + 360))
            bowCharge -= 3;
        
        else
            bowCharge -= 4;
        
        if (bowCharge < 0)
            bowCharge = 0;
    }
    
    @Override
    public void move(int x, int y, boolean selfMove) {
        
        if (movesLeft > 0 && selfMove) {
            
            if (Physics.checkPath(posX, posY, x, y, movesLeft, teamName, invis) <= movesLeft && Physics.checkPath(posX, posY, x, y, movesLeft, teamName, invis) == 1)
                bowCharge -= 2;
            else if (Physics.checkPath(posX, posY, x, y, movesLeft, teamName, invis) <= movesLeft && Physics.checkPath(posX, posY, x, y, movesLeft, teamName, invis) > 1)
                bowCharge -= 3;

            if (bowCharge < 0)
                bowCharge = 0;
        }
        
        super.move(x, y, selfMove);
    }
}
