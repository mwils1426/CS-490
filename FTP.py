from ftplib import FTP
import time as t

file_object = open('./sysUpdate.py', 'rb')
with FTP('192.168.1.124', 'guest', 'guest123') as ftp:
	ftp.login('guest', 'guest123')
	ftp.cwd('/update')
	ftp.storbinary('STOR sysUpdate.py', file_object)
file_object.close()
	
