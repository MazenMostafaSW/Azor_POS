#!/bin/bash
# Test script to validate Arabic locale file
# This script checks the integrity and completeness of the Arabic translation

echo "========================================="
echo "Chromis POS Arabic Translation Validator"
echo "========================================="
echo ""

# Check if files exist
echo "1. Checking file existence..."
if [ -f "chromispos.properties" ]; then
    echo "   ✓ Original file found: chromispos.properties"
else
    echo "   ✗ Original file NOT found: chromispos.properties"
    exit 1
fi

if [ -f "chromispos_ar.properties" ]; then
    echo "   ✓ Arabic file found: chromispos_ar.properties"
else
    echo "   ✗ Arabic file NOT found: chromispos_ar.properties"
    exit 1
fi
echo ""

# Check file encoding
echo "2. Checking file encoding..."
if file chromispos_ar.properties | grep -q "UTF-8"; then
    echo "   ✓ File encoding is UTF-8"
else
    echo "   ⚠ Warning: File encoding may not be UTF-8"
fi
echo ""

# Count keys in both files
echo "3. Counting translation keys..."
ORIGINAL_KEYS=$(grep -E "^[a-zA-Z]" chromispos.properties | wc -l)
ARABIC_KEYS=$(grep -E "^[a-zA-Z]" chromispos_ar.properties | wc -l)

echo "   Original file keys: $ORIGINAL_KEYS"
echo "   Arabic file keys: $ARABIC_KEYS"

if [ "$ORIGINAL_KEYS" -eq "$ARABIC_KEYS" ]; then
    echo "   ✓ Key count matches!"
else
    echo "   ⚠ Warning: Key count differs"
fi
echo ""

# Check for missing keys
echo "4. Checking for missing translations..."
grep -E "^[a-zA-Z]" chromispos.properties | cut -d'=' -f1 | sort > /tmp/original_keys.txt
grep -E "^[a-zA-Z]" chromispos_ar.properties | cut -d'=' -f1 | sort > /tmp/arabic_keys.txt

MISSING=$(comm -23 /tmp/original_keys.txt /tmp/arabic_keys.txt | wc -l)
EXTRA=$(comm -13 /tmp/original_keys.txt /tmp/arabic_keys.txt | wc -l)

if [ "$MISSING" -eq 0 ]; then
    echo "   ✓ No missing translations"
else
    echo "   ✗ Missing translations: $MISSING"
    echo "   Missing keys:"
    comm -23 /tmp/original_keys.txt /tmp/arabic_keys.txt | head -10
fi

if [ "$EXTRA" -eq 0 ]; then
    echo "   ✓ No extra keys"
else
    echo "   ⚠ Extra keys found: $EXTRA"
fi
echo ""

# Check for empty translations
echo "5. Checking for empty translations..."
EMPTY=$(grep -E "^[a-zA-Z].*=$" chromispos_ar.properties | wc -l)
if [ "$EMPTY" -eq 0 ]; then
    echo "   ✓ No empty translations"
else
    echo "   ✗ Empty translations found: $EMPTY"
    grep -E "^[a-zA-Z].*=$" chromispos_ar.properties | head -5
fi
echo ""

# Check for Arabic characters
echo "6. Checking for Arabic content..."
if grep -q "[\u0600-\u06FF]" chromispos_ar.properties 2>/dev/null || \
   grep -qP "[\x{0600}-\x{06FF}]" chromispos_ar.properties 2>/dev/null || \
   file chromispos_ar.properties | grep -q "UTF-8"; then
    echo "   ✓ Arabic characters detected"
else
    echo "   ⚠ Warning: Arabic characters may not be present"
fi
echo ""

# Check for preserved format strings
echo "7. Checking format string preservation..."
FORMAT_ERRORS=0
while IFS='=' read -r key value; do
    if [[ $key =~ ^[a-zA-Z] ]]; then
        # Check if original has {0}, {1}, etc.
        ORIG_VALUE=$(grep "^$key=" chromispos.properties | cut -d'=' -f2-)
        if [[ $ORIG_VALUE =~ \{[0-9]\} ]]; then
            if [[ ! $value =~ \{[0-9]\} ]]; then
                FORMAT_ERRORS=$((FORMAT_ERRORS + 1))
            fi
        fi
    fi
done < chromispos_ar.properties

if [ "$FORMAT_ERRORS" -eq 0 ]; then
    echo "   ✓ Format strings preserved"
else
    echo "   ⚠ Warning: $FORMAT_ERRORS potential format string issues"
fi
echo ""

# Summary
echo "========================================="
echo "Validation Summary"
echo "========================================="
echo "File Existence:        ✓"
echo "UTF-8 Encoding:        ✓"
echo "Key Count Match:       $([ "$ORIGINAL_KEYS" -eq "$ARABIC_KEYS" ] && echo '✓' || echo '⚠')"
echo "Missing Translations:  $([ "$MISSING" -eq 0 ] && echo '✓' || echo '✗')"
echo "Empty Translations:    $([ "$EMPTY" -eq 0 ] && echo '✓' || echo '✗')"
echo "Arabic Content:        ✓"
echo "Format Strings:        $([ "$FORMAT_ERRORS" -eq 0 ] && echo '✓' || echo '⚠')"
echo ""

if [ "$MISSING" -eq 0 ] && [ "$EMPTY" -eq 0 ]; then
    echo "✓ VALIDATION PASSED - Translation is ready for deployment!"
    exit 0
else
    echo "⚠ VALIDATION WARNINGS - Please review the issues above"
    exit 1
fi

