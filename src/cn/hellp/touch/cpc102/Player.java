package cn.hellp.touch.cpc102;

import cn.hellp.touch.cpc102.auxiliary.EntityType;
import cn.hellp.touch.cpc102.componet.ChildBarComponent;
import cn.hellp.touch.cpc102.componet.GravityComponent;
import cn.hellp.touch.cpc102.componet.PlayerController;
import cn.hellp.touch.cpc102.componet.WeaponComponent;
import cn.hellp.touch.cpc102.weapon.Gun;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.KeepOnScreenComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {
    private boolean isOnGround;
    private Entity entity;
    private WeaponComponent weaponComponent;
    private int health = 100;

    public int getHealth() {
        return health;
    }

    public void takeHealth(int value) {
        this.health -= value;
        entity.getComponent(ChildBarComponent.class).setPercent(health);
        if(this.health <= 0) {
            FXGL.showMessage("游戏结束，得分为:"+FXGL.geti("points")+"分。", ()-> {
                FXGL.getGameController().exit();
            });
        }
    }



    public boolean isOnGround() {
        return isOnGround;
    }

    public void setOnGround(boolean onGround) {
        isOnGround = onGround;
    }

    public Entity getEntity() {
        return entity;
    }

    public void joinLevel(Point2D starter) {
        weaponComponent = new WeaponComponent();
        weaponComponent.setWeapon(new Gun(weaponComponent));
        entity = FXGL.entityBuilder()
                .type(EntityType.PLAYER)
                .at(starter)
                .bbox(BoundingShape.box(20,40))
                .collidable()
                .view(new Rectangle(20,40,Color.color(0,1,1)))
                .with(new KeepOnScreenComponent())
                .with(new GravityComponent(300))
                .with(new ChildBarComponent(-20,-35))
                .with(new PlayerController(500))
                .with(weaponComponent)
                .buildAndAttach();
    }
}
