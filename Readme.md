#MIPS Processor in Scala

Wrote this rudimentary MIPS Processor for a Computer Design Course.

To compile the processor, in the main directory of this project in a terminal run:
	scalac *.scala

To then run the processor on any input, in a terminal run:
	scala proc.Main "Memory.in" "Regs.in"

a sample case is in the testCode folder, and to run it :
	scala proc.Main ./testCode/memory1.in ./testCode/regs1.in

Tester Input Files written by Mark Lewis. 
