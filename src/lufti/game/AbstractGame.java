package lufti.game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;
import lufti.ui.Canvas;
import lufti.ui.Window;

/**
 *
 * @author ubik
 */
public abstract class AbstractGame {
    
    public AbstractGame() {}
    
    public static void attach(final AbstractGame target, Window window, int ups) {
        window.getCanvas().addRenderCallback(new Canvas.RenderCallback() {
            @Override
            public void render(Canvas.CanvasPainter pntr) {
                synchronized(target) {
                    target.render(pntr);
                }
            }
        });
        window.getCanvas().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                target.mouseDown();
            }
        });
        
        window.getCanvas().addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                target.mouseMove(e.getX(), e.getY());
            }
        });
        
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                synchronized(target) {
                    target.update();
                }
            }
        }, 0, (long)Math.ceil(1000/(double)ups));
    }
    
    public abstract void mouseMove(int x, int y);
    public abstract void mouseDown();
    
    public abstract void update();
    public abstract void render(Canvas.CanvasPainter pntr);
}
