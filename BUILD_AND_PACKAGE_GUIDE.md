# AZOR POS - Build and Package Guide

**Developed by Mazen Mostafa**

Complete guide for building and packaging AZOR POS for distribution.

---

## 📋 Overview

This guide covers:
1. Building the application from source
2. Creating a Windows executable (AZOR_POS.exe)
3. Packaging for distribution
4. Including a bundled JRE

---

## 🔧 Prerequisites

### Required

- ✅ JDK 11 or higher
- ✅ Apache Maven 3.6+
- ✅ Source code compiled successfully

### Optional (for .exe creation)

- ⭐ **Launch4j** (Recommended) - https://launch4j.sourceforge.net/
- ⭐ **jpackage** (Alternative, requires JDK 14+)
- ⭐ **ImageMagick** (for icon conversion) - https://imagemagick.org/

---

## 📦 Step 1: Build the Application

### Using the Build Script (Easiest)

```batch
cd E:\Azor_POS
scripts\build.bat
```

This will:
1. Clean previous builds
2. Compile all source code
3. Package into JAR file
4. Copy dependencies
5. Create distribution folder

### Using Maven Directly

```batch
cd E:\Azor_POS

# Clean previous build
mvn clean

# Compile and package
mvn package
```

### Build Output

After successful build, you'll have:

```
target/
├── azorpos.jar                    # Main application JAR (28MB)
├── lib/                           # All dependencies
├── AZOR_POS_1.5.5/               # Distribution folder
│   ├── azorpos.jar
│   ├── lib/                       # All JAR dependencies
│   ├── locales/                   # Language files (including Arabic)
│   │   ├── chromispos.properties
│   │   ├── chromispos_ar.properties
│   │   └── ...
│   ├── images/                    # Application images
│   ├── fonts/                     # Custom fonts
│   ├── cssStyles/                 # CSS stylesheets
│   ├── iconsets/                  # Icon sets (ZIP files)
│   ├── licensing/                 # License files
│   ├── beep.wav                   # Sound files
│   ├── error.wav
│   └── chromisposconfig.properties
└── AZOR_POS_1.5.5.zip            # Distribution ZIP file
```

---

## 🖥️ Step 2: Create Windows Executable

### Option A: Using Launch4j (Recommended)

**Why Launch4j?**
- ✅ Works with any JDK version (11+)
- ✅ Creates native Windows .exe
- ✅ Can bundle JRE or use system Java
- ✅ Professional version information
- ✅ Custom icon support

**Installation:**

1. Download Launch4j from https://launch4j.sourceforge.net/
2. Run installer (default location: `C:\Program Files (x86)\Launch4j`)
3. No additional configuration needed

**Prepare Icon (Optional):**

```batch
# If you have ImageMagick installed:
cd E:\Azor_POS
magick convert src-beans\uk\chromis\fixedimages\azoricon.png -define icon:auto-resize=256,128,64,48,32,16 azoricon.ico

# Or use online converter:
# https://convertio.co/png-ico/
# Upload: src-beans\uk\chromis\fixedimages\azoricon.png
# Download: azoricon.ico
# Place in project root
```

**Create Executable:**

```batch
cd E:\Azor_POS

# Make sure you've built first
scripts\build.bat

# Create executable
scripts\package-launch4j.bat
```

**Output:**

```
target/AZOR_POS_Distribution/
├── AZOR_POS.exe                   ← Windows executable (launcher)
├── azorpos.jar                    ← Main application
├── lib/                           ← All dependencies
│   ├── AbsoluteLayout-RELEASE150.jar
│   ├── JTattoo-1.6.13.jar
│   ├── mysql-connector-java-8.0.23.jar
│   └── ... (40+ JAR files)
├── locales/                       ← Language files
│   ├── chromispos_ar.properties   ← Arabic translation
│   └── ...
├── images/                        ← Images
├── fonts/                         ← Fonts
├── cssStyles/                     ← Stylesheets
├── iconsets/                      ← Icon sets
├── licensing/                     ← Licenses
├── jre/                           ← Bundled JRE (if included)
├── beep.wav, error.wav           ← Sound files
├── chromisposconfig.properties.template
└── README.txt
```

### Option B: Using jpackage (JDK 14+)

**Why jpackage?**
- ✅ Built into JDK 14+
- ✅ Creates self-contained application
- ✅ Bundles JRE automatically
- ❌ Requires JDK 14+ (not just 11)

**Create Executable:**

```batch
cd E:\Azor_POS

# Make sure you've built first
scripts\build.bat

# Create executable
scripts\package-exe.bat
```

**Output:**

```
target/installer/AZOR POS/
├── AZOR POS.exe                   ← Self-contained executable
├── app/
│   ├── azorpos.jar
│   └── ...
└── runtime/                       ← Bundled JRE
    └── ...
```

---

## 🎁 Step 3: Bundle Java Runtime (Optional but Recommended)

### Why Bundle JRE?

- ✅ Users don't need to install Java
- ✅ Guaranteed compatible Java version
- ✅ Easier deployment
- ❌ Larger distribution size (~100MB additional)

### Method 1: Using jlink (Create Custom JRE)

The project includes `jlink-pos.bat` to create a minimal JRE:

```batch
cd E:\Azor_POS
jlink-pos.bat
```

This creates a `jre/` folder with only the required Java modules:
- javafx.base, javafx.controls, javafx.swing, javafx.graphics
- java.management, java.base, java.desktop
- java.logging, java.xml, java.sql, java.rmi, java.naming, java.prefs

**Size:** ~80-100MB (vs. full JRE ~200MB)

### Method 2: Copy Full JRE

```batch
# Copy your JDK's JRE
xcopy /E /I "C:\Program Files\Java\jdk-11\jre" "E:\Azor_POS\jre"
```

### Method 3: Download Portable JRE

1. Download JRE 11 from https://adoptium.net/
2. Extract to `E:\Azor_POS\jre`

### Verify JRE Bundle

After bundling, your distribution should have:

```
AZOR_POS_Distribution/
├── AZOR_POS.exe
├── azorpos.jar
├── lib/
├── jre/                           ← Bundled JRE
│   ├── bin/
│   │   ├── java.exe              ← Java runtime
│   │   └── ...
│   ├── lib/
│   └── ...
└── ...
```

The launcher scripts will automatically use the bundled JRE if present.

---

## 📤 Step 4: Final Distribution Package

### What to Include

Your final distribution should contain:

```
AZOR_POS/
├── AZOR_POS.exe                   ← Main executable
├── azorpos.jar                    ← Application JAR
├── lib/                           ← Dependencies (40+ JARs)
├── locales/                       ← Language files
│   ├── chromispos_ar.properties   ← Arabic
│   └── chromispos.properties      ← English
├── images/                        ← Application images
├── fonts/                         ← Custom fonts
├── cssStyles/                     ← Stylesheets (Windows/Linux)
├── iconsets/                      ← Icon sets (4 color schemes)
├── licensing/                     ← License files
├── jre/                           ← Bundled Java Runtime (optional)
├── beep.wav                       ← Sound files
├── error.wav
├── chromisposconfig.properties.template
└── README.txt                     ← User instructions
```

### Create Distribution ZIP

**Using the build script:**

The Maven build automatically creates a ZIP file:
- Location: `target/AZOR_POS_1.5.5.zip`

**Manual creation:**

```batch
cd E:\Azor_POS\target
powershell Compress-Archive -Path "AZOR_POS_Distribution\*" -DestinationPath "AZOR_POS_1.5.5_Complete.zip"
```

### Distribution Sizes

| Package Type | Size (approx.) |
|--------------|----------------|
| Without JRE | ~50-60 MB |
| With jlink JRE | ~130-150 MB |
| With full JRE | ~200-250 MB |

---

## ✅ Testing the Distribution

### Test Checklist

Before distributing, test on a clean machine:

1. **Extract the distribution**
   ```batch
   # Extract ZIP to C:\AZOR_POS_Test
   ```

2. **Test without Java installed** (if JRE bundled)
   ```batch
   cd C:\AZOR_POS_Test
   AZOR_POS.exe
   ```
   - Should start using bundled JRE
   - Check splash screen shows "AZOR POS"
   - Check developer credit: "Developed by Mazen Mostafa"

3. **Test with system Java**
   ```batch
   # Rename or remove jre folder temporarily
   AZOR_POS.exe
   ```
   - Should use system Java
   - Should show warning if Java < 11

4. **Test Arabic language**
   - Configure locale to Arabic
   - Verify RTL layout
   - Check Arabic translation loads

5. **Test all resources**
   - Check images load correctly
   - Check fonts display properly
   - Check icon sets work
   - Check sound files play

### Common Issues

**Issue: "Java not found"**
- Solution: Include bundled JRE or instruct users to install Java 11+

**Issue: "Database connection failed"**
- Solution: Provide `chromisposconfig.properties.template` with instructions

**Issue: "Icons not loading"**
- Solution: Ensure `iconsets/*.zip` files are included

---

## 📝 Build Summary

### Quick Build Commands

```batch
# Full build and package process:
cd E:\Azor_POS

# 1. Build application
scripts\build.bat

# 2. Create JRE (optional)
jlink-pos.bat

# 3. Create executable
scripts\package-launch4j.bat

# Done! Distribution is in:
# target\AZOR_POS_Distribution\
```

### Build Artifacts

| File | Purpose | Required |
|------|---------|----------|
| AZOR_POS.exe | Windows launcher | ✅ Yes |
| azorpos.jar | Main application | ✅ Yes |
| lib/*.jar | Dependencies | ✅ Yes |
| locales/* | Translations | ✅ Yes |
| images/* | UI images | ✅ Yes |
| fonts/* | Custom fonts | ✅ Yes |
| cssStyles/* | Stylesheets | ✅ Yes |
| iconsets/* | Icon themes | ✅ Yes |
| jre/* | Java runtime | ⭐ Recommended |
| licensing/* | Licenses | ✅ Yes |
| *.wav | Sound effects | ⭐ Recommended |

---

## 🚀 Next Steps

1. ✅ Build completed
2. ✅ Executable created
3. ✅ Distribution packaged
4. ⏭️ Test on clean machine
5. ⏭️ Create installer (optional)
6. ⏭️ Deploy to users

**See also:**
- `VSCODE_SETUP_GUIDE.md` - Development environment setup
- `DEPLOYMENT_GUIDE.md` - Deployment instructions
- `README.txt` - User instructions (in distribution)

---

**Build Status:** Ready for distribution! 🎉

