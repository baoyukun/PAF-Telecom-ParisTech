# Using Grobid service mode to parser PDF
# Rather quick but low error tolerance
# @author: BAO Yukun

from subprocess import Popen, PIPE
from os import listdir
from time import time, sleep

def exec_shell(cmd):
    try:
        pipe = Popen(cmd, stdout=PIPE)
        result = pipe.communicate(timeout=20)[0]
        return result
    except:
        pipe.kill()
        return ''

def main(path):
    exceptionFiles = []
    start = time()
    
    for f in listdir(path):
        s1 = exec_shell(['curl', '-v', '--form', 'input=@./paper/'+f, 'localhost:8080/processHeaderDocument'])
        sleep(1)
        s2 = exec_shell(['curl', '-v', '--form', 'input=@./paper/'+f, 'localhost:8080/processReferences'])
        if s1 and s2:
            with open('paper/'+f[:-4]+'-header.xml', 'wb') as out1, open('paper/'+f[:-4]+'-ref.xml', 'wb') as out2:
                out1.write(s1)
                out2.write(s2)
        else:
            exceptionFiles.append(f)
        sleep(1)
    
    print ('Ends with '+ time()-start + ' s.')
    print (len(exceptionFiles))
    print (exceptionFiles)
    
main('F:\WorkSpace\papers\paper')