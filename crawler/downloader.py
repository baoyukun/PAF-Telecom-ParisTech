import urllib
import re

addr = 'http://givingsense.eu/sembib/data/srcPdf/'

with urllib.request.urlopen(addr) as f:
    links = re.findall('"[^<^>]*.pdf', f.read().decode('iso-8859-1'))
    
for link in links:
    try:
        urllib.request.urlretrieve(addr+link[1:], 'papers/'+ urllib.parse.unquote(link[1:]))
    except:
        print (urllib.parse.unquote(link[1:]))