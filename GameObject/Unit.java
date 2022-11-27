

package GameObject;

import Engine.GameDisplay;
import Engine.Physics;
import Game.Game;


public class Unit {
    
    public String teamName;
    public String unitClass;
    public String teamClass;
    public boolean buildingClass;
    public int posX;
    public int posY;
    public int health;
    public int maxHealth;
    public boolean active;
    public boolean activated;
    public boolean alive;
    public int respawnTime;
    public int[] respawnDate = new int[3];
    public int respawnPosX;
    public int respawnPosY;
    public int poisonTime = 0;
    public int[] poisonDate = new int[3];
    public double FOV;
    public double direction;
    public int movement;
    //number of movement points remaining this round
    public int movesLeft;
    public int attacksLeft;
    public boolean invuln = false;
    //healsLeft = 1 means healer can heal on that turn(because it is not active) is only used by healer
    public int freeAttacksLeft;
    public int changeDirectionsLeft;
    public boolean diedThisTurn = false;
    public Unit target;
    public String attack0Name;
    public String attack1Name;
    public String attack2Name;
    public String attack3Name;
    public double[] attack0Range = new double[0];
    public double[] attack1Range = new double[0];
    public double[] attack2Range = new double[0];
    public double[] attack3Range = new double[0];
    public double attack0FOV;
    public double attack1FOV;
    public double attack2FOV;
    public double attack3FOV;
    public boolean invis = false;
    public boolean poisonable;
    public boolean EMP;
    
    public void init(String teamName, String unitClass, int posX, int posY, int maxHealth, int movement, int respawnPosX, int respawnPosY, double FOV, String attack0Name, String attack1Name, String attack2Name, String attack3Name, boolean poisonable, double[] attack0Range, double[] attack1Range, double[] attack2Range, double[] attack3Range, double attack0FOV, double attack1FOV, double attack2FOV, double attack3FOV) {
        
        this.teamName = teamName;
        this.unitClass = unitClass;
        this.posX = posX;
        this.posY = posY;
        this.maxHealth = maxHealth;
        health = maxHealth;
        this.movement = movement;
        movesLeft = 1;
        attacksLeft = 0;
        active = false;
        activated = false;
        alive = true;
        respawnTime = 0;
        respawnDate = new int[3];
        this.respawnPosX = respawnPosX;
        this.respawnPosY = respawnPosY;
        this.FOV = FOV;
        this.attack0Name = attack0Name;
        this.attack1Name = attack1Name;
        this.attack2Name = attack2Name;
        this.attack3Name = attack3Name;
        this.poisonable = poisonable;
        this.attack0Range = attack0Range;
        this.attack1Range = attack1Range;
        this.attack2Range = attack2Range;
        this.attack3Range = attack3Range;
        this.attack0FOV = attack0FOV;
        this.attack1FOV = attack1FOV;
        this.attack2FOV = attack2FOV;
        this.attack3FOV = attack3FOV;
        
        if (teamName == "blue")
            teamClass = "Blue " + unitClass;
        else
            teamClass = "Red " + unitClass;
        
        if (unitClass == "Statue" || unitClass == "HealBot")
            buildingClass = true;
        else
            buildingClass = false;
    }
    
    public void update(int[] round) {
        
        if (teamClass.equals("Red Mage")) {
            System.out.println(alive + " " + respawnTime + " " + respawnDate[0] + " " + respawnDate[2]);
            respawnTime = 3; respawnDate[1] = round[1] + 3; respawnDate[2] = round[2];
        }
        
        if (alive == false && diedThisTurn == false) {
            
            if (respawnDate[1] == round[1] && respawnDate[2] == round[2])
                respawnTime--;
            if (respawnTime == 0) {
                
                respawn();
            }
        }
        
        else if (poisonTime > 0 && poisonDate[1] == round[1] && poisonDate[2] == round[2] && poisonable) {
                
            GameDisplay.consoleAddMessage(unitClass + " took 1 poison damage", teamName);
            damage(1);
            poisonTime--;
        }
        
        diedThisTurn = false;
    }
    
    public void damage(int amt) {
        
        if (invuln == false)
            health = health - amt;
        if (health <= 0) {
            
            death();
        }
    }
    
    public void death() {
        
        alive = false;
        Game.changeUnitList(this, "dead");
        poisonTime = 0;
        
        //respawn stuff
        respawnTime = 4;
        respawnDate[0] = Game.round[0] + respawnTime;
        respawnDate[1] = Game.round[1];
        respawnDate[2] = Game.round[2];
        for (Unit unit : Game.deadUnits)
            if (unit.teamName == this.teamName && unit.respawnTime >= respawnTime - 2) {
            
                respawnTime = unit.respawnTime;
                respawnDate = unit.respawnDate;
            }
        
        //make sure unit's respawn time doesn't count down this turn
        diedThisTurn = true;
        
        GameDisplay.consoleAddMessage(this.teamClass + " died", "both");
    }
    
    public void respawn() {
        
        health = maxHealth;
        alive = true;
        Game.changeUnitList(this, "alive");
        posX = respawnPosX;
        posY = respawnPosY;
    }
    
    public void heal(int amt, boolean overheal) {
        
        health += amt;
        if (health > maxHealth + 1 && overheal)
            health = maxHealth + 1;
        else if (health > maxHealth && !overheal)
            health = maxHealth;
    }
    
    public void damageTarget(int amt, Unit target, String attackName) {
        
        if (attackName == null)
            GameDisplay.consoleAddMessage(this.teamClass + " attacked " + target.teamClass + " for " + amt + " hp", "both");
        else
            GameDisplay.consoleAddMessage(this.teamClass + " " + attackName + " " + target.teamClass + " for " + amt + " hp", "both");
        
        target.damage(amt);
    }
    
    public void healTarget(int amt, Unit target, String healName, boolean overheal) {
        
        if (healName == null)
            GameDisplay.consoleAddMessage(this.teamClass + " healed " + target.teamClass + " for " + amt + " hp", teamName);
        else
            GameDisplay.consoleAddMessage(this.teamClass + " " + healName + " " + target.teamClass + " for " + amt + " hp", teamName);
        
        target.heal(amt, overheal);
    }
    
    public void attack(int amt, int targetPosX, int targetPosY, double range, double attackFOV, String attackName) {
        
        target = null;
        
        //check range
        if ((double)Physics.checkRange(posX, posY, targetPosX, targetPosY, attackFOV, direction, teamName, true).get(0) > 0 && (double)Physics.checkRange(posX, posY, targetPosX, targetPosY, attackFOV, direction, teamName, true).get(0) <= range) {
            
            //find target
            for (int i = 0; i < Game.aliveUnits.size(); i++)
                if (Game.aliveUnits.get(i).posX == (int)Physics.checkRange(posX, posY, targetPosX, targetPosY, attackFOV, direction, teamName, true).get(1) && Game.aliveUnits.get(i).posY == (int)Physics.checkRange(posX, posY, targetPosX, targetPosY, attackFOV, direction, teamName, true).get(2) && Game.aliveUnits.get(i).teamName != teamName)
                    target = Game.aliveUnits.get(i);
            
            if (target != null) {
                
                if (target.invuln == false)
                    damageTarget(amt, target, attackName);
            }
        }
    }
    
    public boolean giveHeal(int amt, int targetPosX, int targetPosY, double range, double attackFOV, String healName, boolean overheal) {
        
        target = null;
        
        //check range
        if ((double)Physics.checkRange(posX, posY, targetPosX, targetPosY, attackFOV, direction, teamName, false).get(0) > 0 && (double)Physics.checkRange(posX, posY, targetPosX, targetPosY, attackFOV, direction, teamName, false).get(0) <= range) {
            
            //find target
            for (int i = 0; i < Game.aliveUnits.size(); i++)
                if (Game.aliveUnits.get(i).posX == targetPosX && Game.aliveUnits.get(i).posY == targetPosY && Game.aliveUnits.get(i).teamName == teamName)
                    target = Game.aliveUnits.get(i);
            
            if (target != null) {
                
                healTarget(amt, target, healName, overheal);
                return true;
            }
        }
        
        return false;
    }
    
    public void poison(int length) {
        
        if (poisonable) {
            
            poisonTime = length;
            poisonDate[0] = Game.round[0] + poisonTime;
            poisonDate[1] = Game.round[1];
            poisonDate[2] = Game.round[2];
        }
    }
    
    public void move(int x, int y, boolean selfMove) {
        
        //selfMove is true if unit is moving itself. false if being airblasted
        
        if (selfMove && movesLeft > 0) {
        
            if (Physics.checkPath(posX, posY, x, y, movesLeft, teamName, invis) <= movesLeft && Physics.checkPath(posX, posY, x, y, movesLeft, teamName, invis) > 0) {
                
                movesLeft -= Physics.checkPath(posX, posY, x, y, movesLeft, teamName, invis);
                posX = x;
                posY = y;
            }
        }
        
        else if (!selfMove) {
            
            if (Physics.checkPath(posX, posY, x, y, 2, teamName, invis) <= 2 && Physics.checkPath(posX, posY, x, y, 2, teamName, invis) > 0) {
                
                posX = x;
                posY = y;
            }
        }
    }
    
    public void changeDirection (int x, int y) {
        
        if (changeDirectionsLeft >= 1) {
        
            //x and y are the target squares
            //calculate direction
            direction = Math.toDegrees(Math.atan2(Math.abs((double)(y - posY)), Math.abs((double)(x - posX))));
            //convert to true bearing
            if (x - posX == Math.abs(x - posX) && y - posY == Math.abs(y - posY)) {
                
                direction = 90 - direction;
            }
            else if (x - posX == Math.abs(x - posX) && y - posY != Math.abs(y - posY)) {
                
                direction += 90;
            }
            else if (x - posX != Math.abs(x - posX)  && y - posY != Math.abs(y - posY)) {
                
                direction = 90 - direction + 180;
            }
            else if (x - posX != Math.abs(x - posX)  && y - posY == Math.abs(y - posY)) {
                
                direction += 270;
            }
            
            changeDirectionsLeft--;
        }
    }
    
    public void freeAttack(int x, int y) {
        
        
    }
    
    public void attack1(int x, int y) {
        
        
    }
    
    public void attack2(int x, int y) {
        
        
    }
    
    public void attack3(int x, int y) {
        
        
    }
    
    public void resetActivated() {
        
        activated = false;
    }
}
