package cn.hellp.touch.cpc102.weapon;

import cn.hellp.touch.cpc102.componet.WeaponComponent;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;

public abstract class Weapon {
    protected WeaponComponent weaponComponent;
    protected Entity entity;
    public abstract void fight();
    protected abstract Entity newEntity();

    public void onAdded() {
        entity = newEntity();
        FXGL.getGameWorld().addEntity(entity);
    }

    public void onUpdate(double tpf) {
        entity.setPosition(weaponComponent.getEntity().getCenter());
    }

    public Weapon(WeaponComponent parentComponent) {
        this.weaponComponent = parentComponent;
    }
}
