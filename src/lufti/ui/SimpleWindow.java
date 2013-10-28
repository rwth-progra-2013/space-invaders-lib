package lufti.ui;

import java.awt.Color;

/**
 *
 * @author ubik
 */
public class SimpleWindow extends javax.swing.JFrame {
    
    private Canvas canvas;

    public static void setLookAndFeel(String name) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (name.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SimpleWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public static SimpleWindow create(int width, int height, int fps, Color background) {

        final SimpleWindow win = new SimpleWindow(width, height, fps, background);

        java.awt.EventQueue.invokeLater(new Runnable() {
	    @Override
            public void run() {
                win.setVisible(true);
            }
        });

        return win;
    }

    private SimpleWindow(int width, int height, int fps, Color background) {
        initComponents(width, height, fps, background);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    private void initComponents(int width, int height, int fps, Color background) {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, width, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, height, Short.MAX_VALUE)
        );

        canvas = new Canvas(fps, background);
        canvas.setSize(width, height);
        getContentPane().add(canvas);

        pack();

        // Center window
        setLocationRelativeTo(null);
    }
}
