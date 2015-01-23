/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animation.agent;

import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import animation.model.Animation;
import animation.model.Data;
import animation.model.Element;
import animation.utility.LogUtility;

/**
 *
 * @author norrye
 */
public class SceneAgent extends Agent {

    Scene scene;
    Group root;
    int timer = 0;//in ms
    ImageView iv1;
    Text label;
    Image image;
    Line line;
    BufferedImage snapshot;
    protected ImagePlus imp = new ImagePlus("Animation");
    protected ImageStack imageStack;
    double progress = 0;

    public SceneAgent() {
    }

    @Override
    protected void setup() {
        scene = (Scene) getArguments()[0];
        root = (Group) getArguments()[1];

        addBehaviour(new CyclicBehaviour(this) {

            @Override
            public void action() {

                Platform.runLater(() -> {
                    root.getChildren().clear();
                    for (Animation animation : Data.animations) {
                        if (timer < animation.getEnd() && timer > animation.getStart()) {
                            //                               
                            if (animation.getElements().isEmpty()) {
                                if (snapshot == null) {
                                    snapshot = createSnapshot(Data.scene);
                                }
                                root.getScene().setFill(Paint.valueOf(animation.getBackground()));
                                iv1 = new ImageView();
                                image = SwingFXUtils.toFXImage(snapshot, null);
                                iv1.setImage(image);
                                iv1.setX(Data.width * progress);
                                progress = progress + 0.1;
                                iv1.setFitHeight(Data.height);
                                iv1.setFitWidth(Data.width);
                                root.getChildren().add(iv1);
                            } else {
                                for (Element desc : animation.getElements()) {
                                    String[] splitStart = desc.getStartPosition().split(",");
                                    String[] splitEnd = desc.getEndPosition().split(",");
                                    double xStart = Data.width * Double.parseDouble(splitStart[0]);
                                    double yStart = Data.height * Double.parseDouble(splitStart[1]);
                                    double xEnd = Data.width * Double.parseDouble(splitEnd[0]);
                                    double yEnd = Data.height * Double.parseDouble(splitEnd[1]);
                                    switch (desc.getType()) {
                                        case "image":
                                            double lMove = 1;
                                            iv1 = new ImageView();
                                            image = new Image(desc.getDescription());
                                            iv1.setImage(image);
                                            iv1.setFitHeight(desc.getHeight());
                                            iv1.setFitWidth(desc.getWidth());
                                            
                                            
                                            if (desc.getEffect().startsWith("middle")) {
                                           	 
                                                xStart = ( Data.width - desc.getWidth() ) / 2;
                                                yStart = (Data.height - desc.getHeight() ) / 2;
                                                xEnd = ( Data.width - desc.getWidth() )/2;
                                                yEnd = (Data.height - desc.getHeight() ) / 2;
                                           }
                                            
                                            if (desc.getEffect().startsWith("Lmove")) {
                                                String ratiosString = desc.getEffect().split(";")[1];
                                                String ratioString = ratiosString.split("-")[0];
                                                lMove = Double.parseDouble(ratioString);

                                                //TODO
                                                if (ratiosString.split("-").length == 1) {
                                                    desc.setEffect("");
                                                } else {
                                                    ratiosString = ratiosString.replaceFirst(ratioString + "-", "");
                                                    desc.setEffect("Lmove;" + ratiosString);
                                                }
                                            }
                                            if (desc.getEffect().startsWith("resize")) {
                                                String ratiosString = desc.getEffect().split(";")[1];
                                                String ratioString = ratiosString.split("-")[0];
                                                double ratio = Double.parseDouble(ratioString);

                                                //TODO
                                                if (ratiosString.split("-").length == 1) {
                                                    desc.setEffect("");
                                                } else {
                                                    ratiosString = ratiosString.replaceFirst(ratioString + "-", "");
                                                    desc.setEffect("resize;" + ratiosString);
                                                }

                                                iv1.setFitHeight(desc.getHeight() * ratio);
                                                iv1.setFitWidth(desc.getWidth() * ratio);
                                            }
                                            if (desc.getEffect().startsWith("fade")) {
                                                String ratiosString = desc.getEffect().split(";")[1];
                                                String ratioString = ratiosString.split("-")[0];
                                                double ratio = Double.parseDouble(ratioString);

                                                //TODO
                                                if (ratiosString.split("-").length == 1) {
                                                    desc.setEffect("");
                                                } else {
                                                    ratiosString = ratiosString.replaceFirst(ratioString + "-", "");
                                                    desc.setEffect("fade;" + ratiosString);
                                                }
                                                iv1.setOpacity(ratio);
                                            }
                                            iv1.setX(xStart);
                                            iv1.setY(yStart * lMove);
                                            Circle clip = new Circle(xStart + desc.getWidth() / 2, yStart + desc.getHeight() / 2, desc.getHeight() / 2);
                                            if ( lMove == 1 ) {
                                            	
                                            	if ( Math.abs(xStart - xEnd) > desc.getSpeedX() )
                                            	{
                                            		LogUtility.printLog("1. SpeedX xStart: " + xStart);
                                                	xStart = xStart + desc.getSpeedX();
                                                    LogUtility.printLog("2. SpeedX xStart: " + xStart);
                                            	}
                                            	else
                                            	{
                                            		LogUtility.printLog("1. SpeedX xStart: " + xStart);
                                                	xStart = xEnd;
                                                    LogUtility.printLog("2. SpeedX xStart: " + xStart);
                                            	}
                                            	
                                            }
                                            if (Math.abs(yStart - yEnd) > desc.getSpeedY()) {
                                            	LogUtility.printLog("1. SpeedY yStart: " + yStart);
                                            	yStart = yStart + desc.getSpeedY();
                                            	LogUtility.printLog("2. SpeedY yStart: " + yStart);
                                            }
                                            desc.setStartPosition((xStart / Data.width) + "," + (yStart / Data.height));
                                            if (desc.isClip()) {
                                                iv1.setClip(clip);
                                            }
                                    
                                           	 root.getChildren().add(iv1);
                                            
                                            
                                            break;
                                        case "text":
                                        	
                                            label = new Text(desc.getDescription());
                                            label.setFont(Font.font(null, FontWeight.SEMI_BOLD, desc.getHeight()));
                                            
                                            if (desc.getEffect().startsWith("middle")) {
                                            	 final double width = label.getLayoutBounds().getWidth();
                                                 xStart = ( Data.width - width ) / 2;
                                                 xEnd = ( Data.width - width )/2;
                                            }
                                            else if (desc.getEffect().startsWith("leftMargin")) {
                                            	xStart = ( 10 );
                                            	xEnd = ( 10 );
                                            }
                                            else if (desc.getEffect().startsWith("rightMargin")) {
                                            	final double width = label.getLayoutBounds().getWidth();
                                            	xStart = ( Data.width - width - 10 );
                                            	xEnd = ( Data.width - width - 10 );
                                            }
                                            else if (desc.getEffect().startsWith("boxMiddle")) {
                                            	final double width = label.getLayoutBounds().getWidth();
//                                            	LogUtility.printLog("1. XEnd: " + xEnd + " Width: " + width);
//                                            	xStart = xStart + ( ( 60 - width ) / 2 );
                                            	xEnd = xEnd  + ( ( 60 - width ) / 2 ) ;
//                                            	LogUtility.printLog("2. XEnd: " + xEnd + " Width: " + width);
                                            }
                                            
                                           
                                            Reflection r = new Reflection();
                                            r.setFraction(0.5f);
                                            if (desc.getEffect().startsWith("resize")) {
                                                String ratiosString = desc.getEffect().split(";")[1];
                                                String ratioString = ratiosString.split("-")[0];
                                                double ratio = Double.parseDouble(ratioString);
                                                //TODO
                                                if (ratiosString.split("-").length == 1) {
                                                    desc.setEffect("");
                                                } else {
                                                    ratiosString = ratiosString.replaceFirst(ratioString + "-", "");
                                                    desc.setEffect("resize;" + ratiosString);
                                                }
                                                label.setFont(Font.font(null, FontWeight.SEMI_BOLD, desc.getHeight() * ratio));
                                            }
                                            if (desc.getEffect().startsWith("fade")) {
                                                String ratiosString = desc.getEffect().split(";")[1];
                                                String ratioString = ratiosString.split("-")[0];
                                                double ratio = Double.parseDouble(ratioString);

                                                //TODO
                                                if (ratiosString.split("-").length == 1) {
                                                    desc.setEffect("");
                                                } else {
                                                    ratiosString = ratiosString.replaceFirst(ratioString + "-", "");
                                                    desc.setEffect("fade;" + ratiosString);
                                                }
                                                label.setOpacity(ratio);
                                            }

//                                            label.setEffect(r);
                                            label.setFill(Paint.valueOf(desc.getColor()));
                                            label.setLayoutX(xStart);
                                            label.setLayoutY(yStart);
                                            if (Math.abs(xStart - xEnd) >= Math.abs(desc.getSpeedX())) {
                                                xStart = xStart + desc.getSpeedX();
                                            } else {
                                            	xStart = xEnd;
                                                if (desc.getSpeedX() != 0 && desc.getEffect().startsWith("comeandgo")) {
                                                    String waitString = desc.getEffect().split(";")[1];
                                                    String direction = desc.getEffect().split("=")[2];
                                                    String wait = waitString.split("=")[1];
                                                    double ratio = Double.parseDouble(wait);

                                                    desc.setEffect("comeandgo;wait=" + (ratio - Data.timeUnit) + "=" + direction);
                                                    //TODO
                                                    if (ratio < 0) {
                                                        desc.setEffect("");
                                                        desc.setSpeedX(desc.getSpeedX() * -1);
                                                        desc.setSpeedY(desc.getSpeedY() * -1);
                                                        desc.setEndPosition(getNewEnd(direction));
                                                    }
                                                }
                                            }
                                            if (Math.abs(yStart - yEnd) >= Math.abs(desc.getSpeedY())) {
                                                yStart = yStart + desc.getSpeedY();
                                            } else {
                                                if (desc.getSpeedY() != 0 && desc.getEffect().startsWith("comeandgo")) {
                                                    String waitString = desc.getEffect().split(";")[1];
                                                    String wait = waitString.split("=")[1];
                                                    String direction = desc.getEffect().split("=")[2];
                                                    double ratio = Double.parseDouble(wait);
                                                    desc.setEffect("comeandgo;wait=" + (ratio - Data.timeUnit) + "=" + direction);
                                                    //TODO
                                                    if (ratio < 0) {
                                                        desc.setEffect("");
                                                        desc.setSpeedX(desc.getSpeedX() * -1);
                                                        desc.setSpeedY(desc.getSpeedY() * -1);
                                                        desc.setEndPosition(getNewEnd(direction));
                                                    }
                                                }
                                            }
                                            desc.setStartPosition(xStart / Data.width + "," + yStart / Data.height);
                                            root.getChildren().add(label);
                                            break;
                                        case "path":
                                            int num = 0;
                                            String[] splitCurrent = desc.getCurrentPosition().split(",");
                                            double xCurrent = Data.width * Double.parseDouble(splitCurrent[0]);
                                            double yCurrent = Data.height * Double.parseDouble(splitCurrent[1]);
                                            line = new Line(xStart, yStart, xCurrent, yCurrent);
                                            line.getStrokeDashArray().addAll(10d, 8d, 2d, 10d);
                                            //line.set
                                            double ratio = 1;
                                            if (desc.getEffect().startsWith("resize")) {
                                                String ratiosString = desc.getEffect().split(";")[1];
                                                String ratioString = ratiosString.split("-")[0];
                                                ratio = Double.parseDouble(ratioString);
                                                //TODO
                                                if (ratiosString.split("-").length == 1) {
                                                    desc.setEffect("");
                                                } else {
                                                    ratiosString = ratiosString.replaceFirst(ratioString + "-", "");
                                                    desc.setEffect("resize;" + ratiosString);
                                                }

                                                line.setStrokeWidth(5.0f * ratio);
                                                if (Math.abs(xCurrent - xEnd) > desc.getSpeedX()) {
                                                    xCurrent = xCurrent + desc.getSpeedX();
                                                }
                                                if (Math.abs(yCurrent - yEnd) > desc.getSpeedY()) {
                                                    yCurrent = yCurrent + desc.getSpeedY();
                                                }
                                                desc.setCurrentPosition(xCurrent / Data.width + "," + yCurrent / Data.height);
                                            }
                                            if (desc.getEffect().startsWith("feet")) {
                                                String feetstr = desc.getEffect().split(";")[1];
                                                if (Math.abs(xCurrent - xEnd) > desc.getSpeedX()) {
                                                    xCurrent = xCurrent + desc.getSpeedX();
                                                }
                                                desc.setCurrentPosition(xCurrent / Data.width + "," + yCurrent / Data.height);
                                                num = (int) (xCurrent - xStart) / 30;
                                                desc.setEffect("feet;" + num);
                                                List<ImageView> feets = createTrack(num, xStart, yStart, desc.getDescription());
                                                root.getChildren().addAll(feets);
                                            }

                                            root.getChildren().add(line);
                                            break;
                                            
                                        case "video":
                                        	// create media player
                                        	File mediaFile = new File("E://videos/" + desc.getDescription());
                                            Media media = new Media(mediaFile.toURI().toString());
                                            MediaPlayer mediaPlayer = new MediaPlayer(media);
                                            mediaPlayer.setAutoPlay(true);
                                             
                                            // create mediaView and add media player to the viewer
                                            MediaView mediaView = new MediaView(mediaPlayer);
                                            
                                            mediaView.setFitWidth(320); mediaView.setFitHeight(240); mediaView.setPreserveRatio(false);

                                            mediaView.setTranslateX(mediaView.getFitWidth()  / 2 + 200); 
                                            mediaView.setTranslateY(mediaView.getFitHeight() / 2 + 200);
                                            mediaView.setScaleX(2); mediaView.setScaleY(2);
                                            
                                            root.getChildren().add(mediaView);
                                        	break;
                                    }
                                }
                                if (progress > 10) {
                                    progress = 0;
                                    imageStack = null;
                                }
                            }
                        }
                    }
                    if (Data.scene != null) {
                        if (imageStack == null) {
                            imp.setImage(createSnapshot(Data.scene));
                            imageStack = imp.getImageStack();
                            imp.setStack(imageStack);
                            imp.show();
                        } else {

                            imp.setImage(createSnapshot(Data.scene));
                            imp.setTitle("Animation " + (timer) / 1000);
                            imageStack.addSlice((new ImagePlus("Animation", imp.getProcessor())).getProcessor());
                            imp.setStack(imageStack);
                            imp.setSlice(imageStack.getSize());
                            timer = timer + Data.timeUnit;
                        }
                    }
                });
                //max is 2 minutes
                if (timer > 420000) {//ms time elapsed
                    //stop and save video
                    System.out.println("Saving video...");
                    IJ.run(imp, "AVI... ", "compression=JPEG frame=10 save=[E:\\videos\\Stack.avi]");
                    System.out.println("Done");
                    doDelete();
                    imp.setSlice(1);
                    imp = new ImagePlus("Animation");
                    imageStack = new ImageStack();
                }
                doWait(Data.timeUnit);
            }
        }
        );
    }

    private List<ImageView> createTrack(int num, double xStart, double yStart, String url) {
        List<ImageView> imageViews = new ArrayList<>();
        Image image1 = new Image(url);
        for (int i = 0; i < num + 1; i++) {
            ImageView imageView = new ImageView(image1);
            imageView.setX(xStart + 30 * i);
            imageView.setY(yStart);
            imageView.setFitHeight(25);
            imageView.setFitWidth(25);
            imageViews.add(imageView);
        }
        return imageViews;
    }

    private String getNewEnd(String direction) {
        switch (direction) {
            case "left":
                return "-1,-1";
            case "right":
                return "2,2";
            case "down":
                return "2,2";
            case "up":
                return "-1,-1";
        }

        return null;
    }

    private BufferedImage createSnapshot(final Scene scene) {
        return SwingFXUtils.fromFXImage(scene.snapshot(new WritableImage(Data.width, Data.height)), null); // Get buffered image.
        //ImageIO.write(image, "jpeg", raOutputStream);
    }
}