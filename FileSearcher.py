'''
Created on Mar 23, 2016
@author: Michael Holloway
'''
#this program searchers through files and finds the occurrence of a word entered by the user
#it then prints out the line number of each occurrence of that word
#import the os functions so we can is .walk and .path.join, etc
import os
#making a class to search the stuff with, this is for my convenience can be done as a function
class searcher:
    def __init__(self,rootDir,searchedPhrase,searchedPhraseInFile):
        #initialize the values of the class
        self.rootDir = rootDir
        self.searchedPhrase = searchedPhrase
        self.searchedPhraseInFile = searchedPhraseInFile
    def search(self):
        #this will be the file where all the logs are stored
        logname = 'findfiletype.txt'
        # in find file type.txt :D
        result = str()
        #store the result as a string 
        instances = 0 #number os instances of the file name
        linNum = 0    #number of instances of the string in the file
        results = str() #this will print out the results in a nice fashion
        commas  = str()
        for dirName, subdirList, fileList in os.walk(self.rootDir): #start by iterating over the directories and sub directories given by the input
            #print('Found directory: %s' % dirName) #print out that we found a directory 
            for fname in fileList: #go ahead and iterate over the files that are in that directory or sub directory
                if self.searchedPhrase in fname: #see if the searched phrase is in that list
                    print('\t%s' % fname) #print out the file name if we found one that matches the string given
                    instances += 1 #rack up the count of instances of that file name
                    result += '%s\n' % os.path.join(dirName, fname) #rack up the path and name of that file
                    with open(os.path.join(dirName,fname),"r") as infile: #open the file then iterate over the lines
                        for index, line in enumerate( infile): #iterate over the lines in the file
                            linNum +=1 # iterate the line number for every line we read
                            if self.searchedPhraseInFile in line: #if the line contains the string then save it in results
                                    commas = commas + str(linNum) + "," #combine the commas with the lin number
                    
                    if commas == "":
                        commas = ""      
                    else:  
                        results += '\n' + "the string " + self.searchedPhraseInFile + " occurs at lines " + commas + " in file " +  os.path.join(dirName, fname)   
                        #print results #print results so we can see it
                        commas = ""   #reset the commas
                        linNum = 0    #reset the line numbers
        with open(logname, 'w') as logfile:
            logfile.write(results)
            print results
dirName = os.path.expanduser("~") + raw_input("please enter a directory name the name must be relative to the user E.G. C:\Users\User\Documents would be /Documents or \Documents ") 
searchFile = raw_input("please enter a file to search ")
SearchPhrase = raw_input("please enter a string to search for in the file ")
searchers = searcher(dirName , searchFile,SearchPhrase)
searchers.search()

