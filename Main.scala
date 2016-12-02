package proc
import java.io.FileInputStream
import java.io.BufferedInputStream
import java.io.DataInputStream
import java.io.FileOutputStream
import java.io.BufferedOutputStream
import java.io.DataOutputStream
import scala.collection.mutable.Buffer

/**
 * @author boakes1
 */
object Main  {
 def main(args:Array[String]){
   var programCounter = new ProgramCounter
   var Adder= new Adder
   var imem = new InstructionMemory
   var Control = new Control 
   var regbank = new RegisterBank
   var writeRegMux = new Mux
   var signExtender = new SignExtender
   var ALUControl = new ALUController
   var ander = new And
   var dmem = new Memory
   var memMux = new Mux
   var ALUMux = new Mux
   var ALU = new ALU
   var Shifter = new Shifter
   var ALUAdder = new Mux
   var adderMux = new Mux
   
   //pc
   programCounter.output.addDest(Adder)
   programCounter.output.addDest(imem)
  
   imem.readAdd = programCounter.output
   Adder.wire1 = programCounter.output
   
   //pcadder
   Adder.wire2 = new Wire
   Adder.wire2.value = 4
   Adder.output.addDest(adderMux)
   adderMux.input0 = Adder.output
   Adder.output.addDest(ALUAdder)
   ALUAdder.input0 = Adder.output
      
   var WireSplit = new WireSplitter 
   
  
   imem.output.addDest(WireSplit)
   WireSplit.input = imem.output
  
   WireSplit.output1.addDest(Control)
   Control.input = WireSplit.output1;
   

   
   WireSplit.output2.addDest(regbank)
   regbank.readreg1 = WireSplit.output2
   
   WireSplit.output3.addDest(regbank)
   regbank.readreg2 = WireSplit.output3 
   
   WireSplit.output3.addDest(writeRegMux)
   writeRegMux.input0 = WireSplit.output3 // writeRegMux
  
   WireSplit.output4.addDest(writeRegMux)
   writeRegMux.input1 = WireSplit.output4
   
   WireSplit.output5.addDest(signExtender)
   signExtender.input = WireSplit.output5
  
   WireSplit.output6.addDest(ALUControl)
   ALUControl.data1 = WireSplit.output6

   
  //Control 
     
   Control.regdst.addDest(writeRegMux)
   writeRegMux.control = Control.regdst
   
   Control.branch.addDest(ander)
   ander.data1 = Control.branch
   
   Control.memread.addDest(dmem)
   dmem.memRead = Control.memread
   
   Control.memtoreg.addDest(memMux)
   memMux.control = Control.memtoreg
   
   Control.aluOP.addDest(ALUControl)
   ALUControl.controlwire = Control.aluOP
   
   Control.memwrite.addDest(dmem)
   dmem.memWrite = Control.memwrite
   
   Control.aluSrc.addDest(ALUMux)
   ALUMux.control = Control.aluSrc
   
   Control.regwrite.addDest(regbank)
   regbank.controlreg = Control.regwrite
   
   //regbank
  
   
   regbank.readdat1.addDest(ALU)
   ALU.data1 = regbank.readdat1
   
   regbank.readdat2.addDest(ALUMux)
   ALUMux.input0 = regbank.readdat2
        
   //writeRegMux
  
   
   writeRegMux.output.addDest(regbank)
   regbank.writereg = writeRegMux.output
   
   
   //signExtender
 
   signExtender.output.addDest(Shifter)
   Shifter.input = signExtender.output
   
   signExtender.output.addDest(ALUMux)
   ALUMux.input1 = signExtender.output
   
   ALUControl.output.addDest(ALU)
   ALU.control = ALUControl.output
   
   ALU.isZero.addDest(ander)
   ander.data2 = ALU.isZero

   //ALU Mux 
   ALUMux.output.addDest(ALU)
   ALU.data2 = ALUMux.output
   
   Shifter.output.addDest(ALUAdder)
   ALUAdder.input1 = Shifter.output
   
   ALUAdder.output.addDest(adderMux)
   adderMux.input1=ALUAdder.output 
   
   ALU.result.addDest(dmem)
   dmem.readAdd = ALU.result
   
   ALU.result.addDest(memMux)
   memMux.input0 = ALU.result
   
   dmem.output.addDest(memMux)
   memMux.input1 = dmem.output
   
   //memMux.output.addDest(regbank) infinite loop
   regbank.writedat = memMux.output

   adderMux.output.addDest(programCounter)
   programCounter.input = adderMux.output
   
    
    var d = args(0)
    var d2 = args(1)
  
   // Reading
      val dis = new DataInputStream(new BufferedInputStream(new FileInputStream(d)))
      val mem1 = Array.fill(0x1000)(dis.readInt) //Instruction imem
      val mem2 = Array.fill((1 << 14) - 0x1000)(dis.readInt) //Data imem
      dis.close
      val dis2 = new DataInputStream(new BufferedInputStream(new FileInputStream(d2)))
      val regs = Array.fill(32)(dis2.readInt) 
      dis2.close
   

   imem.instructmem = mem1
   regbank.arbank = regs
   dmem.datamem = mem2
    
   while(programCounter.data < 0x3FFC){
      programCounter.ClockChanged   
      regbank.ClockChanged
    }
   
  var arr = imem.instructmem ++ dmem.datamem 
  
   // Writing
  val dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(d.replace("in", "out"))))
  for (i <- arr) dos.writeInt(i)
  dos.close()
  val dos2 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(d2.replace("in", "out"))))
  for (i <- regbank.arbank) dos2.writeInt(i)
  dos2.close()
  
 }
   
}