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


package uk.chromis.globals;

import java.awt.Image;
import uk.chromis.basic.BasicException;
import uk.chromis.data.loader.PreparedSentence;
import uk.chromis.data.loader.SerializerReadString;
import uk.chromis.data.loader.SerializerWriteString;
import uk.chromis.data.loader.Session;
import uk.chromis.data.loader.SessionFactory;

/**
 *
 * @author John
 */
public class Company {

    private static Object m_result;
    private static Image m_image;
    private static Session session;


    public static final String NAME;
    public static final String ADDRESSLINE1;
    public static final String ADDRESSLINE2;
    public static final String ADDRESSLINE3;
    public static final String POSTCODE;
    public static final String PHONENUMBER;
    public static final String TAXNUMBER;
    public static final String EMAILADDR;
    public static final String WEBSITE;
    public static final String REGISTRATIONNUMBER;


    static {

        session = SessionFactory.getSession();
       
        NAME = getString("NAME");
        ADDRESSLINE1 = getString("ADDRESSLINE1");
        ADDRESSLINE2 = getString("ADDRESSLINE2");
        ADDRESSLINE3 = getString("ADDRESSLINE3");
        POSTCODE = getString("POSTCODE");
        PHONENUMBER = getString("PHONENUMBER");
        TAXNUMBER = getString("TAXNUMBER");
        EMAILADDR = getString("EMAILADDR");
        WEBSITE = getString("WEBSITE");
        REGISTRATIONNUMBER = getString("REGISTRATIONNUMBER");

    }

    private static String getString(String constant) {
        try {
            m_result = new PreparedSentence(session,
                    "select uservalue from companydetails where constant = ? ",
                    SerializerWriteString.INSTANCE,
                    SerializerReadString.INSTANCE).find(constant);
        } catch (BasicException e) {
                System.out.println("Error in Constant read string!! ");
        }
        return (m_result == null ? "" : (String) m_result);
    }

}
