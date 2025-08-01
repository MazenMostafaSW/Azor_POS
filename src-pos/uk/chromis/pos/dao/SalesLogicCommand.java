/*
**    Chromis Administration  - Open Source Point of Sale
**
**    This file is part of Chromis Administration Version Chromis V1.5.0
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
package uk.chromis.pos.dao;

import uk.chromis.basic.BasicException;
import uk.chromis.data.loader.Datas;
import uk.chromis.data.loader.SentenceExec;
import uk.chromis.data.loader.SerializerWriteBasic;
import uk.chromis.data.loader.Session;
import uk.chromis.data.loader.SessionFactory;
import uk.chromis.data.loader.StaticSentence;

/**
 *
 * @author John
 */
public class SalesLogicCommand {

    protected static Session session  = SessionFactory.getSession();
    protected SentenceExec lineRemoved;

    public void init() {
        
        lineRemoved = new StaticSentence(session,
                "insert into lineremoved (id, name, terminalid, ticketid, description, productid, productname, units, value) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                new SerializerWriteBasic(new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE}));

    }

    
    public final void execLineRemoved(Object[] line) {
        try {
            lineRemoved.exec(line);
        } catch (BasicException e) {
        }
    }
}
