/*
**    Chromis POS  - Open Source Point of Sale
**
**    This file is part of Chromis POS Version Chromis V1.5.4
**
**    Copyright (c) 2015-2023 Chromis & previous Openbravo POS related works   
**
**    https://www.chromis.co.uk
**   
**    Chromis POS is free software: you can redistribute it and/or modify
**    it under the terms of the GNU General Public License as published by
**    the Free Software Foundation, either version 3 of the License, or
**    (at your option) any later version.
**
**    Chromis POS is distributed in the hope that it will be useful,
**    but WITHOUT ANY WARRANTY; without even the implied warranty of
**    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
**    GNU General Public License for more details.
**
**    You should have received a copy of the GNU General Public License
**    along with Chromis POS.  If not, see <http://www.gnu.org/licenses/>
**
 */
package uk.chromis.pos.loyalty;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import uk.chromis.globals.SystemProperty;
import uk.chromis.basic.BasicException;
import uk.chromis.data.loader.SessionFactory;
import uk.chromis.commons.dialogs.JAlertPane;
import uk.chromis.pos.forms.AppLocal;
import uk.chromis.pos.datalogic.DataLogicSales;
import uk.chromis.pos.forms.ChromisFonts;
import uk.chromis.pos.forms.JRootFrame;
import uk.chromis.pos.sales.JPanelTicket;
import uk.chromis.pos.ticket.ProductInfoExt;

/**
 *
 * @author John
 */
public class LoyaltyInfoPanel extends JDialog {

    private final JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
    private JPanel panel;
    private JButton btn;
    private JPanel btnPanel;

    private final customJLabel cardNumber = new customJLabel("", 16);
    private final customJLabel cardPoints = new customJLabel("", 16);
    private final customJLabel lastActivityDate = new customJLabel("", 16);
    private final customJLabel lastActivityAction = new customJLabel("", 16);
    private int openingCardPoints = 0;

    private final customJLabel loyaltyOptions = new customJLabel("Loyalty Offers", 16);
    private JPanelTicket salesTicket;
    private static DataLogicSales dlSales = null;
    private List<ProductInfoExt> products;

    protected LoyaltyInfoPanel() {
        super(new JFrame(), "Member Loyalty Information");
        if (dlSales == null) {
            dlSales = new DataLogicSales();
            dlSales.init(SessionFactory.getSession());
        }
    }

    protected void showLoyaltyInformation(LoyaltyCard loyaltyCard) {
        openingCardPoints = loyaltyCard.getCardBalance();
        cardNumber.setText(loyaltyCard.getCardNumber());
        cardPoints.setText(Integer.toString(openingCardPoints));
        lastActivityDate.setText(loyaltyCard.getLastActivity("date"));
        lastActivityAction.setText(loyaltyCard.getLastActivity("activity"));
        buildLoyaltyInformation();

        pack();
        setLocationRelativeTo(null);
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));

        setVisible(true);
    }

    protected void showLoyaltyInformation(LoyaltyCard loyaltyCard, JPanelTicket ticket) {
        openingCardPoints = loyaltyCard.getCardBalance();
        cardNumber.setText(loyaltyCard.getCardNumber());
        cardPoints.setText(Integer.toString(openingCardPoints));
        lastActivityDate.setText(loyaltyCard.getLastActivity("date"));
        lastActivityAction.setText(loyaltyCard.getLastActivity("activity"));
        salesTicket = ticket;
        buildLoyaltyInformation();
        pack();
        setLocationRelativeTo(JRootFrame.PARENTFRAME);
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));

        setVisible(true);
    }

    private void buildLoyaltyInformation() {
        btnPanel = new JPanel();
        btn = new JButton(AppLocal.getIntString("button.ok"));
        btn.setFont(ChromisFonts.DEFAULTBUTTONFONT);
        btn.setPreferredSize(new Dimension(150, 40));
        btnPanel.add(btn);

        btn.addActionListener((ActionEvent e) -> {
            dispose();
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                dispose();
            }
        });

        panel = new JPanel(new MigLayout("insets 10 10 10 10", "[:150:][:5:][:400:]", "[]4[]4[]4[]15[]5[]"));

        panel.add(new customJLabel(AppLocal.getIntString("label.cardnumber"), 16), "left");
        panel.add(new customJLabel(":", 16), "left");
        panel.add(cardNumber, "left, width 200:200:, wrap");
        panel.add(new customJLabel(AppLocal.getIntString("label.currentpoints"), 16));
        panel.add(new customJLabel(":", 16), "left");
        panel.add(cardPoints, "left, width 200:200:, wrap");

        panel.add(new customJLabel(AppLocal.getIntString("label.lastactivitydate"), 16));
        panel.add(new customJLabel(":", 16), "left");
        panel.add(lastActivityDate, "left, width 200:200:, wrap");
        panel.add(new customJLabel(AppLocal.getIntString("label.lastactivity"), 16));
        panel.add(new customJLabel(":", 16), "left");
        panel.add(lastActivityAction, "left, pushx, wrap");

        if (SystemProperty.LOYALTYTYPE.equals("earnx")) {
            addTransactionalRedeemOptions();
        } else {
            addEarnAndBurn();
        }

        setAlwaysOnTop(true);
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(JRootFrame.PARENTFRAME);
        setUndecorated(true);
        getContentPane().add(panel);
    }

    private void addTransactionalRedeemOptions() {
        btnPanel.add(btn);
        panel.add(btnPanel, "span, gapy 35, height 50:50:50, center");
    }

    private void addEarnAndBurn() {
        try {
            products = new ArrayList(dlSales.getBurnItemsEnoughPoints(openingCardPoints));
        } catch (BasicException ex) {
            Logger.getLogger(LoyaltyInfoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (SystemProperty.SHOWALLOFFERS) {
            try {
                products.addAll(new ArrayList(dlSales.getBurnItemsInsufficientPoints(openingCardPoints)));
            } catch (BasicException ex) {
                Logger.getLogger(LoyaltyInfoPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        loyaltyOptions.setOpaque(true);
        Color backGround = loyaltyOptions.getBackground();
        loyaltyOptions.setBackground(loyaltyOptions.getForeground());
        loyaltyOptions.setForeground(backGround);
        panel.add(loyaltyOptions, "span, left, growx, pushx, wrap");

        JPanel sPane = new JPanel(new MigLayout("insets 11 10 1 1", "[:360:][:70:]"));
        if (products.size() > 0) {
            for (ProductInfoExt p : products) {
                LoyaltyExtendedButton actionButton = new LoyaltyExtendedButton("Buy", openingCardPoints, p);
                actionButton.setFont(ChromisFonts.DEFAULTBUTTONFONT);
                actionButton.setPreferredSize(new Dimension(100, 25));
                sPane.add(new customJLabel(p.getName() + " : " + p.getBurnValue() + " points", 16), "aligny center, span 2");
                if (p.getBurnValue() > openingCardPoints) {
                    actionButton.setEnabled(false);
                }
                actionButton.addActionListener((ActionEvent e) -> {
                    LoyaltyExtendedButton extBtn = (LoyaltyExtendedButton) e.getSource();
                    if (extBtn.getCardBalance() < p.getBurnValue()) {
                        insufficientPoints();
                    } else {
                        salesTicket.addLoyaltyLine(p, false);
                        extBtn.setCardBalance(extBtn.getCardBalance() - p.getBurnValue());
                    }
                });
                sPane.add(actionButton, "right, wrap");
                sPane.add(new JSeparator(JSeparator.HORIZONTAL), "span, center, gapy 1, growx, wrap");
            }
        }

        JScrollPane scroll = new JScrollPane(sPane);
        panel.add(scroll, "span, height :300:, width :550:, wrap");
        btnPanel.add(btn);
        panel.add(btnPanel, "span, gapy 15, height 50:50:50, center");
    }

    private void insufficientPoints() {
        JAlertPane.messageBoxFront(JAlertPane.INFORMATION, AppLocal.getIntString("message.loyaltynotenoughpoints"), 16,
                new Dimension(125, 50), JAlertPane.OK_OPTION);
    }

    private class customJLabel extends JLabel {

        public customJLabel(String text, int fontSize) {
            super();
            this.setText(text);
            this.setFont(ChromisFonts.DEFAULTFONT.deriveFont(fontSize));
        }
    }

    private class customJTextField extends JTextField {

        public customJTextField(Dimension dimension) {
            super();
            this.setText("");
            this.setFont(ChromisFonts.DEFAULTFONT);
            this.setPreferredSize(dimension);
        }
    }

}
