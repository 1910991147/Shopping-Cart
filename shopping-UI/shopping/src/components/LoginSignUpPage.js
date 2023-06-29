import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../cssFiles/LoginSignUpPage.css'

function LoginSignUpPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const user = {
        email : email,
        password : password
    }
    const userDetail=JSON.stringify(user)
    const navigate = useNavigate();

    const login = () => {
            
            let isEmailValid = false;
            if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
                isEmailValid = true;
            }
    
            let isPassworValid = password.length < 6 ? false : true;
    
            
            
            if(! isEmailValid){
                alert('Please enter valid email');
                setEmail('');
                setPassword('');
            }
        
    
           
            if(isEmailValid && isPassworValid){
               
                axios.post("http://localhost:8080/login",  (user) )
                .then(res => {
                  let message=res.data
                  window.alert(message.result)
                  if(message.result!=null){
                    let id=Number(message.id)
                    axios.get(`http://localhost:8080/getProfile/${id}`, )
                    .then(res => {
                      let user=res.data
                     localStorage.setItem("user",JSON.stringify(user))
                     console.log(user)
                    })
                    
                    navigate('/');
                    window.location.reload()
                   
                }
                })
            }
            
     
    }

    return (
        <div className='LoginSignUpPage'>
            <div className='email'>
                <label>Email</label>
                <input
                    type='email'
                    name='email'
                    placeholder='Enter your email'
                    value={email}
                    onChange={e => setEmail(e.target.value)}
                    required
                />
            </div>
            <div className='password'>
                <label>Password</label>
                <input
                    type='password'
                    placeholder='password'
                    value={password}
                    required
                    onChange={e => setPassword(e.target.value)}
                />
            </div>
            <div className='login'>
                <button onClick={login}>Login</button>
            </div>
            <div className='signUp'>
                <Link to='sign-up'>don't have a account? &nbsp;SignUp</Link>
            </div>
        </div>
    )
}

export default LoginSignUpPage