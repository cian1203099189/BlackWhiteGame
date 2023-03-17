package cn.hellp.touch.cpc102.componet;

import cn.hellp.touch.cpc102.auxiliary.Reference;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

import java.util.function.Consumer;

public class BulletComponent extends Component {
    private final ProjectileComponent projectileComponent;
    protected final Consumer<Entity> onHitEnemy;

    public BulletComponent(ProjectileComponent projectileComponent, Consumer<Entity> onHitEnemy) {
        this.onHitEnemy = onHitEnemy;
        this.projectileComponent = projectileComponent;
    }

    public BulletComponent(ProjectileComponent component) {
        this.projectileComponent = component;
        this.onHitEnemy = entity1 -> {
            entity.removeFromWorld();
            entity1.getComponent(EnemyComponent.class).hit(enemyComponent -> {
                enemyComponent.takeHealth(10);
            });
        };
    }

    public BulletComponent(Point2D direction, double speed, Consumer<Entity> onHitEnemy) {
        this(new ProjectileComponent(direction, speed), onHitEnemy);
    }

    public void onHitEnemy(Entity enemy) {
        this.onHitEnemy.accept(enemy);
    }

    @Override
    public void onAdded() {
        Reference.setComponentEntity(projectileComponent, entity);
        projectileComponent.onAdded();
    }

    @Override
    public void onUpdate(double tpf) {
        projectileComponent.onUpdate(tpf);
    }

    @Override
    public void onRemoved() {
        projectileComponent.onRemoved();
    }

    @Override
    public boolean isComponentInjectionRequired() {
        return projectileComponent.isComponentInjectionRequired();
    }
}
