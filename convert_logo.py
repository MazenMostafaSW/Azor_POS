#!/usr/bin/env python3
"""
Script to convert the AZOR POS logo to various required formats
"""

from PIL import Image
import os

def convert_logo():
    """Convert logo.jpeg to various PNG formats needed by the application"""
    
    # Source logo
    source_logo = "logo.jpeg"
    dest_dir = "src-beans/uk/chromis/fixedimages"
    
    if not os.path.exists(source_logo):
        print(f"Error: {source_logo} not found!")
        return False
    
    try:
        # Load the source image
        img = Image.open(source_logo)
        print(f"Loaded {source_logo}: {img.size} pixels, mode: {img.mode}")
        
        # Convert to RGBA if needed (for transparency support)
        if img.mode != 'RGBA':
            img = img.convert('RGBA')
        
        # Define the logo variants needed
        logo_variants = {
            'azorlogo.png': (400, 157),           # Main vertical logo for splash
            'azorlogo_small.png': (300, 100),     # Small logo for dialogs
            'azoricon.png': (64, 64),             # Window icon
            'azorlogo_main.png': (500, 200),      # Main logo
            'azorlogo_main_med.png': (400, 160),  # Medium main logo
        }
        
        for filename, size in logo_variants.items():
            # Create a copy and resize
            resized = img.copy()
            resized.thumbnail(size, Image.Resampling.LANCZOS)
            
            # Create a new image with white background
            new_img = Image.new('RGBA', size, (255, 255, 255, 255))
            
            # Calculate position to center the logo
            x = (size[0] - resized.size[0]) // 2
            y = (size[1] - resized.size[1]) // 2
            
            # Paste the resized logo onto the white background
            new_img.paste(resized, (x, y), resized if resized.mode == 'RGBA' else None)
            
            # Save as PNG
            output_path = os.path.join(dest_dir, filename)
            new_img.save(output_path, 'PNG')
            print(f"Created {output_path} ({size[0]}x{size[1]})")
        
        print("\nAll logo variants created successfully!")
        return True
        
    except Exception as e:
        print(f"Error converting logo: {e}")
        import traceback
        traceback.print_exc()
        return False

if __name__ == "__main__":
    success = convert_logo()
    exit(0 if success else 1)

