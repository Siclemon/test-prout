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
    private String type;
    private ArrayList<String[][]> skin;

    BodySkin(String id, String name, String animCol, ArrayList<String[][]> skin, String type) {
        this.id = id;
        this.name = name;
        this.animationColor = animCol;
        this.skin = skin;
        this.type = type;
    }

    public ArrayList<String[][]> getSkin(){
        return this.skin;
    }

    public String getType(){
        return this.type;
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