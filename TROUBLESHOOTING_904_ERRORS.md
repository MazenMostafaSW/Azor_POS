# Fixing 904 Problems in VS Code

**Issue:** VS Code shows 904 problems/errors in the AZOR POS project

**Root Cause:** The project hasn't been properly configured for VS Code's Java extension yet.

---

## 🔧 Quick Fix (5 Steps)

### Step 1: Install Required Software

**Check what you have:**
```batch
java -version    # Need JDK 11+
mvn -version     # Need Maven 3.6+
```

**If missing, install:**

1. **JDK 11 or higher:**
   - Download: https://adoptium.net/temurin/releases/
   - Choose: Java 11 (LTS), Windows x64, JDK
   - Install and add to PATH

2. **Apache Maven:**
   - Download: https://maven.apache.org/download.cgi
   - Extract to `C:\Program Files\Apache\maven`
   - Add `C:\Program Files\Apache\maven\bin` to PATH
   - Restart terminal/VS Code

**Verify installation:**
```batch
java -version
javac -version
mvn -version
```

---

### Step 2: Install VS Code Java Extensions

**Option A: Automatic (Recommended)**
1. VS Code should show: "This workspace has extension recommendations"
2. Click **"Install All"**

**Option B: Manual**
1. Press `Ctrl+Shift+X` (Extensions view)
2. Search: "Extension Pack for Java"
3. Click **Install**
4. Wait for installation to complete

**Required Extensions:**
- ✅ Extension Pack for Java
- ✅ Language Support for Java (Red Hat)
- ✅ Debugger for Java
- ✅ Maven for Java

---

### Step 3: Configure Java Runtime in VS Code

1. Press `Ctrl+Shift+P` (Command Palette)
2. Type: **"Java: Configure Java Runtime"**
3. Verify JDK 11+ is detected
4. If not detected:
   - Click "Edit in settings.json"
   - Add:
   ```json
   "java.configuration.runtimes": [
     {
       "name": "JavaSE-11",
       "path": "C:\\Program Files\\Java\\jdk-11",
       "default": true
     }
   ]
   ```

---

### Step 4: Clean and Reload VS Code

1. Close VS Code completely
2. Delete these folders if they exist:
   ```batch
   rmdir /s /q .vscode\java
   rmdir /s /q bin
   ```
3. Reopen VS Code
4. Open the `Azor_POS` folder
5. Wait for Java extension to initialize (check bottom-right status bar)

---

### Step 5: Let Java Extension Build the Project

**Wait for automatic build:**
- Look at bottom-right corner of VS Code
- You'll see: "Building workspace..." or "Importing projects..."
- This may take 2-5 minutes
- Don't interrupt this process

**If errors persist after 5 minutes:**

1. Press `Ctrl+Shift+P`
2. Type: **"Java: Clean Java Language Server Workspace"**
3. Click **"Reload and delete"**
4. Wait for rebuild

---

## 🔍 Alternative: Use Maven to Compile First

If VS Code is still having issues, compile with Maven first:

```batch
cd E:\Azor_POS

# Clean previous build
mvn clean

# Compile (don't package yet)
mvn compile
```

This will:
- Download all Maven dependencies
- Compile all Java files
- Create `target/classes` folder
- Help VS Code understand the project

**Then in VS Code:**
1. Press `Ctrl+Shift+P`
2. Type: **"Java: Reload Projects"**
3. Wait for reload to complete

---

## 📊 Understanding the Errors

The 904 errors are likely:

1. **Missing dependencies** (40+ JAR files in `lib/`)
   - VS Code doesn't know about them yet
   - Maven will resolve this

2. **Uncompiled source files** (509 Java files)
   - VS Code needs to compile them
   - Java extension will do this automatically

3. **Missing generated classes**
   - Some classes may be generated during build
   - Maven compile will create them

---

## ✅ Verification Steps

After following the steps above, verify:

### 1. Check Java Extension Status

Look at bottom-right corner of VS Code:
- ✅ Should show: "Java: Ready" or similar
- ❌ If shows errors, click to see details

### 2. Check Problems Panel

1. Press `Ctrl+Shift+M` (Problems panel)
2. Errors should be reducing
3. Final count should be 0 or very low (< 10)

### 3. Check Project Structure

1. Press `Ctrl+Shift+P`
2. Type: **"Java: Show Project Explorer"**
3. Verify you see:
   - ✅ AZOR_POS project
   - ✅ Referenced Libraries (40+ JARs)
   - ✅ Source folders (src-pos, src-beans, src-data)

### 4. Test Compilation

1. Open any Java file (e.g., `src-pos/uk/chromis/pos/forms/StartPOS.java`)
2. Make a small change (add a space)
3. Save (`Ctrl+S`)
4. Check if errors appear or disappear

---

## 🐛 Common Issues and Solutions

### Issue 1: "Maven not found"

**Solution:**
```batch
# Install Maven
# Download from: https://maven.apache.org/download.cgi
# Extract to: C:\Program Files\Apache\maven
# Add to PATH: C:\Program Files\Apache\maven\bin

# Verify
mvn -version
```

### Issue 2: "Java extension not working"

**Solution:**
1. Uninstall all Java extensions
2. Restart VS Code
3. Install "Extension Pack for Java" only
4. Restart VS Code again

### Issue 3: "Still 904 errors after waiting"

**Solution:**
```batch
# Force Maven compile
cd E:\Azor_POS
mvn clean compile

# Then in VS Code:
# Ctrl+Shift+P → "Java: Clean Java Language Server Workspace"
# Choose "Reload and delete"
```

### Issue 4: "Out of memory errors"

**Solution:**
Add to `.vscode/settings.json`:
```json
{
  "java.jdt.ls.vmargs": "-Xmx2G"
}
```

### Issue 5: "Cannot find symbol errors"

**Solution:**
The `lib/` JARs aren't being recognized. Check `.vscode/settings.json`:
```json
{
  "java.project.referencedLibraries": [
    "lib/**/*.jar"
  ]
}
```

---

## 🚀 Expected Timeline

| Step | Time | Status |
|------|------|--------|
| Install JDK | 5 min | Manual |
| Install Maven | 5 min | Manual |
| Install VS Code extensions | 2 min | Automatic |
| Java extension initialization | 2-5 min | Automatic |
| First build | 2-5 min | Automatic |
| **Total** | **15-20 min** | |

---

## 📝 Step-by-Step Checklist

- [ ] JDK 11+ installed and in PATH
- [ ] Maven 3.6+ installed and in PATH
- [ ] VS Code Java extensions installed
- [ ] Java runtime configured in VS Code
- [ ] VS Code reloaded
- [ ] Java extension shows "Ready"
- [ ] Maven compile successful (optional)
- [ ] Problems count reduced to 0 or near 0
- [ ] Can open Java files without errors
- [ ] Can run application with F5

---

## 🎯 Final Verification

**Test that everything works:**

1. **Open a Java file:**
   ```
   src-pos/uk/chromis/pos/forms/StartPOS.java
   ```

2. **Check IntelliSense:**
   - Type `System.` and see if autocomplete works
   - Hover over a class name - should show documentation

3. **Try to run:**
   - Press `F5`
   - Select "Launch AZOR POS"
   - Should start (may fail if database not configured, but should compile)

4. **Check Problems:**
   - Press `Ctrl+Shift+M`
   - Should see 0 errors (or only configuration-related warnings)

---

## 📞 Still Having Issues?

If you still have 904 errors after following all steps:

1. **Check Java Extension Output:**
   - View → Output
   - Select "Language Support for Java" from dropdown
   - Look for error messages

2. **Check Maven Output:**
   ```batch
   cd E:\Azor_POS
   mvn clean compile > build.log 2>&1
   type build.log
   ```

3. **Provide Details:**
   - Java version: `java -version`
   - Maven version: `mvn -version`
   - VS Code version: Help → About
   - Extensions installed: List Java-related extensions
   - Error samples: Copy 5-10 error messages from Problems panel

---

## ✨ Success Indicators

You'll know it's working when:

- ✅ Bottom-right shows "Java: Ready"
- ✅ Problems panel shows 0 errors
- ✅ IntelliSense works (autocomplete, hover documentation)
- ✅ Can navigate to definitions (Ctrl+Click on class names)
- ✅ Can run with F5
- ✅ Referenced Libraries shows 40+ JARs

---

**Most Common Solution:**

90% of the time, the issue is resolved by:
1. Installing Maven
2. Running `mvn clean compile`
3. Reloading VS Code (`Ctrl+Shift+P` → "Reload Window")

**Try this first!**

