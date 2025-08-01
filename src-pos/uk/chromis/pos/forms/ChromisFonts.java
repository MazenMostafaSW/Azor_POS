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
package uk.chromis.pos.forms;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;
import uk.chromis.globals.SystemProperty;

public class ChromisFonts {

    public static Font DEFAULTFONT;
    public static Font DEFAULTFONTBOLD;
    public static Font DEFAULTBUTTONFONT;
    public static Font CHROMISFONT;
    public static Font CHROMISFONTBOLD;

    public static void setFont(String fontName) {

        CHROMISFONT = new Font("Courgette", Font.PLAIN, 14);
        CHROMISFONTBOLD = new Font("Courgette", Font.BOLD, 14);

        String font = SystemProperty.SWINGFONT;
        if (!SystemProperty.SWINGFONT.isBlank()) {
            if (Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()).contains(font)) {
                DEFAULTFONT = new Font(font, Font.PLAIN, SystemProperty.SWINGFONTSIZE);
                DEFAULTFONTBOLD = new Font(font, Font.BOLD, SystemProperty.SWINGFONTSIZE);
                DEFAULTBUTTONFONT = new Font(font, Font.BOLD, SystemProperty.SWINGFONTSIZE + 2);
                return;
            }
        }

        DEFAULTFONT = new Font(fontName, Font.PLAIN, 14);
        DEFAULTFONTBOLD = new Font(fontName, Font.BOLD, 14);
        DEFAULTBUTTONFONT = new Font(fontName, Font.BOLD, 16);

    }

}
