#coding='utf-8'
import re
import os
import json

class Article:
    def __init__(self,identity,title,ref,date,address,belongTo,category,language,audience,project,department,group,author,keyword,citationList,citedList):
        [self.identity,self.title,self.ref,self.date,self.address,self.belongTo,self.category,self.language,self.audience,self.project,self.department,self.group,self.author,self.keyword,self.citationList,self.citedList] = [identity,title,ref,date,address,belongTo,category,language,audience,project,department,group,author,keyword,citationList,citedList]

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
    
    
    
    
def main(headerPath, refPath):
    # Header part
    for fileName in os.listdir(headerPath):
        teiFile = headerPath+'\\'+fileName
        with open(teiFile, 'rb') as f:
            inStream = f.read().decode('utf-8')
        header_fileDesc((re.compile('<fileDesc>.*</fileDesc>', re.DOTALL)).findall(inStream)[0])
        
    
main('F:\WorkSpace\papers\paper-header', 'F:\WorkSpace\papers\paper-reference')