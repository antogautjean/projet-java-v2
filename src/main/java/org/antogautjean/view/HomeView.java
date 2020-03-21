package org.antogautjean.view;

import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.JLabel;
import com.intellij.uiDesigner.core.Spacer;
import javax.swing.Icon;
import java.awt.Component;
import com.intellij.uiDesigner.core.GridConstraints;
import java.awt.Dimension;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.LayoutManager;
import com.intellij.uiDesigner.core.GridLayoutManager;
import java.awt.Insets;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

public class HomeView extends JPanel
{
    private JPanel homePanel;
    private JTabbedPane Dashboard;
    private JProgressBar progressBar1;
    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton loadButton;
    private JTable stockList;

    public HomeView() {
        final String title = "Factory";
        this.setupUI();
        final JFrame frame = new JFrame(title);
        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(3);
        frame.setContentPane(this.homePanel);
        frame.setVisible(true);
    }

    private void setupUI() {
        final JPanel homePanel = new JPanel();
        (this.homePanel = homePanel).setLayout((LayoutManager)new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1, false, false));
        homePanel.setBackground(new Color(-1));
        homePanel.setEnabled(false);
        homePanel.setBorder(BorderFactory.createTitledBorder(null, "FACTORY Manager", 2, 0, this.getFont("Arial Black", -1, 18, homePanel.getFont()), new Color(-4521961)));
        final JTabbedPane dashboard = new JTabbedPane();
        homePanel.add(this.Dashboard = dashboard, new GridConstraints(0, 0, 1, 1, 0, 3, 3, 3, (Dimension)null, new Dimension(200, 200), (Dimension)null));
        final JPanel component = new JPanel();
        component.setLayout((LayoutManager)new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1, false, false));
        dashboard.addTab("Dashboard", null, component, null);
        component.add(this.progressBar1 = new JProgressBar(), new GridConstraints(0, 0, 1, 1, 0, 1, 6, 0, (Dimension)null, (Dimension)null, (Dimension)null));
        component.add((Component)new Spacer(), new GridConstraints(1, 0, 1, 1, 0, 2, 1, 6, (Dimension)null, (Dimension)null, (Dimension)null));
        final JPanel component2 = new JPanel();
        component2.setLayout((LayoutManager)new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1, false, false));
        dashboard.addTab("Stock", null, component2, null);
        component2.add(this.stockList = new JTable(), new GridConstraints(0, 0, 2, 1, 0, 3, 6, 6, (Dimension)null, new Dimension(50, 50), (Dimension)null));
        component2.add((Component)new Spacer(), new GridConstraints(1, 1, 1, 1, 0, 1, 6, 1, (Dimension)null, (Dimension)null, (Dimension)null));
        final JPanel comp = new JPanel();
        comp.setLayout((LayoutManager)new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1, false, false));
        component2.add(comp, new GridConstraints(0, 1, 1, 1, 0, 3, 3, 3, (Dimension)null, (Dimension)null, (Dimension)null));
        final JPanel component3 = new JPanel();
        component3.setLayout((LayoutManager)new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1, false, false));
        dashboard.addTab("History", null, component3, null);
        final JPanel component4 = new JPanel();
        component4.setLayout((LayoutManager)new GridLayoutManager(9, 2, new Insets(0, 0, 0, 0), -1, -1, false, false));
        dashboard.addTab("Settings", null, component4, null);
        final JButton button = new JButton();
        (this.button1 = button).setText("Load");
        component4.add(button, new GridConstraints(4, 0, 1, 1, 0, 1, 3, 0, (Dimension)null, (Dimension)null, (Dimension)null));
        component4.add((Component)new Spacer(), new GridConstraints(4, 1, 1, 1, 0, 1, 6, 1, (Dimension)null, (Dimension)null, (Dimension)null));
        component4.add((Component)new Spacer(), new GridConstraints(8, 0, 1, 1, 0, 2, 1, 6, (Dimension)null, (Dimension)null, (Dimension)null));
        component4.add(this.textField1 = new JTextField(), new GridConstraints(1, 0, 1, 1, 8, 1, 6, 0, (Dimension)null, new Dimension(150, -1), (Dimension)null));
        component4.add(this.textField2 = new JTextField(), new GridConstraints(3, 0, 1, 1, 8, 1, 6, 0, (Dimension)null, new Dimension(150, -1), (Dimension)null));
        final JLabel comp2 = new JLabel();
        comp2.setText("Product File");
        component4.add(comp2, new GridConstraints(0, 0, 1, 1, 8, 0, 0, 0, (Dimension)null, (Dimension)null, (Dimension)null));
        final JLabel comp3 = new JLabel();
        comp3.setText("Price File");
        component4.add(comp3, new GridConstraints(2, 0, 1, 1, 8, 0, 0, 0, (Dimension)null, (Dimension)null, (Dimension)null));
        component4.add((Component)new Spacer(), new GridConstraints(5, 0, 1, 1, 0, 2, 1, 6, (Dimension)null, (Dimension)null, (Dimension)null));
        final JLabel comp4 = new JLabel();
        comp4.setText("Line File");
        component4.add(comp4, new GridConstraints(6, 0, 1, 1, 8, 0, 0, 0, (Dimension)null, (Dimension)null, (Dimension)null));
        final JButton button2 = new JButton();
        (this.loadButton = button2).setText("Load");
        component4.add(button2, new GridConstraints(7, 0, 1, 1, 0, 1, 3, 0, (Dimension)null, (Dimension)null, (Dimension)null));
    }

    private Font getFont(final String name, final int n, final int n2, final Font font) {
        if (font == null) {
            return null;
        }
        String name2;
        if (name == null) {
            name2 = font.getName();
        }
        else {
            final Font font2 = new Font(name, 0, 10);
            if (font2.canDisplay('a') && font2.canDisplay('1')) {
                name2 = name;
            }
            else {
                name2 = font.getName();
            }
        }
        return new Font(name2, (n >= 0) ? n : font.getStyle(), (n2 >= 0) ? n2 : font.getSize());
    }
}