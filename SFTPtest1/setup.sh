#!/bin/bash
apt-get update
#apt-get upgrade
apt install openssh-server
apt install net-tools

#apt install default-jdk
#javac -version
#java -version

passwd ubuntu

ufw allow ssh
ifconfig
systemctl status ssh


