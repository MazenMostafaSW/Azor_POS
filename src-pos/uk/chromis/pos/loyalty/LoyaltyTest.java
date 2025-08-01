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

import uk.chromis.pos.datalogic.DataLogicLoyalty;

/**
 *
 * @author John
 */
public class LoyaltyTest {

    public static void main(String args[]) {

        System.out.println("1535432354  is present : " + DataLogicLoyalty.isCardPresent("1535432354"));
        System.out.println("Card id : " + DataLogicLoyalty.getCardID("1535432354"));
        System.out.println("Card active : " + DataLogicLoyalty.isCardActive("1535432354"));
        System.out.println("Card locked : " + DataLogicLoyalty.isCardLocked("1535432354"));
        System.out.println("Card points : " + DataLogicLoyalty.getCardPoints("1535432354"));
        System.out.println("Card replaced : " + DataLogicLoyalty.cardReplaced("1535432354"));
        System.out.println("card count  : " + DataLogicLoyalty.getLoyaltyCount());
        System.out.println("card enabled : " + DataLogicLoyalty.isCardEnabled("a682e77a-906f-4d85-98b1-494259105c99"));
        System.out.println("cardno by id  : " + DataLogicLoyalty.getCardByCustomerId("a682e77a-906f-4d85-98b1-494259105c99"));
        System.out.println("card by id  : " + DataLogicLoyalty.getCardIdByCustomerId("a682e77a-906f-4d85-98b1-494259105c99"));
        System.out.println("Card has cust : " + DataLogicLoyalty.cardHasCustomerAssigned("1535432354"));
        System.out.println("Card is cust : " + DataLogicLoyalty.isCardAssignedToAnotherCustomer("15354323543"));
        
        
        
        

//        LoyaltyCard loyaltyCard = new LoyaltyCard("199500000001");
//
//        System.out.println("Active    : " + loyaltyCard.isCardActive());
//        System.out.println("Locked    : " + loyaltyCard.isCardLocked());
//        System.out.println("ID        : " + loyaltyCard.getCardId());
//        System.out.println("Opening   : " + loyaltyCard.getOpeningPoints());
//        System.out.println("Balance   : " + loyaltyCard.getCardBalance());
//        System.out.println("Balance T : " + loyaltyCard.getCardTransBalance());
//        System.out.println("Last date : " + loyaltyCard.getLastActivity("date"));
//        System.out.println("Last act  : " + loyaltyCard.getLastActivity("activity"));
//        System.out.println("Count     : " + loyaltyCard.getLoyaltyCount());
//        // System.out.println("Customer  : " + loyaltyCard.cardHasCustomerAssigned());
//
//        System.out.println("Customer  : " + loyaltyCard.cardHasCustomerAssigned("199500000002"));
//
//        System.out.println("An Cust   : " + loyaltyCard.isCardAssignedToAnotherCustomer());
        //  System.out.println("Last id  : " + loyaltyCard.getLastActivity("199500000001"));
        //     System.out.println("redeemed     : " + loyaltyCard.getRedeemedPoints());
        System.exit(0);
    }

}
