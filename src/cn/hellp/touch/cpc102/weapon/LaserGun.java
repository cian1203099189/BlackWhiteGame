package cn.hellp.touch.cpc102.weapon;

import cn.hellp.touch.cpc102.auxiliary.EntityType;
import cn.hellp.touch.cpc102.componet.BulletComponent;
import cn.hellp.touch.cpc102.componet.EnemyComponent;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LaserGun extends Gun {

    public LaserGun() {
        super("激光枪");
    }

    @Override
    protected void shootBullet() {
        FXGL.entityBuilder()
                .at(entity.getRightX(), entity.getY())
                .type(EntityType.BULLET)
                .viewWithBBox(new Rectangle(10, 10, Color.valueOf("31A8D4FF")))
                .with(new BulletComponent(FXGL.getInput().getVectorToMouse(entity.getPosition()), 1000 , entity1 -> {
                    entity1.getComponent(EnemyComponent.class).takeHealth(20);
                }))
                .collidable()
                .buildAndAttach();
    }

    @Override
    protected Entity newEntity() {
        return FXGL.entityBuilder()
                .at(weaponComponent.getEntity().getCenter())
                .view(new Rectangle(30, 8, Color.valueOf("31A8D4FF")))
                .build();
    }
}

