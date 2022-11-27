

package GameObject;

import Engine.GameDisplay;
import Engine.Physics;
import Game.Board;
import Game.Game;


public class Mage extends Unit {
    
    public String unitClass = "Mage";
    public double FOV = 90;
    public String attack0Name = "Free Attack";
    public String attack1Name = "Poison Attack";
    public String attack2Name = "Air Blast";
    public String attack3Name = "Antidote";
    public double[] attack0Range = new double[0];
    public double[] attack1Range = {4};
    public double[] attack2Range = {1, 2};
    public double[] attack3Range = {2};
    public double attack0FOV = 90;
    public double attack1FOV = 90;
    public double attack2FOV = 90;
    public double attack3FOV = 90;
    public boolean poisonable = false;
    public int airBlastDirX;
    public int airBlastDirY;
    
    public int maxHealth = 6;
    public int movement = 2;
    
    public Mage(String teamName, int posX, int posY, int respawnPosX, int respawnPosY) {
        
        init(teamName, unitClass, posX, posY, maxHealth, movement, respawnPosX, respawnPosY, FOV, attack0Name, attack1Name, attack2Name, attack3Name, poisonable, attack0Range, attack1Range, attack2Range, attack3Range, attack0FOV, attack1FOV, attack2FOV, attack3FOV);
    }
    
    @Override
    public void freeAttack(int x, int y) {
        
        GameDisplay.consoleAddMessage("This unit does not have that attack", teamName);
    }
    
    @Override
    public void attack1(int x, int y) {
        
        if (attacksLeft >= 1) {
        
            target = null;

            //deal 1 damage to target square
            attack(1, x, y, attack1Range[0], attack1FOV, null);

            //poison target squares
            //if poison attack hits an enemy unit
            if ((double)Physics.checkRange(posX, posY, x, y, attack1FOV, direction, teamName, true).get(0) > 0 && (double)Physics.checkRange(posX, posY, x, y, attack1FOV, direction, teamName, true).get(0) <= attack1Range[0]) {
                
                int targetPosX = 0;
                int targetPosY = 0;
                
                target = Board.checkSquareUnit((targetPosX = (int)Physics.checkRange(posX, posY, x, y, FOV, direction, teamName, true).get(1)), (targetPosY = (int)Physics.checkRange(posX, posY, x, y, attack1FOV, direction, teamName, true).get(2)));

                if (target != null)
                    if (target.teamName != teamName && target.poisonable) {

                        target.poison(3);
                        GameDisplay.consoleAddMessage(teamClass + " poisoned " + target.teamClass, "both");
                    }

                target = Board.checkSquareUnit(targetPosX, targetPosY + 1);

                if (target != null)
                    if (target.teamName != teamName && target.poisonable) {

                        target.poison(3);
                        GameDisplay.consoleAddMessage(teamClass + " poisoned " + target.teamClass, "both");
                    }

                target = Board.checkSquareUnit(targetPosX, targetPosY - 1);

                if (target != null)
                    if (target.teamName != teamName && target.poisonable) {

                        target.poison(3);
                        GameDisplay.consoleAddMessage(teamClass + " poisoned " + target.teamClass, "both");
                    }

                target = Board.checkSquareUnit(targetPosX + 1, targetPosY);

                if (target != null)
                    if (target.teamName != teamName && target.poisonable) {

                        target.poison(3);
                        GameDisplay.consoleAddMessage(teamClass + " poisoned " + target.teamClass, "both");
                    }

                target = Board.checkSquareUnit(targetPosX - 1, targetPosY);

                if (target != null)
                    if (target.teamName != teamName && target.poisonable) {

                        target.poison(3);
                        GameDisplay.consoleAddMessage(teamClass + " poisoned " + target.teamClass, "both");
                    }
            }

            //else if poison attack hits an empty square
            else if ((double)Physics.checkRange(posX, posY, x, y, attack1FOV, direction, teamName, false).get(0) > 0 && (double)Physics.checkRange(posX, posY, x, y, attack1FOV, direction, teamName, false).get(0) <= attack1Range[0]) {

                target = Board.checkSquareUnit(x, y + 1);

                if (target != null)
                    if (target.teamName != teamName && target.poisonable) {

                        target.poison(3);
                        GameDisplay.consoleAddMessage(teamClass + " poisoned " + target.teamClass, "both");
                    }

                target = Board.checkSquareUnit(x, y - 1);

                if (target != null)
                    if (target.teamName != teamName && target.poisonable) {

                        target.poison(3);
                        GameDisplay.consoleAddMessage(teamClass + " poisoned " + target.teamClass, "both");
                    }

                target = Board.checkSquareUnit(x + 1, y);

                if (target != null)
                    if (target.teamName != teamName && target.poisonable) {

                        target.poison(3);
                        GameDisplay.consoleAddMessage(teamClass + " poisoned " + target.teamClass, "both");
                    }

                target = Board.checkSquareUnit(x - 1, y);

                if (target != null)
                    if (target.teamName != teamName && target.poisonable) {

                        target.poison(3);
                        GameDisplay.consoleAddMessage(teamClass + " poisoned " + target.teamClass, "both");
                    }
            }
        
            attacksLeft--;
        }
    }
    
    @Override
    public void attack2(int x, int y) {
        
        if (attacksLeft > 0) {
            
            target = null;
            
            if ((double)Physics.checkRange(posX, posY, x, y, attack2FOV, direction, teamName, true).get(0) > 0 && (double)Physics.checkRange(posX, posY, x, y, attack2FOV, direction, teamName, true).get(0) <= 2)
                target = Board.checkSquareUnit((int)Physics.checkRange(posX, posY, x, y, attack2FOV, direction, teamName, true).get(1), (int)Physics.checkRange(posX, posY, x, y, FOV, direction, teamName, true).get(2));
            
            if (target != null) {
                
                if (target.posX == posX)
                    airBlastDirX = 0;
                else
                    airBlastDirX = (target.posX - posX) / Math.abs(target.posX - posX);
                if (target.posY == posY)
                    airBlastDirY = 0;
                else
                    airBlastDirY = (target.posY - posY) / Math.abs(target.posY - posY);
                
                target.damage(3 - (int)Math.round((double)Physics.checkRange(posX, posY, target.posX, target.posY, 360, direction, teamName, false).get(0) + 0.49f));
                
                GameDisplay.consoleAddMessage(teamClass + " airblasted " + target.teamClass + " for " + (3 - (int)Math.round((double)Physics.checkRange(posX, posY, target.posX, target.posY, 360, direction, teamName, false).get(0) + 0.49f)) + " hp", "both");
                
                target.move((int)(target.posX + airBlastDirX * (3 - Math.round((double)Physics.checkRange(posX, posY, target.posX, target.posY, 360, direction, teamName, false).get(0) + 0.49f))), (int)(target.posY + airBlastDirY * (3 - Math.round((double)Physics.checkRange(posX, posY, target.posX, target.posY, 360, direction, teamName, false).get(0) + 0.49f))), false);
                
                attacksLeft--;
            }
            
            //check for airblast tnt
            else  {
                
                if (teamName == "blue") {
                    
                    for (int i = 0; i < Game.redTNTGuy.tntPos.length; i += 2) {
                                                
                        if ((Game.redTNTGuy.tntPos[i] >= posX && Game.redTNTGuy.tntPos[i] <= x && posX <= x && Game.redTNTGuy.tntPos[i + 1] >= posY && Game.redTNTGuy.tntPos[i + 1] <= y && posY <= y) ||          (Game.redTNTGuy.tntPos[i] >= x && Game.redTNTGuy.tntPos[i] <= posX && x <= posX && Game.redTNTGuy.tntPos[i + 1] >= posY && Game.redTNTGuy.tntPos[i + 1] <= y && posY <= y) ||      (Game.redTNTGuy.tntPos[i] >= posX && Game.redTNTGuy.tntPos[i] <= x && posX <= x && Game.redTNTGuy.tntPos[i + 1] >= y && Game.redTNTGuy.tntPos[i + 1] <= posY && y <= posY) ||                      (Game.redTNTGuy.tntPos[i] >= x && Game.redTNTGuy.tntPos[i] <= posX && x <= posX && Game.redTNTGuy.tntPos[i + 1] >= y && Game.redTNTGuy.tntPos[i + 1] <= posY && y <= posY))
                            if (Game.redTNTGuy.tntPlaced[i / 2]) {
                                
                                if (Game.redTNTGuy.tntPos[i] == posX)
                                    airBlastDirX = 0;
                                else
                                    airBlastDirX = (Game.redTNTGuy.tntPos[i] - posX) / Math.abs(Game.redTNTGuy.tntPos[i] - posX);
                                if (Game.redTNTGuy.tntPos[i + 1] == posY)
                                    airBlastDirY = 0;
                                else
                                    airBlastDirY = (Game.redTNTGuy.tntPos[i + 1] - posY) / Math.abs(Game.redTNTGuy.tntPos[i + 1] - posY);
                                
                                //check sightline to destination
                                //if diagonal
                                if (airBlastDirX != 0 && airBlastDirY != 0) {
                                    
                                    if ((double)Physics.checkRange(posX, posY, posX + 2 * airBlastDirX, posY + 2 * airBlastDirY, attack2FOV, direction, teamName, false).get(0) > 0) {
                                        
                                        Game.redTNTGuy.tntPos[i] += airBlastDirX;
                                        Game.redTNTGuy.tntPos[i + 1] += airBlastDirY;
                                        Game.redTNTGuy.tntPrimed[i / 2] = false;
                                        Game.redTNTGuy.tntPrimeDate.get(i / 2)[0]--;
                                        GameDisplay.consoleAddMessage(teamClass + " airblasted Red TNTGuy's TNT", "both");
                                        attacksLeft--;
                                    }
                                }
                                
                                //if not diagonal
                                else {
                                    
                                    if ((double)Physics.checkRange(posX, posY, posX + 3 * airBlastDirX, posY + 3 * airBlastDirY, attack2FOV, direction, teamName, false).get(0) > 0) {
                                        
                                        Game.redTNTGuy.tntPos[i] += (3 - (int)Math.round((double)Physics.checkRange(posX, posY, Game.redTNTGuy.tntPos[i], Game.redTNTGuy.tntPos[i + 1], 360, direction, teamName, false).get(0) + 0.49f)) * airBlastDirX;
                                        Game.redTNTGuy.tntPos[i + 1] += (3 - (int)Math.round((double)Physics.checkRange(posX, posY, Game.redTNTGuy.tntPos[i], Game.redTNTGuy.tntPos[i + 1], 360, direction, teamName, false).get(0) + 0.49f)) * airBlastDirY;
                                        Game.redTNTGuy.tntPrimed[i / 2] = false;
                                        Game.redTNTGuy.tntPrimeDate.get(i / 2)[0]--;
                                        GameDisplay.consoleAddMessage(teamClass + " airblasted Red TNTGuy's TNT", "both");
                                        attacksLeft--;
                                    }
                                    
                                    //if can't move tnt 2 spaces, but can move it 1 space
                                    else if ((double)Physics.checkRange(posX, posY, posX + 2 * airBlastDirX, posY + 2 * airBlastDirY, attack2FOV, direction, teamName, false).get(0) > 0) {
                                        
                                        Game.redTNTGuy.tntPos[i] += airBlastDirX;
                                        Game.redTNTGuy.tntPos[i + 1] += airBlastDirY;
                                        Game.redTNTGuy.tntPrimed[i / 2] = false;
                                        Game.redTNTGuy.tntPrimeDate.get(i / 2)[0]--;
                                        GameDisplay.consoleAddMessage(teamClass + " airblasted Red TNTGuy's TNT", "both");
                                        attacksLeft--;
                                    }
                                }
                            }
                    }
                }
                
                else {
                    
                    for (int i = 0; i < Game.blueTNTGuy.tntPos.length; i += 2) {
                                                
                        if ((Game.blueTNTGuy.tntPos[i] >= posX && Game.blueTNTGuy.tntPos[i] <= x && posX <= x && Game.blueTNTGuy.tntPos[i + 1] >= posY && Game.blueTNTGuy.tntPos[i + 1] <= y && posY <= y) ||          (Game.blueTNTGuy.tntPos[i] >= x && Game.blueTNTGuy.tntPos[i] <= posX && x <= posX && Game.blueTNTGuy.tntPos[i + 1] >= posY && Game.blueTNTGuy.tntPos[i + 1] <= y && posY <= y) ||      (Game.blueTNTGuy.tntPos[i] >= posX && Game.blueTNTGuy.tntPos[i] <= x && posX <= x && Game.blueTNTGuy.tntPos[i + 1] >= y && Game.blueTNTGuy.tntPos[i + 1] <= posY && y <= posY) ||                      (Game.blueTNTGuy.tntPos[i] >= x && Game.blueTNTGuy.tntPos[i] <= posX && x <= posX && Game.blueTNTGuy.tntPos[i + 1] >= y && Game.blueTNTGuy.tntPos[i + 1] <= posY && y <= posY))
                            if (Game.blueTNTGuy.tntPlaced[i / 2]) {
                                
                                if (Game.blueTNTGuy.tntPos[i] == posX)
                                    airBlastDirX = 0;
                                else
                                    airBlastDirX = (Game.blueTNTGuy.tntPos[i] - posX) / Math.abs(Game.blueTNTGuy.tntPos[i] - posX);
                                if (Game.blueTNTGuy.tntPos[i + 1] == posY)
                                    airBlastDirY = 0;
                                else
                                    airBlastDirY = (Game.blueTNTGuy.tntPos[i + 1] - posY) / Math.abs(Game.blueTNTGuy.tntPos[i + 1] - posY);
                                
                                //check sightline to destination
                                //if diagonal
                                if (airBlastDirX != 0 && airBlastDirY != 0) {
                                    
                                    if ((double)Physics.checkRange(posX, posY, posX + 2 * airBlastDirX, posY + 2 * airBlastDirY, attack2FOV, direction, teamName, false).get(0) > 0) {
                                        
                                        Game.blueTNTGuy.tntPos[i] += airBlastDirX;
                                        Game.blueTNTGuy.tntPos[i + 1] += airBlastDirY;
                                        Game.blueTNTGuy.tntPrimed[i / 2] = false;
                                        Game.blueTNTGuy.tntPrimeDate.get(i / 2)[0]--;
                                        GameDisplay.consoleAddMessage(teamClass + " airblasted Blue TNTGuy's TNT", "both");
                                        attacksLeft--;
                                    }
                                }
                                
                                //if not diagonal
                                else {
                                    
                                    if ((double)Physics.checkRange(posX, posY, posX + 3 * airBlastDirX, posY + 3 * airBlastDirY, attack2FOV, direction, teamName, false).get(0) > 0) {
                                        
                                        Game.blueTNTGuy.tntPos[i] += (3 - (int)Math.round((double)Physics.checkRange(posX, posY, Game.blueTNTGuy.tntPos[i], Game.blueTNTGuy.tntPos[i + 1], 360, direction, teamName, false).get(0) + 0.49f)) * airBlastDirX;
                                        Game.blueTNTGuy.tntPos[i + 1] += (3 - (int)Math.round((double)Physics.checkRange(posX, posY, Game.blueTNTGuy.tntPos[i], Game.blueTNTGuy.tntPos[i + 1], 360, direction, teamName, false).get(0) + 0.49f)) * airBlastDirY;
                                        Game.blueTNTGuy.tntPrimed[i / 2] = false;
                                        Game.blueTNTGuy.tntPrimeDate.get(i / 2)[0]--;
                                        GameDisplay.consoleAddMessage(teamClass + " airblasted Blue TNTGuy's TNT", "both");
                                        attacksLeft--;
                                    }
                                    
                                    //if can't move tnt 2 spaces, but can move it 1 space
                                    else if ((double)Physics.checkRange(posX, posY, posX + 2 * airBlastDirX, posY + 2 * airBlastDirY, attack2FOV, direction, teamName, false).get(0) > 0) {
                                        
                                        Game.blueTNTGuy.tntPos[i] += airBlastDirX;
                                        Game.blueTNTGuy.tntPos[i + 1] += airBlastDirY;
                                        Game.blueTNTGuy.tntPrimed[i / 2] = false;
                                        Game.blueTNTGuy.tntPrimeDate.get(i / 2)[0]--;
                                        GameDisplay.consoleAddMessage(teamClass + " airblasted Blue TNTGuy's TNT", "both");
                                        attacksLeft--;
                                    }
                                }
                            }
                    }
                }
            }
        }
    }
    
    @Override
    public void attack3(int x, int y) {
        
        if (attacksLeft > 0) {
        
            target = null;

            if ((double)Physics.checkRange(posX, posY, x, y, attack3FOV, direction, teamName, false).get(0) > 0 && (double)Physics.checkRange(posX, posY, x, y, attack3FOV, direction, teamName, false).get(0) <= attack3Range[0])
                target = Board.checkSquareUnit(x, y);
            
            if (target != null) {
                
                target.poisonTime = 0;
                GameDisplay.consoleAddMessage(teamClass + " removed poison from " + target.teamClass, teamName);
                attacksLeft--;
            }
        }
    }
}
