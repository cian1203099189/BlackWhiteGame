package cn.hellp.touch.cpc102.weapon;

import cn.hellp.touch.cpc102.auxiliary.EntityType;
import cn.hellp.touch.cpc102.componet.BulletComponent;
import cn.hellp.touch.cpc102.componet.WeaponComponent;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Gun extends Weapon {
    protected long lastShootTime = 0;

    public Gun(WeaponComponent parentComponent) {
        super(parentComponent, "手枪");
    }

    public Gun() {
        super("手枪");
    }

    public Gun(String name) {
        super(name);
    }

    @Override
    public void fight() {
        if (System.currentTimeMillis() - lastShootTime > 200) {
            shootBullet();
            lastShootTime = System.currentTimeMillis();
        }
    }

    protected void shootBullet() {
        FXGL.entityBuilder()
                .at(entity.getRightX(), entity.getY())
                .type(EntityType.BULLET)
                .viewWithBBox(new Rectangle(10, 10, Color.color(0, 0, 0)))
                .with(new BulletComponent(new ProjectileComponent(FXGL.getInput().getVectorToMouse(entity.getPosition()), 600)))
                .collidable()
                .buildAndAttach();
    }

    @Override
    public void onUpdate(double tpf) {
        super.onUpdate(tpf);
        Point2D to = FXGL.getInput().getMousePositionWorld();
        Point2D from = entity.getPosition().add(1, 0);
        double a = to.getY() - from.getY();
        double b = to.getX() - from.getX();
        double c = Math.sqrt(a * a + b * b);
        double cosA = (b * b + c * c - a * a) / (2 * b * c);
        double rotate = Math.acos(cosA) * (180 / Math.PI);
        if (to.getY() < from.getY()) rotate = -rotate;
        entity.setRotation(rotate);
    }

    @Override
    protected Entity newEntity() {
        return FXGL.entityBuilder()
                .at(weaponComponent.getEntity().getCenter())
                .view(new Rectangle(30, 5, Color.color(0, 0, 0)))
                .build();
    }
}