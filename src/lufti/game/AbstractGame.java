package lufti.game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;
import lufti.ui.Canvas;
import lufti.ui.SimpleWindow;

/**
 *
 * @author ubik
 */
public abstract class AbstractGame {
    
    public AbstractGame() {}
    
    public void setup(SimpleWindow window, int ups) {
        final AbstractGame me = this;
        window.getCanvas().addRenderCallback(new Canvas.RenderCallback() {
            @Override
            public void render(Canvas.CanvasPainter pntr) {
                synchronized(me) {
                    me.render(pntr);
                }
            }
        });
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
                synchronized(me) {
                    update();
                }
            }
        }, 0, (long)Math.ceil(1000/(double)ups));
    }
    
    public abstract void mouseMove(int x, int y);
    public abstract void mouseDown();
    
    public abstract void update();
    public abstract void render(Canvas.CanvasPainter pntr);
}
