package cn.hellp.touch.cpc102.auxiliary;

import cn.hellp.touch.cpc102.componet.DropItem;
import cn.hellp.touch.cpc102.weapon.Gun;
import cn.hellp.touch.cpc102.weapon.LaserGun;
import cn.hellp.touch.cpc102.weapon.Weapon;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.view.TextViewComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DropSpawner implements EntityFactory {
    private static final List<Weapon> weapons = new ArrayList<>();
    private static final Random random = new Random(System.currentTimeMillis());

    static {
        weapons.add(new Gun());
        weapons.add(new LaserGun());
    }

    @Spawns("drop")
    public Entity newDrop(SpawnData spawnData) {
        Weapon weapon = weapons.get(random.nextInt(weapons.size()));
        Entity entity = FXGL.entityBuilder()
                .at(spawnData.getX(), spawnData.getY())
                .viewWithBBox(new Rectangle(40, 40, Color.valueOf("604A4AFF")))
                .with(new TextViewComponent(0,-20, weapon.getName()), new DropItem(weapon))
                .collidable()
                .type(EntityType.DROP)
                .build();
        FXGL.runOnce(entity::removeFromWorld, Duration.seconds(6));
        return entity;
    }
}
