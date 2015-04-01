package ca.yorku.cse2311.tab2pdf.ui.component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.logging.Logger;

/**
 * ToolBar
 *
 * The toolbar of our GUI
 *
 * @author Glib Sitiugin, Marco Cirillo
 * @since 2015-03-19
 */
 public class ToolBar extends JToolBar {

    private final static Logger LOG = Logger.getLogger(ToolBar.class.getName());

    /**
     * Toolbar elements
     */
    private final JButton OPEN_BUTTON;

    private final JButton SAVE_BUTTON;

    private final JButton EXPORT_BUTTON;

    private final JButton SETTINGS_BUTTON;

    private final JButton HELP_BUTTON;

    public JButton getHelpButton() {

        return HELP_BUTTON;
    }

    public JButton getSettingsButton() {

        return SETTINGS_BUTTON;
    }

    public JButton getExportButton() {

        return EXPORT_BUTTON;
    }

    public JButton getSaveButton() {

        return SAVE_BUTTON;
    }

    public JButton getOpenButton() {

        return OPEN_BUTTON;
    }

    public ToolBar() {

        super();

        this.setFloatable(false);   // Don't let user detach (float) this toolbar

        // add buttons
        this.add(this.OPEN_BUTTON = createButton("folder-open.png", "Open Tab", "Open a Tab File"));
        this.add(this.SAVE_BUTTON = createButton("document-save.png", "Save Tab", "Save the Tab File"));
        this.addSeparator();
        this.add(this.EXPORT_BUTTON = createButton("application-pdf.png", "Export to PDF", "Export the Tab to a PDF"));
        this.addSeparator();
        this.add(this.SETTINGS_BUTTON = createButton("settings.png", "Settings", "Edit User Settings"));
        this.add(this.HELP_BUTTON = createButton("help.png", "Help", "Opens the Help Manual"));
    }

    /**
     * Creates a toolbar button
     *
     * @param rawImage  The filename of the image to use. Must be in the `resources/toolbar` directory
     * @param text      The Text to show alongside the image
     * @param tooltip   The tooltip for the button
     *
     * @return  a toolbar button
     */
    private JButton createButton(String rawImage, String text, String tooltip) {

        final JButton button = new JButton(text);
        button.setToolTipText(tooltip);

        try {   // try to give this button an icon
            LOG.info("loading image: resources/toolbar/"+rawImage);
            button.setIcon(new ImageIcon(
                    ImageIO.read(ClassLoader.getSystemResource("toolbar/" + rawImage)),
                    tooltip
            ));
        } catch (Exception e) {
            LOG.severe("Failed to load image: "+e.getMessage());
        }

        return button;
    }

    @Override
    public void setEnabled(boolean enabled) {

        super.setEnabled(enabled);

        getSaveButton().setEnabled(enabled);
        getExportButton().setEnabled(enabled);
    }
}
