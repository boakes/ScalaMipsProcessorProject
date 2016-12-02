package proc

import scala.collection.mutable.Buffer

/**
 * @author boakes1
 */
class WireSplitter extends Component{
    var input:Wire = null
    var output1:Wire = new Wire
    var output2:Wire = new Wire
    var output3:Wire = new Wire
    var output4:Wire = new Wire
    var output5:Wire = new Wire
    var output6:Wire = new Wire
 

    
    override def wireChanged{
       var y = input.value
       var z = y.toBinaryString
       while(z.size < 32){
                 z = "0"++z
       } 
      
       
       output1.setValue(Integer.parseInt(z.substring(0,6),2))
       output2.setValue(Integer.parseInt(z.substring(6,11),2))
       output3.setValue(Integer.parseInt(z.substring(11,16),2))
       output4.setValue((Integer.parseInt(z.substring(16,21),2)))
       output5.setValue(Integer.parseInt(z.substring(16,32),2))
       output6.setValue(Integer.parseInt(z.substring(26,32),2))
       
    }
    
    
    
    
    
    
}