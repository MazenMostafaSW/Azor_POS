# AZOR POS - Quick Start Build Guide

**Developed by Mazen Mostafa**

Get AZOR POS built and packaged in 5 minutes!

---

## 🚀 Quick Start (Windows)

### Prerequisites Check

```batch
# Check Java (need 11+)
java -version

# Check Maven
mvn -version
```

If either is missing, see [Full Setup Guide](VSCODE_SETUP_GUIDE.md).

---

## ⚡ 3-Step Build Process

### Step 1: Build Application

```batch
cd E:\Azor_POS
scripts\build.bat
```

**Output:** `target/azorpos.jar` and `target/AZOR_POS_1.5.5/`

**Time:** 2-3 minutes

---

### Step 2: Create JRE (Optional)

```batch
jlink-pos.bat
```

**Output:** `jre/` folder (~80MB)

**Time:** 1-2 minutes

---

### Step 3: Create Windows Executable

**Option A: Launch4j (Recommended)**

```batch
# Install Launch4j first: https://launch4j.sourceforge.net/
scripts\package-launch4j.bat
```

**Option B: jpackage (JDK 14+)**

```batch
scripts\package-exe.bat
```

**Output:** `target/AZOR_POS_Distribution/AZOR_POS.exe`

**Time:** 1-2 minutes

---

## ✅ Verify Build

```batch
cd target\AZOR_POS_Distribution
AZOR_POS.exe
```

**Expected:**
- ✅ Splash screen shows "AZOR POS"
- ✅ Developer credit: "Developed by Mazen Mostafa"
- ✅ Application starts

---

## 📦 Distribution Package

Your final package is ready at:

```
target/AZOR_POS_Distribution/
├── AZOR_POS.exe          ← Windows executable
├── azorpos.jar           ← Main application
├── lib/                  ← Dependencies (40+ JARs)
├── locales/              ← Languages (including Arabic)
├── jre/                  ← Bundled Java (if created)
└── ... (all resources)
```

**Size:** 
- Without JRE: ~50-60 MB
- With JRE: ~130-150 MB

---

## 🎯 What's Included

✅ **AZOR POS Branding**
- Application name: "AZOR POS"
- Window title: "AZOR POS - V1.5.5"
- Developer credit on splash screen

✅ **Complete Arabic Support**
- Full Arabic translation (458 keys)
- RTL layout implementation
- Arabic locale: `chromispos_ar.properties`

✅ **All Resources**
- Images, fonts, icons, stylesheets
- Sound effects (beep.wav, error.wav)
- License files
- Configuration template

✅ **Self-Contained**
- All dependencies included
- Optional bundled JRE
- No installation required

---

## 🔧 VS Code Setup (Optional)

### Install Extensions

1. Open `Azor_POS` folder in VS Code
2. Click "Install All" when prompted for extensions
3. Or manually install: **Extension Pack for Java**

### Run from VS Code

1. Press `F5`
2. Select "Launch AZOR POS"
3. Application starts

### Debug

1. Set breakpoints (click left of line numbers)
2. Press `F5`
3. Use debug toolbar to step through code

---

## 📚 Full Documentation

| Guide | Purpose |
|-------|---------|
| **VSCODE_SETUP_GUIDE.md** | Complete VS Code setup |
| **BUILD_AND_PACKAGE_GUIDE.md** | Detailed build instructions |
| **DEPLOYMENT_GUIDE.md** | Deployment procedures |
| **AZOR_POS_REBRANDING_SUMMARY.md** | Branding changes |
| **RTL_TESTING_GUIDE.md** | Arabic RTL testing |

---

## 🐛 Quick Troubleshooting

**Build fails?**
```batch
# Clean and retry
mvn clean
mvn package
```

**Java not found?**
- Install JDK 11+ from https://adoptium.net/
- Add to PATH

**Maven not found?**
- Install from https://maven.apache.org/download.cgi
- Add to PATH

**Launch4j fails?**
- Install from https://launch4j.sourceforge.net/
- Or use jpackage instead

---

## ✨ Key Features

### Branding
- ✅ Application name: AZOR POS
- ✅ Artifact ID: azorpos
- ✅ Developer credit: Mazen Mostafa
- ✅ Custom logo and icon

### Internationalization
- ✅ English (default)
- ✅ Arabic (full translation + RTL)
- ✅ Easy to add more languages

### Build System
- ✅ Maven-based build
- ✅ Automated packaging
- ✅ Windows executable creation
- ✅ JRE bundling support

### Development
- ✅ VS Code integration
- ✅ Debug configurations
- ✅ Source code organization
- ✅ Dependency management

---

## 🎉 Success!

You now have:

1. ✅ Compiled AZOR POS application
2. ✅ Windows executable (AZOR_POS.exe)
3. ✅ Complete distribution package
4. ✅ Optional bundled JRE
5. ✅ Ready for deployment!

**Next Steps:**
- Test the application
- Deploy to users
- See DEPLOYMENT_GUIDE.md for deployment procedures

---

## 📞 Need Help?

- **Build issues:** Check Maven output for errors
- **Runtime issues:** Check `logs/` folder
- **VS Code issues:** Check Java extension logs
- **Full guides:** See documentation files listed above

---

**Build Time:** ~5 minutes total
**Difficulty:** Easy
**Status:** Production ready! 🚀

