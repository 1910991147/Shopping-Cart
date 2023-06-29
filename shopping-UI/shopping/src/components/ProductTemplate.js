import axios from 'axios';
import React, {useContext, useState} from 'react'
import '../cssFiles/ProductTemplate.css'
import { context } from './Context';

let preProductCount=0;
function ProductTemplate(props) {
 
  const [products, setProductData] = useContext(context);
  
  const user = localStorage.getItem('user');
 
  
  
  const [productCount, setProductCount] = useState(preProductCount);
  function incrementProductCount(){
    setProductCount(productCount +1);
    const Id=JSON.parse(user).id
  axios.get(`http://localhost:8080/cart/${Id}/add/${props.product.id}`)
    .then((res) => {
let cart=res.data
setProductCount(cart.quantity)
 
  })

    
  }

  function decrementProductCount(){
    const Id=JSON.parse(user).id
    let count=productCount-1
    setProductCount(productCount-1);
    
    if(productCount <= 0){setProductCount(0);}
      const quantity={
        quantity:count
      }
      if(productCount===0){
        axios.get(`http://localhost:8080/cart/${Id}/remove/${props.product.id}`)
        .then((res) => {
          
        })
      }
      else
     {
      axios.post(`http://localhost:8080/cart/${Id}/changeQuantity/${props.product.id}`,(quantity))
     .then((res) => {
       let cart=res.data
       setProductCount(cart.quantity)
       })
         
    }

    }
  

  return (
    
      <div className='ProductTemplate' style={{minWidth:'18rem'}}>
        <div className='details' style={{}}>
          <div className='image'>
            <img src={props.product.image} />
          </div>
          <p className='productCategory' >{props.product.name}</p>
          <div className='data'>
            <div>Price :{props.product.price}</div>
          </div>
          <div className='data'>
          <button onClick={decrementProductCount}>-</button>
              <button>{productCount}</button>
          <button onClick={incrementProductCount}>+</button>
        </div>
      <div>&nbsp;</div>
      </div>
      </div>
    
  )
}

export default ProductTemplate