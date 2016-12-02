package proc

/**
 * @author boakes1
 */
class ALU extends Component{
     
    var data1:Wire = null
    var data2:Wire = null
    var control:Wire = new Wire
    
    var result:Wire = new Wire
    result.input = this
    
    var isZero:Wire = new Wire
    isZero.input = this  
    
    def and(a:Int, b:Int):Int={a & b}
    def or(a:Int, b:Int):Int={a | b}
    def nor(a:Int, b:Int):Int={~ (a | b)}
    def add(a:Int, b:Int):Int={ a+b }
    def sub(a:Int, b:Int):Int={ a-b }
    def slt(a:Int, b:Int):Int={if(a < b){1} else{0}}
    
    override def wireChanged{
      control.value match{
        case 0 => result.setValue(and(data1.value, data2.value))
        case 1 => result.setValue(or(data1.value, data2.value))
        case 2 => result.setValue(add(data1.value, data2.value))
        case 6 => {
          if(sub(data1.value,data2.value) == 0) {isZero.value = 1} else{isZero.setValue(0)}   
          result.setValue(sub(data1.value, data2.value))  
        }
        
        case 7 => result.setValue(slt(data1.value, data2.value))
        case 12 => result.setValue(nor(data1.value, data2.value))
        case _ => return
       }  
       
  
    }
  
}