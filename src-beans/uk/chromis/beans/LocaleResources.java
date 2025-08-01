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


package uk.chromis.beans;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LocaleResources {

    private List<ResourceBundle> m_resources;

    /**
     * Creates a new instance of LocaleResources
     */
    public LocaleResources() {
        m_resources = new LinkedList<>();

    }

    /**
     *
     * @param bundlename
     */

    public void addResource(ResourceBundle resource) {
        m_resources.add(resource);
    }

    public void addBundleName(String bundlename) {
        m_resources.add(ResourceBundle.getBundle(bundlename));
    }

    public void addBundleName(String bundlename, Locale locale) {
        m_resources.add(ResourceBundle.getBundle(bundlename, locale));
    }

    /**
     *
     * @param sKey
     * @return
     */
    public String getString(String sKey) {

        if (sKey == null) {
            return null;
        } else {
            for (ResourceBundle r : m_resources) {
                try {
                    return r.getString(sKey);
                } catch (MissingResourceException e) {
                    // Next
                }
            }
            return "** " + sKey + " **";
        }
    }

    /**
     *
     * @param sKey
     * @param sValues
     * @return
     */
    public String getString(String sKey, Object... sValues) {

        if (sKey == null) {
            return null;
        } else {
            for (ResourceBundle r : m_resources) {
                try {
                    return MessageFormat.format(r.getString(sKey), sValues);
                } catch (MissingResourceException e) {
                    // Next
                }
            }

            // MissingResourceException in all ResourceBundle
            StringBuilder sreturn = new StringBuilder();
            sreturn.append("** ");
            sreturn.append(sKey);
            for (Object value : sValues) {
                sreturn.append(" < ");
                sreturn.append(value.toString());
            }
            sreturn.append("** ");

            return sreturn.toString();
        }
    }
}
