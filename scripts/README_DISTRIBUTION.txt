================================================================================
                            AZOR POS
                    Point of Sale System
                 Developed by Mazen Mostafa
================================================================================

Version: 1.5.5
Date: November 2025

================================================================================
CONTENTS
================================================================================

This package contains:

  AZOR_POS.exe              - Windows executable (double-click to run)
  azorpos.jar               - Main application JAR file
  lib/                      - Application dependencies (40+ JAR files)
  locales/                  - Language files (English, Arabic, etc.)
  images/                   - Application images and logos
  fonts/                    - Custom fonts
  cssStyles/                - User interface stylesheets
  iconsets/                 - Icon themes (4 color schemes)
  licensing/                - License information
  jre/                      - Bundled Java Runtime (if included)
  beep.wav, error.wav       - Sound effects
  chromisposconfig.properties.template - Configuration template

================================================================================
SYSTEM REQUIREMENTS
================================================================================

Minimum:
  - Windows 7 SP1 or higher (64-bit)
  - 4 GB RAM
  - 500 MB disk space
  - 1024x768 display resolution
  - Java 11+ (or use bundled JRE)
  - MySQL 5.7+ or MariaDB 10.2+

Recommended:
  - Windows 10/11 (64-bit)
  - 8 GB RAM
  - 1 GB disk space
  - 1920x1080 display resolution
  - Bundled JRE (included)
  - MySQL 8.0+

================================================================================
INSTALLATION
================================================================================

1. Extract this entire folder to your desired location
   Example: C:\Program Files\AZOR_POS\

2. Configure database connection:
   - Rename "chromisposconfig.properties.template" to 
     "chromisposconfig.properties"
   - Edit the file with Notepad
   - Set your database server, username, password, and database name

3. Create a desktop shortcut (optional):
   - Right-click on AZOR_POS.exe
   - Select "Send to" -> "Desktop (create shortcut)"

4. Run AZOR POS:
   - Double-click AZOR_POS.exe

================================================================================
FIRST RUN
================================================================================

On first run, you should see:

1. Splash screen with AZOR POS logo
2. Developer credit: "Developed by Mazen Mostafa"
3. Version number: V1.5.5
4. Login screen

If you see any errors:
  - Check that the database is running
  - Verify chromisposconfig.properties is configured correctly
  - Check logs/ folder for error details

================================================================================
CONFIGURATION
================================================================================

Edit chromisposconfig.properties:

Database Settings:
  database.server = localhost          (or your database server IP)
  database.port = 3306                 (MySQL default port)
  database.name = azorpos_db           (your database name)
  database.user = azoruser             (database username)
  database.password = crypt:...        (encrypted password)

Terminal Settings:
  terminalID = POS-001                 (unique ID for this terminal)

Display Settings:
  POSwidth = 1024                      (window width)
  POSheight = 768                      (window height)

================================================================================
ARABIC SUPPORT
================================================================================

AZOR POS includes full Arabic language support:

- Complete Arabic translation (458 translated keys)
- Right-to-Left (RTL) layout
- Arabic text rendering
- Automatic language detection

To use Arabic:
  - The application will auto-detect your system locale
  - Or configure manually in settings
  - All UI elements will mirror for RTL display

================================================================================
FEATURES
================================================================================

- Point of Sale operations
- Inventory management
- Customer management
- Employee management
- Sales reporting
- Receipt printing
- Multi-terminal support
- Role-based permissions
- Multiple payment methods
- Product categories and subcategories
- Barcode scanning support
- Touch screen support
- Multiple language support (English, Arabic, etc.)
- Customizable themes and icon sets

================================================================================
TROUBLESHOOTING
================================================================================

Problem: Application won't start
Solution: 
  - If bundled JRE is included, it will be used automatically
  - Otherwise, install Java 11 or higher from https://adoptium.net/
  - Verify JAVA_HOME environment variable is set

Problem: "Cannot connect to database"
Solution:
  - Verify MySQL/MariaDB is running
  - Check database server IP, port, username, password
  - Test connection: mysql -h SERVER -u USER -p DATABASE
  - Check firewall allows MySQL port (3306)

Problem: "Terminal not registered"
Solution:
  - Run TerminalConfig utility to register this terminal
  - Or contact your system administrator

Problem: Images or icons not loading
Solution:
  - Verify all folders (images/, iconsets/, etc.) are present
  - Check file permissions
  - Review logs/ folder for specific errors

Problem: Arabic text not displaying
Solution:
  - Verify locales/chromispos_ar.properties exists
  - Check that system supports Arabic fonts
  - Restart application

================================================================================
SUPPORT
================================================================================

For technical support:

  - Check the logs/ folder for error details
  - Review chromisposconfig.properties settings
  - Consult the documentation files in locales/ folder

Based on Chromis POS:
  - Website: https://www.chromis.co.uk
  - Community support available

================================================================================
LICENSE
================================================================================

AZOR POS is based on Chromis POS, which is licensed under the GNU General
Public License v3.0 (GPL-3.0).

See licensing/ folder for complete license information.

This application is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by the
Free Software Foundation, either version 3 of the License, or (at your
option) any later version.

================================================================================
CREDITS
================================================================================

AZOR POS - Developed by Mazen Mostafa

Based on Chromis POS
Copyright (c) 2015-2023 Chromis
https://www.chromis.co.uk

================================================================================
VERSION HISTORY
================================================================================

Version 1.5.5 (November 2025)
  - Complete rebranding to AZOR POS
  - Full Arabic language support with RTL layout
  - Developer credit integration
  - Improved build and packaging system
  - Comprehensive documentation

================================================================================

Thank you for using AZOR POS!

For more information, see the documentation files in the locales/ folder.

================================================================================

