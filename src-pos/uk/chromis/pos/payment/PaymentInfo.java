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
package uk.chromis.pos.payment;

import uk.chromis.format.Formats;

public abstract class PaymentInfo {

    public abstract String getName();

    public abstract String getDescription();

    public abstract double getTotal();

    public abstract PaymentInfo copyPayment();

    public abstract String getTransactionID();

    public abstract double getPaid();

    public abstract double getChange();

    public abstract double getTendered();

    public abstract String getCardName();
    
    public abstract Boolean isCardPayment();

    public void addToPaid(Double balance) {       
    }
     
    public void addToTotal(Double balance) {       
    }

    public int getBurnPoints() {
        return 0;
    }

    public Double getECardBalance() {
        return 0.00;
    }

    public String getECardNumber() {
        return null;
    }

    public String printTotal() {
        return Formats.CURRENCY.formatValue(getTotal());
    }
}
