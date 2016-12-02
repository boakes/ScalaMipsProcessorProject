package proc

/**
 * @author boakes1
 */
class Control extends Component{
  
    var input:Wire = null
    var regdst:Wire = new Wire
    var branch:Wire = new Wire
    var memread:Wire = new Wire
    var memtoreg:Wire = new Wire
    var aluOP:Wire = new Wire
    var memwrite:Wire = new Wire
    var aluSrc:Wire = new Wire
    var regwrite:Wire = new Wire
    
    override def wireChanged{
         input.value match{
           case 0 => {   
             regdst.value = (1)
             aluSrc.value = (0)
             memtoreg.value = (0)
             regwrite.value = (1)
             memread.value = (0)
             memwrite.value = (0)
             branch.value = (0)
             aluOP.value = (2)
           }
           
           case 4=> {
             regdst.value = (0)
             aluSrc.value = (0)
             memtoreg.value = (0)
             regwrite.value = (0)
             memread.value = (0)
             memwrite.value = (0)
             branch.value = (1)
             aluOP.value = (1)
           }
           
           case 35=>{      //lw 
             regdst.value = (0)
             aluSrc.value = (1)
             memtoreg.value = (1)
             regwrite.value = (1)
             memread.value = (1)
             memwrite.value = (0)
             branch.value = (0)
             aluOP.value = (0)
           }
           
           case 43=>{    //sw             
             regdst.value = (0)
             aluSrc.value = (1)
             memtoreg.value = (0)
             regwrite.value = (0)
             memread.value = (0)
             memwrite.value = (1)
             branch.value = (0)
             aluOP.value = (0)
           }
           
           case _=>{
             return
           }
           
         }
    }
    
}