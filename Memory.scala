package proc

/**
 * @author boakes1
 */
class Memory extends Component{
    
    //for instructmem
     var readAdd:Wire = null
     var output:Wire = new Wire
     
     //for datamem
     var memRead:Wire = null
     var memWrite:Wire = null
     var writeData:Wire = null

     var datamem:Array[Int] = null
     
     override def wireChanged{
          if(memRead.value==1){
               output.setValue(datamem(readAdd.value))
          }
          if(memWrite.value==1){
               datamem(readAdd.value) = writeData.value
          }
      
     } 
     
     
     
    
}