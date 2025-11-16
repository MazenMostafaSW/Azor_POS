/*
**    AZOR POS  - Point of Sale System
**
**    This file is part of AZOR POS Version V1.5.5
**
**    Developed by Mazen Mostafa
**    Based on Chromis POS - Copyright (c) 2015-2023 Chromis & previous Openbravo POS related works
**
**    https://www.chromis.co.uk
**
**    AZOR POS is free software: you can redistribute it and/or modify
**    it under the terms of the GNU General Public License as published by
**    the Free Software Foundation, either version 3 of the License, or
**    (at your option) any later version.
**
**    AZOR POS is distributed in the hope that it will be useful,
**    but WITHOUT ANY WARRANTY; without even the implied warranty of
**    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
**    GNU General Public License for more details.
**
**    You should have received a copy of the GNU General Public License
**    along with AZOR POS.  If not, see <http://www.gnu.org/licenses/>
**
*/

package uk.chromis.pos.forms;

public class AppLocal implements Versions {

    public static final String APP_NAME = "AZOR POS";
    public static final String APP_ID = "azorpos";
    public static final String APP_DEMO = "";

    public static String LIST_BY_RIGHTS = "";

    /**
     *
     * @param sKey
     * @return
     */
    public static String getIntString(String sKey) {
        return LocalResource.getString(sKey);
    }

    /**
     *
     * @param sKey
     * @param sValues
     * @return
     */
    public static String getIntString(String sKey, Object... sValues) {
        return  LocalResource.getString(sKey, sValues);
    }
}
