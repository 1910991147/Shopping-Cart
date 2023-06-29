import React, {createContext, useState, useEffect} from "react";
import axios from "axios";

export const context = createContext();

export const ContextProvider = ({children}) =>{
    const [productsData, setProductData] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/products/getProduct')
            .then((res) => {
            
                setProductData(res.data)
                localStorage.setItem("product",res.data)
                
            })
    },[])

    const products = productsData.length > 0 ? productsData : [];
   
    return(
        <context.Provider value={[products, setProductData]}>
            {children}
        </context.Provider>
    )
}