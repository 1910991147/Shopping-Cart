import React,{useContext} from 'react'
import { context } from './Context'
import ProductTemplate from './ProductTemplate';
import '../cssFiles/Home.css'

function Home({search}) {
    const [products] = useContext(context);
    
    let data;
    if(search===''){
    data=products;

 
    }
    else
    data= products.filter(product=>
   product.category.includes(search)
    )
  return (
      
    <div className='container'>{
        data.map(product =>{
            return(<ProductTemplate key={product.id} product={product}></ProductTemplate>)
        })
    }
        
    </div>
  )
}

export default Home