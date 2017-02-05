import socket
import re

import sys
from subprocess import call

from engine import Engine

port = 0
if len(sys.argv) == 1:
	port = 3600
else:
	port = int(sys.argv[1])
print "Server starting on port " + str(port)

################################################################################

my_file = '../frontend/script.js'

line_good = """$.post("http://127.0.0.1:""" + str(port) + """ ", $scope.newPost);"""
line_nbr = 27

content = ""
with open(my_file, 'r') as content_file:
	content = content_file.read()
	content_file.close()

cnt = 0
output = ""
for line in content.split('\n'):
	cnt += 1
	if cnt == line_nbr:
	 	output += line_good
	else:
		output += line
	output += '\n'

fo = open(my_file, "wb")
fo.write(output)
fo.close()

################################################################################

host = ''
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.bind((host, port))
sock.listen(1)

engine = Engine()

while True:
    csock, caddr = sock.accept()
    print "Connection from: " + `caddr`
    req = csock.recv(1024)

    lines = req.split('\n')

    message = ""
    arrived = False
    for line in lines:
    	if line == "\r":
    		arrived = True
    	line += '\n'
    	if arrived:
    		message += line

    if "POST" in lines[0]:
    	print "POST issued"
    	engine.process_message(message)
    else:
    	print "GET issued"
    	engine.process_files(message)

    csock.sendall("""HTTP/1.0 200 OK
		Content-Type: text/html
		<html>
			<head>
				<title>Success</title>
			</head>
			<body>
				Success
			</body>
		</html>
	""")
