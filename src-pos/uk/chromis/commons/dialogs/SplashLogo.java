/*
**    AZOR POS  - Point of Sale System
**
**    This file is part of AZOR POS Version V1.5.5
**
**    Developed by Mazen Mostafa
**    Based on Chromis POS - Copyright (c) 2015-2023 Chromis & previous Openbravo POS related works
**
**    https://www.chromis.co.uk
**
**    AZOR POS is free software: you can redistribute it and/or modify
**    it under the terms of the GNU General Public License as published by
**    the Free Software Foundation, either version 3 of the License, or
**    (at your option) any later version.
**
**    AZOR POS is distributed in the hope that it will be useful,
**    but WITHOUT ANY WARRANTY; without even the implied warranty of
**    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
**    GNU General Public License for more details.
**
**    You should have received a copy of the GNU General Public License
**    along with AZOR POS.  If not, see <http://www.gnu.org/licenses/>
**
 */
package uk.chromis.commons.dialogs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import uk.chromis.pos.forms.AppLocal;
import uk.chromis.pos.forms.ChromisFonts;

/**
 *
 * @author John
 */
public class SplashLogo extends JFrame {

    private final JPanel splashPanel = new JPanel(new MigLayout("insets 3 0 3 0", "[][]", "[]0[]0[50:20:50]"));
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int centreX = screenSize.width / 2;
    private final int centreY = screenSize.height / 2;

    public SplashLogo() {
        createPage();
        setLocation(centreX - 280, centreY - 150);
        setSize(580, 310);
        setUndecorated(true);
        add(splashPanel);
        setVisible(true);
    }
    
    private void createPage() {
        String currentPath = System.getProperty("user.dir");
        JLabel imageLabel = new JLabel();
        JLabel textLabel = new JLabel();
        JLabel spinning = new JLabel();
        JLabel version = new JLabel();
        JLabel developerCredit = new JLabel();

        imageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uk/chromis/fixedimages/azorlogo.png")));
        textLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/uk/chromis/fixedimages/starttext.png")));
        spinning.setIcon(new javax.swing.ImageIcon(currentPath + "/configuiration/spinning.gif"));

        version.setText("V" + AppLocal.APP_VERSION + "       ");
        version.setForeground(new Color(29, 15, 191));
        version.setFont(ChromisFonts.CHROMISFONTBOLD);

        developerCredit.setText("Developed by Mazen Mostafa");
        developerCredit.setForeground(new Color(0, 102, 204));
        developerCredit.setFont(ChromisFonts.CHROMISFONTBOLD.deriveFont(14f));

        splashPanel.add(imageLabel, "height ::157, span, wrap");
        splashPanel.add(version, "height ::20, span, wrap, align right");
        splashPanel.add(developerCredit, "height ::25, span, wrap, align center");
        splashPanel.add(textLabel, "width ::480 , height :45:");

        splashPanel.add(spinning, "height :50:");
        splashPanel.setBackground(Color.white);
        splashPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        spinning.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
    }
    
    public void deleteSplashLogo() {
        this.setVisible(false);
        this.dispose();
    }
}
