from zipfile import ZipFile 
import os 
  
def compress(directory): 
  
    # initializing empty file paths list 
    with ZipFile('my_python_files.zip','w') as zip: 
    # crawling through directory and subdirectories 
        for root, directories, files in os.walk(directory): 
            for filename in files: 
                # join the two strings in order to form the full filepath. 
                filepath = os.path.join(root, filename)
                print(filepath)
                zip.write(os.path.relpath(filepath))
                
                
    
    
def main(): 
    # path to folder which needs to be zipped 
    directory = 'Z:\Desktop\Stuff\Code\Python'
    compress(directory) 
    print('All files zipped successfully!')         
  
  
if __name__ == "__main__": 
    main()