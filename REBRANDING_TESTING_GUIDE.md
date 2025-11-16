# AZOR POS Rebranding - Testing & Verification Guide

## Quick Start

This guide will help you verify that all rebranding changes are working correctly.

---

## 🔧 Step 1: Compile the Application

Before testing, you **must recompile** the application since Java source files were modified.

### If you have a build script:
```bash
# Look for build files
ls build.xml build.bat build.sh

# Run the appropriate build command
# Example:
ant clean
ant dist
```

### If you're using an IDE:
- Clean and rebuild the project
- Ensure all modified files are included in the build

---

## ✅ Step 2: Verification Checklist

### Test 1: Splash Screen (CRITICAL)

**What to check:**
1. Launch the application
2. Observe the splash screen that appears during startup

**Expected Results:**
- ✅ AZOR POS logo is displayed (not Chromis logo)
- ✅ Text "Developed by Mazen Mostafa" is visible
- ✅ Text is styled in blue color
- ✅ Text is positioned below the version number
- ✅ Version shows "V1.5.5"

**Screenshot Required:** Yes - This is proof of splash screen integration

**Visual Layout:**
```
┌──────────────────────────────────────────┐
│                                          │
│         [AZOR POS LOGO IMAGE]            │
│                                          │
│                          V1.5.5          │
│      Developed by Mazen Mostafa          │
│                                          │
│    [Starting/Loading text/animation]     │
│                                          │
└──────────────────────────────────────────┘
```

---

### Test 2: Main Window Title

**What to check:**
1. After application loads, look at the window title bar
2. Check the taskbar/dock icon

**Expected Results:**
- ✅ Window title shows: "AZOR POS - V1.5.5"
- ✅ Taskbar icon shows AZOR logo (not Chromis logo)

**Screenshot Required:** Yes - Shows window title and icon

---

### Test 3: About Dialog / Information Screen

**What to check:**
1. Look for an "About" option in the menu, or
2. Click on the logo/info icon in the application, or
3. Check the login screen information text

**Expected Results:**
- ✅ Header shows "AZOR POS - Point of Sale System"
- ✅ Text explicitly states: "This app developed by Mazen Mostafa"
- ✅ AZOR logo is displayed (not Chromis logo)
- ✅ Acknowledgment of Chromis POS as base is present

**Screenshot Required:** Yes - This is proof of About dialog update

**Expected Text:**
```
AZOR POS - Point of Sale System
This app developed by Mazen Mostafa

Based on Chromis POS - Copyright © 2015 - 2023 Chromis
http://www.chromis.co.uk

AZOR POS is Open Source Software...
```

---

### Test 4: Login Screen (if applicable)

**What to check:**
1. If the application has a login screen, check the branding there

**Expected Results:**
- ✅ AZOR logo is displayed
- ✅ Information text shows AZOR POS branding
- ✅ Developer credit is visible in the info text

**Screenshot Required:** Optional but recommended

---

### Test 5: All Dialogs and Popups

**What to check:**
1. Open various dialogs (settings, help, warnings, etc.)
2. Check for any remaining Chromis branding

**Expected Results:**
- ✅ No "Chromis POS" text visible anywhere
- ✅ All logos show AZOR branding
- ✅ Consistent branding across all screens

**Screenshot Required:** Optional - capture any dialogs with logos

---

## 📸 Screenshot Collection

Create a folder with the following screenshots:

1. **01_splash_screen.png** - Startup splash with developer credit ⭐ REQUIRED
2. **02_main_window.png** - Main window showing title bar ⭐ REQUIRED
3. **03_about_dialog.png** - About/Info dialog with developer credit ⭐ REQUIRED
4. **04_taskbar_icon.png** - Taskbar showing AZOR icon (optional)
5. **05_login_screen.png** - Login screen if applicable (optional)

---

## 🎥 Video/Screen Recording (Alternative)

Instead of screenshots, you can create a short screen recording (1-2 minutes) showing:

1. Application startup (splash screen)
2. Main window with title
3. Opening the About dialog
4. Navigating through a few screens

**Recommended Tools:**
- Windows: Xbox Game Bar (Win+G)
- OBS Studio (free, cross-platform)
- ShareX (Windows)

---

## 🐛 Troubleshooting

### Issue: Splash screen still shows Chromis logo

**Possible Causes:**
1. Application not recompiled
2. Old JAR file being used
3. Image files not included in build

**Solution:**
1. Clean build directory
2. Recompile completely
3. Verify `azorlogo.png` exists in `src-beans/uk/chromis/fixedimages/`
4. Check build includes resource files

---

### Issue: Developer credit text not visible on splash

**Possible Causes:**
1. SplashLogo.java not recompiled
2. Font/color makes text invisible

**Solution:**
1. Verify `SplashLogo.java` was modified correctly
2. Check lines 60-93 in the file
3. Recompile the application

---

### Issue: Window title still shows "Chromis POS"

**Possible Causes:**
1. AppLocal.java not recompiled
2. Cached configuration

**Solution:**
1. Verify `AppLocal.java` line 29 shows `APP_NAME = "AZOR POS"`
2. Clean and rebuild
3. Clear any application cache

---

### Issue: About dialog still shows Chromis branding

**Possible Causes:**
1. JRootApp.java not recompiled
2. Multiple About dialogs in application

**Solution:**
1. Verify `JRootApp.java` lines 183-194 and 763-777 were modified
2. Search for other About dialog implementations
3. Recompile completely

---

## ✅ Success Criteria

The rebranding is **100% successful** when:

1. ✅ Splash screen shows AZOR logo
2. ✅ Splash screen shows "Developed by Mazen Mostafa"
3. ✅ Window title shows "AZOR POS"
4. ✅ About dialog shows developer credit
5. ✅ No "Chromis POS" text visible anywhere
6. ✅ All logos are AZOR branded

---

## 📋 Proof of Completion

To demonstrate successful rebranding, provide:

### Required:
1. ✅ Screenshot of splash screen with developer credit
2. ✅ Screenshot of About dialog with developer credit
3. ✅ Screenshot of main window title

### Optional but Recommended:
4. Short video showing application startup and navigation
5. List of all modified files (see AZOR_POS_REBRANDING_SUMMARY.md)
6. Before/After comparison images

---

## 📞 Support

If you encounter any issues during testing:

1. Check the `AZOR_POS_REBRANDING_SUMMARY.md` file for complete details
2. Verify all files listed were actually modified
3. Ensure complete recompilation was performed
4. Check that image files were created in the correct location

---

## 🎉 Final Notes

Once all tests pass:

1. Document the results with screenshots/video
2. Archive the modified source code
3. Create a backup of the compiled application
4. Update any deployment documentation

**The rebranding is complete and ready for production use!**

