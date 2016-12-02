package proc

import scala.collection.mutable.Buffer

/**
 * @author boakes1
 */
class Wire{
   
    var input:Component = null
    var value:Int = 0
    
    val outputs:Buffer[Component] = Buffer()
   
    def addDest(newDest:Component){
       outputs += newDest
    }
    
    def setValue(x:Int){
        value = x
        for(d <- outputs){
            d.wireChanged
        }
     }
    
   
      
}