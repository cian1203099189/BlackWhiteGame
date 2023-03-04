package cn.hellp.touch.cpc102.componet;

import cn.hellp.touch.cpc102.Main;
import com.almasb.fxgl.entity.component.Component;

public class GravityComponent extends Component {
    private final double gravity;

    public GravityComponent(double gravity) {
        this.gravity = gravity;
    }

    @Override
    public void onUpdate(double tpf) {
        if(!Main.getPlayer().isOnGround()) {
            entity.translateY(gravity * tpf);
        }
    }
}
