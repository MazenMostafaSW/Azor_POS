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


package uk.chromis.pos.inventory;

import uk.chromis.pos.panels.ComboItemLocal;

public class MovementReason extends ComboItemLocal {

    // The numeric key indicates whether this is an increase or decrease in stock level
    // It is important to get the sign right.
    public static final MovementReason IN_REFUND = new MovementReason(+2, "stock.in.refund");  
  
    public static final MovementReason OUT_SALE = new MovementReason(-1, "stock.out.sale");
    /**
     *
     */
   
    private MovementReason(Integer iKey, String sKeyValue) {
        super(iKey, sKeyValue);
    }

    /**
     *
     * @return
     */
    public boolean isInput() {
        return m_iKey > 0;
    }

    /**
     *
     * @param d
     * @return
     */
    public Double samesignum(Double d) {

        if (d == null || m_iKey == null) {
            return d;
        } else if ((m_iKey > 0 && d < 0.0)
                || (m_iKey < 0 && d > 0.0)) {
            return -d;
        } else {
            return d;
        }
    }

    /**
     *
     * @param dBuyPrice
     * @param dSellPrice
     * @return
     */
    public Double getPrice(Double dBuyPrice, Double dSellPrice) {

        if (this == OUT_SALE || this == IN_REFUND) {
            return dSellPrice;
        } else {
            return null;
        }
    }
}
