from zipfile import ZipFile 
import os 
  
def compress(directory, zip): 
    for root, directories, files in os.walk(directory):
        for filename in files: 
            filepath = os.path.join(root, filename)
            print(filepath)
            zip.write(filepath, arcname = os.path.relpath(filepath, directory))
                
                
    
    
def main(): 
    directory = 'Z:\Desktop\Stuff\Code\C-C++'
    print("Following files are getting zipped")
    with ZipFile(f'{os.path.basename(directory)}.zip','w') as zip:
        compress(directory, zip) 
    print('All files zipped successfully!')         
  
  
if __name__ == "__main__": 
    main()