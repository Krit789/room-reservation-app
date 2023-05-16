package net.itkmitl.room.portal.components;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.Serial;

import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class ButtonGradient extends JButton {
    @Serial
    private static final long serialVersionUID = -4638661821951496102L;
    private static final Font defaultFont = new Font("Cousine", Font.BOLD, 18);

    public float getSizeSpeed() {
        return sizeSpeed;
    }

    public void setSizeSpeed(float sizeSpeed) {
        this.sizeSpeed = sizeSpeed;
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public static Font getDefaultFont() {
        return defaultFont;
    }

    public static String getDefaulFontName() {
        return defaultFont.getFontName();
    }

    private Color color1 = Color.decode("#0099F7");
    private Color color2 = Color.decode("#F11712");
    private final Timer timer;
    private final Timer timerPressed;
    private float alpha = 0.3f;
    private boolean mouseOver;
    private boolean pressed;
    private Point pressedLocation;
    private float pressedSize;
    private float sizeSpeed = 6f;
    private float alphaPressed = 0.5f;

    // ..............Constructor..............
    public ButtonGradient() {
        this(null);
    }

    public ButtonGradient(String text, Color color) {
        this(text, color, color);
    }

    public ButtonGradient(String text, Color color1, Color color2) {
        this(text);
        this.color1 = color1;
        this.color2 = color2;
    }

    public ButtonGradient(String text) {
        super(text);
        setContentAreaFilled(false);
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(new EmptyBorder(10, 20, 10, 20));
        setFont(defaultFont);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                mouseOver = true;
                timer.start();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                mouseOver = false;
                timer.start();
            }

            @Override
            public void mousePressed(MouseEvent me) {
                pressedSize = 0;
                alphaPressed = 0.5f;
                pressed = true;
                pressedLocation = me.getPoint();
                timerPressed.setDelay(0);
                timerPressed.start();
            }
        });
        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mouseOver) {
                    if (alpha < 0.6f) {
                        alpha += 0.05f;
                        repaint();
                    } else {
                        alpha = 0.6f;
                        timer.stop();
                        repaint();
                    }
                } else {
                    if (alpha > 0.3f) {
                        alpha -= 0.05f;
                        repaint();
                    } else {
                        alpha = 0.3f;
                        timer.stop();
                        repaint();
                    }
                }
            }
        });
        timerPressed = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedSize += getSizeSpeed();
                if (alphaPressed <= 0) {
                    pressed = false;
                    timerPressed.stop();
                } else {
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //  Create Gradients Color
        GradientPaint gra = new GradientPaint(0, 0, color1, width, 0, color2);
        g2.setPaint(gra);
        g2.fillRoundRect(0, 0, width, height, height, height);
        //  Add Style
//        createStyle(g2);
        if (pressed) {
            paintPressed(g2);
        }
        g2.dispose();
        g.drawImage(img, 0, 0, null);
        super.paintComponent(g);
    }

    private void paintPressed(Graphics2D g2) {
        if (pressedLocation.x - (pressedSize / 2) < 0 && pressedLocation.x + (pressedSize / 2) > getWidth()) {
            timerPressed.setDelay(20);
            alphaPressed -= 0.05f;
            if (alphaPressed < 0) {
                alphaPressed = 0;
            }
        }
        g2.setColor(Color.WHITE);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alphaPressed));
        float x = pressedLocation.x - (pressedSize / 2);
        float y = pressedLocation.y - (pressedSize / 2);
        g2.fillOval((int) x, (int) y, (int) pressedSize, (int) pressedSize);
    }
}
