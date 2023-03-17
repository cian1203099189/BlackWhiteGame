package cn.hellp.touch.cpc102.auxiliary;

import cn.hellp.touch.cpc102.componet.ChildBarComponent;
import cn.hellp.touch.cpc102.componet.EnemyComponent;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EnemySpawner implements EntityFactory {
    @Spawns("enemy")
    public Entity newEnemy(SpawnData data) {
        return FXGL.entityBuilder()
                .at(data.getX(),data.getY())
                .collidable()
                .type(EntityType.ENEMY)
                .viewWithBBox(new Rectangle(30,80, Color.color(1,0,0)))
                .with(new EnemyComponent(300))
                .with(new ChildBarComponent(-15,-30))
                .build();
    }
}
