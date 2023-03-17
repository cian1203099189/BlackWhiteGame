package cn.hellp.touch.cpc102.componet;

import cn.hellp.touch.cpc102.Main;
import cn.hellp.touch.cpc102.weapon.Weapon;
import com.almasb.fxgl.entity.component.Component;
import org.jetbrains.annotations.NotNull;

public class DropItem extends Component {
    private final @NotNull Weapon targetWeapon;

    public DropItem(@NotNull Weapon targetWeapon) {
        this.targetWeapon = targetWeapon;
    }

    @NotNull
    public Weapon getTargetWeapon() {
        return targetWeapon;
    }

    public void changeItem() {
        Weapon weapon = getTargetWeapon();
        WeaponComponent weaponComponent = Main.getPlayer().getEntity().getComponent(WeaponComponent.class);
        weapon.setWeaponComponent(weaponComponent);
        weaponComponent.setWeapon(weapon);
    }
}
