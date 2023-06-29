import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { context } from './Context'
import '../cssFiles/NavBar.css'
import { useContext } from 'react'
import axios from 'axios'

function NavBar() {
    const [products,setProductData] = useContext(context);
    const [searchItem, setSearchItem] = useState('');
    const [user, setUser] = useState(localStorage.getItem('user'))
    let filteredProducts=[];

    function search(searchItem){
        if(searchItem!==''){
        axios.get(`http://localhost:8080/products/search/${searchItem}`)
        .then((res) => {
         setProductData(res.data)
         setSearchItem('')
        })}
    else{
        axios.get('http://localhost:8080/products/getProduct')
        .then((res) => {
        
            setProductData(res.data)
        })
    }}
    function returnhome(){
        axios.get('http://localhost:8080/products/getProduct')
        .then((res) => {
        
            setProductData(res.data)
        })
    }    
    useEffect(() => {
        filteredProducts = products.map(product => {
            if (product.category === searchItem){ 
                return product
            };
        })
    })
    
    
        return (
            <div className='NavBar'>
                <div className='leftNav'>
                    <ul>
                        <li className='tit'>
                            <Link to='/' onClick={returnhome}>Shopify</Link>
                        </li>
                        <li className='search'>
                                <input
                                    type='text'
                                    name='text'
                                    placeholder='Search Item...'
                                    value={searchItem}
                                    onChange={e => { setSearchItem(e.target.value) }}/>
                                     <button value={searchItem}   onClick={() => search(searchItem)}>Search</button>
                        </li>
                    </ul>
            </div>
            <div className='rightNav'>
                <ul>
                   
                    <li>{
                         user ? (<Link to='/Cart' >Cart</Link>):
                          (<Link to='/Login'>Cart</Link>)
                        }
                      
                    </li>
                   
                    <li>
                        {
                            user ?  (<Link to='/Logout' onClick={()=>{setUser(null)}}>Logout</Link>)
                            :  (<Link to='/login' id='login'>Login</Link>)
                        }
                    </li>


                </ul>
            </div>
        </div>
    )
}

export default NavBar