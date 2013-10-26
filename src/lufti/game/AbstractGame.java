package lufti.game;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import lufti.ui.Canvas;
import lufti.ui.SimpleWindow;

/**
 *
 * @author ubik
 */
public abstract class AbstractGame implements Canvas.RenderCallback {
    
    private SimpleWindow window = null;
    
    public AbstractGame() {}
    
    public void setup(int width, int height, int ups, int fps, Color bg) {
        SimpleWindow window = SimpleWindow.create(width, height, fps, bg);
        
        window.getCanvas().addRenderCallback(this);
        window.getCanvas().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseDown();
            }
        });
        
        window.getCanvas().addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseMove(e.getX(), e.getY());
            }
        });
        
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, 0, (long)Math.ceil(1000/(double)ups));
    }
    
    public abstract void mouseMove(int x, int y);
    public abstract void mouseDown();
    
    public abstract void update();
    public abstract void render(Canvas.CanvasPainter pntr);
}
