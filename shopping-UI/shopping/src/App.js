import { useState } from 'react';
import NavBar from './components/NavBar';
import {
  BrowserRouter as Router, Route, Routes
} from "react-router-dom";
import LoginSignUpPage from './components/LoginSignUpPage';
import SignUpPage from './components/SignUpPage';
import { ContextProvider } from './components/Context';
import Home from './components/Home';

import CartDetails from './components/CartDetails';
import Logout from './components/Logout';
function App() {

  const [search,setSearch]=useState('');
  const handleSearch=(string)=>{  // function used in Navbar component to search items
    setSearch(string);
  }
  
  return (
    <div className="App">
      <ContextProvider>

      <Router>
        <NavBar handleSearch={handleSearch}/>
        <Routes>
          <Route path='/' element={<Home search={search}/>} />
          <Route path='/cart' element={<CartDetails/>} />
          <Route path='/login' element={<LoginSignUpPage />} />
          <Route path='/logout' element={<Logout />} />
          <Route path='/login/sign-up' element={<SignUpPage />} />
        </Routes>
      </Router>
      </ContextProvider>
    </div>
  );
}

export default App;
