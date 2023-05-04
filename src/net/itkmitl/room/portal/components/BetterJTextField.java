package net.itkmitl.room.portal.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
public class BetterJTextField extends JTextField implements MouseListener, FocusListener, DocumentListener {
    private Color normalBgColor = Color.WHITE;
    private Color hoverBgColor = new Color(246, 246, 246);
    private Color borderColor = Color.LIGHT_GRAY;
    private Color activeBorderColor = new Color(94, 135, 197);
    private String placeholderText;
    private Boolean selected;
    public BetterJTextField(String placeholderText) {
        this.placeholderText = placeholderText;
        this.setFont(new Font("Sanserif", Font.PLAIN, 24));
        Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, borderColor);
        setBorder(border);
        setText(placeholderText);
        setForeground(Color.GRAY);
        addMouseListener(this);
        addFocusListener(this);
        this.getDocument().addDocumentListener(this);
    }

    public void setHoverBgColor(Color hoverBgColor) {
        this.hoverBgColor = hoverBgColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public void setPlaceholderText(String placeholderText) {
        this.placeholderText = placeholderText;
    }

    public void setNormalBgColor(Color normalBgColor) {
        this.normalBgColor = normalBgColor;
    }

    private void updatePlaceholderVisibility() {
        if (getText().isEmpty() && !selected) {
            setText(placeholderText);
            setForeground(Color.GRAY);
        } else if (getText().equals(placeholderText)) {
            setForeground(Color.GRAY);
        } else {
            setForeground(Color.BLACK);

        }
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(hoverBgColor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(normalBgColor);
    }

    @Override
    public void focusGained(FocusEvent e) {
        selected = true;
        if (getText().equals(placeholderText) || (getText().isEmpty())) {
            setText("");
            setForeground(Color.BLACK);
        }
        setBackground(hoverBgColor);
        Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, activeBorderColor);
        setBorder(border);
    }

    @Override
    public void focusLost(FocusEvent e) {
        if ((getText().isEmpty())) {
            setText(placeholderText);
            setForeground(Color.GRAY);
        }
        setBackground(normalBgColor);
        selected = false;
        Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, borderColor);
        setBorder(border);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        SwingUtilities.invokeLater(() -> updatePlaceholderVisibility());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        SwingUtilities.invokeLater(() -> updatePlaceholderVisibility());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        SwingUtilities.invokeLater(() -> updatePlaceholderVisibility());
    }
}
