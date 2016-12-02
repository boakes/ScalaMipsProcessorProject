package proc

/**
 * @author boakes1
 */
class InstructionMemory extends Component{
    var readAdd:Wire = null
    var output:Wire = new Wire
     
    var instructmem:Array[Int] = Array.empty[Int]
    
      override def wireChanged{
         output.setValue(instructmem(readAdd.value / 4))         
     }
    
}