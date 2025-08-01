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

import uk.chromis.data.gui.MessageInf;
import uk.chromis.pos.forms.AppLocal;
import uk.chromis.pos.datalogic.DataLogicSystem;
import uk.chromis.pos.printer.TicketParser;
import uk.chromis.pos.printer.TicketPrinterException;
import uk.chromis.pos.scripting.ScriptEngine;
import uk.chromis.pos.scripting.ScriptException;
import uk.chromis.pos.scripting.ScriptFactory;
import uk.chromis.pos.ticket.TicketInfo;
import uk.chromis.data.loader.SessionFactory;
import uk.chromis.pos.printer.IncludeFile;

/**
 *
 * @author John
 */
public class CollectLoyaltyPoints extends LoyaltyCard {

    protected static DataLogicSystem dlSystem = null;

    public CollectLoyaltyPoints(String cardNumber) {
        super(cardNumber);
        dlSystem = new DataLogicSystem();
        dlSystem.init(SessionFactory.getSession());

    }

    private void printVoucher(String sresourcename) {
        String source = dlSystem.getResourceAsXML(sresourcename);

        String sresource;
        IncludeFile incFile = new IncludeFile(source, dlSystem);

        if (source == null) {
            MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"));
        } else {
            sresource = incFile.processInclude();
            try {
                ScriptEngine script = ScriptFactory.getScriptEngine(ScriptFactory.VELOCITY);
                m_TTP.printTicket(script.eval(sresource).toString());
            } catch (ScriptException | TicketPrinterException e) {
                MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotprintticket"), e);
            }
        }
    }

    @Override
    public void processVoucherCheck(TicketInfo ticket, TicketParser m_TTP) {
    }

    @Override
    public void processTicketPoints(String cardNumber, TicketInfo ticket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getRedeemedPoints(String cardNumber, TicketInfo ticket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getEarnedPoints(String cardNumber, TicketInfo ticket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
