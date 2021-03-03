import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.*;
import javafx.scene.text.Font;
import javafx.scene.Group;


public class Main extends Application
{
    int BALLXPOS = 280;
    int BALLYPOS = 280;
    int BALLXSPEED = 3;
    int BALLYSPEED = 3;
    int PLAYERSCORE = 0;
    int COMPUTERSCORE = 0;
    int PLAYERX = 585;
    int PLAYERY = 250;
    int COMPUTERX = 0;
    int COMPUTERY = 250;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Canvas canvas = new Canvas(600, 500);
        GraphicsContext graphics_context = canvas.getGraphicsContext2D();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), a -> pong(graphics_context)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        Group group = new Group(canvas);
        Scene scene = new Scene(group, 600, 500);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                switch(event.getCode())
                {
                    case UP: PLAYERY -= 5; break;
                    case DOWN: PLAYERY += 5; break;
                }
            }
        });


        primaryStage.setScene(scene);
        primaryStage.show();
        timeline.play();
    }

    public void pong(GraphicsContext graphics_context)
    {
        graphics_context.clearRect(0,0, graphics_context.getCanvas().getWidth(), graphics_context.getCanvas().getHeight());

        graphics_context.setFont(Font.font(30));

        COMPUTERY = BALLYPOS - 50;


        BALLXPOS += BALLXSPEED;
        BALLYPOS += BALLYSPEED;

        graphics_context.fillOval(BALLXPOS, BALLYPOS, 15, 15);
        graphics_context.fillRect(COMPUTERX, COMPUTERY, 15, 100);
        graphics_context.fillRect(PLAYERX, PLAYERY, 15, 100);
        graphics_context.fillText( " " + COMPUTERSCORE, 200, 166, 100);
        graphics_context.fillText( " " + PLAYERSCORE, 400, 166, 100);

        if(BALLXPOS + 15 > PLAYERX && BALLYPOS >= PLAYERY && BALLYPOS <= PLAYERY + 100 || BALLXPOS < COMPUTERX + 15 && BALLYPOS > COMPUTERY && BALLYPOS <= COMPUTERY + 100)
        {
            BALLXSPEED *= -1;
            BALLYSPEED *= -1;
        }
        if(BALLYPOS >= 500 || BALLYPOS <= 0)
        {
            BALLYSPEED *= -1;
        }
        if(BALLXPOS >= 600 || BALLXPOS <= 0|| BALLXPOS == COMPUTERX || BALLXPOS == PLAYERX)
        {
            BALLXSPEED *=-1;
        }
        if(BALLXPOS <= 0)
        {
            PLAYERSCORE++;
        }
        if(BALLXPOS >= 600)
        {
            COMPUTERSCORE++;
        }

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}