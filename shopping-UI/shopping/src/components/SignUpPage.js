import React, {useEffect, useState} from 'react'
import { useNavigate } from 'react-router-dom';
import '../cssFiles/SignUpPage.css'
import axios from 'axios';

function SignUpPage() {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const validation = () => {
        let isNameValid = false;
        if(name.length > 1 || /^[a-zA-Z]+$/.test(name[0])) {
            isNameValid = true;
        }

        let isEmailValid = false;
        if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
            isEmailValid = true;
        }

        let isPassworValid = password.length < 6 ? false : true;

        if(! isNameValid){
            alert('first character must be a letter and length of user name should be greater than 1 character')
        }
        
        if(! isEmailValid){
            alert('Please enter valid email');
            setEmail('');
            setPassword('');
        }
        if(! isPassworValid){
            alert('Please enter atleast 6 character')
            setPassword('');
        }

        const userDetail = {
            name : name,
            email : email,
            password : password
        }
        const user=JSON.stringify(userDetail)
        if(isNameValid && isEmailValid && isPassworValid){
            axios.post("http://localhost:8080/signup", (userDetail))
            .then(res => {
              console.log(res.data);
              console.log(user);
              
              let userList=res.data;
               window.alert(userList.message)
       
            })
            navigate('/login');
        }
        
        
        
    }
    

    
   
   
    return (
        <div className='SignUpPage'>
            <div className='userName'>
               
                <input
                    type='text'
                    placeholder='Enter your name'
                    value={name}
                    onChange={e => setName(e.target.value)}
                />
            </div>
            <div className='email'>
                
                <input
                    type='email'
                    placeholder='Enter your email'
                    value={email}
                    onChange={e => setEmail(e.target.value)}
                />
            </div>
            <div className='password'>
             
                <input
                    type='password'
                    placeholder='Enter Password'
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                />
            </div>
            <div className='submit'>
                <button onClick={validation}>Submit</button>
            </div>
        </div>
    )
}

export default SignUpPage