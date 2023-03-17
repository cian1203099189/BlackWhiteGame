package cn.hellp.touch.cpc102.componet;

import cn.hellp.touch.cpc102.Main;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;

public class PlayerController extends Component {
    protected double speed;
    private static boolean pressD;
    private static boolean pressA;
    private static boolean jump;


    public PlayerController(double speed) {
        this.speed = speed;
    }

    private static volatile boolean alreadyInit = false;
    public static void initInput(){
        if(!alreadyInit) {
            synchronized (PlayerController.class) {
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
                            PlayerController.jump = true;
                        }

                        @Override
                        protected void onActionEnd() {
                            PlayerController.jump = false;
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

    private double jumpedY = 0;
    private boolean jumping = false;
    private static final int maxJumpHeight = 275;
    private static final int jumpSpeed = 1000;

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
            vec[1] -= jumpSpeed * tpf;
            jumpedY -= vec[1];
            jumping = true;
            Main.getPlayer().setOnGround(false);
            if(jumpedY > maxJumpHeight) {
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
