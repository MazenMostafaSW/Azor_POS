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
package uk.chromis.pos.sales.restaurant;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import uk.chromis.basic.BasicException;
import uk.chromis.commons.dialogs.JAlertPane;
import uk.chromis.custom.CustomColour;
import uk.chromis.custom.ExtendedJButton;
import uk.chromis.data.user.ListProvider;
import uk.chromis.pos.customers.CustomerRenderer;
import uk.chromis.pos.datalogic.DataLogicSystem;
import uk.chromis.pos.forms.AppLocal;
import uk.chromis.pos.forms.ChromisFonts;
import uk.chromis.pos.forms.JRootFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.chromis.data.user.ListProviderCreator;
import uk.chromis.pos.ticket.UserInfo;

/**
 *
 * @author John
 */
public final class JWaiterSelector extends JDialog {

    private final Font btnFont = ChromisFonts.DEFAULTFONTBOLD;
    private final Font listFont = ChromisFonts.DEFAULTFONTBOLD;

    //Main panels to be used by miglayout
    private final JPanel mainPanel = new JPanel(new MigLayout("insets 10 10 10 10 ", "", ""));
    private final JPanel waiterFinder = new JPanel(new MigLayout("insets 0 0 0 0 ", "15[420]", "[][][]"));

    private final JPanel listPane = new JPanel();
    private final JScrollPane jScrollPane1 = new JScrollPane();
    private final JList jListItems = new JList();

    private JPanel btnMainPanel;
    private JPanel btnPanel;

    private ExtendedJButton btn;
    private JButton btnOK;

    private UserInfo selectedWaiter = null;
    private ListProvider lpr;

    public JWaiterSelector(DataLogicSystem dlSystem) {
        super(new JFrame());
        waiterSelectorPane(dlSystem);
        pack();
        int x = JRootFrame.PARENTFRAME.getX() + ((JRootFrame.PARENTFRAME.getWidth() - this.getWidth()) / 2);
        int y = JRootFrame.PARENTFRAME.getY() + this.getHeight()/2 ;
        setLocation(x, y);

    }

    protected void waiterSelectorPane(DataLogicSystem dlSystem) {
        lpr = new ListProviderCreator(dlSystem.getActiveWaiters());
        try {
            jListItems.setModel(new MyListData(lpr.loadData()));
        } catch (BasicException ex) {
            Logger.getLogger(JWaiterSelector.class.getName()).log(Level.SEVERE, null, ex);
        }

        createListPanel(400, 400);
        setButtonPanel(new Dimension(105, 35));
        btnOK.setEnabled(false);

        waiterFinder.add(listPane, " wrap, growy, wrap");
        waiterFinder.add(btnPanel, "  align right, wrap");
        waiterFinder.add(btnMainPanel, "span ,  align right");
        mainPanel.add(waiterFinder, "align center, wrap");

        setResizable(false);
        setModal(true);

        mainPanel.setBorder(BorderFactory.createLineBorder(CustomColour.getBorderColour(), 2));
        setTitle("Waiter Selector");
        getContentPane().add(mainPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                selectedWaiter = null;
            }
        });
        setAlwaysOnTop(true);
    }

    private static class MyListData extends javax.swing.AbstractListModel {

        private final java.util.List m_data;

        public MyListData(java.util.List data) {
            m_data = data;
        }

        @Override
        public Object getElementAt(int index) {
            return m_data.get(index);
        }

        @Override
        public int getSize() {
            return m_data.size();
        }
    }

    private void createListPanel(Integer width, Integer height) {

        listPane.setPreferredSize(new Dimension(width, height));
        listPane.setLayout(new java.awt.BorderLayout());
        listPane.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jListItems.setCellRenderer(new CustomerRenderer());
        jListItems.setFont(listFont);
        jListItems.setFixedCellHeight(40);
        jListItems.setFocusable(false);
        jListItems.setRequestFocusEnabled(false);
        jListItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    selectedWaiter = (UserInfo) jListItems.getSelectedValue();
                    dispose();
                }
            }
        });

        jListItems.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                btnOK.setEnabled(jListItems.getSelectedValue() != null);
            }
        });

        jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(35, 35));
        jScrollPane1.setViewportView(jListItems);

    }

    private void setButtonPanel(Dimension dimension) {
        btnPanel = new JPanel();

        btnMainPanel = new JPanel();

        btnOK = new ExtendedJButton(AppLocal.getIntString("button.ok"), JAlertPane.OK);
        btnOK.setPreferredSize(dimension);
        btnOK.setFont(btnFont);
        btnOK.setFocusable(false);
        btnOK.addActionListener((ActionEvent e) -> {
            selectedWaiter = (UserInfo) jListItems.getSelectedValue();
            dispose();
        });
        btnMainPanel.add(btnOK);

        btn = new ExtendedJButton(AppLocal.getIntString("button.cancel"), JAlertPane.CANCEL);
        btn.setPreferredSize(dimension);
        btn.setFont(btnFont);
        btn.setFocusable(false);
        btn.addActionListener((ActionEvent e) -> {
            selectedWaiter = null;
            dispose();
        });
        btnMainPanel.add(btn);
    }

    public UserInfo getSelectedWaiter() {
        return selectedWaiter;
    }
}
