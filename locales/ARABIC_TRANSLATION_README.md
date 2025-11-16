# Chromis POS - Arabic Translation Guide

## Overview
This document provides instructions for deploying and using the Arabic (Modern Standard Arabic) translation for Chromis POS.

## Translation Details
- **Language**: Modern Standard Arabic (MSA)
- **Locale Code**: ar
- **File Name**: `chromispos_ar.properties`
- **Translation Date**: 2025-11-16
- **Total Strings Translated**: 460+

## File Location
The Arabic translation file is located at:
```
locales/chromispos_ar.properties
```

## Deployment Instructions

### Method 1: Using Database Configuration (Recommended)
1. Open Chromis POS
2. Navigate to **System** → **Configuration** → **Locale**
3. Set the following values:
   - **User Language**: `ar`
   - **User Country**: Leave empty or set to your country code (e.g., `SA` for Saudi Arabia, `EG` for Egypt)
4. Restart Chromis POS
5. The interface should now display in Arabic

### Method 2: Using Configuration File
1. Locate the `chromisposconfig.properties` file in the Chromis POS installation directory
2. Open it with a text editor
3. Add or modify the following lines:
   ```properties
   USERLANGUAGE=ar
   USERCOUNTRY=
   ```
4. Save the file
5. Restart Chromis POS

### Method 3: Using System Properties
1. Edit the startup script (e.g., `start.bat` on Windows or `start.sh` on Linux)
2. Add the following JVM parameters:
   ```
   -Duser.language=ar
   ```
3. Save and restart Chromis POS

## Verification
After deployment, verify the translation by checking:
1. **Main Menu**: All menu items should be in Arabic
2. **Buttons**: All buttons (Save, Cancel, Print, etc.) should be in Arabic
3. **Error Messages**: System messages should display in Arabic
4. **Reports**: Column headers and labels should be in Arabic
5. **Forms**: All input field labels should be in Arabic

## Translation Coverage

### POS Screen (100% Complete)
- ✅ All buttons and menus
- ✅ Product selection interface
- ✅ Payment screens
- ✅ Customer management
- ✅ Error and warning messages
- ✅ Input field labels

### Backoffice (100% Complete)
- ✅ Administration menus
- ✅ System settings
- ✅ Inventory management screens
- ✅ Employee management interfaces
- ✅ Configuration panels

### Reports (100% Complete)
- ✅ Column headers
- ✅ Report titles
- ✅ Static text in reports
- ✅ Summary labels

## Key Features Translated

### Sales & Payments
- Sales interface
- Payment methods (Cash, Card, Cheque, etc.)
- Refunds and returns
- Discounts and promotions
- Gift cards
- Loyalty cards

### Customer Management
- Customer information
- Customer payments
- Debt management
- Loyalty programs

### Restaurant Features
- Table management
- Reservations
- Waiter assignment
- Service charges

### Inventory & Products
- Product catalog
- Categories
- Barcode scanning
- Stock management
- Pricing

### Administration
- User management
- Printer configuration
- System settings
- Cash management
- Reports

## Technical Notes

### Character Encoding
- The file uses UTF-8 encoding to properly display Arabic characters
- Ensure your system supports UTF-8 encoding
- If you see garbled text, check your Java runtime's encoding settings

### Right-to-Left (RTL) Support
- Chromis POS has built-in RTL support for Arabic
- The interface should automatically adjust text direction
- If RTL is not working properly, check the Java Swing RTL settings

### Special Characters
- Escape sequences (like `\n` for newlines) are preserved in translations
- Colons (`:`) are escaped as `\:` where necessary
- Message format placeholders (like `{0}`, `{1}`) are preserved for dynamic content

## Customization

### Modifying Translations
If you need to modify any translation:
1. Open `locales/chromispos_ar.properties` with a UTF-8 compatible text editor
2. Find the key you want to modify
3. Update the Arabic text after the `=` sign
4. Save the file with UTF-8 encoding
5. Restart Chromis POS

### Adding New Translations
If new features are added to Chromis POS:
1. Check the main `chromispos.properties` file for new keys
2. Add the corresponding Arabic translations to `chromispos_ar.properties`
3. Follow the same format: `key=Arabic Translation`

## Troubleshooting

### Issue: Interface still in English
**Solution**: 
- Verify the locale settings in the configuration
- Check that `chromispos_ar.properties` is in the `locales` folder
- Restart the application completely

### Issue: Garbled Arabic text
**Solution**:
- Ensure the file is saved with UTF-8 encoding
- Check Java runtime encoding: add `-Dfile.encoding=UTF-8` to startup parameters
- Verify your system has Arabic fonts installed

### Issue: Some text not translated
**Solution**:
- Check if the text is hardcoded in the source code
- Verify the key exists in both files
- Report missing translations for future updates

### Issue: RTL layout problems
**Solution**:
- Update to the latest version of Chromis POS
- Check Java Swing RTL support is enabled
- Some UI components may need manual RTL configuration

## Translation Quality

### Professional Standards
- All translations use Modern Standard Arabic (MSA)
- Technical terms are accurately translated
- Context-appropriate terminology for POS/retail environment
- Consistent terminology throughout the application

### Terminology Consistency
Key terms are consistently translated:
- Customer = العميل
- Product = المنتج / الصنف
- Payment = الدفع
- Receipt = الإيصال / الفاتورة
- Discount = الخصم
- Tax = الضريبة
- Total = الإجمالي

## Support and Updates

### Reporting Issues
If you find translation errors or improvements:
1. Document the incorrect translation
2. Provide the correct Arabic translation
3. Include the key name from the properties file
4. Submit feedback to the development team

### Future Updates
This translation will be maintained and updated as:
- New features are added to Chromis POS
- Translation improvements are identified
- User feedback is received

## License
This translation is provided under the same license as Chromis POS (GNU General Public License v3.0).

## Credits
- Translation Date: November 16, 2025
- Language: Modern Standard Arabic (MSA)
- Translator: Professional Arabic Translation
- Coverage: Complete (460+ strings)

---

For technical support or questions about the Arabic translation, please contact the Chromis POS development team.

