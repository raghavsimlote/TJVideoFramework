/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animation.agent;

import animation.model.Animation;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import javafx.scene.Group;
import javafx.scene.image.Image;

/**
 *
 * @author norrye
 */
public class AnimationAgent extends Agent {

    Animation animation;
    Image image;  

    @Override
    protected void setup() {
        animation = (Animation) getArguments()[0];
        final Group root = (Group) getArguments()[1];
        

        

        // iv1.setX(x);
        // iv1.setY(y);
        addBehaviour(new CyclicBehaviour(this) {

            @Override
            public void action() {
                doWait(200);

            }
        });
    }

}
