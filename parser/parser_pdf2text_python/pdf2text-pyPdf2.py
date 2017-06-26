# Using pyPDF2
# Lower speed with common performance
# @author: BAO Yukun

from PyPDF2 import PdfFileReader

pdf = PdfFileReader(open('{3d} Articulated Growth Model of the Fetus Skeleton, Envelope and Soft Tissues.pdf', "rb"))

with open('output.txt','w',encoding='utf-8') as outFile:
    for i in range(0, pdf.getNumPages()):
        extractedText = pdf.getPage(i).extractText()
        outFile.write(extractedText)