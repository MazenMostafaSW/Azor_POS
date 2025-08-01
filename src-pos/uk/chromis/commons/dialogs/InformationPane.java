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
package uk.chromis.commons.dialogs;

import javax.swing.*;

/**
 * @author John Lewis
 */
public class InformationPane extends JDialog {

    public static void showInformationDialog(Boolean showLogo, JPanel content, Boolean undecorated, JPanel frame) {//, Boolean border) {
        InformationDialog jInfo = new InformationDialog(showLogo,
                content,
                undecorated);
        jInfo.setLocationRelativeTo(frame);
        jInfo.setModal(true);
        jInfo.setVisible(true);

    }

    public static void showInformationDialog(Boolean showLogo, JPanel content, Boolean undecorated, Boolean border, JPanel frame) {
        InformationDialog jInfo = new InformationDialog(showLogo,
                content,
                undecorated,
                border);
        jInfo.setLocationRelativeTo(frame);
        jInfo.setModal(true);
        jInfo.setVisible(true);

    }

}
