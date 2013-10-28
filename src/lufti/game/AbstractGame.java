package lufti.game;

import java.util.Timer;
import java.util.TimerTask;
import lufti.ui.Canvas;
import lufti.ui.Window;

public abstract class AbstractGame {
    
    private final PlayerInput playerInput = new PlayerInput();
    
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

	window.getMainWindow().addKeyListener(target.playerInput);
	
	// Test: mouse input
	/*
	window.getCanvas().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
		synchronized(target.playerInput) {
		    target.playerInput.addCommand(PlayerInput.Command.SHOOT);
		}
            }
        });*/
        
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                synchronized(target) {
                    target.updateGame();
                }
            }
        }, 0, (long)Math.ceil(1000/(double)ups));
    }
    
    private void updateGame() {
	this.update(playerInput);
    }
    
    /**
     * This function is the one that will be called by the main loop of the
     * game. It is expected to pass information to the different components of
     * the game which perform actions and perform global actions. It must also
     * pass the input of the player to the appropriate elements of the game.
     *
     * @param input All the input commands from the player. 
     */
    public abstract void update(PlayerInput input);
    public abstract void render(Canvas.CanvasPainter pntr);
}
