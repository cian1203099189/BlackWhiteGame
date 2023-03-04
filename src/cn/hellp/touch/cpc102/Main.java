package cn.hellp.touch.cpc102;

import cn.hellp.touch.cpc102.auxiliary.EntityType;
import cn.hellp.touch.cpc102.componet.EnemySpawner;
import cn.hellp.touch.cpc102.componet.PlayerControl;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends GameApplication {
    private static Player player;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle("黑白 created by Zhou 会昌实验学校");
        gameSettings.setWidth(800);
        gameSettings.setHeight(600);
        gameSettings.setVersion("1.0");
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("points",0);
    }

    @Override
    protected void initGame() {
        FXGL.getInput().clearAll();
        player = new Player();
        player.joinLevel(new Point2D(400, 400));
        FXGL.entityBuilder()
                .view(new Rectangle(800, 150))
                .type(EntityType.GROUND)
                .at(0, 450)
                .bbox(BoundingShape.box(800, 150))
                .collidable()
                .with(new PhysicsComponent())
                .buildAndAttach();
        FXGL.getGameWorld().addEntityFactory(new EnemySpawner());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                FXGL.runOnce( ()-> {
                    FXGL.getGameController().pauseEngine();
                    FXGL.showMessage("游玩提示\n" +
                            "按A、D来移动，按空格跳跃，使用鼠标控制枪口，按下左键开火");
                    FXGL.getGameController().resumeEngine();
                },Duration.ZERO);
            }
        },1);
        FXGL.addUINode(pointText = new Text("得分: 0"),20,30);
        pointText.setFont(new Font(16));
    }

    public static Player getPlayer() {
        return player;
    }

    private Text pointText;

    public void addPoint() {
        FXGL.getip("points").setValue(FXGL.geti("points") + 1);
        pointText.setText("得分: "+FXGL.geti("points"));
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().setGravity(0, 5000);
        FXGL.onCollision(EntityType.GROUND, EntityType.PLAYER, (g, p)->{
            Main.getPlayer().setOnGround(true);
        });
        FXGL.onCollisionBegin(EntityType.BULLET, EntityType.ENEMY, (b, e)->{
            b.removeFromWorld();
            e.removeFromWorld();
            addPoint();
        });
        FXGL.onCollisionBegin(EntityType.PLAYER, EntityType.ENEMY, (p, e)->{
            FXGL.showMessage("游戏结束，得分为:"+FXGL.geti("points")+"分。", ()-> {
                FXGL.getGameController().exit();
            });
        });
    }

    private long lastSpawnTime = System.currentTimeMillis();
    private final Random random = new Random(System.currentTimeMillis());

    @Override
    protected void onUpdate(double tpf) {
        if(System.currentTimeMillis() - lastSpawnTime > 1000) {
            int i;
            FXGL.getGameWorld().spawn("enemy",new SpawnData(new Point2D(((i = random.nextInt(0,400)) > 200 ? i+400 : i),370)));
            lastSpawnTime = System.currentTimeMillis();
        }
    }

    @Override
    protected void initInput() {
        FXGL.getInput().clearAll();
        PlayerControl.initInput();
    }
}
