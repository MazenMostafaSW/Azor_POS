# AZOR POS - Deployment Guide

**Developed by Mazen Mostafa**

Complete guide for deploying AZOR POS to end users.

---

## 📋 Deployment Overview

This guide covers:
1. System requirements
2. Pre-deployment checklist
3. Installation procedures
4. Configuration
5. Post-deployment verification

---

## 💻 System Requirements

### Minimum Requirements

| Component | Requirement |
|-----------|-------------|
| **Operating System** | Windows 7 SP1 or higher (64-bit) |
| **Processor** | Intel Core i3 or equivalent |
| **RAM** | 4 GB |
| **Disk Space** | 500 MB (application + data) |
| **Display** | 1024x768 resolution |
| **Java** | JRE 11+ (or use bundled JRE) |
| **Database** | MySQL 5.7+ or MariaDB 10.2+ |
| **Network** | Required for database connection |

### Recommended Requirements

| Component | Requirement |
|-----------|-------------|
| **Operating System** | Windows 10/11 (64-bit) |
| **Processor** | Intel Core i5 or higher |
| **RAM** | 8 GB or more |
| **Disk Space** | 1 GB |
| **Display** | 1920x1080 resolution |
| **Java** | Bundled JRE (included) |
| **Database** | MySQL 8.0+ |
| **Network** | Gigabit Ethernet |

---

## ✅ Pre-Deployment Checklist

### 1. Database Setup

- [ ] MySQL/MariaDB server installed and running
- [ ] Database created (e.g., `azorpos_db`)
- [ ] Database user created with appropriate permissions
- [ ] Database schema initialized
- [ ] Test connection from client machine

### 2. Application Package

- [ ] AZOR_POS distribution folder ready
- [ ] All files present (see BUILD_AND_PACKAGE_GUIDE.md)
- [ ] Bundled JRE included (recommended)
- [ ] Configuration template prepared

### 3. Client Machine

- [ ] Windows 7 SP1 or higher
- [ ] Sufficient disk space (500MB+)
- [ ] Network connectivity to database server
- [ ] User has appropriate permissions
- [ ] Antivirus exclusions configured (if needed)

---

## 📦 Installation Procedures

### Method 1: Simple Copy Deployment (Recommended)

**Step 1: Copy Files**

1. Copy the entire `AZOR_POS_Distribution` folder to the target machine
2. Recommended location: `C:\Program Files\AZOR_POS\`

```batch
# Example:
xcopy /E /I "\\server\share\AZOR_POS_Distribution" "C:\Program Files\AZOR_POS\"
```

**Step 2: Configure Database Connection**

1. Navigate to installation folder
2. Rename `chromisposconfig.properties.template` to `chromisposconfig.properties`
3. Edit with Notepad:

```properties
# Database Configuration
database.user = azoruser
database.password = crypt:YOUR_ENCRYPTED_PASSWORD
database.server = localhost
database.port = 3306
database.name = azorpos_db
database.library = /lib/mysql-connector-java-8.0.23.jar
database.class = com.mysql.cj.jdbc.Driver

# Terminal ID (unique for each POS terminal)
terminalID = POS-001
```

**Step 3: Create Desktop Shortcut**

1. Right-click on `AZOR_POS.exe`
2. Select "Send to" → "Desktop (create shortcut)"
3. Rename shortcut to "AZOR POS"

**Step 4: First Run**

1. Double-click `AZOR_POS.exe`
2. Application should start and show splash screen
3. Verify:
   - Splash shows "AZOR POS" logo
   - Developer credit: "Developed by Mazen Mostafa"
   - Database connection successful
   - Login screen appears

### Method 2: Network Deployment

For deploying to multiple machines:

**Step 1: Prepare Network Share**

```batch
# On server:
mkdir \\server\AZOR_POS_Deploy
xcopy /E /I "AZOR_POS_Distribution" "\\server\AZOR_POS_Deploy\"
```

**Step 2: Create Deployment Script**

Save as `deploy.bat`:

```batch
@echo off
echo Installing AZOR POS...

# Create installation directory
mkdir "C:\Program Files\AZOR_POS"

# Copy files
xcopy /E /I /Y "\\server\AZOR_POS_Deploy\*" "C:\Program Files\AZOR_POS\"

# Copy pre-configured settings
copy /Y "\\server\AZOR_POS_Deploy\configs\%COMPUTERNAME%.properties" "C:\Program Files\AZOR_POS\chromisposconfig.properties"

# Create desktop shortcut
powershell "$s=(New-Object -COM WScript.Shell).CreateShortcut('%userprofile%\Desktop\AZOR POS.lnk');$s.TargetPath='C:\Program Files\AZOR_POS\AZOR_POS.exe';$s.Save()"

echo Installation complete!
pause
```

**Step 3: Run on Each Client**

```batch
# Run as Administrator
\\server\AZOR_POS_Deploy\deploy.bat
```

---

## ⚙️ Configuration

### Database Configuration

**File:** `chromisposconfig.properties`

**Key Settings:**

```properties
# Database Connection
database.user = azoruser
database.password = crypt:0B1F6EBBC0C0D5F5
database.server = 192.168.1.100
database.port = 3306
database.name = azorpos_db

# Terminal Configuration
terminalID = POS-001

# Display Settings
POSwidth = 1024
POSheight = 768

# Receipt Printer
paper.receipt.x = 10
paper.receipt.y = 10
paper.receipt.width = 190
paper.receipt.height = 546
```

### Terminal Registration

Each POS terminal must be registered:

1. Run `TerminalConfig.exe` (if available) or configure manually
2. Set unique `terminalID` for each terminal
3. Configure terminal-specific settings

### Language Configuration

**For Arabic:**

1. The application will auto-detect system locale
2. Or manually set in configuration
3. Arabic translation is in `locales/chromispos_ar.properties`
4. RTL layout is automatically applied

---

## 🔍 Post-Deployment Verification

### Verification Checklist

**1. Application Startup**
- [ ] Application starts without errors
- [ ] Splash screen displays correctly
- [ ] AZOR POS branding visible
- [ ] Developer credit shown: "Developed by Mazen Mostafa"

**2. Database Connection**
- [ ] Database connection successful
- [ ] No connection errors in logs
- [ ] Terminal ID recognized

**3. User Interface**
- [ ] Login screen appears
- [ ] Images and icons load correctly
- [ ] Fonts display properly
- [ ] Colors and themes work

**4. Functionality**
- [ ] User can log in
- [ ] Main POS screen loads
- [ ] Products can be scanned/selected
- [ ] Transactions can be processed
- [ ] Receipts can be printed

**5. Arabic Support (if applicable)**
- [ ] Arabic language loads
- [ ] RTL layout applied correctly
- [ ] Arabic text displays properly
- [ ] All UI elements mirrored

**6. Resources**
- [ ] Sound effects work (beep.wav, error.wav)
- [ ] Icon sets load
- [ ] Custom fonts display
- [ ] CSS styles applied

### Testing Procedure

**Test 1: Basic Startup**
```batch
1. Double-click AZOR_POS.exe
2. Observe splash screen (should show AZOR branding)
3. Wait for login screen
4. Verify no error messages
```

**Test 2: Database Connection**
```batch
1. Check logs folder for errors
2. Verify terminal ID is recognized
3. Test login with valid credentials
```

**Test 3: Transaction Flow**
```batch
1. Log in as cashier
2. Start new sale
3. Add products
4. Process payment
5. Print receipt
6. Verify transaction saved to database
```

---

## 🐛 Troubleshooting

### Common Issues

**Issue 1: "Java not found" error**

**Symptoms:**
- Application won't start
- Error message about Java

**Solutions:**
1. If bundled JRE included: Check `jre\bin\java.exe` exists
2. If using system Java: Install JRE 11+ from https://adoptium.net/
3. Verify JAVA_HOME environment variable

**Issue 2: Database connection failed**

**Symptoms:**
- Error on startup
- "Cannot connect to database"

**Solutions:**
1. Verify database server is running
2. Check `chromisposconfig.properties`:
   - Correct server IP/hostname
   - Correct port (default 3306)
   - Valid username/password
   - Database name exists
3. Test connection: `mysql -h SERVER -u USER -p DATABASE`
4. Check firewall allows MySQL port

**Issue 3: Terminal not registered**

**Symptoms:**
- "This device has not been setup as a POS terminal"

**Solutions:**
1. Run TerminalConfig utility
2. Or manually add to Windows Registry:
   ```
   HKEY_LOCAL_MACHINE\SOFTWARE\chromis
   $1308823401 = [unique_terminal_id]
   posname = [terminal_name]
   ```

**Issue 4: Images/Icons not loading**

**Symptoms:**
- Blank images
- Missing icons
- Error in logs

**Solutions:**
1. Verify `images/` folder exists
2. Verify `iconsets/*.zip` files present
3. Check file permissions
4. Review logs for specific file errors

**Issue 5: Arabic not displaying correctly**

**Symptoms:**
- Arabic text shows as boxes
- RTL not applied

**Solutions:**
1. Verify `locales/chromispos_ar.properties` exists
2. Check system supports Arabic fonts
3. Verify RTL implementation (see RTL_TESTING_GUIDE.md)

---

## 📊 Deployment Scenarios

### Scenario 1: Single Terminal

**Setup:**
- 1 POS terminal
- Local or remote database

**Steps:**
1. Install AZOR POS on terminal
2. Configure database connection
3. Register terminal
4. Test and verify

**Time:** 15-30 minutes

### Scenario 2: Multiple Terminals (Same Location)

**Setup:**
- Multiple POS terminals
- Shared database server
- Local network

**Steps:**
1. Set up database server
2. Deploy AZOR POS to each terminal
3. Configure each with unique terminal ID
4. Test all terminals

**Time:** 1-2 hours

### Scenario 3: Multiple Locations

**Setup:**
- Multiple locations
- Centralized or distributed databases
- VPN or cloud connectivity

**Steps:**
1. Set up database infrastructure
2. Prepare deployment package
3. Deploy to each location
4. Configure network connectivity
5. Test and verify all locations

**Time:** Varies by scale

---

## 📝 Deployment Checklist

### Pre-Deployment
- [ ] Database server ready
- [ ] Network connectivity verified
- [ ] Application package prepared
- [ ] Configuration files ready
- [ ] User accounts created

### During Deployment
- [ ] Files copied to installation directory
- [ ] Configuration file customized
- [ ] Desktop shortcut created
- [ ] Terminal registered
- [ ] Initial test successful

### Post-Deployment
- [ ] Full functionality tested
- [ ] User training completed
- [ ] Documentation provided
- [ ] Support contact information shared
- [ ] Backup procedures established

---

## 🎓 User Training

### Essential Topics

1. **Application Startup**
   - How to launch AZOR POS
   - Understanding the splash screen
   - Login procedures

2. **Basic Operations**
   - Processing sales
   - Handling returns
   - Managing inventory
   - Running reports

3. **Arabic Interface** (if applicable)
   - Switching languages
   - RTL navigation
   - Arabic input

4. **Troubleshooting**
   - Common errors
   - When to contact support
   - Log file locations

---

## 📞 Support

### Support Resources

- **Documentation:** See all `*.md` files in installation directory
- **Logs:** Check `logs/` folder for error details
- **Configuration:** Review `chromisposconfig.properties`

### Contact Information

- **Developer:** Mazen Mostafa
- **Based on:** Chromis POS - https://www.chromis.co.uk

---

**Deployment Status:** Ready for production! 🚀

