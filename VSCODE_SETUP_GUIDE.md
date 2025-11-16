# AZOR POS - VS Code Development Environment Setup Guide

**Developed by Mazen Mostafa**

This guide will help you set up a complete development environment for AZOR POS in Visual Studio Code.

---

## 📋 Table of Contents

1. [Prerequisites](#prerequisites)
2. [VS Code Extensions](#vs-code-extensions)
3. [Project Setup](#project-setup)
4. [Building the Application](#building-the-application)
5. [Creating Windows Executable](#creating-windows-executable)
6. [Running and Debugging](#running-and-debugging)
7. [Troubleshooting](#troubleshooting)

---

## 1. Prerequisites

### Required Software

1. **Java Development Kit (JDK) 11 or higher**
   - Download from: https://www.oracle.com/java/technologies/downloads/
   - Or use OpenJDK: https://adoptium.net/
   - Verify installation: `java -version` and `javac -version`

2. **Apache Maven 3.6+**
   - Download from: https://maven.apache.org/download.cgi
   - Add to PATH environment variable
   - Verify installation: `mvn -version`

3. **Visual Studio Code**
   - Download from: https://code.visualstudio.com/
   - Latest stable version recommended

4. **Git** (Optional but recommended)
   - Download from: https://git-scm.com/

### Optional Software (for creating .exe)

5. **Launch4j** (Recommended for Windows executable)
   - Download from: https://launch4j.sourceforge.net/
   - Install to default location: `C:\Program Files (x86)\Launch4j`

6. **jpackage** (Alternative, requires JDK 14+)
   - Included in JDK 14 and higher
   - No separate installation needed

---

## 2. VS Code Extensions

### Required Extensions

The project includes a `.vscode/extensions.json` file that recommends the following extensions. VS Code will prompt you to install them automatically.

**Core Java Extensions:**

1. **Extension Pack for Java** (`vscjava.vscode-java-pack`)
   - Includes: Language Support, Debugger, Test Runner, Maven, Project Manager
   - Install from: VS Code Extensions Marketplace

2. **Language Support for Java™ by Red Hat** (`redhat.java`)
   - Provides Java language support
   - Automatically included in Java Extension Pack

3. **Debugger for Java** (`vscjava.vscode-java-debug`)
   - Java debugging support
   - Automatically included in Java Extension Pack

4. **Maven for Java** (`vscjava.vscode-maven`)
   - Maven project support
   - Automatically included in Java Extension Pack

### Installation Steps

**Method 1: Automatic (Recommended)**
1. Open the AZOR_POS folder in VS Code
2. VS Code will show a notification: "This workspace has extension recommendations"
3. Click "Install All" to install recommended extensions

**Method 2: Manual**
1. Open VS Code
2. Press `Ctrl+Shift+X` to open Extensions view
3. Search for "Extension Pack for Java"
4. Click "Install"

### Verify Installation

1. Press `Ctrl+Shift+P` to open Command Palette
2. Type "Java: Configure Java Runtime"
3. Verify that JDK 11+ is detected

---

## 3. Project Setup

### Opening the Project

1. Launch VS Code
2. File → Open Folder
3. Select the `Azor_POS` directory
4. VS Code will automatically detect the Java project

### Project Structure

```
Azor_POS/
├── .vscode/              # VS Code configuration
│   ├── settings.json     # Project settings
│   ├── launch.json       # Debug configurations
│   └── extensions.json   # Recommended extensions
├── src-pos/              # Main source code
├── src-beans/            # UI beans and resources
├── src-data/             # Data layer
├── lib/                  # Dependencies (JAR files)
├── locales/              # Language files (including Arabic)
├── images/               # Application images
├── fonts/                # Custom fonts
├── cssStyles/            # CSS stylesheets
├── iconsets/             # Icon sets
├── scripts/              # Build and launcher scripts
├── pom.xml               # Maven build configuration
├── assembly.xml          # Distribution assembly config
└── launch4j-config.xml   # Launch4j executable config
```

### Configuration Files

The following configuration files have been created:

1. **`.vscode/settings.json`** - Project settings
   - Source paths: `src-pos`, `src-beans`, `src-data`
   - Output path: `bin`
   - Referenced libraries: `lib/**/*.jar`

2. **`.vscode/launch.json`** - Debug configurations
   - "Launch AZOR POS" - Normal mode
   - "Launch AZOR POS (Debug Mode)" - With debug logging

3. **`pom.xml`** - Maven build configuration
   - Artifact ID: `azorpos`
   - Name: `AZOR POS`
   - Version: `1.5.5`

---

## 4. Building the Application

### Method 1: Using VS Code (Recommended)

1. **Open Maven view:**
   - Click on the Maven icon in the left sidebar (or press `Ctrl+Shift+P` and type "Maven")

2. **Run Maven goals:**
   - Expand "AZOR_POS" → "Lifecycle"
   - Right-click "clean" → Run
   - Right-click "package" → Run

3. **View build output:**
   - Output will be in `target/` directory
   - Main JAR: `target/azorpos.jar`
   - Distribution: `target/AZOR_POS_1.5.5/`

### Method 2: Using Command Line

**Windows:**
```batch
cd E:\Azor_POS
scripts\build.bat
```

**Manual Maven commands:**
```batch
mvn clean
mvn package
```

### Build Output

After successful build:
```
target/
├── azorpos.jar                    # Main application JAR
├── lib/                           # Copied dependencies
├── AZOR_POS_1.5.5/               # Distribution folder
│   ├── azorpos.jar
│   ├── lib/
│   ├── locales/
│   ├── images/
│   ├── fonts/
│   ├── cssStyles/
│   ├── iconsets/
│   ├── licensing/
│   └── *.wav
└── AZOR_POS_1.5.5.zip            # Distribution ZIP
```

---

## 5. Creating Windows Executable

### Option 1: Using Launch4j (Recommended)

**Prerequisites:**
- Install Launch4j from https://launch4j.sourceforge.net/
- Build the application first (see section 4)

**Steps:**

1. **Convert icon (if needed):**
   - Convert `src-beans/uk/chromis/fixedimages/azoricon.png` to `azoricon.ico`
   - Use online tool: https://convertio.co/png-ico/
   - Or use ImageMagick: `convert azoricon.png -define icon:auto-resize=256,128,64,48,32,16 azoricon.ico`
   - Place `azoricon.ico` in project root

2. **Run packaging script:**
   ```batch
   cd E:\Azor_POS
   scripts\package-launch4j.bat
   ```

3. **Output location:**
   ```
   target/AZOR_POS_Distribution/
   ├── AZOR_POS.exe          ← Windows executable
   ├── azorpos.jar
   ├── lib/
   ├── locales/
   ├── images/
   ├── fonts/
   ├── cssStyles/
   ├── iconsets/
   ├── jre/                  (if bundled)
   └── README.txt
   ```

### Option 2: Using jpackage (JDK 14+)

**Prerequisites:**
- JDK 14 or higher installed
- Build the application first

**Steps:**

1. **Run packaging script:**
   ```batch
   cd E:\Azor_POS
   scripts\package-exe.bat
   ```

2. **Output location:**
   ```
   target/installer/AZOR POS/
   ├── AZOR POS.exe
   ├── app/
   ├── runtime/
   └── (all resources)
   ```

---

## 6. Running and Debugging

### Running from VS Code

**Method 1: Using Debug Panel**
1. Press `F5` or click Run → Start Debugging
2. Select "Launch AZOR POS" from the dropdown
3. Application will start

**Method 2: Using Command Palette**
1. Press `Ctrl+Shift+P`
2. Type "Java: Run Java"
3. Select `StartPOS` class

### Debug Mode

1. Set breakpoints by clicking left of line numbers
2. Press `F5` to start debugging
3. Use debug toolbar to step through code:
   - `F10` - Step Over
   - `F11` - Step Into
   - `Shift+F11` - Step Out
   - `F5` - Continue

### Running from Command Line

**Using JAR:**
```batch
cd E:\Azor_POS
java -cp "chromispos.jar;lib/*" uk.chromis.pos.forms.StartPOS
```

**Using built distribution:**
```batch
cd E:\Azor_POS\target\AZOR_POS_Distribution
AZOR_POS.exe
```

---

## 7. Troubleshooting

### Common Issues

**Issue 1: "Java runtime could not be located"**

**Solution:**
- Install JDK 11 or higher
- Set JAVA_HOME environment variable
- Add Java bin directory to PATH

**Issue 2: "Maven not found"**

**Solution:**
- Install Apache Maven
- Add Maven bin directory to PATH
- Restart VS Code

**Issue 3: "Cannot find symbol" errors**

**Solution:**
- Clean and rebuild: `mvn clean compile`
- Reload VS Code window: `Ctrl+Shift+P` → "Reload Window"
- Check that all JARs in `lib/` are included

**Issue 4: "Class not found: uk.chromis.pos.forms.StartPOS"**

**Solution:**
- Ensure `src-pos` is in source path
- Check `.vscode/settings.json` has correct source paths
- Rebuild project

**Issue 5: Launch4j fails**

**Solution:**
- Verify Launch4j is installed
- Check `launch4j-config.xml` paths are correct
- Ensure `azoricon.ico` exists (or remove icon line from config)

**Issue 6: Application starts but shows errors**

**Solution:**
- Check `chromisposconfig.properties` exists and is configured
- Verify database is running and accessible
- Check logs in `logs/` folder (if running in debug mode)

### Getting Help

- Check application logs: `logs/POSstd.log` and `logs/POSerror.log`
- Review Maven build output for errors
- Verify all dependencies in `lib/` folder are present

---

## 📝 Quick Reference

### Essential Commands

| Task | Command |
|------|---------|
| Clean build | `mvn clean` |
| Compile | `mvn compile` |
| Package | `mvn package` |
| Full build | `mvn clean package` |
| Run from JAR | `java -cp "chromispos.jar;lib/*" uk.chromis.pos.forms.StartPOS` |

### File Locations

| Item | Location |
|------|----------|
| Main JAR | `target/azorpos.jar` |
| Distribution | `target/AZOR_POS_1.5.5/` |
| Executable (Launch4j) | `target/AZOR_POS_Distribution/AZOR_POS.exe` |
| Logs | `logs/` |
| Configuration | `chromisposconfig.properties` |

---

## ✅ Setup Checklist

- [ ] JDK 11+ installed and in PATH
- [ ] Maven installed and in PATH
- [ ] VS Code installed
- [ ] Java Extension Pack installed in VS Code
- [ ] Project opened in VS Code
- [ ] Maven build successful (`mvn clean package`)
- [ ] Application runs from VS Code (F5)
- [ ] (Optional) Launch4j installed
- [ ] (Optional) Windows executable created
- [ ] (Optional) Distribution folder tested

---

**Next Steps:**
- See `BUILD_AND_PACKAGE_GUIDE.md` for detailed packaging instructions
- See `DEPLOYMENT_GUIDE.md` for deployment procedures

**Support:**
- For build issues, check Maven output
- For runtime issues, check application logs
- For VS Code issues, check Java extension logs

