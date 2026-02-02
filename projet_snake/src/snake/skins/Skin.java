package snake.skins;

import java.util.ArrayList;

public class Skin {
    protected String id;
    protected String name;

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}

class ItemSkin extends Skin {
    protected String animationColor;

    public String getAnimationColor() {
        return this.animationColor;
    }
}

class HeadSkin extends ItemSkin {
    private String[][] skin;

    HeadSkin(String id, String name, String animCol, String[][] skin) {
        this.id = id;
        this.name = name;
        this.animationColor = animCol;
        this.skin = skin;
    }

    public String[][] getSkin(){
        return this.skin;
    }
}

class BodySkin extends ItemSkin {
    private ArrayList<String[][]> skin;
    private boolean hasNeck;
    private String[][] neck;
    private boolean hasTail;
    private String[][] tail;

    BodySkin(String id, String name, String animCol, ArrayList<String[][]> skin, String[][] neck, String[][] tail) {
        this.id = id;
        this.name = name;
        this.animationColor = animCol;
        this.skin = skin;
        this.neck = neck;
        this.hasNeck = neck!=null;
        this.tail = tail;
        this.hasTail = tail!=null;
    }

    public ArrayList<String[][]> getSkin() {
        return this.skin;
    }

    public boolean getHasNeck() {
        return hasNeck;
    }

    public boolean getHasTail() {
        return hasTail
    }

    public String[][] getNeck() {
        return neck;
    }

    public String[][] getTail() {
        return tail;
    }
}

class FruitSkin extends ItemSkin {
    private String[][] skin;
    //plus variantes

    FruitSkin(String id, String name, String animCol, String[][] skin) {
        this.id = id;
        this.name = name;
        this.animationColor = animCol;
        this.skin = skin;
    }

    public String[][] getSkin(){
        return this.skin;
    }
}

class BackSkin extends Skin {
    private String colorOne;
    private String colorTwo;
    private String colorFrame;

    BackSkin(String colorOne, String colorTwo, String colorFrame) {
        this.colorOne = colorOne;
        this.colorTwo = colorTwo;
        this.colorFrame = colorFrame;
    }

    public String getColorOne(){
        return this.colorOne;
    }

    public String getColorTwo(){
        return this.colorTwo;
    }

    public String getColorFrame(){
        return this.colorFrame;
    }
}