#coding='utf-8'
# Using Python regular expressions to extract information from TEI format files
# Good results with rather high speed
import re
import os
import json

class Article:
    def __init__(self,title, author, monoTitle, date, doi, keywords, abstract, lang, identity, bibliography):
        [self.title, self.author, self.monoTitle, self.date, self.doi, self.keywords, self.abstract, self.lang, self.identity, self.bibliography] = [title, author, monoTitle, date, doi, keywords, abstract, lang, identity, bibliography]

def lookup(l_label, r_label, text):
    result = re.findall(l_label+'.*</'+r_label, text)
    if result == []:
        return ''
    if len(result)>1:
        return [re.split('<|>', x)[1] for x in result]
    else:
        return re.split('<|>', result[0])[1]
    
def header_fileDesc(text):
    titleStmt = (re.compile('<titleStmt>.*</titleStmt>', re.DOTALL)).findall(text)[0]
    title = lookup('title level="a" type="main"', 'title', titleStmt)
    
    authors = (re.compile('<author.*?</author>', re.DOTALL)).findall(text)
    author = []
    for temp in authors:
        firstName = lookup('forename type="first"', 'forename', temp)
        middleName = lookup('forename type="middle"', 'forename', temp)
        lastName = lookup('surname', 'surname', temp)
        email = lookup('email', 'email', temp)
        department = lookup('orgName type="department"', 'orgName', temp)
        laboratory = lookup('orgName type="laboratory"', 'orgName', temp)
        institution = lookup('orgName type="institution"', 'orgName', temp)
        address = lookup('addrLine', 'addrLine', temp)
        settlement = lookup('settlement', 'settlement', temp)
        country = lookup('country', 'country', temp)
        
        name = {'firstName': firstName, 'middleName': middleName, 'lastName': lastName}
        affiliation = {'department': department, 'laboratory': laboratory, 'institution': institution, 'address': address, 'settlement': settlement, 'country': country}
        author.append({'name': name, 'email': email, 'affiliation': affiliation})
    
    monography = (re.compile('<monogr.*?</monogr>', re.DOTALL)).findall(text)[0]
    monoTitle = lookup('title level="j" type="main"', 'title', monography)
    year = ''
    month = ''
    pubdate = lookup('date type="published" when', 'date', monography)
    if pubdate!='':
        year = re.findall('[0-9]{4}', pubdate)[0]
                
    pubDetail = lookup('note type="submission"', 'note', text)
    if pubDetail!='':
        temp = re.findall('(January|February|March|April|May|June|July|August|September|October|November|December)', pubDetail)
        if temp!=[]:
            month = temp[-1]
    doi = lookup('idno type="DOI"', 'idno', text)
    
    return [title, author, monoTitle, {'year': year, 'month': month}, doi]

def header_profileDesc(text):
    keywords = []
    abstract = ''
    if '<abstract>' in text:
        abstract = lookup('p', 'p', (re.compile('<abstract>.*</abstract>', re.DOTALL)).findall(text)[0])
    if '<keywords>' in text:
        keywordsList = lookup('term', 'term', (re.compile('<keywords>.*</keywords>', re.DOTALL)).findall(text)[0])
        for term in keywordsList:
            for word in re.split(',', term):
                keywords.append(word)
    return [keywords, abstract]

def ref_biblStruct(text):
    bibs=(re.compile('<biblStruct.*?</biblStruct>', re.DOTALL)).findall(text)
    bibliography = []
    for bib in bibs:
        title = lookup('title level="a" type="main"', 'title', bib)
        
        author = []
        authors = (re.compile('<author.*?</author>', re.DOTALL)).findall(bib)
        for temp in authors:
            firstName = lookup('forename type="first"', 'forename', temp)
            middleName = lookup('forename type="middle"', 'forename', temp)
            lastName = lookup('surname', 'surname', temp)
            name = {'firstName': firstName, 'middleName': middleName, 'lastName': lastName}
            author.append({'name': name})
        
        monography = (re.compile('<monogr.*</monogr>', re.DOTALL)).findall(bib)[0]
        monoTemp = re.findall('title level="[^a]"',monography)
        monoTitle = ''
        if monoTemp!=[]:
            monoTitle = lookup('title level=', 'title', monoTemp[0])
        year = ''
        month = ''
        if 'date type="published" when=' in monography:
            date = re.split('"', re.findall('date type="published" when=.*/>', monography)[0])[-2]
            year = re.findall('[0-9]{4}', date)[0]
        
        bibliography.append({'title': title, 'author': author, 'monoTitle': monoTitle, 'date': {'year': year, 'month': month}})
    
    return bibliography
    
    
def main(headerPath, refPath, jsonPath):
    for fileName in os.listdir(headerPath):
        # Header part
        teiFile = headerPath+'\\'+fileName
        with open(teiFile, 'rb') as f:
            inStream = f.read().decode('utf-8')
            
        [title, author, monoTitle, date, doi] = header_fileDesc((re.compile('<fileDesc>.*</fileDesc>', re.DOTALL)).findall(inStream)[0])
        
        [keywords, abstract] = header_profileDesc((re.compile('<profileDesc>.*</profileDesc>', re.DOTALL)).findall(inStream)[0])
        
        lang = ''
        if 'text xml:lang=' in inStream:
            lang = re.split('"',re.findall('<text xml:lang=.*>',inStream)[0])[1]
            
        # Reference part
        teiFile = refPath+'\\'+fileName[:-8]+'.references.tei.xml'
        with open(teiFile, 'rb') as f:
            inStream = f.read().decode('utf-8')
        
        identity = 'http://givingsense.eu/sembib/data/tpt/paf2017/' + re.split('"',re.findall('<fileDesc xml:id=.*>',inStream)[0])[1]
        
        bibliography = ref_biblStruct(inStream)
        
        # Write to json file
        article = Article(title, author, monoTitle, date, doi, keywords, abstract, lang, identity, bibliography)
        
        jsonFile = jsonPath+'\\'+fileName[:-8]+'.json'
        with open(jsonFile, 'wb') as f:
            f.write(json.dumps(obj=vars(article),sort_keys=True,indent=2).encode('utf-8'))
    
main('F:\WorkSpace\papers\paper-header', 'F:\WorkSpace\papers\paper-reference', 'F:\WorkSpace\papers\json')