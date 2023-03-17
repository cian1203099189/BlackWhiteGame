package cn.hellp.touch.cpc102.componet;

import cn.hellp.touch.cpc102.Main;
import com.almasb.fxgl.entity.component.Component;

import java.util.function.Consumer;

public class EnemyComponent extends Component {
    private final MoveToPlayerComponent moveComponent;
    private int health = 20;

    @Override
    public void onAdded() {
        moveComponent.setEntity(entity);
        moveComponent.onAdded();
    }

    @Override
    public void onRemoved() {
        moveComponent.onRemoved();
    }

    public EnemyComponent(MoveToPlayerComponent moveComponent) {
        this.moveComponent = moveComponent;
    }

    public EnemyComponent(double speed) {
        this(new MoveToPlayerComponent(speed));
    }

    public EnemyComponent() {
        this(new MoveToPlayerComponent(400d));
    }

    @Override
    public void onUpdate(double tpf) {
        moveComponent.onUpdate(tpf);
    }

    public void setHealth(int health) {
        this.health = health;
        if(getHealth() <= 0) {
            die();
            return;
        }
        entity.getComponent(ChildBarComponent.class).setPercent(health * 5);
    }

    public void onTouchedPlayer() {
        Main.getPlayer().takeHealth(40);
        entity.removeFromWorld();
    }

    private void die() {
        entity.removeFromWorld();
        Main.addPoint();
    }

    public int getHealth() {
        return health;
    }

    public void takeHealth(int value) {
        setHealth(getHealth() - value);
    }

    public void hit(Consumer<EnemyComponent> action) {
        action.accept(this);
    }
}
