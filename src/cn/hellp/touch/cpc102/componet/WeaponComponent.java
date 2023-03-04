package cn.hellp.touch.cpc102.componet;

import cn.hellp.touch.cpc102.weapon.Weapon;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.MouseButton;

public class WeaponComponent extends Component {
    private Weapon weapon;
    private final UserAction fight = new UserAction("fight") {
        @Override
        protected void onAction() {
            weapon.fight();
        }
    };

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void onAdded() {
        try {
            FXGL.getInput().addAction(fight, MouseButton.PRIMARY);
        }catch (Exception ignored) {}
        weapon.onAdded();
    }

    @Override
    public void onUpdate(double tpf) {
        weapon.onUpdate(tpf);
    }
}
