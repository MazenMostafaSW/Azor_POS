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


package uk.chromis.pos.datalogic;

import java.util.List;
import uk.chromis.globals.SystemProperty;
import uk.chromis.basic.BasicException;
import uk.chromis.data.loader.Datas;
import uk.chromis.data.loader.PreparedSentence;
import uk.chromis.data.loader.SerializerReadBasic;
import uk.chromis.data.loader.SerializerReadClass;
import uk.chromis.data.loader.SerializerWriteBasicExt;
import uk.chromis.data.loader.SerializerWriteString;
import uk.chromis.data.loader.Session;
import uk.chromis.data.loader.StaticSentence;
import uk.chromis.pos.forms.BeanFactoryDataSingle;
import uk.chromis.pos.sales.CustomerDeliveryInfo;
import uk.chromis.pos.sales.SharedTicketInfo;
import uk.chromis.pos.ticket.ProductInfoExt;
import uk.chromis.pos.ticket.TicketInfo;

public class DataLogicReceipts extends BeanFactoryDataSingle {

    private Session s;

    /**
     * Creates a new instance of DataLogicReceipts
     */
    public DataLogicReceipts() {
    }

    /**
     *
     * @param s
     */
    @Override
    public void init(Session s) {
        this.s = s;
    }

    /**
     *
     * @param Id
     * @return
     * @throws BasicException
     */
    public final TicketInfo getSharedTicket(String Id) throws BasicException {

        if (Id == null) {
            return null;
        } else {
            Object[] record = (Object[]) new StaticSentence(s, "select content from sharedtickets where id = ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.SERIALIZABLE})).find(Id);
            return record == null ? null : (TicketInfo) record[0];
        }
    }

    /**
     *
     * @return @throws BasicException
     */
    public final List<SharedTicketInfo> getSharedTicketList() throws BasicException {

        return (List<SharedTicketInfo>) new StaticSentence(s,
                //   "select id, name, content, pickupid, loyaltycard, pickupbarcode from sharedtickets order by id", null, new SerializerReadClass(SharedTicketInfo.class)).list();
                "select id, name, content, pickupid, loyaltycard, pickupbarcode, deliveryinfo from sharedtickets order by id", null, new SerializerReadClass(SharedTicketInfo.class)).list();
        //     "select id, name, content, pickupid from sharedtickets order by id", null, new SerializerReadClass(SharedTicketInfo.class)).list();
    }

    /**
     * Get shared ticket list for current logged in user only.
     *
     * @return @throws BasicException
     */
    public final List<SharedTicketInfo> getSharedTicketListByUser(String User) throws BasicException {

        return (List<SharedTicketInfo>) new StaticSentence(s,
                "select id, name, content, pickupid, loyaltycard, pickupbarcode, deliveryinfo from sharedtickets where name like '%" + User + " -%' ", null, new SerializerReadClass(SharedTicketInfo.class)).list();
        //  "select id, name, content, pickupid from sharedtickets where name like '%" + User + " -%' ", null, new SerializerReadClass(SharedTicketInfo.class)).list();
    }

    /**
     *
     * @param id
     * @param ticket
     * @param pickupid
     * @throws BasicException
     */
    public final void updateSharedTicket(final String id, final TicketInfo ticket, int pickupid, String pickupbarcode, CustomerDeliveryInfo deliveryinfo) throws BasicException {
        String loyalty = (ticket.getLoyaltyCard() == null) ? null : ticket.getLoyaltyCard().getCardNumber();
        Object[] values = new Object[]{
            id,
            ticket.getSharedName(),
            ticket,
            pickupid,
            loyalty,
            pickupbarcode,
            deliveryinfo};
        Datas[] datas = new Datas[]{
            Datas.STRING,
            Datas.STRING,
            Datas.SERIALIZABLE,
            Datas.INT,
            Datas.STRING,
            Datas.STRING,
            Datas.OBJECT
        };
        new PreparedSentence(s, "update sharedtickets set "
                + "name = ?, "
                + "content = ?, "
                + "pickupid = ?, "
                + "loyaltycard = ?, "
                + "pickupbarcode = ?, "
                + "deliveryinfo = ? "
                + "where id = ?", new SerializerWriteBasicExt(datas, new int[]{1, 2, 3, 4, 5, 6, 0})).exec(values);
    }

    /**
     *
     * @param id
     * @param ticket
     * @param pickupid
     * @throws BasicException
     */
    public final void insertSharedTicket(final String id, final TicketInfo ticket, int pickupid, String pickupbarcode, CustomerDeliveryInfo deliveryinfo) throws BasicException {
        String loyalty = (ticket.getLoyaltyCard() == null) ? null : ticket.getLoyaltyCard().getCardNumber();

        ProductInfoExt prod = new ProductInfoExt();
        Object[] values = new Object[]{
            id,
            ticket.getSharedName(),
            ticket,
            pickupid,
            ticket.printUser(),
            loyalty,
            pickupbarcode,
            deliveryinfo
        };
        Datas[] datas;
        datas = new Datas[]{
            Datas.STRING,
            Datas.STRING,
            Datas.SERIALIZABLE,
            Datas.INT,
            Datas.STRING,
            Datas.STRING,
            Datas.STRING,
            Datas.OBJECT
        };

        new PreparedSentence(s, "insert into sharedtickets ("
                + "id, "
                + "name, "
                + "content, "
                + "pickupid, "
                + "appuser, "
                + "loyaltycard, "
                + "pickupbarcode,"
                + "deliveryinfo) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?)", new SerializerWriteBasicExt(datas, new int[]{0, 1, 2, 3, 4, 5, 6, 7})).exec(values);
    }

    public final void insertSharedTicketUsingPickUpID(final String id, final TicketInfo ticket, int pickupid, String pickupbarcode, CustomerDeliveryInfo deliveryinfo) throws BasicException {
        String loyalty = (ticket.getLoyaltyCard() == null) ? null : ticket.getLoyaltyCard().getCardNumber();
        Object[] values = new Object[]{
            id,
            "Pickup Id: " + getPickupString(pickupid),
            ticket,
            pickupid,
            ticket.getUser().getName(),
            loyalty,
            pickupbarcode,
            deliveryinfo
        };
        Datas[] datas;
        datas = new Datas[]{
            Datas.STRING,
            Datas.STRING,
            Datas.SERIALIZABLE,
            Datas.INT,
            Datas.STRING,
            Datas.STRING,
            Datas.STRING,
            Datas.STRING
        };

        new PreparedSentence(s, "insert into sharedtickets ("
                + "id, "
                + "name, "
                + "content, "
                + "pickupid, "
                + "appuser, "
                + "loyaltycard, "
                + "pickupbarcode, "
                + "deliveryinfo) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?)", new SerializerWriteBasicExt(datas, new int[]{0, 1, 2, 3, 4, 5, 6, 7})).exec(values);
    }

    /**
     *
     * @param id
     * @throws BasicException
     */
    public final void deleteSharedTicket(final String id) throws BasicException {
        new StaticSentence(s, "delete from sharedtickets where id = ?", SerializerWriteString.INSTANCE).exec(id);
    }

    public final void deleteAllSharedTickets() throws BasicException {
        new StaticSentence(s, "delete from sharedtickets", SerializerWriteString.INSTANCE).exec();
    }

    public final void deleteSharedTickets(String appUser) throws BasicException {
        new StaticSentence(s, "delete from sharedtickets where appuser = ? ",
                SerializerWriteString.INSTANCE).exec(appUser);
    }
    
    /**
     *
     * @param Id
     * @return
     * @throws BasicException
     */
    public final Integer getPickupId(String Id) throws BasicException {

        if (Id == null) {
            return null;
        } else {
            Object[] record = (Object[]) new StaticSentence(s, "select pickupid from sharedtickets where id = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.INT})).find(Id);
            return record == null ? 0 : (Integer) record[0];
        }
    }

    public final String getPickupBarcode(String Id) throws BasicException {

        if (Id == null) {
            return null;
        } else {
            if (Id == null) {
                return null;
            } else {
                Object[] record = (Object[]) new StaticSentence(s, "select pickupbarcode from sharedtickets where id = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(Id);
                return record == null ? "" : (String) record[0];
            }
        }
    }

    public final String getAppUser(String Id) throws BasicException {
        if (Id == null) {
            return null;
        } else {
            Object[] record = (Object[]) new StaticSentence(s, "select appuser from sharedtickets where id = ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(Id);
            return record == null ? "" : (String) record[0];
        }
    }

    public final String getTicketName(String Id) throws BasicException {
        if (Id == null) {
            return null;
        } else {
            Object[] record = (Object[]) new StaticSentence(s, "select name from sharedtickets where id = ?", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(Id);
            return record == null ? "" : (String) record[0];
        }
    }

    private String getPickupString(int pickup) {
        String tmpPickupId = Integer.toString(pickup);
        if (SystemProperty.PICKUPSIZE >= tmpPickupId.length()) {
            while (tmpPickupId.length() < SystemProperty.PICKUPSIZE) {
                tmpPickupId = "0" + tmpPickupId;
            }
        }
        return (tmpPickupId);
    }

    public final String getLoyaltyCard(String Id) throws BasicException {
        if (Id == null) {
            return null;
        } else {
            Object[] record = (Object[]) new StaticSentence(s, "select loyaltycard from sharedtickets where id = ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.STRING})).find(Id);
            return record == null ? "" : (String) record[0];
        }
    }

    public final CustomerDeliveryInfo fetchDeliveryInfo(String Id) throws BasicException {

        if (Id == null) {
            return null;
        } else {
            Object[] record = (Object[]) new StaticSentence(s, "select deliveryinfo from sharedtickets where id = ? ", SerializerWriteString.INSTANCE, new SerializerReadBasic(new Datas[]{Datas.SERIALIZABLE})).find(Id);
            return record == null ? null : (CustomerDeliveryInfo) record[0];
        }
    }

}
