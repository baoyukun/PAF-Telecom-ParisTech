#coding=utf-8
from os import listdir
from re import sub
import csv

fileNames = [sub('[\W]',' ',f[:-4]) for f in listdir('F:\WorkSpace\PAF-Telecom-ParisTech\crawler\papers')]
with open('papers.csv', 'w', encoding='UTF-8', newline='') as f:
    outFile = csv.writer(f, dialect='excel')
    outFile.writerow(fileNames)