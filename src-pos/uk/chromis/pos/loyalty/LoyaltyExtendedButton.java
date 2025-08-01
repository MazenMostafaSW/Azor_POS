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

import javax.swing.JButton;
import uk.chromis.pos.ticket.ProductInfoExt;

/**
 *
 * @author john.lewis
 */
public class LoyaltyExtendedButton extends JButton {

        private int cardBalance;
        private ProductInfoExt product;

        public LoyaltyExtendedButton(String text) {
            super(text);
        }

         public LoyaltyExtendedButton(String text, int cardBalance) {
            super(text);
            this.cardBalance = cardBalance;
        }
        
        public LoyaltyExtendedButton(String text, int cardBalance, ProductInfoExt product) {
            super(text);
            this.cardBalance = cardBalance;
            this.product = product;
        }

        public int getCardBalance() {
            return cardBalance;
        }

        public void setCardBalance(int cardBalance) {
            this.cardBalance = cardBalance;
        }
    }
