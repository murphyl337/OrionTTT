#!/bin/bash
reldir='dirname $0'
cd $reldir
directory=`pwd`
cd directory
mvn package appassembler:assemble
cd target/appassembler/bin
chmod +x orion-ttt
./orion-ttt -d /Users/tmurphy/common/projects/Orion-TTT/web/ -p 5000
