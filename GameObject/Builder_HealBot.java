

package GameObject;

import Engine.GameDisplay;
import Game.Game;
import java.util.ArrayList;
import java.util.Arrays;


public class Builder_HealBot extends Unit {
    
    public String unitClass = "HealBot";
    public int[] buildDate = new int[3];
    public boolean built = false;
    public double FOV = -1;
    public ArrayList<Unit> healLog = new ArrayList<Unit>(Arrays.asList(null, null, null));
    public ArrayList<Unit> targetList = new ArrayList<Unit>();
    public String attack0Name = "";
    public String attack1Name = "";
    public String attack2Name = "";
    public String attack3Name = "";
    public double[] attack0Range = {1.8};
    public double[] attack1Range = {1.8};
    public double[] attack2Range = {1.8};
    public double[] attack3Range = {1.8};
    public double attack0FOV;
    public double attack1FOV;
    public double attack2FOV;
    public double attack3FOV;
    public boolean poisonable = false;
    
    public int maxHealth = 4;
    public int movement = 0;
    
    public Builder_HealBot(String teamName, int posX, int posY, int respawnPosX, int respawnPosY) {
        
        init(teamName, unitClass, posX, posY, maxHealth, movement, respawnPosX, respawnPosY, FOV, attack0Name, attack1Name, attack2Name, attack3Name, poisonable, attack0Range, attack1Range, attack2Range, attack3Range, attack0FOV, attack1FOV, attack2FOV, attack3FOV);
    }
    
    @Override
    public void update(int[] round) {
        
        if (buildDate[0] == Game.round[0] && buildDate[1] == Game.round[1] && buildDate[2] == Game.round[2])
            finishBuild();
        
        if (((Game.round[2] == 1 && teamName == "blue") || Game.round[2] == 2 && teamName == "red") && built == true && !EMP)
            healBotHeal();
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
        health = 2;
        maxHealth = 2;
    }
    
    public void finishBuild() {
        
        built = true;
        GameDisplay.consoleAddMessage(unitClass + " has finished construction", teamName);
        //add half health
        health += 2;
        maxHealth = 4;
    }
    
    public void healBotHeal() {
        
        targetList.clear();
        target = null;
        
        for (int i = 0; i < Game.aliveUnits.size(); i++) {
            
            //make sure unit is friendly, damaged, and adjacent or diagonal to healBot
            if (Game.aliveUnits.get(i).teamName == teamName && Game.aliveUnits.get(i).health < Game.aliveUnits.get(i).maxHealth && ((Game.aliveUnits.get(i).posX == posX && Math.abs(Game.aliveUnits.get(i).posY - posY) <= 1.8) || (Game.aliveUnits.get(i).posY == posY && Math.abs(Game.aliveUnits.get(i).posX - posX) <= 1.8)))
                targetList.add(Game.aliveUnits.get(i));
        }
        
        //find most damaged unit
        for (int i = 0; i < targetList.size(); i++) {
            
            //make sure unit hasn't already been healed twice this round
            if (!(healLog.get(1) == targetList.get(i) && healLog.get(2) == targetList.get(i))) {
                
                if (target == null)
                    target = targetList.get(i);
                
                else if (targetList.get(i).maxHealth - targetList.get(i).health > target.maxHealth - target.health)
                        target = targetList.get(i);
            }
        }
        
        //heal target, can't overheal
        if (target != null)
            if (target.health < target.maxHealth)
                healTarget(1, target, null, false);
        
        //update heal log
        healLog.remove(0);
        healLog.add(target);
    }
    
    @Override
    public void damage(int amt) {
        
        health = health - amt;
        
        if (health <= 0)
            death();
    }
    
    @Override
    public void death() {
        
        alive = false;
        Game.changeUnitList(this, "dead");
        built = false;
        
        if (teamName == "blue")
            Game.blueBuilder.healBotDestroyed();
        else
            Game.redBuilder.healBotDestroyed();
        
        GameDisplay.consoleAddMessage(teamClass + " has been destroyed", "both");
        
        maxHealth = 4;
    }
    
    @Override
    public void heal(int amt, boolean overheal) {
        
        //can't overheal
        health += amt;
        if (health > maxHealth)
            health = maxHealth;
    }
}
