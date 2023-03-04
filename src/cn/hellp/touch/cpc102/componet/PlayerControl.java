package cn.hellp.touch.cpc102.componet;

import cn.hellp.touch.cpc102.Main;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;

public class PlayerControl extends Component {
    protected double speed;
    private static boolean pressD;
    private static boolean pressA;
    private static boolean jump;


    public PlayerControl(double speed) {
        this.speed = speed;
    }

    private static volatile boolean alreadyInit = false;
    public static void initInput(){
        if(!alreadyInit) {
            synchronized (PlayerControl.class) {
                if(!alreadyInit) {
                    UserAction moveA = new UserAction("playerMoveA") {
                        @Override
                        protected void onActionBegin() {
                            pressA = true;
                        }

                        @Override
                        protected void onActionEnd() {
                            pressA = false;
                        }
                    };
                    UserAction moveD = new UserAction("playerMoveD") {
                        @Override
                        protected void onActionBegin() {
                            pressD = true;
                        }

                        @Override
                        protected void onActionEnd() {
                            pressD = false;
                        }
                    };
                    UserAction jump = new UserAction("playerJump") {
                        @Override
                        protected void onActionBegin() {
                            PlayerControl.jump = true;
                        }

                        @Override
                        protected void onActionEnd() {
                            PlayerControl.jump = false;
                        }
                    };
                    FXGL.getInput().addAction(moveA, KeyCode.A);
                    FXGL.getInput().addAction(moveD, KeyCode.D);
                    FXGL.getInput().addAction(jump, KeyCode.SPACE);
                    alreadyInit = true;
                }
            }
        }
    }

    public PlayerControl() {
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    private double jumpedY = 0;
    private boolean jumping = false;

    @Override
    public void onUpdate(double tpf) {
        double[] vec = new double[] {0, 0};
        if(pressA) {
            vec[0] += (-speed) * tpf;
        }
        if(pressD) {
            vec[0] += speed * tpf;
        }
        if(jump && (Main.getPlayer().isOnGround() || jumping)) {
            vec[1] -= 800 * tpf;
            jumpedY -= vec[1];
            jumping = true;
            Main.getPlayer().setOnGround(false);
            if(jumpedY > 200) {
                jumping = false;
                jumpedY = 0;
            }
        } else {
            jumping = false;
            jumpedY = 0;
        }
        entity.translate(vec[0], vec[1]);
    }
}
