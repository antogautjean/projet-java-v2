package org.antogautjean.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import org.antogautjean.Controller.FactoryController;
import org.antogautjean.Controller.StockController;
import org.antogautjean.view.tabs.FactoryTab;
import org.antogautjean.view.tabs.SettingsTab;
import org.antogautjean.view.tabs.StaffTab;
import org.antogautjean.view.tabs.TabInterface;

public class HomeView {

    protected JTabbedPane tabs;

    public HomeView(StockController stockList, FactoryController lineList) throws Exception {

        frameInit();

        TabInterface[] tabs = { new FactoryTab(stockList, lineList), new StaffTab(),  new SettingsTab() };
        this.initTabs(tabs);
        // initTabs(factoryTab(stockList, lineList), settingTab());

        this.mainFrame.setVisible(true);
    }

    private JFrame mainFrame;

    public void frameInit(){
        this.mainFrame = new JFrame("Factory");
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setMinimumSize(new Dimension(1200,500));
        this.mainFrame.setTitle("Gestionnaire de production");
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setIconImage(new ImageIcon("./src/main/java/org/antogautjean/data/factory_icon.png").getImage());
    }

    private void initTabs(TabInterface[] tabs) throws Exception {
        this.tabs = new JTabbedPane();

        for (int i = 0; i < tabs.length; i++) {
            TabInterface tab = tabs[i];
            this.tabs.add(tab.getTabTitle(), tab.getComponent());
            JLabel tabLabel = new JLabel(tab.getTabTitle());
            tabLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            this.tabs.setTabComponentAt(i, tabLabel);
        }

        this.mainFrame.getContentPane().add(this.tabs);
    }
}
