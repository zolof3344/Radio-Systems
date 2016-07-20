
import urllib
http = "http"
url = raw_input("please enter the site who's source code you want to look at ") #write the url here
if http in url:
    usock = urllib.urlopen(url)
    data = usock.read()
    usock.close()
    fileName = "html site data.txt"
    with open(fileName, 'w') as log:
        log.write(data)
        print data
else: 
        urlNew = "http://" + url
        usock = urllib.urlopen(urlNew)
        data = usock.read()
        usock.close()
        fileName = url + " html site data.txt"
        with open(fileName, 'w') as log:
                log.write(data)
                print data