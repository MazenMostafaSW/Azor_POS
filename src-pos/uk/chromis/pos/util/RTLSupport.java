/*
**    Chromis POS  - Open Source Point of Sale
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

package uk.chromis.pos.util;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import uk.chromis.globals.SystemProperty;

/**
 * Utility class for RTL (Right-to-Left) language support
 * Provides methods to apply RTL orientation to Swing components
 * based on the user's language settings
 * 
 * @author Chromis POS
 */
public class RTLSupport {
    
    private static ComponentOrientation currentOrientation = null;
    private static boolean isRTL = false;
    
    /**
     * Initialize RTL support based on user language
     * Should be called during application startup
     */
    public static void initialize() {
        String userLanguage = SystemProperty.USERLANGUAGE;
        
        // Check if the language is RTL (Arabic, Hebrew, Persian, Urdu)
        isRTL = isRTLLanguage(userLanguage);
        
        if (isRTL) {
            currentOrientation = ComponentOrientation.RIGHT_TO_LEFT;
        } else {
            currentOrientation = ComponentOrientation.LEFT_TO_RIGHT;
        }
    }
    
    /**
     * Check if a language code represents an RTL language
     * @param languageCode ISO 639-1 language code
     * @return true if the language is RTL
     */
    public static boolean isRTLLanguage(String languageCode) {
        if (languageCode == null || languageCode.isEmpty()) {
            return false;
        }
        
        // RTL languages: Arabic (ar), Hebrew (he/iw), Persian (fa), Urdu (ur)
        return languageCode.equals("ar") || 
               languageCode.equals("he") || 
               languageCode.equals("iw") ||  // Old Hebrew code
               languageCode.equals("fa") || 
               languageCode.equals("ur");
    }
    
    /**
     * Get the current component orientation
     * @return ComponentOrientation for the current language
     */
    public static ComponentOrientation getOrientation() {
        if (currentOrientation == null) {
            initialize();
        }
        return currentOrientation;
    }
    
    /**
     * Check if current language is RTL
     * @return true if RTL mode is active
     */
    public static boolean isRTL() {
        if (currentOrientation == null) {
            initialize();
        }
        return isRTL;
    }
    
    /**
     * Apply RTL orientation to a component and all its children
     * @param component The component to apply orientation to
     */
    public static void applyOrientation(Component component) {
        if (component == null) {
            return;
        }
        
        ComponentOrientation orientation = getOrientation();
        component.applyComponentOrientation(orientation);
        
        // Special handling for specific component types
        if (component instanceof JTable) {
            applyToTable((JTable) component);
        } else if (component instanceof JList) {
            applyToList((JList) component);
        } else if (component instanceof JComboBox) {
            applyToComboBox((JComboBox) component);
        } else if (component instanceof JTextField) {
            applyToTextField((JTextField) component);
        } else if (component instanceof JTextArea) {
            applyToTextArea((JTextArea) component);
        }
    }
    
    /**
     * Apply RTL orientation to a JTable
     * @param table The table to configure
     */
    public static void applyToTable(JTable table) {
        if (table == null) {
            return;
        }
        
        table.applyComponentOrientation(getOrientation());
        
        // Apply to table header
        JTableHeader header = table.getTableHeader();
        if (header != null) {
            header.applyComponentOrientation(getOrientation());
        }
        
        // Set auto-resize mode to accommodate RTL
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
    }
    
    /**
     * Apply RTL orientation to a JList
     * @param list The list to configure
     */
    public static void applyToList(JList list) {
        if (list == null) {
            return;
        }
        
        list.applyComponentOrientation(getOrientation());
    }
    
    /**
     * Apply RTL orientation to a JComboBox
     * @param comboBox The combo box to configure
     */
    public static void applyToComboBox(JComboBox comboBox) {
        if (comboBox == null) {
            return;
        }
        
        comboBox.applyComponentOrientation(getOrientation());
    }
    
    /**
     * Apply RTL orientation to a JTextField
     * @param textField The text field to configure
     */
    public static void applyToTextField(JTextField textField) {
        if (textField == null) {
            return;
        }
        
        textField.applyComponentOrientation(getOrientation());
        
        // Set horizontal alignment based on orientation
        if (isRTL()) {
            textField.setHorizontalAlignment(JTextField.RIGHT);
        }
    }
    
    /**
     * Apply RTL orientation to a JTextArea
     * @param textArea The text area to configure
     */
    public static void applyToTextArea(JTextArea textArea) {
        if (textArea == null) {
            return;
        }
        
        textArea.applyComponentOrientation(getOrientation());
    }
}

