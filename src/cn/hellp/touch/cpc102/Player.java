package cn.hellp.touch.cpc102;

import cn.hellp.touch.cpc102.auxiliary.EntityType;
import cn.hellp.touch.cpc102.componet.GravityComponent;
import cn.hellp.touch.cpc102.componet.WeaponComponent;
import cn.hellp.touch.cpc102.componet.PlayerControl;
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
        WeaponComponent weaponComponent = new WeaponComponent();
        weaponComponent.setWeapon(new Gun(weaponComponent));
        entity = FXGL.entityBuilder()
                .type(EntityType.PLAYER)
                .at(starter)
                .bbox(BoundingShape.box(20,40))
                .collidable()
                .view(new Rectangle(20,40,Color.color(0,1,1)))
                .with(new KeepOnScreenComponent())
                .with(new GravityComponent(400))
                .with(new PlayerControl(400))
                .with(weaponComponent)
                .buildAndAttach();
    }

    public void fight() {

    }

    public void leaveLevel() {
        entity.removeFromWorld();
        entity = null;
    }
}
