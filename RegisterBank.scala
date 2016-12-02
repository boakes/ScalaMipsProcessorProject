package proc

/**
 * @author boakes1
 */
class RegisterBank extends Component{
 
  var arbank = Array.fill(32){0} //only 32 in mips? 
  var controlreg:Wire = null
  
  var readreg1:Wire = null
  var readreg2:Wire = null
 
  var readdat1:Wire = new Wire
  var readdat2:Wire = new Wire
  
  var writereg:Wire = null
  var writedat:Wire = null
  
  override def wireChanged{
    println(readreg1.value)
    readdat1.setValue(arbank(readreg1.value))
    readdat2.setValue(arbank(readreg2.value))
  }
  
  def ClockChanged{
      if(controlreg.value==1){arbank(writereg.value) = writedat.value}
  }

  
}

