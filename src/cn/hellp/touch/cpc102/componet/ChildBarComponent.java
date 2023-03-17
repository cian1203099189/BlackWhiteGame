package cn.hellp.touch.cpc102.componet;

import com.almasb.fxgl.dsl.components.view.ChildViewComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ChildBarComponent extends ChildViewComponent {
    private int percent;
    private Rectangle bar;

    public int getPercent() {
        return percent;
    }

    public ChildBarComponent(double x, double y) {
        super(x, y);
    }

    public void setPercent(int percent) {
        this.percent = percent;
        bar.setWidth(((double)percent) / 100.0 * 55.0);
    }

    @Override
    public void onAdded() {
        super.onAdded();
        var background = new Rectangle(60, 10, Color.color(0,0,0));
        bar = new Rectangle(55,6,Color.valueOf("47DA33FF"));
        bar.setX(2.5);
        bar.setY(2);
        this.getViewRoot().getChildren().add(background);
        this.getViewRoot().getChildren().add(bar);
    }
}
