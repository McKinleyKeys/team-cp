

package Engine;

import Game.Board;
import Game.Game;
import Game.Position;
import Game.Replay;
import GameObject.Unit;
import Inputs.Inputs;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;


public class GameDisplay {
    
    //bottom left corner of board
    public static int boardPosX;
    public static int boardPosY;
    //scrolling variables
    public static int boardDefaultPosX;
    public static int boardDefaultPosY;
    public static int boardSizeX;
    public static int boardSizeY;
    //number of squares
    public static int boardSquaresX;
    public static int boardSquaresY;
    public static double squareSizeX;
    public static double squareSizeY;
    //the white area around the board
    public static int boardOutline = 20;
    //bottom left corner of select unit area
    public static int selectUnitPosX;
    public static int selectUnitPosY;
    public static int selectUnitSizeX;
    public static int selectUnitSizeY;
    //1 = archer, 2 = builder...
    public static int selectedUnit = 0;
    public static String playerTurn = "blue";
    public static int[] blueScroll = new int[2];
    public static int[] redScroll = new int[2];
    public static boolean flip = false;
    //console
    public static int consolePosX;
    public static int consolePosY;
    public static int consoleSizeX;
    public static int consoleSizeY;
    public static int scoreboardPosX;
    public static int scoreboardPosY;
    public static int scoreboardSizeX;
    public static int scoreboardSizeY;
    public static ArrayList<String> consoleMessagesBlue = new ArrayList<String>(Arrays.asList("DO NOT REMOVE THIS TEXT", "empty", "empty", "empty", "empty", "empty", "empty", "empty", "empty"));
    public static ArrayList<String> consoleMessagesRed = new ArrayList<String>(Arrays.asList("DO NOT REMOVE THIS TEXT", "empty", "empty", "empty", "empty", "empty", "empty", "empty", "empty"));
//visible squares
    public static ArrayList visSquares = new ArrayList();
    public static ArrayList<Unit> visUnits = new ArrayList<Unit>();
    public static boolean doNotAddUnit;
    public static int FOVlengthX;
    public static int FOVlengthY;
    public static double directionAngle;
    public static boolean mouseFirstClick = true;
    public static Button scrollUp = new Button(21, 600, 20, 20, "scroll up", true, 1, 1, 1);
    public static Button scrollDown = new Button(21, 200, 20, 20, "scroll down", true, 1, 1, 1);
    public static Button selectSquare;
    public static Button selectArcher = new Button(75, 39, 82, 82, "select archer", false, 0, 0, 0);
    public static Button selectBuilder = new Button(177, 39, 82, 82, "select builder", false, 0, 0, 0);
    public static Button selectHealer = new Button(279, 39, 82, 82, "select healer", false, 0, 0, 0);
    public static Button selectKnight = new Button(381, 39, 82, 82, "select knight", false, 0, 0, 0);
    public static Button selectMage = new Button(483, 39, 82, 82, "select mage", false, 0, 0, 0);
    public static Button selectScout = new Button(585, 39, 82, 82, "select scout", false, 0, 0, 0);
    public static Button selectSpy = new Button(687, 39, 82, 82, "select spy", false, 0, 0, 0);
    public static Button selectTNTGuy = new Button(789, 39, 82, 82, "select tntguy", false, 0, 0, 0);
    public static Button move = new Button(775, 537, 162, 50, "move", false, 0.8f, 0.8f, 0.8f);
    public static Button direction = new Button(962, 537, 162, 50, "change direction", false, 0.8f, 0.8f, 0.8f);
    public static Button attack = new Button(775, 457, 90, 50, "attack", false, 0.8f, 0.8f, 0.8f);
    public static Button attackNumber = new Button(880, 457, 50, 50, "increase attack number", false, 0.8f, 0.8f, 0.8f);
    public static Button attackName = new Button(945, 457, 178, 50, null, false, 0.8f, 0.8f, 0.8f);
    public static Button confirm = new Button(775, 377, 162, 50, "confirm", false, 0.8f, 0.8f, 0.8f);
    public static Button endPhase = new Button(962, 377, 162, 50, "end phase", false, 0.8f, 0.8f, 0.8f);
    public static ArrayList<Button> buttons = new ArrayList<Button>(Arrays.asList(scrollUp, scrollDown, move, direction, attack, attackNumber, attackName, confirm, endPhase, selectArcher, selectBuilder, selectHealer, selectKnight, selectMage, selectScout, selectSpy, selectTNTGuy));
    public static float[] clearColor = {0.4f, 0.55f, 0.7f};
    public static Color clearColorName = new Color(clearColor[0], clearColor[1], clearColor[2]);
    public static float replaySpeed = 3.5f * 100000000l;
    public static float keyDoublePressTime = 2 * 100000000l;
    public static long keyRightLastPress;
    public static long keyRightLastRelease;
    public static long keyLeftLastPress;
    public static long keyLeftLastRelease;
    public static boolean drawFullFOV;
    public static Unit selectedUnitDrawFOV;
    public static double selectedUnitDrawFOVRange;
    public static double selectedUnitDrawFOVFOV;
    public static TrueTypeFont font14;
    public Font awtFont14;
    public static TrueTypeFont font18;
    public Font awtFont18;
    public static TrueTypeFont font24;
    public Font awtFont24;
    
    public GameDisplay(int boardPosX, int boardPosY, double squareSizeX, double squareSizeY, int boardSquaresX, int boardSquaresY, int selectUnitPosX, int selectUnitPosY, int selectUnitSizeX, int selectUnitSizeY) {
        
//        this.boardDefaultPosX = boardPosX;
//        this.boardDefaultPosY = boardPosY;
//        this.boardPosX = boardPosX;
//        this.boardPosY = boardPosY;
//        this.boardSizeX = boardSizeX;
//        this.boardSizeY = boardSizeY;
//        this.boardSquaresX = boardSquaresX;
//        this.boardSquaresY = boardSquaresY;
//        this.selectUnitPosX = selectUnitPosX;
//        this.selectUnitPosY = selectUnitPosY;
//        this.selectUnitSizeX = selectUnitSizeX;
//        this.selectUnitSizeY = selectUnitSizeY;
        this.boardDefaultPosX = boardPosX;
        this.boardDefaultPosY = boardPosY;
        this.squareSizeX = squareSizeX;
        this.squareSizeY = squareSizeY;
        this.boardSizeX = (int)(squareSizeX * (double)boardSquaresX);
        this.boardSizeY = (int)(squareSizeY * (double)boardSquaresY);
        this.boardPosX = boardDefaultPosX;
        this.boardPosY = boardDefaultPosY;
//        this.boardSizeX = 692;
//        this.boardSizeY = 1500;
        this.boardSquaresX = boardSquaresX;
        this.boardSquaresY = boardSquaresY;
        this.selectUnitPosX = 50;
        this.selectUnitPosY = 10;
        this.selectUnitSizeX = 1050;
        this.selectUnitSizeY = 140;
        consolePosX = 775;
        consolePosY = 170;
        consoleSizeX = 350;
        consoleSizeY = 168;
        scoreboardPosX = 775;
        scoreboardPosY = 620;
        scoreboardSizeX = 350;
        scoreboardSizeY = 80;
//        squareSizeX = (double)this.boardSizeX / (double)this.boardSquaresX;
//        squareSizeY = (double)this.boardSizeY / (double)this.boardSquaresY;
        
        selectSquare = new Button(boardDefaultPosX, boardDefaultPosY, boardSizeX, boardSizeY, "select square", false, clearColor[0], clearColor[1], clearColor[2]);
        buttons.add(selectSquare);
        
        initDisplay();
    }
    
    public static void update() {
        
        selectedUnit = Inputs.getSelectedUnit();
        
        if (playerTurn == "blue") {
            
            Game.updatePlayerView(playerTurn);
            visSquares = Game.blueView;
        }
        
        else {
            
            Game.updatePlayerView(playerTurn);
            visSquares = Game.redView;
        }
        
        visUnits.clear();
        
        //visible units
        for (int i = 0; i < Game.aliveUnits.size(); i++) {
            
            for (int a = 0; a < visSquares.size(); a += 2) {
                
                doNotAddUnit = false;
                
                //do not add a unit if it is already in the list
                for (int e = 0; e < visUnits.size(); e++) {
                    
                    if (visUnits.get(e).posX == (int)visSquares.get(a) && visUnits.get(e).posY == (int)visSquares.get(a + 1))
                        doNotAddUnit = true;
                }
                
                    if (doNotAddUnit == false && Game.aliveUnits.get(i).posX == (int)visSquares.get(a) && Game.aliveUnits.get(i).posY == (int)visSquares.get(a + 1)) {
                        
                        //invis
                        if (Game.aliveUnits.get(i).invis == false || Game.aliveUnits.get(i).teamName == playerTurn)
                            visUnits.add(Game.aliveUnits.get(i));
                    }
            }
        }
        
        //update selectedUnitDrawFOV
        if (drawFullFOV) {
            
            if (!(Board.checkSquareUnit(Inputs.selectedSquare[0], Inputs.selectedSquare[1]) instanceof Unit)) {
                
                selectedUnitDrawFOV = null;
            }
            
            if (Board.checkSquareUnit(Inputs.selectedSquare[0], Inputs.selectedSquare[1]) instanceof Unit) {
                
                selectedUnitDrawFOV = Board.checkSquareUnit(Inputs.selectedSquare[0], Inputs.selectedSquare[1]);
            }
            
            else if (Inputs.selectedUnit instanceof Unit) {
                
                selectedUnitDrawFOV = Inputs.selectedUnit;
            }
        }
        
        render();
    }
    
    public void initDisplay() {
        
        try {
            
            Display.setDisplayMode(Display.getAvailableDisplayModes()[0]);
            Display.setFullscreen(true);
            Display.setVSyncEnabled(true);
            Display.create();
            Mouse.create();
            Keyboard.create();
        } 
	catch (LWJGLException e) {
                e.printStackTrace();
		System.exit(0);
	}
        
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, 0);
	glShadeModel(GL_SMOOTH);        
	glDisable(GL_DEPTH_TEST);
        glDisable(GL_LIGHTING);                    
 
	glClearColor(clearColor[0], clearColor[1], clearColor[2], 0.0f);                
        glClearDepth(1);                                       
 
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
 
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
        glOrtho(0, Display.getWidth(), Display.getHeight(), 0, -1, 1);
	glMatrixMode(GL_MODELVIEW);
        
        awtFont14 = new Font("Calibri", Font.PLAIN, 14);
        font14 = new TrueTypeFont(awtFont14, true);
        awtFont18 = new Font("Calibri", Font.PLAIN, 18);
        font18 = new TrueTypeFont(awtFont18, true);
        awtFont24 = new Font("Calibri", Font.PLAIN, 24);
        font24 = new TrueTypeFont(awtFont24, true);
    }
    
    public static void render() {
        
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity();
        
        drawButtons();
        drawBoard();
        drawSelectUnit();
        drawConsole();
        drawScoreboard();
        drawActionButtons();
        
        Display.update();
        
        Display.sync(60);
    }
    
    public static void changePlayerTurn() {
        
        if (Game.playerTurn == "blue") {
            
            playerTurn = "blue";
            flip = false;
            
            //load blue player's scroll
            scroll(blueScroll[1], true, true);
            scroll(blueScroll[0], false, true);
            //blueScroll just got doubled. undo that
            blueScroll[1] /= 2;
            blueScroll[0] /= 2;
            
            //clear red messages
//            consoleAddMessage("DO NOT REMOVE THIS TEXT", "red");
//            for (int i = 0; i < 8; i++)
//                consoleAddMessage("empty", "red");
            consoleClearGreyMessages("red");
        }
        
        else {
            
            playerTurn = "red";
            flip = true;
            
            //load red player's scroll
            scroll(redScroll[1], true, true);
            scroll(redScroll[0], false, true);
            //redScroll just got doubled. undo that
            redScroll[1] /= 2;
            redScroll[0] /= 2;
            
            //clear blue messages
//            consoleAddMessage("DO NOT REMOVE THIS TEXT", "blue");
//            for (int i = 0; i < 8; i++)
//                consoleAddMessage("empty", "blue");
            consoleClearGreyMessages("blue");
        }
    }
    
    public static void drawBoard() {
        
        //draw white background
        drawQuad(boardPosX - boardOutline, boardPosY - boardOutline, boardPosX + boardSizeX + boardOutline, boardPosY + boardSizeY + boardOutline, 1, 1, 1);
        
        //draw lines
        //vertical lines
        for (int x = 0; x <= boardSquaresX; x++)
            drawLine(boardPosX + (int)(x * squareSizeX), boardPosY, boardPosX + (int)(x * squareSizeX), boardPosY + boardSizeY, 1, 0, 0, 0);
        for (int y = 0; y <= boardSquaresY; y++)
            drawLine(boardPosX, boardPosY + (int)(y * squareSizeY), boardPosX + boardSizeX, boardPosY + (int)(y * squareSizeY), 1, 0, 0, 0);
        
        //shade invisible squares
        for (int x = 1; x <= boardSquaresX; x++)
            for (int y = 1; y <= boardSquaresY; y++)
                //shade all squares grey
                drawQuad((int)((x - 1) * squareSizeX) + boardPosX + 1, (int)((y - 1) * squareSizeY) + boardPosY + 1, (int)(x * squareSizeX) + boardPosX - 1, (int)(y * squareSizeY) + boardPosY - 1, 0.7f, 0.7f, 0.7f);
        //unshade visible squares
        if (playerTurn == "blue")
            visSquares = Game.blueView;
        else
            visSquares = Game.redView;
        for (int i = 0; i < visSquares.size(); i = i + 2) {
            
            if (flip == false)
                drawQuad((int)(((int)visSquares.get(i) - 1) * squareSizeX) + boardPosX + 1, (int)(((int)visSquares.get(i + 1) - 1) * squareSizeY) + boardPosY + 1, (int)((int)visSquares.get(i) * squareSizeX) + boardPosX - 1, (int)((int)visSquares.get(i + 1) * squareSizeY) + boardPosY - 1, 1f, 1f, 1f);
            else
                drawQuad((int)((boardSquaresX + 1 - (int)visSquares.get(i) - 1) * squareSizeX) + boardPosX + 1, (int)((boardSquaresY + 1 - (int)visSquares.get(i + 1) - 1) * squareSizeY) + boardPosY + 1, (int)((boardSquaresX + 1 - (int)visSquares.get(i)) * squareSizeX) + boardPosX - 1, (int)((boardSquaresY + 1 - (int)visSquares.get(i + 1)) * squareSizeY) + boardPosY - 1, 1f, 1f, 1f);
        }
        
        //draw obstacles
        for (int x = 1; x <= boardSquaresX; x++)
            for (int y = 1; y <= boardSquaresY; y++) {
                
                if (Board.checkSquare(x, y) == 1) {
                    
                    //draw black
                    if (flip == false)
                        drawQuad((int)((x - 1) * squareSizeX) + boardPosX + 1, (int)((y - 1) * squareSizeY) + boardPosY + 1, (int)(x * squareSizeX) + boardPosX - 1, (int)(y * squareSizeY) + boardPosY - 1, 0f, 0f, 0f);
                    
                    else
                        drawQuad((int)((boardSquaresX + 1 - x - 1) * squareSizeX) + boardPosX + 1, (int)((boardSquaresY + 1 - y - 1) * squareSizeY) + boardPosY + 1, (int)((boardSquaresX + 1 - x) * squareSizeX) + boardPosX - 1, (int)((boardSquaresY + 1 - y) * squareSizeY) + boardPosY - 1, 0f, 0f, 0f);
                }
                
                if (Board.checkSquare(x, y) == 2) {
                    
                    //draw purple
                    if (flip == false)
                        drawQuad((int)((x - 1) * squareSizeX) + boardPosX + 1, (int)((y - 1) * squareSizeY) + boardPosY + 1, (int)(x * squareSizeX) + boardPosX - 1, (int)(y * squareSizeY) + boardPosY - 1, 1f, 0f, 1f);
                    
                    else
                        drawQuad((int)((boardSquaresX + 1 - x - 1) * squareSizeX) + boardPosX + 1, (int)((boardSquaresY + 1 - y - 1) * squareSizeY) + boardPosY + 1, (int)((boardSquaresX + 1 - x) * squareSizeX) + boardPosX - 1, (int)((boardSquaresY + 1 - y) * squareSizeY) + boardPosY - 1, 1f, 0f, 1f);
                }
                    
                else if (Board.checkSquare(x, y) == 3) {
                    
                    //draw green
                    if (flip == false)
                        drawQuad((int)((x - 1) * squareSizeX) + boardPosX + 1, (int)((y - 1) * squareSizeY) + boardPosY + 1, (int)(x * squareSizeX) + boardPosX - 1, (int)(y * squareSizeY) + boardPosY - 1, 0f, 1f, 0f);
                    else
                        drawQuad((int)((boardSquaresX + 1 - x - 1) * squareSizeX) + boardPosX + 1, (int)((boardSquaresY + 1 - y - 1) * squareSizeY) + boardPosY + 1, (int)((boardSquaresX + 1 - x) * squareSizeX) + boardPosX - 1, (int)((boardSquaresY + 1 - y) * squareSizeY) + boardPosY - 1, 0f, 1f, 0f);

                }
                
                else if (Board.checkSquare(x, y) == 4) {
                    
                    //draw dark blue
                    if (flip == false)
                        drawQuad((int)((x - 1) * squareSizeX) + boardPosX + 1, (int)((y - 1) * squareSizeY) + boardPosY + 1, (int)(x * squareSizeX) + boardPosX - 1, (int)(y * squareSizeY) + boardPosY - 1, 0f, 0f, 0.5f);
                    else
                        drawQuad((int)((boardSquaresX + 1 - x - 1) * squareSizeX) + boardPosX + 1, (int)((boardSquaresY + 1 - y - 1) * squareSizeY) + boardPosY + 1, (int)((boardSquaresX + 1 - x) * squareSizeX) + boardPosX - 1, (int)((boardSquaresY + 1 - y) * squareSizeY) + boardPosY - 1, 0f, 0f, 0.5f);
                }
                
                else if (Board.checkSquare(x, y) == 5) {
                    
                    //draw dark red
                    if (flip == false)
                        drawQuad((int)((x - 1) * squareSizeX) + boardPosX + 1, (int)((y - 1) * squareSizeY) + boardPosY + 1, (int)(x * squareSizeX) + boardPosX - 1, (int)(y * squareSizeY) + boardPosY - 1, 0.5f, 0f, 0f);
                    else
                        drawQuad((int)((boardSquaresX + 1 - x - 1) * squareSizeX) + boardPosX + 1, (int)((boardSquaresY + 1 - y - 1) * squareSizeY) + boardPosY + 1, (int)((boardSquaresX + 1 - x) * squareSizeX) + boardPosX - 1, (int)((boardSquaresY + 1 - y) * squareSizeY) + boardPosY - 1, 0.5f, 0f, 0f);

                }    
            }
        
        drawUnits();
        
        //outline selected square. do not question the code. it just works
        if (Inputs.selectedSquare[0] != 0 && Inputs.selectedSquare[1] != 0) {
            
            //if selected square is not a green square, draw green outline
            if (Board.checkSquare(Inputs.selectedSquare[0], Inputs.selectedSquare[1]) != 3) {
                
                if (flip == false) {

                    //left vertical
                    drawQuad((int)((Inputs.selectedSquare[0] - 1) * squareSizeX) + boardPosX + 1 - 1, (int)((Inputs.selectedSquare[1] - 1) * squareSizeY) + boardPosY + 2 - 1, (int)((Inputs.selectedSquare[0] - 1) * squareSizeX) + boardPosX + 3, (int)(Inputs.selectedSquare[1] * squareSizeY) + boardPosY - 2 + 1, 0f, 1f, 0f);
                    //top horizontal
                    drawQuad((int)((Inputs.selectedSquare[0] - 1) * squareSizeX) + boardPosX + 1 - 1, (int)(Inputs.selectedSquare[1] * squareSizeY) + boardPosY - 1 + 1, (int)(Inputs.selectedSquare[0] * squareSizeX) + boardPosX - 2 + 1, (int)(Inputs.selectedSquare[1] * squareSizeY) + boardPosY - 3, 0f, 1f, 0f);
                    //right vertical
                    drawQuad((int)(Inputs.selectedSquare[0] * squareSizeX) + boardPosX - 2 + 1, (int)(Inputs.selectedSquare[1] * squareSizeY) + boardPosY - 2 + 1, (int)(Inputs.selectedSquare[0] * squareSizeX) + boardPosX - 4, (int)((Inputs.selectedSquare[1] - 1) * squareSizeY) + boardPosY + 2 - 1, 0f, 1f, 0f);
                    //bottom horizontal
                    drawQuad((int)(Inputs.selectedSquare[0] * squareSizeX) + boardPosX - 2 + 1, (int)((Inputs.selectedSquare[1] - 1) * squareSizeY) + boardPosY + 2 - 1, (int)((Inputs.selectedSquare[0] - 1) * squareSizeX) + boardPosX + 2 - 1, (int)((Inputs.selectedSquare[1] - 1) * squareSizeY) + boardPosY + 4, 0f, 1f, 0f);
                }

                else {

                    drawQuad((int)((boardSquaresX + 1 - Inputs.selectedSquare[0] - 1) * squareSizeX) + boardPosX + 1 - 1, (int)((boardSquaresY + 1 - Inputs.selectedSquare[1] - 1) * squareSizeY) + boardPosY + 2 - 1, (int)((boardSquaresX + 1 - Inputs.selectedSquare[0] - 1) * squareSizeX) + boardPosX + 3, (int)((boardSquaresY + 1 - Inputs.selectedSquare[1]) * squareSizeY) + boardPosY - 2 + 1, 0f, 1f, 0f);
                    drawQuad((int)((boardSquaresX + 1 - Inputs.selectedSquare[0] - 1) * squareSizeX) + boardPosX + 1 - 1, (int)((boardSquaresY + 1 - Inputs.selectedSquare[1]) * squareSizeY) + boardPosY - 1 + 1, (int)((boardSquaresX + 1 - Inputs.selectedSquare[0]) * squareSizeX) + boardPosX - 2 + 1, (int)((boardSquaresY + 1 - Inputs.selectedSquare[1]) * squareSizeY) + boardPosY - 3, 0f, 1f, 0f);
                    drawQuad((int)((boardSquaresX + 1 - Inputs.selectedSquare[0]) * squareSizeX) + boardPosX - 2 + 1, (int)((boardSquaresY + 1 - Inputs.selectedSquare[1]) * squareSizeY) + boardPosY - 2 + 1, (int)((boardSquaresX + 1 - Inputs.selectedSquare[0]) * squareSizeX) + boardPosX - 4, (int)((boardSquaresY + 1 - Inputs.selectedSquare[1] - 1) * squareSizeY) + boardPosY + 2 - 1, 0f, 1f, 0f);
                    drawQuad((int)((boardSquaresX + 1 - Inputs.selectedSquare[0]) * squareSizeX) + boardPosX - 2 + 1, (int)((boardSquaresY + 1 - Inputs.selectedSquare[1] - 1) * squareSizeY) + boardPosY + 2 - 1, (int)((boardSquaresX + 1 - Inputs.selectedSquare[0] - 1) * squareSizeX) + boardPosX + 2 - 1, (int)((boardSquaresY + 1 - Inputs.selectedSquare[1] - 1) * squareSizeY) + boardPosY + 4, 0f, 1f, 0f);
                }
            }
            
            //if selected square is a green square, draw purple outline
            else {
                
                if (flip == false) {

                    //left vertical
                    drawQuad((int)((Inputs.selectedSquare[0] - 1) * squareSizeX) + boardPosX + 1 - 1, (int)((Inputs.selectedSquare[1] - 1) * squareSizeY) + boardPosY + 2 - 1, (int)((Inputs.selectedSquare[0] - 1) * squareSizeX) + boardPosX + 3, (int)(Inputs.selectedSquare[1] * squareSizeY) + boardPosY - 2 + 1, 1f, 0f, 1f);
                    //top horizontal
                    drawQuad((int)((Inputs.selectedSquare[0] - 1) * squareSizeX) + boardPosX + 1 - 1, (int)(Inputs.selectedSquare[1] * squareSizeY) + boardPosY - 1 + 1, (int)(Inputs.selectedSquare[0] * squareSizeX) + boardPosX - 2 + 1, (int)(Inputs.selectedSquare[1] * squareSizeY) + boardPosY - 3, 1f, 0f, 1f);
                    //right vertical
                    drawQuad((int)(Inputs.selectedSquare[0] * squareSizeX) + boardPosX - 2 + 1, (int)(Inputs.selectedSquare[1] * squareSizeY) + boardPosY - 2 + 1, (int)(Inputs.selectedSquare[0] * squareSizeX) + boardPosX - 4, (int)((Inputs.selectedSquare[1] - 1) * squareSizeY) + boardPosY + 2 - 1, 1f, 0f, 1f);
                    //bottom horizontal
                    drawQuad((int)(Inputs.selectedSquare[0] * squareSizeX) + boardPosX - 2 + 1, (int)((Inputs.selectedSquare[1] - 1) * squareSizeY) + boardPosY + 2 - 1, (int)((Inputs.selectedSquare[0] - 1) * squareSizeX) + boardPosX + 2 - 1, (int)((Inputs.selectedSquare[1] - 1) * squareSizeY) + boardPosY + 4, 1f, 0f, 1f);
                }

                else {

                    drawQuad((int)((boardSquaresX + 1 - Inputs.selectedSquare[0] - 1) * squareSizeX) + boardPosX + 1 - 1, (int)((boardSquaresY + 1 - Inputs.selectedSquare[1] - 1) * squareSizeY) + boardPosY + 2 - 1, (int)((boardSquaresX + 1 - Inputs.selectedSquare[0] - 1) * squareSizeX) + boardPosX + 3, (int)((boardSquaresY + 1 - Inputs.selectedSquare[1]) * squareSizeY) + boardPosY - 2 + 1, 1f, 0f, 1f);
                    drawQuad((int)((boardSquaresX + 1 - Inputs.selectedSquare[0] - 1) * squareSizeX) + boardPosX + 1 - 1, (int)((boardSquaresY + 1 - Inputs.selectedSquare[1]) * squareSizeY) + boardPosY - 1 + 1, (int)((boardSquaresX + 1 - Inputs.selectedSquare[0]) * squareSizeX) + boardPosX - 2 + 1, (int)((boardSquaresY + 1 - Inputs.selectedSquare[1]) * squareSizeY) + boardPosY - 3, 1f, 0f, 1f);
                    drawQuad((int)((boardSquaresX + 1 - Inputs.selectedSquare[0]) * squareSizeX) + boardPosX - 2 + 1, (int)((boardSquaresY + 1 - Inputs.selectedSquare[1]) * squareSizeY) + boardPosY - 2 + 1, (int)((boardSquaresX + 1 - Inputs.selectedSquare[0]) * squareSizeX) + boardPosX - 4, (int)((boardSquaresY + 1 - Inputs.selectedSquare[1] - 1) * squareSizeY) + boardPosY + 2 - 1, 1f, 0f, 1f);
                    drawQuad((int)((boardSquaresX + 1 - Inputs.selectedSquare[0]) * squareSizeX) + boardPosX - 2 + 1, (int)((boardSquaresY + 1 - Inputs.selectedSquare[1] - 1) * squareSizeY) + boardPosY + 2 - 1, (int)((boardSquaresX + 1 - Inputs.selectedSquare[0] - 1) * squareSizeX) + boardPosX + 2 - 1, (int)((boardSquaresY + 1 - Inputs.selectedSquare[1] - 1) * squareSizeY) + boardPosY + 4, 1f, 0f, 1f);
                }
            }
        }
    }
    
    public static void drawSelectUnit() {
        
        //make sure the area around it is black
        drawQuad(selectUnitPosX - 20, 0, selectUnitPosX + selectUnitSizeX + 20, selectUnitPosY + selectUnitSizeY + 20, clearColor[0], clearColor[1], clearColor[2]);
        drawQuad(selectUnitPosX, selectUnitPosY, selectUnitPosX + selectUnitSizeX, selectUnitPosY + selectUnitSizeY, 1, 1, 1);
        
        for (int i = 0; i < 10; i++) {
            
            drawQuad(selectUnitPosX + 25 + (i * 102), selectUnitPosY + selectUnitSizeY - 70, selectUnitPosX + 25 + (i * 102) + 41, selectUnitPosY + selectUnitSizeY - 29, 1, 1, 1);
            drawQuad(selectUnitPosX + 25 + (i * 102) + 41, selectUnitPosY + selectUnitSizeY - 70, selectUnitPosX + 25 + (i * 102) + 82, selectUnitPosY + selectUnitSizeY - 29, 0, 1, 0);
            drawQuad(selectUnitPosX + 25 + (i * 102), selectUnitPosY + selectUnitSizeY - 111, selectUnitPosX + 25 + (i * 102) + 41, selectUnitPosY + selectUnitSizeY - 70, 1, 1, 1);
            drawQuad(selectUnitPosX + 25 + (i * 102) + 41, selectUnitPosY + selectUnitSizeY - 111, selectUnitPosX + 25 + (i * 102) + 82, selectUnitPosY + selectUnitSizeY - 70, 0.3f, 0.3f, 1);
            
            //outline
            drawLine(selectUnitPosX + 25 + (i * 102), selectUnitPosY + selectUnitSizeY - 29, selectUnitPosX + 25 + (i * 102) + 82, selectUnitPosY + selectUnitSizeY - 29, 1, 0, 0, 0);
            drawLine(selectUnitPosX + 25 + (i * 102), selectUnitPosY + selectUnitSizeY - 29, selectUnitPosX + 25 + (i * 102), selectUnitPosY + selectUnitSizeY - 111, 1, 0, 0, 0);
            drawLine(selectUnitPosX + 25 + (i * 102), selectUnitPosY + selectUnitSizeY - 111, selectUnitPosX + 25 + (i * 102) + 82, selectUnitPosY + selectUnitSizeY - 111, 1, 0, 0, 0);
            drawLine(selectUnitPosX + 25 + (i * 102) + 82, selectUnitPosY + selectUnitSizeY - 29, selectUnitPosX + 25 + (i * 102) + 82, selectUnitPosY + selectUnitSizeY - 111, 1, 0, 0, 0);
            drawLine(selectUnitPosX + 25 + (i * 102), selectUnitPosY + selectUnitSizeY - 70, selectUnitPosX + 25 + (i * 102) + 82, selectUnitPosY + selectUnitSizeY - 70, 1, 0, 0, 0);
            drawLine(selectUnitPosX + 25 + (i * 102) + 41, selectUnitPosY + selectUnitSizeY - 29, selectUnitPosX + 25 + (i * 102) + 41, selectUnitPosY + selectUnitSizeY - 111, 1, 0, 0, 0);
        }
        
        drawString(font14, selectUnitPosX + 25 + 1, selectUnitPosY + selectUnitSizeY - 38, "Archer", Color.black);
        drawString(font14, selectUnitPosX + 25 + 102, selectUnitPosY + selectUnitSizeY - 38, "Builder", Color.black);
        drawString(font14, selectUnitPosX + 25 + 204 + 1, selectUnitPosY + selectUnitSizeY - 38, "Healer", Color.black);
        drawString(font14, selectUnitPosX + 25 + 306 + 1, selectUnitPosY + selectUnitSizeY - 38, "Knight", Color.black);
        drawString(font14, selectUnitPosX + 25 + 408 + 4, selectUnitPosY + selectUnitSizeY - 38, "Mage", Color.black);
        drawString(font14, selectUnitPosX + 25 + 510 + 4, selectUnitPosY + selectUnitSizeY - 38, "Scout", Color.black);
        drawString(font14, selectUnitPosX + 25 + 612 + 10, selectUnitPosY + selectUnitSizeY - 38, "Spy", Color.black);
        drawString(font14, selectUnitPosX + 25 + 714 + 6, selectUnitPosY + selectUnitSizeY - 35, "TNT", Color.black);
        drawString(font14, selectUnitPosX + 25 + 714 + 6, selectUnitPosY + selectUnitSizeY - 46, "Guy", Color.black);
        drawString(font14, selectUnitPosX + 25 + 816 + 6, selectUnitPosY + selectUnitSizeY - 35, "Heal", Color.black);
        drawString(font14, selectUnitPosX + 25 + 816 + 9, selectUnitPosY + selectUnitSizeY - 46, "Bot", Color.black);
        drawString(font14, selectUnitPosX + 25 + 918 + 1, selectUnitPosY + selectUnitSizeY - 38, "Statue", Color.black);
        
        //purple bottom left square for active units
        for (int i = 0; i < Inputs.activeUnits.size(); i++) {
            
            if (Inputs.activeUnits.get(i).unitClass == "Archer")
                drawQuad(selectUnitPosX + 25, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 - 1, selectUnitPosY + selectUnitSizeY - 70, 1, 0, 1);
            else if (Inputs.activeUnits.get(i).unitClass == "Builder")
                drawQuad(selectUnitPosX + 25 + 102, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 102 + 41 - 1, selectUnitPosY + selectUnitSizeY - 70, 1, 0, 1);
            else if (Inputs.activeUnits.get(i).unitClass == "Healer")
                drawQuad(selectUnitPosX + 25 + (2 * 102), selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + (2 * 102) + 41 - 1, selectUnitPosY + selectUnitSizeY - 70, 1, 0, 1);
            else if (Inputs.activeUnits.get(i).unitClass == "Knight")
                drawQuad(selectUnitPosX + 25 + (3 * 102), selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + (3 * 102) + 41 - 1, selectUnitPosY + selectUnitSizeY - 70, 1, 0, 1);
            else if (Inputs.activeUnits.get(i).unitClass == "Mage")
                drawQuad(selectUnitPosX + 25 + (4 * 102), selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + (4 * 102) + 41 - 1, selectUnitPosY + selectUnitSizeY - 70, 1, 0, 1);
            else if (Inputs.activeUnits.get(i).unitClass == "Scout")
                drawQuad(selectUnitPosX + 25 + (5 * 102), selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + (5 * 102) + 41 - 1, selectUnitPosY + selectUnitSizeY - 70, 1, 0, 1);
            else if (Inputs.activeUnits.get(i).unitClass == "Spy")
                drawQuad(selectUnitPosX + 25 + (6 * 102), selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + (6 * 102) + 41 - 1, selectUnitPosY + selectUnitSizeY - 70, 1, 0, 1);
            else if (Inputs.activeUnits.get(i).unitClass == "TNTGuy")
                drawQuad(selectUnitPosX + 25 + (7 * 102), selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + (7 * 102) + 41 - 1, selectUnitPosY + selectUnitSizeY - 70, 1, 0, 1);
        }
        
        //stats
        if (playerTurn == "blue") {
            
            //dark green top right square for poisoned units
            if (Game.blueArcher.poisonTime > 0 && Game.blueArcher.alive)
                drawQuad(selectUnitPosX + 25 + 41, selectUnitPosY + selectUnitSizeY - 70 + 1, selectUnitPosX + 25 + 82 - 1, selectUnitPosY + selectUnitSizeY - 29, 0f, 0.6f, 0f);
            if (Game.blueBuilder.poisonTime > 0 && Game.blueBuilder.alive)
                drawQuad(selectUnitPosX + 25 + 41 + 102, selectUnitPosY + selectUnitSizeY - 70 + 1, selectUnitPosX + 25 + 82 + 102 - 1, selectUnitPosY + selectUnitSizeY - 29, 0f, 0.6f, 0f);
            if (Game.blueHealer.poisonTime > 0 && Game.blueHealer.alive)
                drawQuad(selectUnitPosX + 25 + 41 + 204, selectUnitPosY + selectUnitSizeY - 70 + 1, selectUnitPosX + 25 + 82 + 204 - 1, selectUnitPosY + selectUnitSizeY - 29, 0f, 0.6f, 0f);
            if (Game.blueKnight.poisonTime > 0 && Game.blueKnight.alive)
                drawQuad(selectUnitPosX + 25 + 41 + 306, selectUnitPosY + selectUnitSizeY - 70 + 1, selectUnitPosX + 25 + 82 + 306 - 1, selectUnitPosY + selectUnitSizeY - 29, 0f, 0.6f, 0f);
            if (Game.blueMage.poisonTime > 0 && Game.blueMage.alive)
                drawQuad(selectUnitPosX + 25 + 41 + 408, selectUnitPosY + selectUnitSizeY - 70 + 1, selectUnitPosX + 25 + 82 + 408 - 1, selectUnitPosY + selectUnitSizeY - 29, 0f, 0.6f, 0f);
            if (Game.blueScout.poisonTime > 0 && Game.blueScout.alive)
                drawQuad(selectUnitPosX + 25 + 41 + 510, selectUnitPosY + selectUnitSizeY - 70 + 1, selectUnitPosX + 25 + 82 + 510 - 1, selectUnitPosY + selectUnitSizeY - 29, 0f, 0.6f, 0f);
            if (Game.blueSpy.poisonTime > 0 && Game.blueSpy.alive)
                drawQuad(selectUnitPosX + 25 + 41 + 612, selectUnitPosY + selectUnitSizeY - 70 + 1, selectUnitPosX + 25 + 82 + 612 - 1, selectUnitPosY + selectUnitSizeY - 29, 0f, 0.6f, 0f);
            if (Game.blueTNTGuy.poisonTime > 0 && Game.blueTNTGuy.alive)
                drawQuad(selectUnitPosX + 25 + 41 + 714, selectUnitPosY + selectUnitSizeY - 70 + 1, selectUnitPosX + 25 + 82 + 714 - 1, selectUnitPosY + selectUnitSizeY - 29, 0f, 0.6f, 0f);
            
            //health
            if (Game.blueArcher.health >= 0 && Game.blueArcher.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.blueArcher.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            if (Game.blueBuilder.health >= 0 && Game.blueBuilder.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 102 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.blueBuilder.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 102 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            if (Game.blueHealer.health >= 0 && Game.blueHealer.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 204 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.blueHealer.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 204 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            if (Game.blueKnight.health >= 0 && Game.blueKnight.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 306 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.blueKnight.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 306 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            if (Game.blueMage.health >= 0 && Game.blueMage.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 408 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.blueMage.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 408 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            if (Game.blueScout.health >= 0 && Game.blueScout.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 510 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.blueScout.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 510 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            if (Game.blueSpy.health >= 0 && Game.blueSpy.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 612 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.blueSpy.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 612 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            if (Game.blueTNTGuy.health >= 0 && Game.blueTNTGuy.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 714 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.blueTNTGuy.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 714 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            if (Game.blueHealBot.health >= 0 && Game.blueHealBot.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 816 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.blueHealBot.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 816 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            if (Game.blueStatue.health >= 0 && Game.blueStatue.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 918 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.blueStatue.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 918 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            
            //respawn
            if (Game.blueArcher.alive == false) {
                
                drawQuad(selectUnitPosX + 25, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
                drawString(font24, selectUnitPosX + 25 + 1, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.blueArcher.respawnTime + ">", Color.black);
            }
            
            if (Game.blueBuilder.alive == false) {
                
                drawQuad(selectUnitPosX + 25 + 102, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 102 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
                drawString(font24, selectUnitPosX + 25 + 102 + 1, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.blueBuilder.respawnTime + ">", Color.black);
            }
            
            if (Game.blueHealer.alive == false) {
                
                drawQuad(selectUnitPosX + 25 + 204, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 204 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
                drawString(font24, selectUnitPosX + 25 + 204 + 1, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.blueHealer.respawnTime + ">", Color.black);
            }
            
            if (Game.blueKnight.alive == false) {
                
                drawQuad(selectUnitPosX + 25 + 306, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 306 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
                drawString(font24, selectUnitPosX + 25 + 306 + 1, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.blueKnight.respawnTime + ">", Color.black);
            }
            
            if (Game.blueMage.alive == false) {
                
                drawQuad(selectUnitPosX + 25 + 408, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 408 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
                drawString(font24, selectUnitPosX + 25 + 408 + 1, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.blueBuilder.respawnTime + ">", Color.black);
            }
            
            if (Game.blueScout.alive == false) {
                
                drawQuad(selectUnitPosX + 25 + 510, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 510 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
                drawString(font24, selectUnitPosX + 25 + 510 + 1, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.blueScout.respawnTime + ">", Color.black);
            }
            
            if (Game.blueSpy.alive == false) {
                
                drawQuad(selectUnitPosX + 25 + 612, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 612 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
                drawString(font24, selectUnitPosX + 25 + 612 + 1, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.blueSpy.respawnTime + ">", Color.black);
            }
            
            if (Game.blueTNTGuy.alive == false) {
                
                drawQuad(selectUnitPosX + 25 + 714, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 714 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
                drawString(font24, selectUnitPosX + 25 + 714 + 1, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.blueTNTGuy.respawnTime + ">", Color.black);
            }
            
            if (Game.blueHealBot.alive == false) {
                
                drawQuad(selectUnitPosX + 25 + 816, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 816 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
            }
            
            if (Game.blueStatue.alive == false) {
                
                drawQuad(selectUnitPosX + 25 + 918, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 918 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
            }
            
            //bottom left square is green if unit has used all movement and attacks
            if (Game.blueArcher.movesLeft == 0 && Game.blueArcher.attacksLeft == 0 && Game.blueArcher.alive == true /**/&& Game.blueArcher.changeDirectionsLeft == 0/**/) {
                
                drawQuad(selectUnitPosX + 25, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 - 1, selectUnitPosY + selectUnitSizeY - 70, 0f, 1f, 0f);
            }
            
            if (Game.blueBuilder.movesLeft == 0 && Game.blueBuilder.attacksLeft == 0 && Game.blueBuilder.alive == true /**/&& Game.blueBuilder.changeDirectionsLeft == 0/**/) {
                
                drawQuad(selectUnitPosX + 25 + 102, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 102 - 1, selectUnitPosY + selectUnitSizeY - 70, 0f, 1f, 0f);
            }
            
            if (Game.blueHealer.movesLeft == 0 && Game.blueHealer.attacksLeft == 0 && Game.blueHealer.freeAttacksLeft == 0 && Game.blueHealer.alive == true /**/&& Game.blueHealer.changeDirectionsLeft == 0/**/) {
                
                drawQuad(selectUnitPosX + 25 + 204, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 204 - 1, selectUnitPosY + selectUnitSizeY - 70, 0f, 1f, 0f);
            }
            
            if (Game.blueKnight.movesLeft == 0 && Game.blueKnight.attacksLeft == 0 && Game.blueKnight.alive == true /**/&& Game.blueKnight.changeDirectionsLeft == 0/**/) {
                
                drawQuad(selectUnitPosX + 25 + 306, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 306 - 1, selectUnitPosY + selectUnitSizeY - 70, 0f, 1f, 0f);
            }
            
            if (Game.blueMage.movesLeft == 0 && Game.blueMage.attacksLeft == 0 && Game.blueMage.alive == true /**/&& Game.blueMage.changeDirectionsLeft == 0/**/) {
                
                drawQuad(selectUnitPosX + 25 + 408, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 408 - 1, selectUnitPosY + selectUnitSizeY - 70, 0f, 1f, 0f);
            }
            
            if (Game.blueScout.movesLeft == 0 && Game.blueScout.attacksLeft == 0 && Game.blueScout.alive == true /**/&& Game.blueScout.changeDirectionsLeft == 0/**/) {
                
                drawQuad(selectUnitPosX + 25 + 510, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 510 - 1, selectUnitPosY + selectUnitSizeY - 70, 0f, 1f, 0f);
            }
            
            if (Game.blueSpy.movesLeft == 0 && Game.blueSpy.attacksLeft == 0 && Game.blueSpy.alive == true /**/&& Game.blueSpy.changeDirectionsLeft == 0/**/) {
                
                drawQuad(selectUnitPosX + 25 + 612, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 612 - 1, selectUnitPosY + selectUnitSizeY - 70, 0f, 1f, 0f);
            }
            
            if (Game.blueTNTGuy.movesLeft == 0 && Game.blueTNTGuy.attacksLeft == 0 && Game.blueTNTGuy.alive == true /**/&& Game.blueTNTGuy.changeDirectionsLeft == 0/**/) {
                
                drawQuad(selectUnitPosX + 25 + 714, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 714 - 1, selectUnitPosY + selectUnitSizeY - 70, 0f, 1f, 0f);
            }
            
            //light-blue bottom right square if superCharge is activated
            
            if (Game.blueHealer.superCharge)
                drawQuad(selectUnitPosX + 25 + 204 + 41, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 204 + 82 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.6f, 0.6f, 1);
            
            //grey bottom right square if building is being built or EMP
            
            if (Game.blueHealBot.alive && (!Game.blueHealBot.built || Game.blueHealBot.EMP))
                drawQuad(selectUnitPosX + 25 + 816 + 41, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 816 + 82 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.6f, 0.6f, 0.6f);
            
            if (Game.blueStatue.alive && (!Game.blueStatue.built || Game.blueStatue.EMP))
                drawQuad(selectUnitPosX + 25 + 918 + 41, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 918 + 82 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.6f, 0.6f, 0.6f);

            //healer mana
            
            //if less than 2 digits
            if (Game.blueHealer.mana < 10)
                drawString(font24, selectUnitPosX + 25 + 204 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.blueHealer.mana + ">", Color.black);
            else
                drawString(font18, selectUnitPosX + 25 + 204 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75 - 3, "<" + Game.blueHealer.mana + ">", Color.black);
            
            //archer charge
            
            drawString(font24, selectUnitPosX + 25 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.blueArcher.bowCharge + ">", Color.black);
            
            //spy cloak
            
            if (Game.blueSpy.invis)
                //draw closed eye
                drawString(font24, selectUnitPosX + 25 + 612 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75, "<=>", Color.black);
            
            else
                //draw open eye
                drawString(font24, selectUnitPosX + 25 + 612 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75, "<0>", Color.black);
            
            //knight charge cooldown
            
            drawString(font24, selectUnitPosX + 25 + 306 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.blueKnight.chargeAttackCooldownTime + ">", Color.black);
            
            //EMP
            
//            if (Game.blueHealBot.EMP && Game.blueHealBot.alive)
//                //draw /*/
//                drawString(font24, selectUnitPosX + 25 + 816 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75, "<#>", Color.black);
//            
//            else if (Game.blueHealBot.alive)
//                //draw <0>
//                drawString(font24, selectUnitPosX + 25 + 816 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75, "<0>", Color.black);
//            
//            if (Game.blueStatue.EMP && Game.blueStatue.alive)
//                //draw /*/
//                drawString(font24, selectUnitPosX + 25 + 918 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75, "<#>", Color.black);
//            
//            else if (Game.blueStatue.alive)
//                //draw <0>
//                drawString(font24, selectUnitPosX + 25 + 918 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75, "<0>", Color.black);
        }
        
        else {
            
            //dark green top right square for poisoned units
            if (Game.redArcher.poisonTime > 0 && Game.redArcher.alive)
                drawQuad(selectUnitPosX + 25 + 41, selectUnitPosY + selectUnitSizeY - 70 + 1, selectUnitPosX + 25 + 82 - 1, selectUnitPosY + selectUnitSizeY - 29, 0f, 0.6f, 0f);
            if (Game.redBuilder.poisonTime > 0 && Game.redBuilder.alive)
                drawQuad(selectUnitPosX + 25 + 41 + 102, selectUnitPosY + selectUnitSizeY - 70 + 1, selectUnitPosX + 25 + 82 + 102 - 1, selectUnitPosY + selectUnitSizeY - 29, 0f, 0.6f, 0f);
            if (Game.redHealer.poisonTime > 0 && Game.redHealer.alive)
                drawQuad(selectUnitPosX + 25 + 41 + 204, selectUnitPosY + selectUnitSizeY - 70 + 1, selectUnitPosX + 25 + 82 + 204 - 1, selectUnitPosY + selectUnitSizeY - 29, 0f, 0.6f, 0f);
            if (Game.redKnight.poisonTime > 0 && Game.redKnight.alive)
                drawQuad(selectUnitPosX + 25 + 41 + 306, selectUnitPosY + selectUnitSizeY - 70 + 1, selectUnitPosX + 25 + 82 + 306 - 1, selectUnitPosY + selectUnitSizeY - 29, 0f, 0.6f, 0f);
            if (Game.redMage.poisonTime > 0 && Game.redMage.alive)
                drawQuad(selectUnitPosX + 25 + 41 + 408, selectUnitPosY + selectUnitSizeY - 70 + 1, selectUnitPosX + 25 + 82 + 408 - 1, selectUnitPosY + selectUnitSizeY - 29, 0f, 0.6f, 0f);
            if (Game.redScout.poisonTime > 0 && Game.redScout.alive)
                drawQuad(selectUnitPosX + 25 + 41 + 510, selectUnitPosY + selectUnitSizeY - 70 + 1, selectUnitPosX + 25 + 82 + 510 - 1, selectUnitPosY + selectUnitSizeY - 29, 0f, 0.6f, 0f);
            if (Game.redSpy.poisonTime > 0 && Game.redSpy.alive)
                drawQuad(selectUnitPosX + 25 + 41 + 612, selectUnitPosY + selectUnitSizeY - 70 + 1, selectUnitPosX + 25 + 82 + 612 - 1, selectUnitPosY + selectUnitSizeY - 29, 0f, 0.6f, 0f);
            if (Game.redTNTGuy.poisonTime > 0 && Game.redTNTGuy.alive)
                drawQuad(selectUnitPosX + 25 + 41 + 714, selectUnitPosY + selectUnitSizeY - 70 + 1, selectUnitPosX + 25 + 82 + 714 - 1, selectUnitPosY + selectUnitSizeY - 29, 0f, 0.6f, 0f);
            
            //health
            if (Game.redArcher.health >= 0 && Game.redArcher.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.redArcher.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            if (Game.redBuilder.health >= 0 && Game.redBuilder.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 102 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.redBuilder.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 102 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            if (Game.redHealer.health >= 0 && Game.redHealer.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 204 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.redHealer.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 204 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            if (Game.redKnight.health >= 0 && Game.redKnight.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 306 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.redKnight.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 306 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            if (Game.redMage.health >= 0 && Game.redMage.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 408 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.redMage.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 408 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            if (Game.redScout.health >= 0 && Game.redScout.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 510 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.redScout.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 510 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            if (Game.redSpy.health >= 0 && Game.redSpy.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 612 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.redSpy.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 612 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            if (Game.redTNTGuy.health >= 0 && Game.redTNTGuy.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 714 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.redTNTGuy.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 714 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            if (Game.redHealBot.health >= 0 && Game.redHealBot.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 816 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.redHealBot.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 816 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            if (Game.redStatue.health >= 0 && Game.redStatue.alive)
                drawString(font24, selectUnitPosX + 25 + 41 + 918 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + Game.redStatue.health + ">", Color.black);
            else
                drawString(font24, selectUnitPosX + 25 + 41 + 918 + 1 + 1, selectUnitPosY + selectUnitSizeY - 34, "<" + 0 + ">", Color.black);
            
            //respawn
            if (Game.redArcher.alive == false) {
                
                drawQuad(selectUnitPosX + 25, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
                drawString(font24, selectUnitPosX + 25 + 1, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.redArcher.respawnTime + ">", Color.black);
            }
            
            if (Game.redBuilder.alive == false) {
                
                drawQuad(selectUnitPosX + 25 + 102, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 102 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
                drawString(font24, selectUnitPosX + 25 + 102 + 1, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.redBuilder.respawnTime + ">", Color.black);
            }
            
            if (Game.redHealer.alive == false) {
                
                drawQuad(selectUnitPosX + 25 + 204, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 204 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
                drawString(font24, selectUnitPosX + 25 + 204 + 1, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.redHealer.respawnTime + ">", Color.black);
            }
            
            if (Game.redKnight.alive == false) {
                
                drawQuad(selectUnitPosX + 25 + 306, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 306 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
                drawString(font24, selectUnitPosX + 25 + 306 + 1, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.redKnight.respawnTime + ">", Color.black);
            }
            
            if (Game.redMage.alive == false) {
                
                drawQuad(selectUnitPosX + 25 + 408, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 408 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
                drawString(font24, selectUnitPosX + 25 + 408 + 1, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.redBuilder.respawnTime + ">", Color.black);
            }
            
            if (Game.redScout.alive == false) {
                
                drawQuad(selectUnitPosX + 25 + 510, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 510 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
                drawString(font24, selectUnitPosX + 25 + 510 + 1, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.redScout.respawnTime + ">", Color.black);
            }
            
            if (Game.redSpy.alive == false) {
                
                drawQuad(selectUnitPosX + 25 + 612, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 612 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
                drawString(font24, selectUnitPosX + 25 + 612 + 1, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.redSpy.respawnTime + ">", Color.black);
            }
            
            if (Game.redTNTGuy.alive == false) {
                
                drawQuad(selectUnitPosX + 25 + 714, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 714 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
                drawString(font24, selectUnitPosX + 25 + 714 + 1, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.redTNTGuy.respawnTime + ">", Color.black);
            }
            
            if (Game.redHealBot.alive == false) {
                
                drawQuad(selectUnitPosX + 25 + 816, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 816 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
            }
            
            if (Game.redStatue.alive == false) {
                
                drawQuad(selectUnitPosX + 25 + 918, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 918 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.7f, 0.7f, 0.7f);
            }
            
            //bottom left square is green if unit has used all movement and attacks
            if (Game.redArcher.movesLeft == 0 && Game.redArcher.attacksLeft == 0 && Game.redArcher.alive == true /**/&& Game.redArcher.changeDirectionsLeft == 0/**/) {
                
                drawQuad(selectUnitPosX + 25, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 - 1, selectUnitPosY + selectUnitSizeY - 70, 0f, 1f, 0f);
            }
            
            if (Game.redBuilder.movesLeft == 0 && Game.redBuilder.attacksLeft == 0 && Game.redBuilder.alive == true /**/&& Game.redBuilder.changeDirectionsLeft == 0/**/) {
                
                drawQuad(selectUnitPosX + 25 + 102, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 102 - 1, selectUnitPosY + selectUnitSizeY - 70, 0f, 1f, 0f);
            }
            
            if (Game.redHealer.movesLeft == 0 && Game.redHealer.attacksLeft == 0 && Game.redHealer.freeAttacksLeft == 0 && Game.redHealer.alive == true /**/&& Game.redHealer.changeDirectionsLeft == 0/**/) {
                
                drawQuad(selectUnitPosX + 25 + 204, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 204 - 1, selectUnitPosY + selectUnitSizeY - 70, 0f, 1f, 0f);
            }
            
            if (Game.redKnight.movesLeft == 0 && Game.redKnight.attacksLeft == 0 && Game.redKnight.alive == true /**/&& Game.redKnight.changeDirectionsLeft == 0/**/) {
                
                drawQuad(selectUnitPosX + 25 + 306, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 306 - 1, selectUnitPosY + selectUnitSizeY - 70, 0f, 1f, 0f);
            }
            
            if (Game.redMage.movesLeft == 0 && Game.redMage.attacksLeft == 0 && Game.redMage.alive == true /**/&& Game.redMage.changeDirectionsLeft == 0/**/) {
                
                drawQuad(selectUnitPosX + 25 + 408, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 408 - 1, selectUnitPosY + selectUnitSizeY - 70, 0f, 1f, 0f);
            }
            
            if (Game.redScout.movesLeft == 0 && Game.redScout.attacksLeft == 0 && Game.redScout.alive == true /**/&& Game.redScout.changeDirectionsLeft == 0/**/) {
                
                drawQuad(selectUnitPosX + 25 + 510, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 510 - 1, selectUnitPosY + selectUnitSizeY - 70, 0f, 1f, 0f);
            }
            
            if (Game.redSpy.movesLeft == 0 && Game.redSpy.attacksLeft == 0 && Game.redSpy.alive == true /**/&& Game.redSpy.changeDirectionsLeft == 0/**/) {
                
                drawQuad(selectUnitPosX + 25 + 612, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 612 - 1, selectUnitPosY + selectUnitSizeY - 70, 0f, 1f, 0f);
            }
            
            if (Game.redTNTGuy.movesLeft == 0 && Game.redTNTGuy.attacksLeft == 0 && Game.redTNTGuy.alive == true /**/&& Game.redTNTGuy.changeDirectionsLeft == 0/**/) {
                
                drawQuad(selectUnitPosX + 25 + 714, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 41 + 714 - 1, selectUnitPosY + selectUnitSizeY - 70, 0f, 1f, 0f);
            }
            
            //light-blue bottom right square if superCharge is activated
            
            if (Game.redHealer.superCharge)
                drawQuad(selectUnitPosX + 25 + 204 + 41, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 204 + 82 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.75f, 0.75f, 1);
            
            //grey bottom right square if building is being built or EMP
            
            if (Game.redHealBot.alive && (!Game.redHealBot.built || Game.redHealBot.EMP))
                drawQuad(selectUnitPosX + 25 + 816 + 41, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 816 + 82 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.6f, 0.6f, 0.6f);
            
            if (Game.redStatue.alive && (!Game.redStatue.built || Game.redStatue.EMP))
                drawQuad(selectUnitPosX + 25 + 918 + 41, selectUnitPosY + selectUnitSizeY - 111 + 1, selectUnitPosX + 25 + 918 + 82 - 1, selectUnitPosY + selectUnitSizeY - 70, 0.6f, 0.6f, 0.6f);
            
            //healer mana
            
            //if less than 2 digits
            if (Game.redHealer.mana < 10)
                drawString(font24, selectUnitPosX + 25 + 204 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.redHealer.mana + ">", Color.black);
            else
                drawString(font18, selectUnitPosX + 25 + 204 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75 - 3, "<" + Game.redHealer.mana + ">", Color.black);
            
            //archer charge
            
            drawString(font24, selectUnitPosX + 25 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.redArcher.bowCharge + ">", Color.black);
            
            //spy cloak
            
            if (Game.redSpy.invis)
                //draw closed eye
                drawString(font24, selectUnitPosX + 25 + 612 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75, "<=>", Color.black);
            
            else
                //draw open eye
                drawString(font24, selectUnitPosX + 25 + 612 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75, "<0>", Color.black);
            
            //knight charge cooldown
            
            drawString(font24, selectUnitPosX + 25 + 306 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75, "<" + Game.redKnight.chargeAttackCooldownTime + ">", Color.black);
            
            //EMP
            
//            if (Game.redHealBot.EMP && Game.redHealBot.alive)
//                //draw /*/
//                drawString(font24, selectUnitPosX + 25 + 816 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75, "<#>", Color.black);
//            
//            else if (Game.redHealBot.alive)
//                //draw <0>
//                drawString(font24, selectUnitPosX + 25 + 816 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75, "<0>", Color.black);
//            
//            if (Game.redStatue.EMP && Game.redStatue.alive)
//                //draw /*/
//                drawString(font24, selectUnitPosX + 25 + 918 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75, "<#>", Color.black);
//            
//            else if (Game.redStatue.alive)
//                //draw <0>
//                drawString(font24, selectUnitPosX + 25 + 918 + 41 + 2, selectUnitPosY + selectUnitSizeY - 75, "<0>", Color.black);
        }
        
        //red outline for selected unit
        if (selectedUnit != 0) {
            
            drawQuad(selectUnitPosX + 25 + ((selectedUnit - 1) * 102) - 3, selectUnitPosY + selectUnitSizeY - 29 + 1, selectUnitPosX + 25 + ((selectedUnit - 1) * 102) + 82 + 2, selectUnitPosY + selectUnitSizeY - 29 + 3, 1, 0, 0);
            drawQuad(selectUnitPosX + 25 + ((selectedUnit - 1) * 102) - 1, selectUnitPosY + selectUnitSizeY - 29 + 3, selectUnitPosX + 25 + ((selectedUnit - 1) * 102) - 3, selectUnitPosY + selectUnitSizeY - 111 - 2, 1, 0, 0);
            drawQuad(selectUnitPosX + 25 + ((selectedUnit - 1) * 102) - 3, selectUnitPosY + selectUnitSizeY - 111, selectUnitPosX + 25 + ((selectedUnit - 1) * 102) + 82 + 2, selectUnitPosY + selectUnitSizeY - 111 - 2, 1, 0, 0);
            drawQuad(selectUnitPosX + 25 + ((selectedUnit - 1) * 102) + 82, selectUnitPosY + selectUnitSizeY - 29 + 3, selectUnitPosX + 25 + ((selectedUnit - 1) * 102) + 82 + 2, selectUnitPosY + selectUnitSizeY - 111, 1, 0, 0);
        }
    }
    
    public static void drawFOVs() {
        
        selectedUnitDrawFOVFOV = 0;
        selectedUnitDrawFOVRange = 1;
        
        for (int i = 0; i < visUnits.size(); i++) {
            
            if (visUnits.get(i).direction < 90)
                directionAngle = 90 - visUnits.get(i).direction;
            else if (visUnits.get(i).direction >= 90)
                directionAngle = visUnits.get(i).direction - 90;
            else if (visUnits.get(i).direction >= 180)
                directionAngle = visUnits.get(i).direction - 180;
            else if (visUnits.get(i).direction >= 270)
                directionAngle = 270 - visUnits.get(i).direction;
            
            if (drawFullFOV && selectedUnitDrawFOV instanceof Unit) {
                
                if (selectedUnitDrawFOV.equals(visUnits.get(i))) {
                    
                    if (Inputs.attackNumber == 0) {
                        
                        if (visUnits.get(i).attack0Range.length > 0) {
                            
                            //line is as long as the longest range for attack 0
                            FOVlengthX = Math.abs((int)(Math.cos(Math.toRadians(directionAngle)) * visUnits.get(i).attack0Range[visUnits.get(i).attack0Range.length - 1] * squareSizeX));
                            FOVlengthY = Math.abs((int)(Math.sin(Math.toRadians(directionAngle)) * visUnits.get(i).attack0Range[visUnits.get(i).attack0Range.length - 1] * squareSizeY));
                            selectedUnitDrawFOVRange = visUnits.get(i).attack0Range[visUnits.get(i).attack0Range.length - 1];
                        }
                        
                        else {

                            FOVlengthX = Math.abs((int)(Math.cos(Math.toRadians(directionAngle)) * squareSizeX));
                            FOVlengthY = Math.abs((int)(Math.sin(Math.toRadians(directionAngle)) * squareSizeY));
                        }
                    }
                    
                    else if (Inputs.attackNumber == 1) {
                        
                        if (visUnits.get(i).attack1Range.length > 0) {
                            
                            FOVlengthX = Math.abs((int)(Math.cos(Math.toRadians(directionAngle)) * visUnits.get(i).attack1Range[visUnits.get(i).attack1Range.length - 1] * squareSizeX));
                            FOVlengthY = Math.abs((int)(Math.sin(Math.toRadians(directionAngle)) * visUnits.get(i).attack1Range[visUnits.get(i).attack1Range.length - 1] * squareSizeY));
                            selectedUnitDrawFOVRange = visUnits.get(i).attack1Range[visUnits.get(i).attack1Range.length - 1];
                        }
                        
                        else {

                            FOVlengthX = Math.abs((int)(Math.cos(Math.toRadians(directionAngle)) * squareSizeX));
                            FOVlengthY = Math.abs((int)(Math.sin(Math.toRadians(directionAngle)) * squareSizeY));
                        }
                    }
                    
                    else if (Inputs.attackNumber == 2) {
                        
                        if (visUnits.get(i).attack2Range.length > 0) {
                        
                            FOVlengthX = Math.abs((int)(Math.cos(Math.toRadians(directionAngle)) * visUnits.get(i).attack2Range[visUnits.get(i).attack2Range.length - 1] * squareSizeX));
                            FOVlengthY = Math.abs((int)(Math.sin(Math.toRadians(directionAngle)) * visUnits.get(i).attack2Range[visUnits.get(i).attack2Range.length - 1] * squareSizeY));
                            selectedUnitDrawFOVRange = visUnits.get(i).attack2Range[visUnits.get(i).attack2Range.length - 1];
                        }
                        
                        else {

                            FOVlengthX = Math.abs((int)(Math.cos(Math.toRadians(directionAngle)) * squareSizeX));
                            FOVlengthY = Math.abs((int)(Math.sin(Math.toRadians(directionAngle)) * squareSizeY));
                        }
                    }
                    
                    else if (Inputs.attackNumber == 3) {
                        
                        if (visUnits.get(i).attack3Range.length > 0) {
                        
                            FOVlengthX = Math.abs((int)(Math.cos(Math.toRadians(directionAngle)) * visUnits.get(i).attack3Range[visUnits.get(i).attack3Range.length - 1] * squareSizeX));
                            FOVlengthY = Math.abs((int)(Math.sin(Math.toRadians(directionAngle)) * visUnits.get(i).attack3Range[visUnits.get(i).attack3Range.length - 1] * squareSizeY));
                            selectedUnitDrawFOVRange = visUnits.get(i).attack3Range[visUnits.get(i).attack3Range.length - 1];
                        }
                        
                        else {

                            FOVlengthX = Math.abs((int)(Math.cos(Math.toRadians(directionAngle)) * squareSizeX));
                            FOVlengthY = Math.abs((int)(Math.sin(Math.toRadians(directionAngle)) * squareSizeY));
                        }
                    }

                    if (visUnits.get(i).direction > 90 && visUnits.get(i).direction < 270)
                        FOVlengthY = -1 * FOVlengthY;
                    if (visUnits.get(i).direction > 180 && visUnits.get(i).direction < 360)
                        FOVlengthX = -1 * FOVlengthX;
                }
                
                else {

                    FOVlengthX = Math.abs((int)(Math.cos(Math.toRadians(directionAngle)) * squareSizeX));
                    FOVlengthY = Math.abs((int)(Math.sin(Math.toRadians(directionAngle)) * squareSizeY));

                    if (visUnits.get(i).direction > 90 && visUnits.get(i).direction < 270)
                        FOVlengthY = -1 * FOVlengthY;
                    if (visUnits.get(i).direction > 180 && visUnits.get(i).direction < 360)
                        FOVlengthX = -1 * FOVlengthX;
                }
            }
            
            else {
                
                FOVlengthX = Math.abs((int)(Math.cos(Math.toRadians(directionAngle)) * squareSizeX));
                FOVlengthY = Math.abs((int)(Math.sin(Math.toRadians(directionAngle)) * squareSizeY));

                if (visUnits.get(i).direction > 90 && visUnits.get(i).direction < 270)
                    FOVlengthY = -1 * FOVlengthY;
                if (visUnits.get(i).direction > 180 && visUnits.get(i).direction < 360)
                    FOVlengthX = -1 * FOVlengthX;
            }
            
            if (flip) {
                
                FOVlengthX = -1 * FOVlengthX;
                FOVlengthY = -1 * FOVlengthY;
            }
            
            if (!visUnits.get(i).buildingClass) {
                
                //draw green line
                if (flip == false )
                    drawLine(boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 25, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 25, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 25 + FOVlengthX, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 25 + FOVlengthY, 2, 1, 0, 1);
                else
                    drawLine(boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 25, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 25, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 25 + FOVlengthX, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 25 + FOVlengthY, 2, 1, 0, 1);
            }
        }
        
        //draw 2 lines for the FOV of selectedUnitDrawFOV
        if (drawFullFOV && selectedUnitDrawFOV instanceof Unit) {
            
            if (Inputs.action == null) {
                
                selectedUnitDrawFOVFOV = selectedUnitDrawFOV.FOV;
            }
            
            else {
                
                if (Inputs.action.equals("move") || Inputs.action.equals("change direction")) {

                    selectedUnitDrawFOVFOV = selectedUnitDrawFOV.FOV;
                }

                else {

                    if (Inputs.attackNumber == 0) {

                        selectedUnitDrawFOVFOV = selectedUnitDrawFOV.attack0FOV;
                    }

                    else if (Inputs.attackNumber == 1) {

                        selectedUnitDrawFOVFOV = selectedUnitDrawFOV.attack1FOV;
                    }

                    else if (Inputs.attackNumber == 2) {

                        selectedUnitDrawFOVFOV = selectedUnitDrawFOV.attack2FOV;
                    }

                    else if (Inputs.attackNumber == 3) {

                        selectedUnitDrawFOVFOV = selectedUnitDrawFOV.attack3FOV;
                    }
                }
            }
            
            //first line ( + selectedUnitDrawFOVFOV / 2)
            if (selectedUnitDrawFOV.direction + selectedUnitDrawFOVFOV / 2 < 90)
                directionAngle = 90 - (selectedUnitDrawFOV.direction + selectedUnitDrawFOVFOV / 2);
            else if (selectedUnitDrawFOV.direction + selectedUnitDrawFOVFOV / 2 >= 90)
                directionAngle = selectedUnitDrawFOV.direction + selectedUnitDrawFOVFOV / 2 - 90;
            else if (selectedUnitDrawFOV.direction + selectedUnitDrawFOVFOV / 2 >= 180)
                directionAngle = selectedUnitDrawFOV.direction + selectedUnitDrawFOVFOV / 2 - 180;
            else if (selectedUnitDrawFOV.direction + selectedUnitDrawFOVFOV / 2 >= 270)
                directionAngle = 270 - (selectedUnitDrawFOV.direction + selectedUnitDrawFOVFOV / 2);
            
            FOVlengthX = Math.abs((int)(Math.cos(Math.toRadians(directionAngle)) * selectedUnitDrawFOVRange * squareSizeX));
            FOVlengthY = Math.abs((int)(Math.sin(Math.toRadians(directionAngle)) * selectedUnitDrawFOVRange * squareSizeY));

            if (selectedUnitDrawFOV.direction + selectedUnitDrawFOVFOV / 2 > 90 && selectedUnitDrawFOV.direction + selectedUnitDrawFOVFOV / 2 < 270)
                FOVlengthY = -1 * FOVlengthY;
            if (selectedUnitDrawFOV.direction + selectedUnitDrawFOVFOV / 2 > 180 && selectedUnitDrawFOV.direction + selectedUnitDrawFOVFOV / 2 < 360)
                FOVlengthX = -1 * FOVlengthX;
            
            if (flip) {
                
                FOVlengthX = -1 * FOVlengthX;
                FOVlengthY = -1 * FOVlengthY;
            }
            
            if (!selectedUnitDrawFOV.buildingClass) {
                
                //draw pink line
                if (flip == false )
                    drawLine(boardPosX + (int)((selectedUnitDrawFOV.posX - 1) * squareSizeX) + 25, boardPosY + (int)((selectedUnitDrawFOV.posY - 1) * squareSizeY) + 25, boardPosX + (int)((selectedUnitDrawFOV.posX - 1) * squareSizeX) + 25 + FOVlengthX, boardPosY + (int)((selectedUnitDrawFOV.posY - 1) * squareSizeY) + 25 + FOVlengthY, 2, 1, 0.7f, 0.7f);
                else
                drawLine(boardPosX + (int)((boardSquaresX + 1 - selectedUnitDrawFOV.posX - 1) * squareSizeX) + 25, boardPosY + (int)((boardSquaresY + 1 - selectedUnitDrawFOV.posY - 1) * squareSizeY) + 25, boardPosX + (int)((boardSquaresX + 1 - selectedUnitDrawFOV.posX - 1) * squareSizeX) + 25 + FOVlengthX, boardPosY + (int)((boardSquaresY + 1 - selectedUnitDrawFOV.posY - 1) * squareSizeY) + 25 + FOVlengthY, 2, 1, 0.7f, 0.7f);
            }
            
            
            
            //second line ( - selectedUnitDrawFOVFOV / 2)
            if (selectedUnitDrawFOV.direction - selectedUnitDrawFOVFOV / 2 < 0) {
                
                selectedUnitDrawFOVFOV = (selectedUnitDrawFOVFOV / 2 - 360) * 2;
            }
                
            if (selectedUnitDrawFOV.direction - selectedUnitDrawFOVFOV / 2 < 90)
                directionAngle = 90 - (selectedUnitDrawFOV.direction - selectedUnitDrawFOVFOV / 2);
            else if (selectedUnitDrawFOV.direction - selectedUnitDrawFOVFOV / 2 >= 90)
                directionAngle = selectedUnitDrawFOV.direction - selectedUnitDrawFOVFOV / 2 - 90;
            else if (selectedUnitDrawFOV.direction - selectedUnitDrawFOVFOV / 2 >= 180)
                directionAngle = selectedUnitDrawFOV.direction - selectedUnitDrawFOVFOV / 2 - 180;
            else if (selectedUnitDrawFOV.direction - selectedUnitDrawFOVFOV / 2 >= 270)
                directionAngle = 270 - (selectedUnitDrawFOV.direction - selectedUnitDrawFOVFOV / 2);
            
            FOVlengthX = Math.abs((int)(Math.cos(Math.toRadians(directionAngle)) * selectedUnitDrawFOVRange * squareSizeX));
            FOVlengthY = Math.abs((int)(Math.sin(Math.toRadians(directionAngle)) * selectedUnitDrawFOVRange * squareSizeY));

            if (selectedUnitDrawFOV.direction - selectedUnitDrawFOVFOV / 2 > 90 && selectedUnitDrawFOV.direction - selectedUnitDrawFOVFOV / 2 < 270)
                FOVlengthY = -1 * FOVlengthY;
            if (selectedUnitDrawFOV.direction - selectedUnitDrawFOVFOV / 2 > 180 && selectedUnitDrawFOV.direction - selectedUnitDrawFOVFOV / 2 < 360)
                FOVlengthX = -1 * FOVlengthX;
            
            if (flip) {
                
                FOVlengthX = -1 * FOVlengthX;
                FOVlengthY = -1 * FOVlengthY;
            }
            
            if (!selectedUnitDrawFOV.buildingClass) {
                
                //draw pink line
                if (flip == false )
                    drawLine(boardPosX + (int)((selectedUnitDrawFOV.posX - 1) * squareSizeX) + 25, boardPosY + (int)((selectedUnitDrawFOV.posY - 1) * squareSizeY) + 25, boardPosX + (int)((selectedUnitDrawFOV.posX - 1) * squareSizeX) + 25 + FOVlengthX, boardPosY + (int)((selectedUnitDrawFOV.posY - 1) * squareSizeY) + 25 + FOVlengthY, 2, 1, 0.7f, 0.7f);
                else
                    drawLine(boardPosX + (int)((boardSquaresX + 1 - selectedUnitDrawFOV.posX - 1) * squareSizeX) + 25, boardPosY + (int)((boardSquaresY + 1 - selectedUnitDrawFOV.posY - 1) * squareSizeY) + 25, boardPosX + (int)((boardSquaresX + 1 - selectedUnitDrawFOV.posX - 1) * squareSizeX) + 25 + FOVlengthX, boardPosY + (int)((boardSquaresY + 1 - selectedUnitDrawFOV.posY - 1) * squareSizeY) + 25 + FOVlengthY, 2, 1, 0.7f, 0.7f);
            }
            
            //circles
            if (Inputs.action != null) {
                
                if (/*!(Inputs.action.equals("move") || Inputs.action.equals("change direction"))*/true) {

                    double[] attackRange = new double[0];

                    if (Inputs.attackNumber == 0)
                        attackRange = selectedUnitDrawFOV.attack0Range;
                    if (Inputs.attackNumber == 1)
                        attackRange = selectedUnitDrawFOV.attack1Range;
                    if (Inputs.attackNumber == 2)
                        attackRange = selectedUnitDrawFOV.attack2Range;
                    if (Inputs.attackNumber == 3)
                        attackRange = selectedUnitDrawFOV.attack3Range;

                    for (int i = 0; i < attackRange.length; i++) {

                        //draw purple circle
                        if (flip == false )
                            drawCircle(boardPosX + (int)((selectedUnitDrawFOV.posX - 1) * squareSizeX) + 25, boardPosY + (int)((selectedUnitDrawFOV.posY - 1) * squareSizeY) + 25, (int)(attackRange[i] * squareSizeX), 2, 1, 0, 1);
                        else
                            drawCircle(boardPosX + (int)((boardSquaresX + 1 - selectedUnitDrawFOV.posX - 1) * squareSizeX) + 25, boardPosY + (int)((boardSquaresY + 1 - selectedUnitDrawFOV.posY - 1) * squareSizeY) + 25, (int)(attackRange[i] * squareSizeX), 2, 1, 0, 1);
                    }
                }
            }
            
            else {
                
                double[] attackRange = new double[0];
                
                if (Inputs.attackNumber == 0)
                    attackRange = selectedUnitDrawFOV.attack0Range;
                if (Inputs.attackNumber == 1)
                    attackRange = selectedUnitDrawFOV.attack1Range;
                if (Inputs.attackNumber == 2)
                    attackRange = selectedUnitDrawFOV.attack2Range;
                if (Inputs.attackNumber == 3)
                    attackRange = selectedUnitDrawFOV.attack3Range;
                
                for (int i = 0; i < attackRange.length; i++) {
                    
                    //draw purple circle
                    if (flip == false )
                        drawCircle(boardPosX + (int)((selectedUnitDrawFOV.posX - 1) * squareSizeX) + 25, boardPosY + (int)((selectedUnitDrawFOV.posY - 1) * squareSizeY) + 25, (int)(attackRange[i] * squareSizeX), 2, 1, 0, 1);
                    else
                        drawCircle(boardPosX + (int)((boardSquaresX + 1 - selectedUnitDrawFOV.posX - 1) * squareSizeX) + 25, boardPosY + (int)((boardSquaresY + 1 - selectedUnitDrawFOV.posY - 1) * squareSizeY) + 25, (int)(attackRange[i] * squareSizeX), 2, 1, 0, 1);
                }
            }
        }
    }
    
    public static void drawUnits() {
        
        //draw light-blue background on square if unit is invuln
        for (int i = 0; i < visUnits.size(); i++) {
            
            //draw light-blue background on square if unit is invuln
            if (visUnits.get(i).invuln) {
                
                if (flip == false)
                    drawQuad((int)((visUnits.get(i).posX - 1) * squareSizeX) + boardPosX + 1 - 1, (int)((visUnits.get(i).posY - 1) * squareSizeY) + boardPosY + 1, (int)(visUnits.get(i).posX * squareSizeX) + boardPosX - 1, (int)(visUnits.get(i).posY * squareSizeY) + boardPosY - 1 + 1, 0.75f, 0.75f, 1f);
                else
                    drawQuad((int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + boardPosX + 1 - 1, (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + boardPosY + 1, (int)((boardSquaresX + 1 - visUnits.get(i).posX) * squareSizeX) + boardPosX - 1, (int)((boardSquaresY + 1 - visUnits.get(i).posY) * squareSizeY) + boardPosY - 1 + 1, 0.75f, 0.75f, 1f);
            }
        }
        
        drawFOVs();
        
        //draw units
        for (int i = 0; i < visUnits.size(); i++) {
            
            //draw a small white background on square. make this light-blue if unit is invuln
            if (visUnits.get(i).invuln) {
                
                if (flip == false)
                    drawQuad((int)(((int)visUnits.get(i).posX - 1) * squareSizeX) + boardPosX + 1 + 5, (int)(((int)visUnits.get(i).posY - 1) * squareSizeY) + boardPosY + 1 + 5, (int)((int)visUnits.get(i).posX * squareSizeX) + boardPosX - 1 - 5, (int)((int)visUnits.get(i).posY * squareSizeY) + boardPosY - 1 - 5, 0.75f, 0.75f, 1f);
                else
                    drawQuad((int)((boardSquaresX + 1 - (int)visUnits.get(i).posX - 1) * squareSizeX) + boardPosX + 1 + 5, (int)((boardSquaresY + 1 - (int)visUnits.get(i).posY - 1) * squareSizeY) + boardPosY + 1 + 5, (int)((boardSquaresX + 1 - (int)visUnits.get(i).posX) * squareSizeX) + boardPosX - 1 - 5, (int)((boardSquaresY + 1 - (int)visUnits.get(i).posY) * squareSizeY) + boardPosY - 1 - 5, 0.75f, 0.75f, 1f);
            }
            
            else {
                
                if (flip == false)
                    drawQuad((int)(((int)visUnits.get(i).posX - 1) * squareSizeX) + boardPosX + 1 + 5, (int)(((int)visUnits.get(i).posY - 1) * squareSizeY) + boardPosY + 1 + 5, (int)((int)visUnits.get(i).posX * squareSizeX) + boardPosX - 1 - 5, (int)((int)visUnits.get(i).posY * squareSizeY) + boardPosY - 1 - 5, 1f, 1f, 1f);
                else
                    drawQuad((int)((boardSquaresX + 1 - (int)visUnits.get(i).posX - 1) * squareSizeX) + boardPosX + 1 + 5, (int)((boardSquaresY + 1 - (int)visUnits.get(i).posY - 1) * squareSizeY) + boardPosY + 1 + 5, (int)((boardSquaresX + 1 - (int)visUnits.get(i).posX) * squareSizeX) + boardPosX - 1 - 5, (int)((boardSquaresY + 1 - (int)visUnits.get(i).posY) * squareSizeY) + boardPosY - 1 - 5, 1f, 1f, 1f);
            }
            
            //draw unit
            if (visUnits.get(i).unitClass == "Archer" && visUnits.get(i).teamName == "blue") {
                
                if (flip == false)
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.blue);
                else
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.blue);
            }
            
            else if (visUnits.get(i).unitClass == "Builder" && visUnits.get(i).teamName == "blue") {
                
                if (flip == false)
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 - 2, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.blue);
                else
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 - 2, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.blue);
            }
            
            else if (visUnits.get(i).unitClass == "HealBot" && visUnits.get(i).teamName == "blue") {
                
                if (flip == false) {
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 + 7, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 46, "Heal", Color.blue);
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 + 10, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 29, "Bot", Color.blue);
                }
                
                else {
                    
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 + 7, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 46, "Heal", Color.blue);
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 + 10, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 29, "Bot", Color.blue);
                }
            }
            
            else if (visUnits.get(i).unitClass == "Statue" && visUnits.get(i).teamName == "blue") {
                
                if (flip == false)
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.blue);
                else
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.blue);
            }
            
            else if (visUnits.get(i).unitClass == "Healer" && visUnits.get(i).teamName == "blue") {
                
                if (flip == false)
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 - 1, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.blue);
                else
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 - 1, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.blue);
            }
            
            else if (visUnits.get(i).unitClass == "Knight" && visUnits.get(i).teamName == "blue") {
                
                if (flip == false)
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 + 1, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.blue);
                else
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 + 1, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.blue);
            }
            
            else if (visUnits.get(i).unitClass == "Mage" && visUnits.get(i).teamName == "blue") {
                
                if (flip == false)
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 + 2, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.blue);
                else
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 + 2, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.blue);
            }
            
            else if (visUnits.get(i).unitClass == "Scout" && visUnits.get(i).teamName == "blue") {
                
                if (flip == false)
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 + 3, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.blue);
                else
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 + 3, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.blue);
            }
            
            else if (visUnits.get(i).unitClass == "Spy" && visUnits.get(i).teamName == "blue") {
                
                if (flip == false)
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 + 11, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.blue);
                else
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 + 11, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.blue);
            }
            
            else if (visUnits.get(i).unitClass == "TNTGuy" && visUnits.get(i).teamName == "blue") {
                
                if (flip == false) {
                    
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 + 8, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 45, "TNT", Color.blue);
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 + 8, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 30, "Guy", Color.blue);
                }
                else {
                    
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 + 8, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 45, "TNT", Color.blue);
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 + 8, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 30, "Guy", Color.blue);
                }
            }
            
            else if (visUnits.get(i).unitClass == "Archer" && visUnits.get(i).teamName == "red") {
                
                if (flip == false)
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.red);
                else
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.red);

            }
            
            else if (visUnits.get(i).unitClass == "Builder" && visUnits.get(i).teamName == "red") {
                
                if (flip == false)
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 - 2, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.red);
                else
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 - 2, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.red);
            }
            
            else if (visUnits.get(i).unitClass == "HealBot" && visUnits.get(i).teamName == "red") {
                
                if (flip == false) {
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 + 7, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 46, "Heal", Color.red);
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 + 10, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 29, "Bot", Color.red);
                }
                
                else {
                    
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 + 7, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 46, "Heal", Color.red);
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 + 10, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 29, "Bot", Color.red);
                }
            }
            
            else if (visUnits.get(i).unitClass == "Statue" && visUnits.get(i).teamName == "red") {
                
                if (flip == false)
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.red);
                else
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.red);
            }
            
            else if (visUnits.get(i).unitClass == "Healer" && visUnits.get(i).teamName == "red") {
                
                if (flip == false)
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 - 1, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.red);
                else
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 - 1, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.red);
            }
            
            else if (visUnits.get(i).unitClass == "Knight" && visUnits.get(i).teamName == "red") {
                
                if (flip == false)
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 + 1, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.red);
                else
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 + 1, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.red);
            }
            
            else if (visUnits.get(i).unitClass == "Mage" && visUnits.get(i).teamName == "red") {
                
                if (flip == false)
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 + 2, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.red);
                else
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 + 2, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.red);
            }
            
            else if (visUnits.get(i).unitClass == "Scout" && visUnits.get(i).teamName == "red") {
                
                if (flip == false)
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 + 3, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.red);
                else
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 + 3, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.red);
            }
            
            else if (visUnits.get(i).unitClass == "Spy" && visUnits.get(i).teamName == "red") {
                
                if (flip == false)
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 + 11, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.red);
                else
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 + 11, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 38, visUnits.get(i).unitClass, Color.red);
            }
            
            else if (visUnits.get(i).unitClass == "TNTGuy" && visUnits.get(i).teamName == "red") {
                
                if (flip == false) {
                    
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 + 8, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 45, "TNT", Color.red);
                    drawString(font18, boardPosX + (int)((visUnits.get(i).posX - 1) * squareSizeX) + 1 + 8, boardPosY + (int)((visUnits.get(i).posY - 1) * squareSizeY) + 30, "Guy", Color.red);
                }
                else {
                    
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 + 8, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 45, "TNT", Color.red);
                    drawString(font18, boardPosX + (int)((boardSquaresX + 1 - visUnits.get(i).posX - 1) * squareSizeX) + 1 + 8, boardPosY + (int)((boardSquaresY + 1 - visUnits.get(i).posY - 1) * squareSizeY) + 30, "Guy", Color.red);
                }
            }
        }
        
        //tnt
        for (int i = 0; i < Game.blueTNTGuy.tntPlaced.length; i++) {

            if (playerTurn == "blue") {

                if (Game.blueTNTGuy.tntPlaced[i]) {

                    if (flip == false)
                        drawString(font14, boardPosX + (int)((Game.blueTNTGuy.tntPos[i * 2] - 1) * squareSizeX) + 1 + 5, boardPosY + (int)((Game.blueTNTGuy.tntPos[i * 2 + 1] - 1) * squareSizeY) + 38 - 3, "_TNT_", Color.blue);
                    else
                        drawString(font14, boardPosX + (int)((boardSquaresX + 1 - Game.blueTNTGuy.tntPos[i * 2] - 1) * squareSizeX) + 1 + 5, boardPosY + (int)((boardSquaresY + 1 - Game.blueTNTGuy.tntPos[i * 2 + 1] - 1) * squareSizeY) + 38 - 3, "_TNT_", Color.blue);
                }
            }

            else {
                
                //only draw visible tnt
                for (int a = 0; a < visSquares.size(); a += 2) {

                    if (Game.blueTNTGuy.tntPos[i * 2] == (int)visSquares.get(a) && Game.blueTNTGuy.tntPos[i * 2 + 1] == (int)visSquares.get(a + 1) && Game.blueTNTGuy.tntPlaced[i]) {

                        if (flip == false)
                            drawString(font14, boardPosX + (int)((Game.blueTNTGuy.tntPos[i * 2] - 1) * squareSizeX) + 1 + 5, boardPosY + (int)((Game.blueTNTGuy.tntPos[i * 2 + 1] - 1) * squareSizeY) + 38 - 3, "_TNT_", Color.blue);
                        else
                            drawString(font14, boardPosX + (int)((boardSquaresX + 1 - Game.blueTNTGuy.tntPos[i * 2] - 1) * squareSizeX) + 1 + 5, boardPosY + (int)((boardSquaresY + 1 - Game.blueTNTGuy.tntPos[i * 2 + 1] - 1) * squareSizeY) + 38 - 3, "_TNT_", Color.blue);
                    }
                }
            }
        }
        
        for (int i = 0; i < Game.redTNTGuy.tntPlaced.length; i++) {

            if (playerTurn == "red") {

                if (Game.redTNTGuy.tntPlaced[i]) {

                    if (flip == false)
                        drawString(font14, boardPosX + (int)((Game.redTNTGuy.tntPos[i * 2] - 1) * squareSizeX) + 1 + 5, boardPosY + (int)((Game.redTNTGuy.tntPos[i * 2 + 1] - 1) * squareSizeY) + 38 - 3, "_TNT_", Color.red);
                    else
                        drawString(font14, boardPosX + (int)((boardSquaresX + 1 - Game.redTNTGuy.tntPos[i * 2] - 1) * squareSizeX) + 1 + 5, boardPosY + (int)((boardSquaresY + 1 - Game.redTNTGuy.tntPos[i * 2 + 1] - 1) * squareSizeY) + 38 - 3, "_TNT_", Color.red);
                }
            }

            else {
                
                //only draw visible tnt
                for (int a = 0; a < visSquares.size(); a += 2) {

                    if (Game.redTNTGuy.tntPos[i * 2] == (int)visSquares.get(a) && Game.redTNTGuy.tntPos[i * 2 + 1] == (int)visSquares.get(a + 1) && Game.redTNTGuy.tntPlaced[i]) {

                        if (flip == false)
                            drawString(font14, boardPosX + (int)((Game.redTNTGuy.tntPos[i * 2] - 1) * squareSizeX) + 1 + 5, boardPosY + (int)((Game.redTNTGuy.tntPos[i * 2 + 1] - 1) * squareSizeY) + 38 - 3, "_TNT_", Color.red);
                        else
                            drawString(font14, boardPosX + (int)((boardSquaresX + 1 - Game.redTNTGuy.tntPos[i * 2] - 1) * squareSizeX) + 1 + 5, boardPosY + (int)((boardSquaresY + 1 - Game.redTNTGuy.tntPos[i * 2 + 1] - 1) * squareSizeY) + 38 - 3, "_TNT_", Color.red);
                    }
                }
            }
        }
        
        //cameras
        if (playerTurn == "blue" && Game.blueSpy.cameraPlaced)
            drawString(font14, boardPosX + (int)((Game.blueSpy.camPosX - 1) * squareSizeX) + 1 + 3, boardPosY + (int)((Game.blueSpy.camPosY - 1) * squareSizeY) + 38 - 3, "_CAM_", Color.blue);
        if (playerTurn == "red" && Game.redSpy.cameraPlaced)
            drawString(font14, boardPosX + (int)((boardSquaresX + 1 - Game.redSpy.camPosX - 1) * squareSizeX) + 1 + 3, boardPosY + (int)((boardSquaresY + 1 - Game.redSpy.camPosY - 1) * squareSizeY) + 38 - 3, "_CAM_", Color.red);
    }
    
    public static void drawConsole() {
        
        drawQuad(consolePosX, consolePosY, consolePosX + consoleSizeX, consolePosY + consoleSizeY, 1, 1, 1);
        
        //display messages
        if (playerTurn == "blue") {
            
            for (int i = consoleMessagesBlue.size() - 1; i >= 0; i--) {
                
                if (consoleMessagesBlue.get(i).length() >= 6) {
                    
                    if (!consoleMessagesBlue.get(i).equals("empty") && i != 0 && !consoleMessagesBlue.get(i).substring(0, 6).equals("*grey*"))
                        drawString(font18, 795, consolePosY + 20 * (consoleMessagesBlue.size() - 1 - i) + 30, (String)consoleMessagesBlue.get(i), Color.black);
                    
                    else if (!consoleMessagesBlue.get(i).equals("empty") && i != 0 && consoleMessagesBlue.get(i).substring(0, 6).equals("*grey*"))
                        drawString(font18, 795, consolePosY + 20 * (consoleMessagesBlue.size() - 1 - i) + 30, consoleMessagesBlue.get(i).substring(6, consoleMessagesBlue.get(i).length()), Color.gray);
                }
                
                else if (!consoleMessagesBlue.get(i).equals("empty") && i != 0) {
                    
                    drawString(font18, 795, consolePosY + 20 * (consoleMessagesBlue.size() - 1 - i) + 30, (String)consoleMessagesBlue.get(i), Color.black);
                }
                
                if (i == 0 && !consoleMessagesBlue.get(i).equals("empty"))
                    drawString(font18, 795, consolePosY + 20 * (consoleMessagesBlue.size() - 1 - i) + 30, (String)consoleMessagesBlue.get(i), clearColorName);
            }
        }
        
        else {
            
            for (int i = consoleMessagesRed.size() - 1; i >= 0; i--) {
                
                if (consoleMessagesRed.get(i).length() >= 6) {
                    
                    if (!consoleMessagesRed.get(i).equals("empty") && i != 0 && !consoleMessagesRed.get(i).substring(0, 6).equals("*grey*"))
                        drawString(font18, 795, consolePosY + 20 * (consoleMessagesRed.size() - 1 - i) + 30, (String)consoleMessagesRed.get(i), Color.black);
                    
                    else if (!consoleMessagesRed.get(i).equals("empty") && i != 0 && consoleMessagesRed.get(i).substring(0, 6).equals("*grey*"))
                        drawString(font18, 795, consolePosY + 20 * (consoleMessagesRed.size() - 1 - i) + 30, consoleMessagesRed.get(i).substring(6, consoleMessagesRed.get(i).length()), Color.gray);
                }
                
                else if (!consoleMessagesRed.get(i).equals("empty") && i != 0) {
                    
                    drawString(font18, 795, consolePosY + 20 * (consoleMessagesRed.size() - 1 - i) + 30, (String)consoleMessagesRed.get(i), Color.black);
                }
                
                if (i == 0 && !consoleMessagesRed.get(i).equals("empty"))
                    drawString(font18, 795, consolePosY + 20 * (consoleMessagesRed.size() - 1 - i) + 30, (String)consoleMessagesRed.get(i), clearColorName);
            }
        }
    }
    
    public static void drawScoreboard() {
        
        //white background
        drawQuad(scoreboardPosX, scoreboardPosY, scoreboardPosX + scoreboardSizeX, scoreboardPosY + scoreboardSizeY, 1, 1, 1);
        
        //blue score
        drawString(font24, scoreboardPosX + 30, scoreboardPosY + scoreboardSizeY - 10, "Blue", Color.blue);
        drawString(font24, scoreboardPosX + 30, scoreboardPosY + scoreboardSizeY - 40, "<" + Game.blueScore + ">", Color.black);
        
        //red score
        drawString(font24, scoreboardPosX + scoreboardSizeX - 70, scoreboardPosY + scoreboardSizeY - 10, "Red", Color.red);
        drawString(font24, scoreboardPosX + scoreboardSizeX - 70, scoreboardPosY + scoreboardSizeY - 40, "<" + Game.redScore + ">", Color.black);
        
        //round
        drawString(font24, scoreboardPosX + 140, scoreboardPosY + scoreboardSizeY - 10, "Round", Color.black);
        drawString(font24, scoreboardPosX + 145, scoreboardPosY + scoreboardSizeY - 40, "<" + Game.round[0] + "." + Game.round[1] + ">", Color.black);
        
        //highlight which player is to move
        if (playerTurn == "blue")
            drawQuad(scoreboardPosX + 30, scoreboardPosY + scoreboardSizeY - 35, scoreboardPosX + 75, scoreboardPosY + scoreboardSizeY - 38, 0, 1, 0);
        else
            drawQuad(scoreboardPosX + scoreboardSizeX - 70, scoreboardPosY + scoreboardSizeY - 35, scoreboardPosX + scoreboardSizeX - 30, scoreboardPosY + scoreboardSizeY - 38, 0, 1, 0);
    }
    
    public static void drawActionButtons() {
        
        //label the buttons
        //move
        drawString(font24, 823, 578, "Move", Color.black);
        //direction
        drawString(font24, 998, 578, "Direction", Color.black);
        //attack
        drawString(font24, 788, 497, "Attack", Color.black);
        //attack number
        drawString(font24, 886, 497, "<" + Inputs.getAttackNumber() + ">", Color.black);
        //attack name (centered)
        drawString(font24, 1034 - (int)(((double)Inputs.getAttackName().length() + 1d) * (17d - (double)Inputs.getAttackName().length() / 2.4d) / 2d), 497, "<" + Inputs.getAttackName() + ">", Color.black);
        //confirm
        drawString(font24, 815, 418, "Confirm", Color.black);
        //end phase
        drawString(font24, 994, 418, "End Phase", Color.black);
        
        if (Inputs.phase == "activate units")
            drawString(font24, 896, 618, "Select Unit", Color.white);
        else if (Inputs.phase == "change direction")
            drawString(font24, 870, 618, "Change Direction", Color.white);
        else if (Inputs.phase == "move and attack")
            drawString(font24, 870, 618, "Move and Attack", Color.white);
    }
    
    public static void drawButtons() {
        
        //draw green outline around move, attack and change direction if they are selected
        if (Inputs.action == "move") {
            
            drawQuad(775 - 3, 537 - 3, 937 + 3, 587 + 3, 0, 1, 0);
        }
        
        else if (Inputs.action == "change direction") {
            
            drawQuad(962 - 3, 537 - 3, 1124 + 3, 587 + 3, 0, 1, 0);
        }
        
        else if (Inputs.action == "attack") {
            
            drawQuad(775 - 3, 457 - 3, 865 + 3, 507 + 3, 0, 1, 0);
        }
        
        for (Button button : buttons)
            button.draw();
    }
    
    public static void drawLine(int x1, int y1, int x2, int y2, int width, float r, float g, float b) {
        
        glColor3f(r, g, b);
        
        glLineWidth(width);
        
        glBegin(GL_LINE_STRIP);
        {
            glVertex2f(x1, Display.getHeight() - y1);
            glVertex2f(x2, Display.getHeight() - y2);
        }
        
        glEnd();
    }
    
    public static void drawQuad(int x1, int y1, int x2, int y2, float r, float g, float b) {
        
        glColor3f(r, g, b);
        
        glBegin(GL_QUADS);
        {
            glVertex2f(x1, Display.getHeight() - y1);
            glVertex2f(x2, Display.getHeight() - y1);
            glVertex2f(x2, Display.getHeight() - y2);
            glVertex2f(x1, Display.getHeight() - y2);
        }
        
        glEnd();
    }
    
    public static void drawCircle(int x, int y, int radius, int width, float r, float g, float b) {
        
        glColor3f(r, g, b);
        
        glLineWidth(width);
        
        glBegin(GL_LINE_LOOP);
 
        for (int i=0; i <= 360; i++) {
            
            glVertex2f((float)(Math.cos(Math.toRadians(i)) * radius) + x, (float)(Math.sin(Math.toRadians(i)) * radius) + Display.getHeight() - y);
        }

        glEnd();
    }
    
    public static void drawString(TrueTypeFont font, int x, int y, String text, Color color) {
        
        //enable transparency
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        
        font.drawString(x, Display.getHeight() - y, text, color);
        
        //disable transparency
        glDisable(GL_BLEND);
    }
    
    public void getInput() {
        
        Mouse.poll();
        
        if (Mouse.isButtonDown(0) && mouseFirstClick) {
            
            for (Button button : buttons)
                button.mouseClick(Mouse.getX(), Mouse.getY());
            
            mouseFirstClick = false;
        }
        
        if (!Mouse.isButtonDown(0)) {
            
            for (Button button : buttons)
                button.mouseRelease();

            mouseFirstClick = true;
        }
        
        for (Button button : buttons)
            button.update(Mouse.getX(), Mouse.getY());
        
        //keyboard inputs
        Keyboard.poll();
        
        while (Keyboard.next()) {
            
            if (Keyboard.getEventKeyState()) {
                
                if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
                    
                    try {
                        if (Display.isFullscreen())
                            Display.setFullscreen(false);
                        else
                            Display.setFullscreen(true);
                        render();
                    }
                    catch (LWJGLException ex) {
            
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                if (Keyboard.getEventKey() == Keyboard.KEY_UP)
                    Replay.finalMove();
                
                if (Keyboard.getEventKey() == Keyboard.KEY_DOWN)
                    Replay.firstMove();
                
                if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT)
                    Replay.nextMove();
                
                if (Keyboard.getEventKey() == Keyboard.KEY_LEFT)
                    Replay.lastMove();
                
                if (Keyboard.getEventKey() == Keyboard.KEY_GRAVE) {
                    
                    if (drawFullFOV)
                        drawFullFOV = false;
                    
                    else
                        drawFullFOV = true;
                }
                
                if (Keyboard.isKeyDown(Keyboard.KEY_S) && Keyboard.isKeyDown(Keyboard.KEY_RETURN))
                    Position.savePosition();
                
                if (Keyboard.isKeyDown(Keyboard.KEY_L) && Keyboard.isKeyDown(Keyboard.KEY_RETURN))
                    Position.loadPosition();
                
                if (Keyboard.isKeyDown(Keyboard.KEY_R) && Keyboard.isKeyDown(Keyboard.KEY_RETURN))
                    Replay.saveReplay();
                
                if (Keyboard.isKeyDown(Keyboard.KEY_P) && Keyboard.isKeyDown(Keyboard.KEY_RETURN))
                    Replay.loadReplay();
                
//                if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT && System.nanoTime() <= keyRightLastPress + keyDoublePressTime)
//                    //skip to end
            }
        }
        
//        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
//            
//            if (Replay.replayOn && System.nanoTime() >= keyRightLastPress + replaySpeed) {
//                
//                Replay.nextMove();
//            }
//            
//            keyRightLastPress = System.nanoTime();
//        }
                
//        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
//            
//            if (Replay.replayOn && System.nanoTime() >= keyLeftLastPress + replaySpeed) {
//                
//                Replay.lastMove();
//            }
//            
//            keyLeftLastPress = System.nanoTime();
//        }
        
        update();
    }
    
    public static void scroll(int amt, boolean vertical, boolean reset) {
        
        if (reset == true) {
            
            if (vertical == true)
                boardPosY = boardDefaultPosY;
            else
                boardPosX = boardDefaultPosX;
        }
        
        if (vertical == true) {
            
            boardPosY -= amt;
            if (playerTurn == "blue")
                blueScroll[1] += amt;
            else
                redScroll[1] += amt;
        }
        
        else {
            
            boardPosX -= amt;
            if (playerTurn == "blue")
                blueScroll[0] += amt;
            else
                redScroll[0] += amt;
        }
    }
    
    public static void consoleAddMessage(String message, String teamName) {
        
        if (teamName == "blue") {
            
            consoleMessagesBlue.remove(0);
            consoleMessagesBlue.add(message);
        }
        
        else if (teamName == "red") {
            
            consoleMessagesRed.remove(0);
            consoleMessagesRed.add(message);
        }
        
        else if (teamName == "both") {
            
            consoleMessagesBlue.remove(0);
            consoleMessagesBlue.add(message);
            consoleMessagesRed.remove(0);
            consoleMessagesRed.add(message);
        }
    }
    
    public static void consoleClearGreyMessages(String teamName) {
        
        if (teamName.equals("blue")) {
            
            //clear grey messages
            for (int i = 0; i < consoleMessagesBlue.size(); i++) {
                
                if (consoleMessagesBlue.get(i).length() >= 6)
                    if (consoleMessagesBlue.get(i).substring(0, 6).equals("*grey*"))
                        consoleMessagesBlue.set(i, "empty");
            }

            //make black messages grey
            for (int i = 0; i < consoleMessagesBlue.size(); i++) {

                if (!consoleMessagesBlue.get(i).equals("empty"))
                    consoleMessagesBlue.set(i, "*grey*" + consoleMessagesBlue.get(i));
            }

            consoleMessagesBlue.set(0, "DO NOT REMOVE THIS TEXT");
        }
        
        else if (teamName.equals("red")) {
            
            //clear grey messages
            for (int i = 0; i < consoleMessagesRed.size(); i++) {
                
                if (consoleMessagesRed.get(i).length() >= 6)
                    if (consoleMessagesRed.get(i).substring(0, 6).equals("*grey*"))
                        consoleMessagesRed.set(i, "empty");
            }

            //make black messages grey
            for (int i = 0; i < consoleMessagesRed.size(); i++) {

                if (!consoleMessagesRed.get(i).equals("empty"))
                    consoleMessagesRed.set(i, "*grey*" + consoleMessagesRed.get(i));
            }

            consoleMessagesRed.set(0, "DO NOT REMOVE THIS TEXT");
        }
    }
}
