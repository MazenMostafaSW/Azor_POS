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


package uk.chromis.custom;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPasswordField;
import uk.chromis.pos.util.RTLSupport;

/**
 *
 * @author John
 */
    public class CustomJPasswordField extends JPasswordField {

        public CustomJPasswordField(Dimension dimension, Font font) {
            super();
            this.setText("");
            this.setFont(font);
            this.setPreferredSize(dimension);

            // Apply RTL support
            ComponentOrientation orientation = RTLSupport.getOrientation();
            this.applyComponentOrientation(orientation);
            if (RTLSupport.isRTL()) {
                this.setHorizontalAlignment(JPasswordField.RIGHT);
            }
        }
    }
