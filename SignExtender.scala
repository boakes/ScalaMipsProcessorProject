package proc

/**
 * @author boakes1
 */
class SignExtender extends Component{
  //not right :\ 
  
  var input:Wire = null
  var output:Wire = new Wire
  
  def extenderino(a:Int):Int={
   var x = a
   var bitpos = 0
   while(x!=0){
      bitpos+= 1
      x = x >> 1
    }
    if(bitpos == 16){
      a | (-65536)  
    }
    else{
      a
    }
   
  }
  
   override def wireChanged{
      output.value = extenderino(input.value)
    
   }
  
}