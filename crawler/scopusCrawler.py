#coding='utf-8'
# Using Scopus public APIs to verify paper information
# and eventually fetch the cited list
# However, only 403 in 1677 papers are consultable in Scopus
from scopus import ScopusSearch, ScopusAbstract
import re
import json

with open('paperJson.json', 'rb') as inFile:
    s=inFile.read().decode('utf-8')
obj = json.loads(s)

for i in obj['articles']:
    title = i['title']
    title = ' '.join(re.split('[\W]',title))
    i['title'] = title
    i['scopus'] = {}
    i['citedCount'] = 0
    
    try:
        rep = ScopusSearch('TITLE ' + '(' + title + ')', refresh=True)
        rep = rep.org_summary
        
        if len(str(rep))>0:
            links = re.findall(r'(https?://[^\s]+)', rep)
            scopus_link = links[2][:-1]
            scopus_id = re.split('\[|\]',links[0])[2]
        
            rep = ScopusAbstract(scopus_id, refresh=True)
            
            i['title'] = rep.title
            i['scopus']['publicationName'] = rep.publicationName
            i['scopus']['aggregationType'] = rep.aggregationType
            i['scopus']['paperScopusId'] = scopus_id
            date = re.split('-',rep.coverDate)
            i['date']['year'] = date[0]
            i['date']['month'] = date[1]
            i['doi'] = rep.doi
            i['citedCount'] = rep.citedby_count
            i['scopus']['author'] = []
            for author in rep.authors:
                au = str(author)
                element = au.split()
                fullName = ' '.join(element[1:-2])
                affiliation_id = re.split('\:',element[-1])[1]
                scopus_id = re.split('\:',element[-2])[1]
                i['scopus']['author'].append({'fullName': fullName, 'affiliation_id': affiliation_id, 'scopus_id': scopus_id})
    except:
        continue
    
with open('paperJson.json', 'wb') as outFile:
    outFile.write(json.dumps(obj,sort_keys=True,indent=2).encode('utf-8'))

print ('OK!')