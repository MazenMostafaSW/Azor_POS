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
package uk.chromis.pos.ticket;

import java.awt.Component;
import java.awt.Image;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import uk.chromis.format.Formats;
import uk.chromis.globals.IconFactory;
import uk.chromis.pos.forms.AppLocal;
import uk.chromis.pos.util.ThumbNailBuilder;

public class ProductRenderer extends DefaultListCellRenderer {

    ThumbNailBuilder tnbprod;

    /**
     * Creates a new instance of ProductRenderer
     */
    public ProductRenderer() {
        tnbprod = new ThumbNailBuilder(48, 48, IconFactory.getIcon("package.png"));
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, null, index, isSelected, cellHasFocus);

        ProductInfoExt prod = (ProductInfoExt) value;
        if (prod != null) {
            
//            setText("<html>" + prod.getReference() + " - " + prod.getName() + "<br>&nbsp;&nbsp;&nbsp;&nbsp;" + Formats.CURRENCY.formatValue(prod.getPriceSell()));
            setText("<html>" + prod.getReference() + " - " + prod.getName() + "<br> "
                    + "<b>" + AppLocal.getIntString("label.stockunits") + ":</b> " + Formats.DOUBLE.formatValue(prod.getStockLevel()) + "<b>     "
                    + AppLocal.getIntString("label.prodpricesell") + ":</b> "
                    + Formats.CURRENCY.formatValue(prod.getPriceSell()));
            Image img = tnbprod.getThumbNail(prod.getImage());
            setIcon(img == null ? null : new ImageIcon(img));
        }
        return this;
    }
}
