package org.antogautjean.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import org.antogautjean.controller.FactoryController;
import org.antogautjean.controller.StaffController;
import org.antogautjean.controller.StockController;
import org.antogautjean.view.tabs.FactoryTab;
import org.antogautjean.view.tabs.SettingsTab;
import org.antogautjean.view.tabs.StaffTab;
import org.antogautjean.view.tabs.TabInterface;

/**
 * Classe permettant l'affichage de l'application
 */
public class HomeView {

    protected JTabbedPane tabsContainer;
    protected ArrayList<TabInterface> tabsContent = new ArrayList<>();

    public HomeView(StockController stockCtrl, FactoryController factoryCtrl, StaffController staffCtrl) {
        frameInit();

        this.tabsContent.add(new FactoryTab(stockCtrl, factoryCtrl, this));
        this.tabsContent.add(new StaffTab(staffCtrl, this));
        this.tabsContent.add(new SettingsTab(this));

        this.init();
    }

    private JFrame mainFrame;

    public void frameInit() {
        this.mainFrame = new JFrame("Factory");
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setMinimumSize(new Dimension(1200, 500));
        this.mainFrame.setTitle("Gestionnaire de production");
        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setIconImage(new ImageIcon("./src/main/java/org/antogautjean/data/factory_icon.png").getImage());
    }

    public void refreshTabs(boolean refreshFromFiles) {
        // refresh controllers from file
        ArrayList<String> failedTabLoading = new ArrayList<>();
        int cursor = 0;
        for (TabInterface tab : this.tabsContent) {
            if (!(tab instanceof SettingsTab)) {
                this.tabsContainer.setComponentAt(cursor, tab.getComponent(refreshFromFiles));
                if (tab.getIfRenderedCorrectly()) {
                    failedTabLoading.add(tab.getTabTitle());
                }
                cursor++;
            }
        }
        displayFailedTabs(failedTabLoading);
    }

    public void init() {
        this.tabsContainer = new JTabbedPane();
        Font font = new Font("Arial", Font.BOLD, 12);

        int cursor = 0;
        ArrayList<String> failedTabLoading = new ArrayList<>();
        for (TabInterface tab : this.tabsContent) {
            this.tabsContainer.add(tab.getTabTitle(), tab.getComponent(true));
            JLabel tabLabel = new JLabel(tab.getTabTitle());
            tabLabel.setFont(font);
            tabLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            this.tabsContainer.setTabComponentAt(cursor, tabLabel);
            if (tab.getIfRenderedCorrectly()) {
                failedTabLoading.add(tab.getTabTitle());
            }
            cursor++;
        }
        this.mainFrame.getContentPane().add(this.tabsContainer);
        this.mainFrame.setVisible(true);

        displayFailedTabs(failedTabLoading);
    }

    /**
     * N'affiche rien si la liste est vide
     */
    protected void displayFailedTabs(ArrayList<String> failedTabLoading) {
        if (failedTabLoading.size() > 0) {
            JOptionPane.showMessageDialog(null, "Les onglets suivant n'ont pas pu être chargés correctement :\n- "
                    + String.join("\n- ", failedTabLoading), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshStockPanel() {
        ((FactoryTab) this.tabsContent.get(0)).configStockPanel();
    }

    public void refreshProdLinesPanel() {
        ((FactoryTab) this.tabsContent.get(0)).configProdLinesPanel();
        refreshStockPanel();
    }
}
