import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class JGradientButton extends JButton {
    private static final long serialVersionUID = 1L;
    private Color[] stops = new Color[2];
    private Paint colorGradients[] = new Paint[2];
    private Point[] stopPoints = new Point[3];

    public JGradientButton(Color stopTop, Color stopBottom) {
        this("", stopTop, stopBottom);
    }

    public JGradientButton(String text, Color stopTop, Color stopBottom) {
        this(text, null, stopTop, stopBottom);
    }

    public JGradientButton(String text, Icon icon, Color stopTop, Color stopBottom) {
        super(text, icon);

        setContentAreaFilled(false);
        setFocusPainted(false);

        stops[0] = stopTop;
        stops[1] = stopBottom;

        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                invalidate();
            }
        });
    }

    public void invalidate() {
        super.invalidate();

        stopPoints[0] = new Point(0, 0);
        stopPoints[1] = new Point(0, (int) (getHeight() * 0.33));
        stopPoints[2] = new Point(0, (int) (getHeight() * 1.00));

        int stop = getModel().isPressed() ? 1 : 0;
        Color highlight = stop == 0 ? Color.WHITE : Color.BLACK;

        colorGradients[0] = new GradientPaint(stopPoints[0], stops[stop], stopPoints[1], highlight);
        colorGradients[1] = new GradientPaint(stopPoints[1], highlight, stopPoints[2], stops[1 - stop]);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        for (int i = 0, offset = getHeight() / stopPoints.length; i < colorGradients.length; i++) {
            g2.setPaint(colorGradients[i]);
            g2.fillRect(0, i * offset, getWidth(), getHeight() - (i * offset));
        }
        g2.dispose();

        super.paintComponent(g);
    }
}