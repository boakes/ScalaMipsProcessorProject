package proc

/**
 * @author boakes1
 */
class ALUController extends Component{
     
    var data1:Wire = null
    var controlwire:Wire = null
    var output:Wire = new Wire
    
    override def wireChanged{
     controlwire.value match{
          case 0 => output.setValue(2)
          case 1 => output.setValue(6)
          case 2 => {
            data1.value match{
              case 32 => output.setValue(2)
              case 34 => output.setValue(6)
              case 36 => output.setValue(0)
              case 37 => output.setValue(1)
              case 42 => output.setValue(7)
              case _ => return
            }
          }
          case _ => return
          
       }  
    }
}

