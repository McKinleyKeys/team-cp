

package GameObject;

import Engine.GameDisplay;
import Engine.Physics;
import Game.Board;
import Game.Game;


public class Builder_Statue extends Unit {
    
    public String unitClass = "Statue";
    public int[] buildDate = new int[3];
    public boolean built = false;
    public double FOV = -1;
    public double range = 8;
    public String attack0Name = "";
    public String attack1Name = "";
    public String attack2Name = "";
    public String attack3Name = "";
    public double[] attack0Range = {range};
    public double[] attack1Range = {range};
    public double[] attack2Range = {range};
    public double[] attack3Range = {range};
    public double attack0FOV = 180;
    public double attack1FOV = 180;
    public double attack2FOV = 180;
    public double attack3FOV = 180;
    public int attackCharge;
    public Unit closestTarget;
    public boolean poisonable = false;
    
    public int maxHealth = 8;
    public int movement = 0;
    
    public Builder_Statue(String teamName, int posX, int posY, int respawnPosX, int respawnPosY) {
        
        init(teamName, unitClass, posX, posY, maxHealth, movement, respawnPosX, respawnPosY, FOV, attack0Name, attack1Name, attack2Name, attack3Name, poisonable, attack0Range, attack1Range, attack2Range, attack3Range, attack0FOV, attack1FOV, attack2FOV, attack3FOV);
        
        if (this.teamName == "blue")
            direction = 0;
        
        else if (this.teamName == "red")
            direction = 180;
    }
    
    @Override
    public void update(int[] round) {
        
        if (buildDate[0] == Game.round[0] && buildDate[1] == Game.round[1] && buildDate[2] == Game.round[2] && alive == true)
            finishBuild();
        
        if (((Game.round[2] == 1 && teamName == "blue") || Game.round[2] == 2 && teamName == "red") && built == true && !EMP)
            statueAttack();
    }
    
    public void build() {
        
        alive = true;
        built = false;
        //set buildDate
        buildDate[0] = Game.round[0] + 1;
        buildDate[1] = Game.round[1];
        buildDate[2] = Game.round[2];
        Game.changeUnitList(this, "alive");
        //set health to half
        health = 4;
        maxHealth = 4;
        EMP = false;
    }
    
    public void finishBuild() {
        
        built = true;
        GameDisplay.consoleAddMessage(unitClass + " has finished construction", teamName);
        //add half health
        health += 4;
        attackCharge = 0;
        maxHealth = 8;
    }
    
    public void statueAttack() {
        
        target = null;
        
        for (int i = 0; i < Game.aliveUnits.size(); i++)
            if (Game.aliveUnits.get(i).teamName != teamName && !Game.aliveUnits.get(i).invis) {
                
                //check if target is in range
                if ((double)Physics.checkRange(posX, posY, Game.aliveUnits.get(i).posX, Game.aliveUnits.get(i).posY, 180, direction, teamName, false).get(0) <= range && (double)Physics.checkRange(posX, posY, Game.aliveUnits.get(i).posX, Game.aliveUnits.get(i).posY, 180, direction, teamName, false).get(0) > 0d) {
                    
                    //check if unit is closer than previous target
                    if (target != null) {
                        
                        if ((double)Physics.checkRange(posX, posY, Game.aliveUnits.get(i).posX, Game.aliveUnits.get(i).posY, 180, direction, teamName, true).get(0) < (double)Physics.checkRange(posX, posY, target.posX, target.posY, 180, direction, teamName, true).get(0))
                            target = Board.checkSquareUnit((int)Physics.checkRange(posX, posY, Game.aliveUnits.get(i).posX, Game.aliveUnits.get(i).posY, 180, direction, teamName, true).get(1), (int)Physics.checkRange(posX, posY, Game.aliveUnits.get(i).posX, Game.aliveUnits.get(i).posY, 180, direction, teamName, true).get(2));
                    }
                        
                    else
                        target = Game.aliveUnits.get(i);
                }
            }
        
        if (target != null) {
            
            if (Physics.checkRange(posX, posY, target.posX, target.posY, 360, 0, teamName, true).size() >= 3) {
            
                damageTarget(1 + attackCharge, Board.checkSquareUnit((int)Physics.checkRange(posX, posY, target.posX, target.posY, 360, 0, teamName, true).get(1), (int)Physics.checkRange(posX, posY, target.posX, target.posY, 360, 0, teamName, true).get(2)), null);
                attackCharge = 0;
            }
        }
        
        else
            //charge attack
            attackCharge = 1;
    }
    
    @Override
    public void damage(int amt) {
        
        health = health - amt;
        
        if (health <= 0) {
            
            death();
        }
    }
    
    @Override
    public void death() {
        
        alive = false;
        Game.changeUnitList(this, "dead");
        built = false;
        
        if (teamName == "blue")
            Game.blueBuilder.statueDestroyed();
        else
            Game.redBuilder.statueDestroyed();
        
        GameDisplay.consoleAddMessage(teamClass + " has been destroyed", "both");
        
        maxHealth = 8;
        
        EMP = false;
    }
    
    @Override
    public void heal(int amt, boolean overheal) {
        
        //can't overheal
        health += amt;
        if (health > maxHealth)
            health = maxHealth;
    }
}
