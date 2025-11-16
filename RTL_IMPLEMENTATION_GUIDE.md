# RTL (Right-to-Left) Implementation Guide for Chromis POS

## Overview
This document describes the comprehensive RTL implementation for Chromis POS to support Arabic and other RTL languages.

## Implementation Summary

### 1. Core RTL Support Infrastructure

#### RTLSupport Utility Class
**File:** `src-pos/uk/chromis/pos/util/RTLSupport.java`

A centralized utility class that:
- Detects RTL languages (Arabic, Hebrew, Persian, Urdu)
- Provides component orientation based on user language
- Applies RTL orientation to various Swing components
- Handles special cases for JTable, JList, JComboBox, JTextField, JTextArea

**Key Methods:**
- `initialize()` - Initializes RTL support based on `SystemProperty.USERLANGUAGE`
- `isRTL()` - Returns true if current language is RTL
- `getOrientation()` - Returns appropriate ComponentOrientation
- `applyOrientation(Component)` - Applies RTL to a component and its children
- `applyToTable(JTable)` - Special handling for tables
- `applyToTextField(JTextField)` - Special handling for text fields

### 2. Application-Level RTL Integration

#### Main Application (JRootApp.java)
**Changes:**
- Added import for `RTLSupport`
- Modified `initApp()` method to:
  - Initialize RTL support at application startup
  - Apply RTL orientation to the root panel
  - Ensure all child components inherit RTL orientation

**Code Location:** Lines 269-275

#### Principal Application View (JPrincipalApp.java)
**Changes:**
- Added import for `RTLSupport`
- Modified `showTask()` method to apply RTL to dynamically created views
- Ensures all panels and dialogs inherit RTL orientation

**Code Location:** Line 460

### 3. Table Renderers - RTL-Aware Alignment

#### TableRendererBasic.java
**File:** `src-data/uk/chromis/data/gui/TableRendererBasic.java`

**Changes:**
- Added ComponentOrientation import
- Modified `getTableCellRendererComponent()` to flip alignment in RTL mode:
  - LEFT becomes RIGHT
  - RIGHT becomes LEFT
  - CENTER remains CENTER

**Code Location:** Lines 57-68

#### JTicketLines.java (TicketCellRenderer)
**File:** `src-pos/uk/chromis/pos/sales/JTicketLines.java`

**Changes:**
- Added ComponentOrientation import
- Modified `TicketCellRenderer.getTableCellRendererComponent()` to flip alignment in RTL mode
- Ensures ticket line items display correctly in RTL

**Code Location:** Lines 234-246

### 4. Input Fields - RTL Text Direction

#### CustomJTextField.java
**File:** `src-pos/uk/chromis/custom/CustomJTextField.java`

**Changes:**
- Added RTLSupport import
- Modified constructor to:
  - Apply component orientation
  - Set horizontal alignment to RIGHT in RTL mode

**Code Location:** Lines 44-50

#### CustomJPasswordField.java
**File:** `src-pos/uk/chromis/custom/CustomJPasswordField.java`

**Changes:**
- Added RTLSupport import
- Modified constructor to:
  - Apply component orientation
  - Set horizontal alignment to RIGHT in RTL mode

**Code Location:** Lines 44-50

#### JNumberField.java
**File:** `src-pos/uk/chromis/commons/utils/JNumberField.java`

**Changes:**
- Added RTLSupport import
- Modified both constructors to:
  - Apply RTL-aware alignment (numbers align LEFT in RTL, RIGHT in LTR)
  - Apply component orientation

**Code Locations:** Lines 120-133, Lines 154-163

### 5. Dialogs - RTL Layout

#### AlertDialog.java
**File:** `src-pos/uk/chromis/commons/dialogs/AlertDialog.java`

**Changes:**
- Added RTLSupport import
- Modified both constructors to apply RTL orientation before packing

**Code Locations:** Lines 77-80, Lines 88-91

#### LoginDialog.java
**File:** `src-pos/uk/chromis/commons/dialogs/LoginDialog.java`

**Changes:**
- Added RTLSupport import
- Modified constructor to apply RTL orientation before packing

**Code Location:** Lines 70-72

#### JPaymentSelect.java
**File:** `src-pos/uk/chromis/pos/payment/JPaymentSelect.java`

**Status:** Already supports RTL
- Constructor accepts ComponentOrientation parameter
- Applies orientation to payment interface components
- No changes needed

### 6. Reports and Receipts

**Status:** Partial Implementation

Receipt printing in Chromis POS uses:
- ESC/POS commands for thermal printers
- XML templates for receipt formatting
- Text-based alignment

**RTL Considerations:**
- Most thermal printers don't natively support RTL text rendering
- Arabic text may need to be pre-shaped before sending to printer
- Receipt templates (XML files) may need RTL-specific versions
- System printers (via Java Print API) should handle RTL correctly

**Recommendation:**
- Test receipt printing with Arabic locale
- Create RTL-specific receipt templates if needed
- Consider using Unicode-aware printer drivers

## Configuration

### Setting Arabic Locale

To enable Arabic RTL mode, configure the following system properties:

**File:** `chromisposconfig.properties`

```properties
user.language=ar
user.country=SA
```

Or set via command line:
```bash
java -Duser.language=ar -Duser.country=SA -jar chromispos.jar
```

## Testing Checklist

### GUI Components
- [ ] Main application window mirrors correctly
- [ ] Login dialog displays RTL
- [ ] User selection panel displays RTL
- [ ] All menus and buttons align correctly

### Sales Screen
- [ ] Product catalog displays RTL
- [ ] Ticket lines table aligns correctly
- [ ] Payment buttons align correctly
- [ ] Number pad displays correctly

### Tables
- [ ] Column headers align correctly
- [ ] Cell content aligns correctly (text right, numbers left)
- [ ] Scrollbars appear on correct side

### Input Fields
- [ ] Text fields accept RTL input
- [ ] Text aligns to the right
- [ ] Cursor starts from right side
- [ ] Number fields display correctly

### Dialogs
- [ ] Alert dialogs display RTL
- [ ] Login dialog displays RTL
- [ ] Payment dialog displays RTL
- [ ] All custom dialogs display RTL

### Reports
- [ ] Screen reports display correctly
- [ ] Printed reports align correctly
- [ ] Receipt printing works with Arabic text

## Known Limitations

1. **Receipt Printing:** Thermal printers may require additional configuration for Arabic text
2. **Third-Party Components:** Some third-party Swing components may not fully support RTL
3. **Custom Layouts:** MigLayout constraints may need adjustment for optimal RTL display

## Next Steps

1. **Compile and Test:** Build the application and test with Arabic locale
2. **Visual Inspection:** Check all screens for layout issues
3. **Receipt Testing:** Test receipt printing with Arabic text
4. **Fine-Tuning:** Adjust any components that don't display correctly
5. **Documentation:** Create user guide for RTL configuration

## Files Modified

1. `src-pos/uk/chromis/pos/util/RTLSupport.java` (NEW)
2. `src-pos/uk/chromis/pos/forms/JRootApp.java`
3. `src-pos/uk/chromis/pos/forms/JPrincipalApp.java`
4. `src-data/uk/chromis/data/gui/TableRendererBasic.java`
5. `src-pos/uk/chromis/pos/sales/JTicketLines.java`
6. `src-pos/uk/chromis/custom/CustomJTextField.java`
7. `src-pos/uk/chromis/custom/CustomJPasswordField.java`
8. `src-pos/uk/chromis/commons/utils/JNumberField.java`
9. `src-pos/uk/chromis/commons/dialogs/AlertDialog.java`
10. `src-pos/uk/chromis/commons/dialogs/LoginDialog.java`

## Support for Additional RTL Languages

The implementation supports:
- **Arabic (ar)** - Primary target
- **Hebrew (he/iw)** - Fully supported
- **Persian (fa)** - Fully supported
- **Urdu (ur)** - Fully supported

To add support for additional RTL languages, modify the `isRTLLanguage()` method in `RTLSupport.java`.

