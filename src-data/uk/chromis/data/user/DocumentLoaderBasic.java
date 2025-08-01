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


package uk.chromis.data.user;

public class DocumentLoaderBasic implements DocumentLoader {
    
    /**
     *
     */
    public static final DocumentLoader INSTANCE = new DocumentLoaderBasic();
    
    /** Creates a new instance of DocumentLoaderBasic */
    private DocumentLoaderBasic() {
    }
    
    /**
     *
     * @param key
     * @return
     */
    public Object getValue(Object key) {
        return key;
    }

    /**
     *
     * @param value
     * @return
     */
    public Object getKey(Object value) {
        return value;
    }
}
