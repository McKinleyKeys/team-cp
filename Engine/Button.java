

package Engine;

import Game.Game;
import Inputs.Inputs;


public class Button {
    
    public int posX;
    public int posY;
    public int sizeX;
    public int sizeY;
    public String function;
    //if you can hold the button down
    public boolean holdable;
    public boolean holding;
    public float r;
    public float g;
    public float b;
    
    public Button(int posX, int posY, int sizeX, int sizeY, String function, boolean holdable, float r, float g, float b) {
        
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.function = function;
        this.holdable = holdable;
        this.r = r;
        this.g = g;
        this.b = b;
    }
    
    public void mouseClick(int x, int y) {
        
        if (x >= posX && x <= posX + sizeX && y >= posY && y <= posY + sizeY) {
            
            activate(x, y);
            if (holdable)
                holding = true;
        }
    }
    
    public void mouseRelease() {
        
        holding = false;
    }
    
    public void activate(int x, int y) {
        
        if (function == "scroll up")
            GameDisplay.scroll(5, true, false);
        else if (function == "scroll down")
            GameDisplay.scroll(-5, true, false);
        
        else if (function == "select square") {
            
            if (GameDisplay.flip == false) {
                
                //check if square is on board
                if (x >= GameDisplay.boardPosX && x <= GameDisplay.boardPosX + GameDisplay.boardSizeX && y >= GameDisplay.boardPosY && y <= GameDisplay.boardPosY + GameDisplay.boardSizeY)
                    Inputs.selectSquare((int)((double)(x - GameDisplay.boardPosX) / (double)GameDisplay.squareSizeX) + 1, (int)((double)(y - GameDisplay.boardPosY) / (double)GameDisplay.squareSizeY) + 1);
            }
            else {
                
                //check if square is on board
                if (x >= GameDisplay.boardPosX && x <= GameDisplay.boardPosX + GameDisplay.boardSizeX && y >= GameDisplay.boardPosY && y <= GameDisplay.boardPosY + GameDisplay.boardSizeY)
                    Inputs.selectSquare(GameDisplay.boardSquaresX + 1 - ((int)((double)(x - GameDisplay.boardPosX) / (double)GameDisplay.squareSizeX) + 1), GameDisplay.boardSquaresY + 1 - ((int)((double)(y - GameDisplay.boardPosY) / (double)GameDisplay.squareSizeY) + 1));
            }
        }
        
        else if (function == "select archer") {
            
            Inputs.selectUnit("Archer", Game.playerTurn);
        }
        else if (function == "select builder") {
            
            Inputs.selectUnit("Builder", Game.playerTurn);
        }
        else if (function == "select healer") {
            
            Inputs.selectUnit("Healer", Game.playerTurn);
        }
        else if (function == "select knight") {
            
            Inputs.selectUnit("Knight", Game.playerTurn);
        }
        else if (function == "select mage") {
            
            Inputs.selectUnit("Mage", Game.playerTurn);
        }
        else if (function == "select scout") {
            
            Inputs.selectUnit("Scout", Game.playerTurn);
        }
        else if (function == "select spy") {
            
            Inputs.selectUnit("Spy", Game.playerTurn);
        }
        else if (function == "select tntguy") {
            
            Inputs.selectUnit("TNTGuy", Game.playerTurn);
        }
        
        else if (function == "increase attack number") {
            
            Inputs.increaseAttackNumber();
        }
        
        else if (function == "confirm")
            Inputs.confirm();
        
        else if (function == "end phase")
            Inputs.endPhase();
        
        else if (function == "move")
            Inputs.move();
        else if (function == "change direction")
            Inputs.changeDirection();
        else if (function == "attack")
            Inputs.attack();
    }
    
    public void update(int x, int y) {
        
        if (holding)
            activate(x, y);
    }
    
    public void draw() {
        
        GameDisplay.drawQuad(posX, posY, posX + sizeX, posY + sizeY, r, g, b);
    }
}
