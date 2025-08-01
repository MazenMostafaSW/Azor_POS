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


package uk.chromis.pos.sales;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import uk.chromis.globals.SystemProperty;
import uk.chromis.basic.BasicException;
import uk.chromis.commons.utils.TerminalInfo;
import uk.chromis.pos.catalog.CatalogSelector;
import uk.chromis.pos.catalog.JCatalog;
import uk.chromis.pos.catalog.JCatalogFull;
import uk.chromis.pos.datalogic.DataLogicSystem;
import uk.chromis.pos.forms.AppView;
import uk.chromis.pos.ticket.ProductInfoExt;

/**
 *
 *
 */
public class JPanelTicketSales extends JPanelTicket {

    private CatalogSelector m_cat;
    private DataLogicSystem dlSystem;

    public JPanelTicketSales() {
    }

    @Override
    public void init(AppView app) {
        super.init(app);
        dlSystem = (DataLogicSystem) app.getBean("uk.chromis.pos.datalogic.DataLogicSystem");
        ticketLines.addListSelectionListener(new CatalogSelectionListener());
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    protected Component getSouthComponent() {

        if (SystemProperty.NEWSCREEN) {
            m_cat = new JCatalogFull(dlSales,
                    "true".equals(m_jbtnconfig.getProperty("pricevisible")),
                    "true".equals(m_jbtnconfig.getProperty("taxesincluded")),
                    siteGuid);
        } else {
            m_cat = new JCatalog(dlSales,
                    "true".equals(m_jbtnconfig.getProperty("pricevisible")),
                    "true".equals(m_jbtnconfig.getProperty("taxesincluded")),
                    siteGuid);
        }

        m_cat.addActionListener(new CatalogListener());
        m_cat.getComponent().setPreferredSize(new Dimension(
                0,
                Integer.parseInt(m_jbtnconfig.getProperty("cat-height", "245"))));
        return m_cat.getComponent();
    }

    @Override
    protected void resetSouthComponent() {
        m_cat.showCatalogPanel(null);
    }

    @Override
    protected JTicketsBag getJTicketsBag() {
        return JTicketsBag.createTicketsBag(TerminalInfo.getPosType(), m_App, this);
    }

    @Override
    public void activate() throws BasicException {
        super.activate();
        m_cat.loadCatalog(siteGuid);
    }

    @Override
    public void reLoadCatalog() {
        try {
            m_cat.loadCatalog(siteGuid);
        } catch (BasicException ex) {
        }

    }

    private class CatalogListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            buttonTransition((ProductInfoExt) e.getSource());
        }
    }

    private class CatalogSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {

            if (!e.getValueIsAdjusting()) {
                int i = ticketLines.getSelectedIndex();
                
                if (i >= 0) {
                    // Look for the first non auxiliar product.
                    while (i >= 0 && m_oTicket.getLine(i).isProductCom()) {
                        i--;
                    }

                    // Show the accurate catalog panel...
                    if (i >= 0) {
                        m_cat.showCatalogPanel(m_oTicket.getLine(i).getProductID());
                    } else {
                        m_cat.showCatalogPanel(null);
                    }
                }
            }
        }
    }    
}
