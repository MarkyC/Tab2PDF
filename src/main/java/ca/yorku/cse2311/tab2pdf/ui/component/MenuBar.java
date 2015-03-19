package ca.yorku.cse2311.tab2pdf.ui.component;

import javax.swing.*;

/**
 * MenuBar
 *
 * The menu bar of our application
 *
 * @author Marco
 * @since 2015-03-19
 */
public class MenuBar extends JMenuBar {

    private final JMenu FILE_MENU;
    private final JMenu HELP_MENU;

    private final JMenuItem OPEN_ITEM;
    private final JMenuItem SAVE_ITEM;
    private final JMenuItem EXPORT_ITEM;
    private final JMenuItem SETTINGS_ITEM;
    private final JMenuItem EXIT_ITEM;

    private final JMenuItem USER_MANUAL_ITEM;
    private final JMenuItem ABOUT_ITEM;

    public MenuBar() {

        super();

        this.FILE_MENU = new JMenu("File");
        FILE_MENU.add(this.OPEN_ITEM = new JMenuItem("Open..."));
        FILE_MENU.add(this.SAVE_ITEM = new JMenuItem("Save"));
        FILE_MENU.addSeparator();
        FILE_MENU.add(this.EXPORT_ITEM = new JMenuItem("Export to PDF"));
        FILE_MENU.addSeparator();
        FILE_MENU.add(this.SETTINGS_ITEM = new JMenuItem("Settings"));
        FILE_MENU.addSeparator();
        FILE_MENU.add(this.EXIT_ITEM = new JMenuItem("Exit"));

        this.HELP_MENU = new JMenu("Help");
        HELP_MENU.add(this.USER_MANUAL_ITEM = new JMenuItem("User Manual"));
        HELP_MENU.add(this.ABOUT_ITEM = new JMenuItem("About"));

        this.add(FILE_MENU);
        this.add(HELP_MENU);
    }

    public JMenuItem getOpenMenuItem() {

        return OPEN_ITEM;
    }

    public JMenuItem getSaveMenuItem() {

        return SAVE_ITEM;
    }

    public JMenuItem getExportMenuItem() {

        return EXPORT_ITEM;
    }

    public JMenuItem getSettingsMenuItem() {

        return SETTINGS_ITEM;
    }

    public JMenuItem getExitMenuItem() {

        return EXIT_ITEM;
    }

    public JMenuItem getUserManualMenuItem() {

        return USER_MANUAL_ITEM;
    }

    public JMenuItem getAboutMenuItem() {

        return ABOUT_ITEM;
    }
}
