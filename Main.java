package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    static final int HEIGHT = 50, TRANSITIONSTEPS=450;
    static double vx=0, vy=0, velocity=0.5, jump=20, gravity=1, friction=0, width=50;
    static boolean isPressedLeft=false, isPressedRight=false, isPressedUp=false, isJumping=false,
    isOnSlime=false, landed=true;
    static Color newColor2;
    static Rectangle player;
    static int mode=0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane root = new AnchorPane();

        Scene scene = new Scene(root, 1200, 600);

        Rectangle background = new Rectangle(scene.getWidth(), scene.getHeight());
        background.setY(0);
        background.setX(0);
        background.setFill(Color.BEIGE);
        root.getChildren().add(background);

        Rectangle floor = new Rectangle(1200, HEIGHT);
        floor.setY(550);
        floor.setX(0);
        root.getChildren().add(floor);

        Rectangle gravel = new Rectangle(250, HEIGHT);
        gravel.setY(550);
        gravel.setX(900);
        gravel.setFill(Color.GRAY);
        root.getChildren().add(gravel);

        Rectangle ice = new Rectangle(300, HEIGHT);
        ice.setY(550);
        ice.setX(600);
        ice.setFill(Color.LIGHTBLUE);
        root.getChildren().add(ice);

        Rectangle slime = new Rectangle(150, HEIGHT);
        slime.setY(550);
        slime.setX(300);
        slime.setFill(Color.LIGHTGREEN);
        root.getChildren().add(slime);

        Rectangle sswitch = new Rectangle(100, HEIGHT);
        sswitch.setY(550);
        sswitch.setX(100);
        sswitch.setFill(Color.FIREBRICK);
        root.getChildren().add(sswitch);

        Rectangle ceiling = new Rectangle(1200, HEIGHT);
        ceiling.setY(0);
        ceiling.setX(0);
        ceiling.setFill(Color.BEIGE);
        root.getChildren().add(ceiling);

        Rectangle slime2 = new Rectangle(300, HEIGHT);
        slime2.setY(0);
        slime2.setX(100);
        slime2.setFill(Color.BEIGE);
        root.getChildren().add(slime2);

        Rectangle gravel2 = new Rectangle(400, HEIGHT);
        gravel2.setY(0);
        gravel2.setX(400);
        gravel2.setFill(Color.BEIGE);
        root.getChildren().add(gravel2);

        Rectangle ice2 = new Rectangle(100, HEIGHT);
        ice2.setY(0);
        ice2.setX(900);
        ice2.setFill(Color.BEIGE);
        root.getChildren().add(ice2);

        Rectangle sswitch2 = new Rectangle(100, HEIGHT);
        sswitch2.setY(0);
        sswitch2.setX(1050);
        sswitch2.setFill(Color.BEIGE);
        root.getChildren().add(sswitch2);

        player = new Rectangle(width, 50);
        player.setY(300);
        player.setX(600);
        player.setFill(Color.VIOLET);
        root.getChildren().add(player);

        Rectangle player2 = new Rectangle(width, 50);
        player2.setY(300);
        player2.setX(600-scene.getWidth());
        player2.setFill(Color.VIOLET);
        root.getChildren().add(player2);

        scene.setOnKeyPressed(event -> {
            if(event.getCode()== KeyCode.LEFT)
            {
                isPressedLeft=true;
            }
            if(event.getCode()== KeyCode.RIGHT)
            {
                isPressedRight=true;
            }
            if(event.getCode()== KeyCode.UP)
            {
                if(isJumping==false) {
                    isPressedUp = true;
                    isJumping = true;
                }
            }
        });

        scene.setOnKeyReleased(event -> {
            if(event.getCode()== KeyCode.LEFT)
            {
                isPressedLeft=false;
            }
            if(event.getCode()== KeyCode.RIGHT)
            {
                isPressedRight=false;
            }
            if(event.getCode()== KeyCode.UP)
            {
                isPressedUp=false;
            }
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),event -> {
            if(isPressedUp && !isJumping)
            {
                vy-=jump;
            }
            if(isPressedLeft)
            {
                vx-=velocity;
            }
            if(isPressedRight)
            {
                vx+=velocity;
            }

            player.setX(player.getX()+vx);
            player.setY(player.getY()+vy);
            player2.setX(player.getX()-scene.getWidth());
            player2.setY(player.getY());
            if(mode==0)
            {
                if(player.getY()>=498)
                {
                    landed=true;
                }
            if((player.getX()<100-width || (player.getX()>200 && player.getX()<300-width)
              || (player.getX()>450 && player.getX()<600-width) || player.getX()>1150
              || isJumping==true)&& isOnSlime !=true)
            {
                friction=0.9;
                jump=20;
            }
            if(player.getX()<300-width || player.getX()>450)
            {
                isOnSlime=false;
            }
            if(player.getX()>300-width && player.getX()<450 && isJumping==false)
            {
                jump=2;
                friction=0.4;
                isOnSlime=true;
                if(player.getY()<490)
                {
                    vy=0;
                    player.setY(490);
                }

            }
            if(player.getX()>600 && player.getX()<900-width && isJumping==false)
            {
                friction=1;
            }
            if(player.getX()>900 && player.getX()<1150 && isJumping==false)
            {
                friction=0.7;
            }
            if(player.getX()>100-width && player.getX()<200)
            {
                if(player.getY()>=498 && isPressedUp==true)
                {
                    vy=0;
                    gravity*=-1;
                    mode=1;
                    landed=false;
                }
            } }
            else
            {
                if(player.getY()<=52)
                {
                    landed=true;
                }
                if((player.getX()<100-width || (player.getX()>800 && player.getX()<900-width)
                        || player.getX()>1000 || isJumping==true)&& isOnSlime !=true)
                {
                    friction=0.9;
                    jump=-20;
                }
                if(player.getX()<100-width || player.getX()>400)
                {
                    isOnSlime=false;
                }
                if(player.getX()>100-width && player.getX()<400 && isJumping==false)
                {
                    jump=-2;
                    friction=0.4;
                    isOnSlime=true;
                    if(player.getY()>60)
                    {
                        vy=0;
                        player.setY(60);
                    }

                }
                if(player.getX()>900 && player.getX()<1000-width && isJumping==false)
                {
                    friction=1;
                }
                if(player.getX()>400 && player.getX()<800 && isJumping==false)
                {
                    friction=0.7;
                }
                if(player.getX()>1050-width && player.getX()<1150)
                {
                    if(player.getY()<=52 && isPressedUp==true)
                    {
                        vy=0;
                        gravity*=-1;
                        mode=0;
                        landed=false;
                    }
                }
            }
            vx*=friction;
            if(mode==0)
            {
            if(player.getY()<=500)
            {
                vy+=gravity;
            }
            if(player.getY()>500)
            {
                player.setY(500);
                vy=0;
                isJumping=false;
            }
            if(player.getY()!=500)
            {
                isJumping=true;
            }
            if(player2.getY()>500)
            {
                player2.setY(500);
                vy=0;
            }}
            else if(mode==1)
            {
                if(player.getY()>=50)
                {
                    vy+=gravity;
                }
                if(player.getY()<50)
                {
                    player.setY(50);
                    vy=0;
                    isJumping=false;
                }
                if(player.getY()!=50)
                {
                    isJumping=true;
                }
                if(player2.getY()<50)
                {
                    player2.setY(50);
                    vy=0;
                }
            }
            if(player.getX()>1205)
            {
                double temp;
                temp=player.getX();
                player.setX(player2.getX());
                player2.setX(temp);
            }
            if(player.getX()<50)
            {
                double temp;
                temp=player.getX();
                player.setX(temp+scene.getWidth());
                player2.setX(temp);
            }
            if(landed==false)
            {
                colorChange(background, Color.BLACK, Color.BEIGE, player2.getY());
                colorChange(slime2, Color.DARKGREEN, Color.BEIGE, player.getY());
                colorChange(ice2, Color.BLUE, Color.BEIGE, player.getY());
                colorChange(gravel2, Color.DARKGRAY, Color.BEIGE, player.getY());
                colorChange(sswitch2, Color.DARKRED, Color.BEIGE, player.getY());
                colorChange(slime, Color.BLACK, Color.LIGHTGREEN, player.getY());
                colorChange(ice, Color.BLACK, Color.LIGHTBLUE, player.getY());
                colorChange(gravel, Color.BLACK, Color.GRAY, player.getY());
                colorChange(sswitch, Color.BLACK, Color.FIREBRICK, player.getY());
                colorChange(player, Color.PURPLE, Color.VIOLET, player.getY());
                colorChange(player2, Color.PURPLE, Color.VIOLET, player.getY());
            }
            if((landed==true && mode==1) && (background.getFill()!=Color.BLACK
            || slime2.getFill()!=Color.DARKGREEN || ice2.getFill()!=Color.BLUE
            || gravel2.getFill()!=Color.DARKGRAY || sswitch2.getFill()!=Color.DARKRED
            || slime.getFill()!=Color.BLACK || ice.getFill()!=Color.BLACK
            || gravel.getFill()!=Color.BLACK || sswitch.getFill()!=Color.BLACK
            || player.getFill()!=Color.PURPLE || player2.getFill()!=Color.PURPLE))
            {
                background.setFill(Color.BLACK);
                slime2.setFill(Color.DARKGREEN);
                ice2.setFill(Color.BLUE);
                gravel2.setFill(Color.DARKGRAY);
                sswitch2.setFill(Color.DARKRED);
                slime.setFill(Color.BLACK);
                ice.setFill(Color.BLACK);
                gravel.setFill(Color.BLACK);
                sswitch.setFill(Color.BLACK);
                player.setFill(Color.PURPLE);
                player2.setFill(Color.PURPLE);
            }
            if((landed==true && mode==0) && (background.getFill()!=Color.BEIGE
                    || slime2.getFill()!=Color.BEIGE || ice2.getFill()!=Color.BEIGE
                    || gravel2.getFill()!=Color.BEIGE || sswitch2.getFill()!=Color.BEIGE
                    || slime.getFill()!=Color.LIGHTGREEN || ice.getFill()!=Color.LIGHTBLUE
                    || gravel.getFill()!=Color.GRAY || sswitch.getFill()!=Color.FIREBRICK
                    || player.getFill()!=Color.VIOLET || player2.getFill()!=Color.VIOLET))
            {
                background.setFill(Color.BEIGE);
                slime2.setFill(Color.BEIGE);
                ice2.setFill(Color.BEIGE);
                gravel2.setFill(Color.BEIGE);
                sswitch2.setFill(Color.BEIGE);
                slime.setFill(Color.LIGHTGREEN);
                ice.setFill(Color.LIGHTBLUE);
                gravel.setFill(Color.GRAY);
                sswitch.setFill(Color.FIREBRICK);
                player.setFill(Color.VIOLET);
                player2.setFill(Color.VIOLET);
            }
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Koopa");
        stage.show();
    }

    public static void colorChange(Rectangle rec, Color newColor, Color oldColor, double y)
    {

        double transitionRed = newColor.getRed()-oldColor.getRed();
        double transitionGreen = newColor.getGreen()-oldColor.getGreen();
        double transitionBlue = newColor.getBlue()-oldColor.getBlue();
            newColor2 = new Color(
                    Math.abs(oldColor.getRed()+(transitionRed/TRANSITIONSTEPS*(500-y))),
                    Math.abs(oldColor.getGreen()+(transitionGreen/TRANSITIONSTEPS*(500-y))),
                    Math.abs(oldColor.getBlue()+(transitionBlue/TRANSITIONSTEPS*(500-y))),
                    1);
            rec.setFill(newColor2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
