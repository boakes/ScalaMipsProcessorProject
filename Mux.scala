package proc

 
/**
 * @author boakes1
 */
class Mux extends Component{
  var input0:Wire = null
  var input1:Wire = null
  var control:Wire = new Wire
  var output:Wire = new Wire
  override def wireChanged {
      control.value match{
        case 0 => output.setValue(input0.value)
        case 1 => output.setValue(input1.value)
        case _ => return
      }
  }
}