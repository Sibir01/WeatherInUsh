package Animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake { //Создаем анимацию тряски
    private TranslateTransition tt;

    public Shake(Node node){
       tt = new TranslateTransition(Duration.millis(70), node);
       tt.setFromX(0f);
       tt.setByX(10f);
       tt.setCycleCount(3);
       tt.setAutoReverse(true);
    }

    public void playAnim() {
        tt.playFromStart();
    }


}
