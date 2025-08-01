/*
**    Chromis Administration  - Open Source Point of Sale
**
**    This file is part of Chromis Administration Version Chromis V1.5.3
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



package uk.chromis.pos.auditing;

import java.util.UUID;
import uk.chromis.commons.dbmanager.DbUtils;
import uk.chromis.commons.utils.TerminalDataLogic;
import uk.chromis.pos.dao.SalesLogicCommand;
import uk.chromis.pos.ticket.TicketInfo;
import uk.chromis.pos.ticket.TicketLineInfo;

/**
 *
 * @author John Lewis
 */
public class Audit {

    private static final SalesLogicCommand salesLogicCommand;
    private static final TerminalDataLogic terminal;

    static {
        salesLogicCommand = new SalesLogicCommand();
        salesLogicCommand.init();
        terminal = new TerminalDataLogic();
    }

    public static void itemRemoved(TicketInfo ticket, Integer i, String reason) {
        salesLogicCommand.execLineRemoved(
                new Object[]{
                    UUID.randomUUID().toString(),
                    terminal.getCurrentUser().getName(),
                    DbUtils.getTerminalName(),
                    ticket.getId(),
                    reason,
                    ticket.getLine(i).getProductID(),
                    ticket.getLine(i).getProductName(),
                    ticket.getLine(i).getMultiply(),
                    ticket.getLine(i).getPrice()
                });
    }

        public static void itemRemoved(TicketInfo ticket, Integer i, Double qty, String reason) {
        salesLogicCommand.execLineRemoved(
                new Object[]{
                    UUID.randomUUID().toString(),
                    terminal.getCurrentUser().getName(),
                    DbUtils.getTerminalName(),
                    ticket.getId(),
                    reason,
                    ticket.getLine(i).getProductID(),
                    ticket.getLine(i).getProductName(),
                    qty,
                    ticket.getLine(i).getPrice()
                });
    }
    
    
    
    public static void itemRemoved(TicketInfo ticket, TicketLineInfo line, String reason) {
        salesLogicCommand.execLineRemoved(
                new Object[]{
                    UUID.randomUUID().toString(),
                    terminal.getCurrentUser().getName(),
                    DbUtils.getTerminalName(),
                    ticket.getId(),
                    reason,
                    line.getProductID(),
                    line.getProductName(),
                    line.getMultiply(),
                    line.getPrice()
                });
    }
}
