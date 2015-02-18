import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import animation.agent.SceneAgent;
import animation.model.Animation;
import animation.model.Data;
import animation.model.VideoModel;

import com.traveljar.vo.TravelJarVO;

/**
 *
 * @author norrye
 */
public class Main extends Application {

    static Scene scene;
    static Group root = new Group();


    public static void main(String[] args) {
        jade.core.Runtime rt = jade.core.Runtime.instance();
        rt.setCloseVM(true);
        AgentContainer agentContainer = rt.createMainContainer(new ProfileImpl("localhost", 8080,
                null));
        AgentController rma, animation, sceneAgent = null;

        try {
           // rma = agentContainer.createNewAgent("rma", jade.tools.rma.rma.class.getName(), new Object[0]);
           // rma.start();
        	
        	VideoModel model = new VideoModel();
        	TravelJarVO travelJar = model.createVideo();
        	Data.animations = model.createAnimations(travelJar);
        	
//            ReadXML readXML = new ReadXML();
//            Data.animations = readXML.getAnimations();
//            //System.out.println("Data.animations " + Data.animations.size());
            Object[] obj = new Object[2];
            obj[1] = root;
            for (Animation a : Data.animations) {
                obj[0] = a;
               // animation = agentContainer.createNewAgent("animation-" + a.getName(), AnimationAgent.class.getName(), obj);
               // animation.start();
            }
            obj = new Object[2];
            obj[0] = scene;
            obj[1] = root;

            sceneAgent = agentContainer.createNewAgent("scene", SceneAgent.class.getName(), obj);
            sceneAgent.start();
            //System.out.println("*******************");
            launch(args);
        } catch (StaleProxyException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(root);
        scene.setFill(Paint.valueOf("white"));
        stage.setTitle("Animation");        
        stage.setY(200);
        stage.setWidth(Data.width);
        stage.setHeight(Data.height);
        stage.setScene(scene);
        stage.setResizable(false);
        Data.scene = scene;
        //stage.show();
    }
}
