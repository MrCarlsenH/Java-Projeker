package gui.scenes;

import javafx.scene.Parent;
import javafx.scene.Scene;

public abstract class GuiBase extends Scene {
//    Alle scenes nedarver denne for at kunne give individuelle navne til GuiLayout
    public GuiBase(Parent parent, double v, double v1, String name) {
        super(parent, v, v1);
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }
}
