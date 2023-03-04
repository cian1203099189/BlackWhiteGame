package cn.hellp.touch.cpc102.componet;

import cn.hellp.touch.cpc102.Main;
import com.almasb.fxgl.entity.component.Component;

public class MoveToPlayerComponent extends Component {
    private final double speed;

    public MoveToPlayerComponent(double speed) {
        this.speed = speed;
    }

    @Override
    public void onUpdate(double tpf) {
        entity.translateX((entity.getX() > Main.getPlayer().getEntity().getX() ? -speed : speed) * tpf);
    }
}
