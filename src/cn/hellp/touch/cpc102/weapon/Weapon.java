package cn.hellp.touch.cpc102.weapon;

import cn.hellp.touch.cpc102.componet.WeaponComponent;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;

public abstract class Weapon {
    protected WeaponComponent weaponComponent;
    protected Entity entity;
    protected final String name;
    public abstract void fight();
    protected abstract Entity newEntity();
    public String getName() {
        return name;
    }

    public Entity getEntity() {
        return entity;
    }

    public void onAdded() {
        entity = newEntity();
        FXGL.getGameWorld().addEntity(entity);
    }

    public Weapon(String name) {
        this.name = name;
    }

    public void onUpdate(double tpf) {
        entity.setPosition(weaponComponent.getEntity().getCenter());
    }

    public void setWeaponComponent(WeaponComponent weaponComponent) {
        this.weaponComponent = weaponComponent;
    }

    public Weapon(WeaponComponent parentComponent, String name) {
        this.weaponComponent = parentComponent;
        this.name = name;
    }
}
